package org.eclipse.basyx.aas.backend.connector;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.basyx.aas.backend.http.tools.GSONTools;
import org.eclipse.basyx.aas.backend.http.tools.factory.DefaultTypeFactory;
import org.eclipse.basyx.aas.backend.http.tools.factory.GSONToolsFactory;

public class MetaprotocolHandler implements IMetaProtocolHandler {

	/**
	 * Reference to serializer / deserializer
	 */
	protected GSONTools serializer = null;
	
	/**
	 * Constructor that create the serializer
	 * 
	 */
	public MetaprotocolHandler() {
		// Create GSON serializer
		serializer = new GSONTools(new DefaultTypeFactory());
	}
	
	/**
	 * Constructor that accepts specific factory for serializer
	 * @param factory
	 */
	public MetaprotocolHandler(GSONToolsFactory factory) {
		// Create GSON serializer
		serializer = new GSONTools(factory);
	}
	
	
	@SuppressWarnings("unchecked")
	public Object verify(String message) throws Exception {

		// First get the GSON object from the JSON string
		Object gsonObj = serializer.deserialize(message.toString());
		
		Object result = null;

		if (gsonObj instanceof Map) {
			Map<String, Object> responseMap = (Map<String, Object>) gsonObj;
				
				// Handle meta information and exceptions
				result = verifyResponse(responseMap);
				
			}
        return result;
	}
	
	
	/**
	 * Verify the response header and try to extract response if any. Process
	 * information of "success", "entityType" and "messages"
	 * 
	 * @param responseMap
	 *            - provide deserialized message
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Object verifyResponse(Map<String, Object> responseMap) throws Exception {

		System.out.println("Verify Response ...");
		
		// Retrieve messages if any
		Collection<Map<String, Object>> messages = (Collection<Map<String, Object>>) responseMap.get("messages");
		if (messages == null) messages = new LinkedList<Map<String, Object>>();
		
		boolean success =  (boolean) responseMap.get("success");
			
		// Return result if success
		if (success) {
			
			for (Map<String, Object> m : messages) {
				System.out.println(m.get("messageType")+ ", "+ m.get("code") + ", "+ m.get("text"));
			}
			
			Object result =  responseMap.get("entity");	
			if (result != null) return result;		
		}
		
		// Throw exception if no success
		else if (!success){
			if (responseMap.get("isException").equals(true)) {
				Map<String, Object> first = messages.iterator().next(); //assumes an Exception always comes with a message
				
				String text = (String) first.get("text");
				throw new Exception("Server threw exception: " + text);
			} else {
				throw new Exception("Format Error: no success but isException not true or not found.");
			}
		} else {
			throw new Exception("Format Error: success not found.");
		}
		
		return null;

	}
}
