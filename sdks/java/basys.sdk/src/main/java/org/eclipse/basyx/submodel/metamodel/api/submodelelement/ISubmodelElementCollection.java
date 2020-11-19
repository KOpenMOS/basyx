package org.eclipse.basyx.submodel.metamodel.api.submodelelement;

import java.util.Map;

import org.eclipse.basyx.submodel.metamodel.api.IElementContainer;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.dataelement.IProperty;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.operation.IOperation;

/**
 * A submodel element collection is a set or list of submodel elements.
 * 
 * @author rajashek, schnicke
 *
 */
public interface ISubmodelElementCollection extends ISubmodelElement, IElementContainer {
	
	/**
	 * Gets if the collection is ordered or unordered
	 * 
	 * @return
	 */
	public boolean isOrdered();
	
	/**
	 * Gets if the collection allows duplicates
	 * 
	 * @return
	 */
	public boolean isAllowDuplicates();
	
	/**
	 * Gets all the elements contained in the collection
	 * 
	 * @return
	 */
	@Override
	public Map<String, ISubmodelElement> getSubmodelElements();

	/**
	 * Gets the data elements contained in the collection
	 * 
	 * @return
	 */
	@Override
	public Map<String, IProperty> getProperties();

	/**
	 * Gets the operations contained in the collection
	 * 
	 * @return
	 */
	@Override
	public Map<String, IOperation> getOperations();
}