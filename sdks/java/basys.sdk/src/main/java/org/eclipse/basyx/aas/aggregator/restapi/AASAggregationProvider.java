package org.eclipse.basyx.aas.aggregator.restapi;

import java.net.URLDecoder;
import java.util.Map;

import org.eclipse.basyx.aas.aggregator.api.IAASAggregator;
import org.eclipse.basyx.aas.metamodel.api.IAssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.map.AssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.map.descriptor.ModelUrn;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IIdentifier;
import org.eclipse.basyx.submodel.metamodel.map.modeltype.ModelType;
import org.eclipse.basyx.vab.modelprovider.VABPathTools;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;

/**
 * Connects an IAASAggregator to the VAB
 * 
 * @author conradi
 *
 */
public class AASAggregationProvider implements IModelProvider {
	
	private IAASAggregator aggregator;
	
	private static final String PREFIX = "aasList";
	private static final String ENCODING_SCHEME = "UTF-8";
	
	public AASAggregationProvider(IAASAggregator aggregator) {
		this.aggregator = aggregator;
	}

	/**
	 * Check for correctness of path and returns a stripped path (i.e. no leading
	 * prefix)
	 * @param path
	 * @return
	 */
	private String stripPrefix(String path) {
		path = VABPathTools.stripSlashes(path);
		if (!path.startsWith(PREFIX)) {
			throw new RuntimeException("Path " + path + " not recognized as aggregator path. Has to start with " + PREFIX);
		}
		path = path.replace(PREFIX, "");
		path = VABPathTools.stripSlashes(path);
		return path;
	}
	
	/**
	 * Makes sure, that given Object is an AAS by checking its ModelType<br />
	 * Creates a new AAS with the content of the given Map
	 * 
	 * @param value the AAS Map object
	 * @return an AAS
	 */
	@SuppressWarnings("unchecked")
	private AssetAdministrationShell createAASFromMap(Object value) {
		
		//check if the given value is a Map
		if(!(value instanceof Map)) {
			throw new RuntimeException("Given newValue is not a Map");
		}

		Map<String, Object> map = (Map<String, Object>) value;
		
		//check if the given Map contains an AAS
		String type = ModelType.createAsFacade(map).getName();
		
		//have to accept Objects without modeltype information,
		//as modeltype is not part of the public metamodel
		if(!AssetAdministrationShell.MODELTYPE.equals(type) && type != null) {
			throw new RuntimeException("Given newValue map has not the correct ModelType");
		}
		
		AssetAdministrationShell aas = AssetAdministrationShell.createAsFacade(map);
		
		return aas;
	}
	
	
	
	@Override
	public Object getModelPropertyValue(String path) throws Exception {
		path = stripPrefix(path);
		
		if(path.isEmpty()) { //Return all AAS if path is empty
			return aggregator.getAASList();
		} else { //A specific AAS was requested
			path = URLDecoder.decode(path, ENCODING_SCHEME);
			IAssetAdministrationShell aas = aggregator.getAAS(new ModelUrn(path));
			
			//Throw an Exception if the requested aas does not exist
			if(aas == null) {
				throw new RuntimeException("Requested aasID '" + path + "' does not exist.");
			}
			return aas;
		}
	}

	@Override
	public void setModelPropertyValue(String path, Object newValue) throws Exception {
		
		AssetAdministrationShell aas = createAASFromMap(newValue);
		
		path = stripPrefix(path);
		
		if (!path.isEmpty()) { // Overwriting existing entry
			// Decode encoded path
			path = URLDecoder.decode(path, ENCODING_SCHEME);
			ModelUrn identifier = new ModelUrn(path);
			
			if(!aas.getIdentification().getId().equals(path)) {
				throw new RuntimeException("Given aasID and given AAS do not match");
			}
			
			if(aggregator.getAAS(identifier) == null) {
				throw new RuntimeException("Can not update non existing value '" + path + "'. Try create instead.");
			}

			aggregator.updateAAS(aas);
		} else {
			throw new RuntimeException("Set with empty path is not supported by aggregator");
		}
	}

	@Override
	public void createValue(String path, Object newEntity) throws Exception {

		AssetAdministrationShell aas = createAASFromMap(newEntity);
		
		path = stripPrefix(path);

		if (path.isEmpty()) { // Creating new entry
			
			if(aggregator.getAAS(aas.getIdentification()) != null) {
				throw new RuntimeException("Value '" + path + "' to be created already exists. Try update instead.");
			}
			
			aggregator.createAAS(aas);
		} else {
			throw new RuntimeException("Create was called with an unsupported path: " + path);
		}
		
	}

	@Override
	public void deleteValue(String path) throws Exception {
		path = stripPrefix(path);

		if (!path.isEmpty()) { // Deleting an entry
			// Decode encoded path
			path = URLDecoder.decode(path, ENCODING_SCHEME);
			
			IIdentifier identifier = new ModelUrn(path);
			
			if(aggregator.getAAS(identifier) == null) {
				throw new RuntimeException("Value '" + path + "' to be deleted does not exists.");
			}
			
			aggregator.deleteAAS(identifier);
		} else {
			throw new RuntimeException("Delete with empty path is not supported by registry");
		}
	}

	@Override
	public void deleteValue(String path, Object obj) throws Exception {
		throw new RuntimeException("DeleteValue with parameter not supported by aggregator");
	}

	@Override
	public Object invokeOperation(String path, Object... parameter) throws Exception {
		throw new RuntimeException("Invoke not supported by aggregator");
	}
	
}