package org.eclipse.basyx.submodel.restapi;

import java.util.Map;

import org.eclipse.basyx.submodel.metamodel.api.submodelelement.ISubmodelElement;
import org.eclipse.basyx.submodel.metamodel.facade.submodelelement.SubmodelElementFacadeFactory;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.SubmodelElementCollection;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation.Operation;
import org.eclipse.basyx.vab.exception.provider.MalformedRequestException;
import org.eclipse.basyx.vab.exception.provider.ProviderException;
import org.eclipse.basyx.vab.modelprovider.VABPathTools;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;

/**
 * Handles a SubmodelElement according to AAS meta model
 *
 * @author schnicke, conradi
 *
 */
public class SubmodelElementProvider extends MetaModelProvider {

	private IModelProvider proxy;
	
	// Flag used to indicate whether a specialized ElementProvider is used
	private boolean specializedProvider = false;

	public SubmodelElementProvider(IModelProvider proxy) {
		this.proxy = getElementProvider(proxy);
	}
	
	/**
	 * Used to find out if an Element needs a specialized Provider (Collection, Operation)
	 * 
	 * @param proxy the Provider given from above
	 * @return either the unchanged Provider or the Provider nested into a specialized ElementProvider
	 */
	@SuppressWarnings("unchecked")
	private IModelProvider getElementProvider(IModelProvider proxy) {
		Map<String, Object> elementMap = (Map<String, Object>) proxy.getModelPropertyValue("");
		if(Operation.isOperation(elementMap)) {
			specializedProvider = true;
			return new OperationProvider(proxy);
		} else if (SubmodelElementCollection.isSubmodelElementCollection(elementMap)) {
			specializedProvider = true;
			return new SubmodelElementCollectionProvider(proxy);
		} else if(Property.isProperty(elementMap)) {
			specializedProvider = true;
			return new PropertyProvider(proxy);
		} else {
			specializedProvider = false;
			return proxy;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getModelPropertyValue(String path) throws ProviderException {
		path = VABPathTools.stripSlashes(path);

		if (path.equals(MultiSubmodelElementProvider.VALUE)) {
			// Handle "/value" path
			// return value
			Map<String, Object> elementMap = (Map<String, Object>) proxy.getModelPropertyValue("");
			
			ISubmodelElement element = SubmodelElementFacadeFactory.createSubmodelElement(elementMap);
			
			try {
				return element.getValue();
			} catch (UnsupportedOperationException e) {
				// e.g. an Operation
				throw new MalformedRequestException("The requested Element '" + element.getIdShort() + "' has no value.");
			}
		} else {
			// Path has more Elements -> pass it to Provider below
			return proxy.getModelPropertyValue(path);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setModelPropertyValue(String path, Object newValue) throws ProviderException {
		path = VABPathTools.stripSlashes(path);
		
		if(!path.endsWith(MultiSubmodelElementProvider.VALUE)) {
			throw new MalformedRequestException("The given path '" + path + "' does not end in /value.");
		}
		
		if (!specializedProvider && path.equals(MultiSubmodelElementProvider.VALUE)) {
			// Path is only "value" and no specialized Provider has to be used -> update the Element of this Provider
			Map<String, Object> elementMap = (Map<String, Object>) proxy.getModelPropertyValue("");
			
			ISubmodelElement element = SubmodelElementFacadeFactory.createSubmodelElement(elementMap);
			
			try {
				element.setValue(newValue);				
			} catch (IllegalArgumentException e) {
				throw new MalformedRequestException("The given Value was not valid for Element '" + path + "'");
			}
			
			proxy.setModelPropertyValue("", element);
			
		} else {
			// Path has more Elements -> pass it to Provider below
			proxy.setModelPropertyValue(path, newValue);
		}
	}

	@Override
	public void createValue(String path, Object newEntity) throws ProviderException {
		if(!specializedProvider) {
			// In a regular SubmodelElement nothing can be created
			throw new MalformedRequestException("Creating a new Element is not allowed at '" + path + "'");
		} else {
			// If a specialized Provider is used, pass it down
			proxy.createValue(path, newEntity);
		}
	}

	@Override
	public void deleteValue(String path) throws ProviderException {
		if(!specializedProvider) {
			// From a regular SubmodelElement nothing can be deleted
			throw new MalformedRequestException("Deleting the Element '" + path + "' is not allowed");
		} else {
			// If a specialized Provider is used, pass it down
			proxy.deleteValue(path);
		}
	}

	@Override
	public void deleteValue(String path, Object obj) throws ProviderException {
		throw new MalformedRequestException("Delete with a passed argument not allowed");
	}

	@Override
	public Object invokeOperation(String path, Object... parameter) throws ProviderException {
		return proxy.invokeOperation(path, parameter);		
	}

}
