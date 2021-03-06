/*******************************************************************************
 * Copyright (C) 2021 the Eclipse BaSyx Authors
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.basyx.vab.exception;

public class ReadOnlyException extends Exception {

	
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	/**
	 * Constructor
	 */
	public ReadOnlyException(String name) {
		// Store message
		message = "The Submodel "+name+" is frozen.";
	}
	
	
	
	/**
	 * Return detailed message
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
