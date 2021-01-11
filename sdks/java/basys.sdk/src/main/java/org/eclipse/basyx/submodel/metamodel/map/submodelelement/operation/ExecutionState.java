package org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation;

import org.eclipse.basyx.submodel.metamodel.enumhelper.StandardizedLiteralEnum;
import org.eclipse.basyx.submodel.metamodel.enumhelper.StandardizedLiteralEnumHelper;

/**
 * ExecutionState for Operations
 * 
 * @author espen
 *
 */
public enum ExecutionState implements StandardizedLiteralEnum {

	/**
	 * Initial state
	 */
	INITIATED("Initiated"),
	/**
	 * State during execution
	 */
	RUNNING("Running"),
	/**
	 * Operation has been completed
	 */
	COMPLETED("Completed"),
	/**
	 * Operation has been canceled
	 */
	CANCELED("Canceled"),
	/**
	 * Operation has failed
	 */
	FAILED("Failed"),
	/**
	 * Operation has timed out
	 */
	TIMEOUT("Timeout");

	private String standardizedLiteral;

	private ExecutionState(String standardizedLiteral) {
		this.standardizedLiteral = standardizedLiteral;
	}

	@Override
	public String getStandardizedLiteral() {
		return standardizedLiteral;
	}
	
	@Override
	public String toString() {
		return standardizedLiteral;
	}

	public static ExecutionState fromString(String str) {
		return StandardizedLiteralEnumHelper.fromLiteral(ExecutionState.class, str);
	}
}
