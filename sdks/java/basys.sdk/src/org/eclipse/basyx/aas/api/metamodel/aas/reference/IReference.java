package org.eclipse.basyx.aas.api.metamodel.aas.reference;

import java.util.List;

import org.eclipse.basyx.aas.metamodel.hashmap.aas.reference.Key;

/**
 * Interface for Reference
 * 
 * @author rajashek
 *
*/

public interface IReference {

	/**
	 * Reference to either a model element of the same or another AAS or to an
	 * external entity. A reference is an ordered list of keys, each key referencing
	 * an element. The complete list of keys may for example be concatenated to a
	 * path that then gives unique access to an element or entity.
	 * 
	 * @return The complete list of keys
	 */
	public List<Key> getKeys();
	
	public void setKeys(List<Key> keys);

}
