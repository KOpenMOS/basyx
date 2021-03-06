/*******************************************************************************
 * Copyright (C) 2021 the Eclipse BaSyx Authors
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation;

/**
 * Used to indicate that the execution of an Operation timed out
 * 
 * @author espen
 *
 */
public class OperationExecutionTimeoutException extends RuntimeException {

	
	/**
	 * Version information for serialized instances
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Store message
	 */
	protected String message = null;
	
	
	/**
	 * Constructor
	 */
	public OperationExecutionTimeoutException(String msg) {
		// Store message
		message = msg;
	}
	
		
	public OperationExecutionTimeoutException(Exception e) {
		super(e);
	}


	public OperationExecutionTimeoutException(String message, Throwable cause) {
		super(cause);
		this.message = message;
	}


	/**
	 * Return detailed message
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
