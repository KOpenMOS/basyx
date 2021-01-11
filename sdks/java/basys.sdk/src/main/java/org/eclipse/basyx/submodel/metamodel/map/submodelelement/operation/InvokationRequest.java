package org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.basyx.submodel.metamodel.api.submodelelement.operation.IOperationVariable;
import org.eclipse.basyx.vab.model.VABModelMap;

/**
 * Request for invoking operation submodel elements
 * 
 * @author schnicke
 *
 */
public class InvokationRequest extends VABModelMap<Object> {
	public static final String REQUESTID = "requestId";
	public static final String INOUTARGUMENTS = "inoutputArguments";
	public static final String INPUTARGUMENTS = "inputArguments";
	public static final String TIMEOUT = "timeout";

	private InvokationRequest() {
	}

	public InvokationRequest(String requestId, Collection<IOperationVariable> inoutArguments, Collection<IOperationVariable> inputArguments, int timeout) {
		put(REQUESTID, requestId);
		put(INOUTARGUMENTS, inoutArguments);
		put(INPUTARGUMENTS, inputArguments);
		put(TIMEOUT, timeout);
	}

	public static InvokationRequest createAsFacade(Map<String, Object> map) {
		if (map == null) {
			return null;
		}
		
		InvokationRequest ret = new InvokationRequest();
		ret.setRequestId((String) map.get(REQUESTID));
		Collection<IOperationVariable> inoutArguments = createInoutArguments(map);
		ret.setInOutArguments(inoutArguments);

		Collection<IOperationVariable> inputArguments = createInputArguments(map);
		ret.setInputArguments(inputArguments);

		ret.setTimeout((int) map.get(TIMEOUT));

		return ret;
	}

	@SuppressWarnings("unchecked")
	private static Collection<IOperationVariable> createInputArguments(Map<String, Object> map) {
		Collection<Map<String, Object>> inputMap = (Collection<Map<String, Object>>) map.get(INPUTARGUMENTS);
		return createOperationVariables(inputMap);
	}

	/**
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	private static Collection<IOperationVariable> createInoutArguments(Map<String, Object> map) {
		Collection<Map<String, Object>> inoutMap = (Collection<Map<String, Object>>) map.get(INOUTARGUMENTS);
		return createOperationVariables(inoutMap);
	}

	private static Collection<IOperationVariable> createOperationVariables(Collection<Map<String, Object>> variableMap) {
		if (variableMap != null) {
			return variableMap.stream().map(OperationVariable::createAsFacade).collect(Collectors.toList());
		} else {
			return new ArrayList<>();
		}
	}

	private void setRequestId(String request) {
		put(REQUESTID, request);
	}

	private void setInOutArguments(Collection<IOperationVariable> inoutArguments) {
		put(INOUTARGUMENTS, inoutArguments);
	}

	private void setInputArguments(Collection<IOperationVariable> inputArguments) {
		put(INPUTARGUMENTS, inputArguments);
	}

	private void setTimeout(int timeout) {
		put(TIMEOUT, timeout);
	}

	public String getRequestId() {
		return (String) get(REQUESTID);
	}

	@SuppressWarnings("unchecked")
	public Collection<IOperationVariable> getInOutArguments() {
		return (Collection<IOperationVariable>) get(INOUTARGUMENTS);
	}

	@SuppressWarnings("unchecked")
	public Collection<IOperationVariable> getInputArguments() {
		return (Collection<IOperationVariable>) get(INPUTARGUMENTS);
	}

	public int getTimeout() {
		return (int) get(TIMEOUT);
	}
}
