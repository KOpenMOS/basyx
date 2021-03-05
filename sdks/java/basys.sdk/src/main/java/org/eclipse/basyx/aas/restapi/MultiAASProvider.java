/*******************************************************************************
 * Copyright (C) 2021 the Eclipse BaSyx Authors
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.basyx.aas.restapi;

import java.util.HashMap;

import org.eclipse.basyx.vab.exception.provider.MalformedRequestException;
import org.eclipse.basyx.vab.exception.provider.ProviderException;
import org.eclipse.basyx.vab.exception.provider.ResourceNotFoundException;
import org.eclipse.basyx.vab.modelprovider.VABPathTools;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;

/**
 * Provider, that redirects requests for different Asset Administration Shells.
 * e.g. aas1 refers to the AAS with id "aas1".
 * 
 * @author espen
 *
 */
public class MultiAASProvider implements IModelProvider {
	protected HashMap<String, MultiSubmodelProvider> aas_providers;

	public MultiAASProvider() {
		aas_providers = new HashMap<>();
	}

	/**
	 * Adds an Asset Administration Shell to this provider. The AAS will be
	 * accessible via *id
	 * 
	 * @param aasIdShort
	 *            The id of the added Asset Administration Shell.
	 * @param modelProvider
	 *            The provider that contains the Asset Administration Shell.
	 */
	public void addMultiSubmodelProvider(String aasIdShort, MultiSubmodelProvider modelProvider) {
		aas_providers.put(aasIdShort, modelProvider);
	}

	/**
	 * Removes all connected Asset Administration Shells from this provider
	 */
	public void clear() {
		aas_providers.clear();
	}

	@Override
	public Object getModelPropertyValue(String path) throws ProviderException {
		String aasId = getId(path);
		MultiSubmodelProvider provider = aas_providers.get(aasId);
		if (provider == null) {
			throw new ResourceNotFoundException("AAS with ID \"" + aasId + "\" does not exist.");
		}
		String subPath = getSubPath(path, aasId);
		return provider.getModelPropertyValue(subPath);
	}

	@Override
	public void setModelPropertyValue(String path, Object newValue) throws ProviderException {
		String aasId = getId(path);
		MultiSubmodelProvider provider = aas_providers.get(aasId);
		if (provider == null) {
			throw new ResourceNotFoundException("AAS with ID \"" + aasId + "\" does not exist.");
		}
		String subPath = getSubPath(path, aasId);
		provider.setModelPropertyValue(subPath, newValue);
	}

	@Override
	public void createValue(String path, Object newEntity) throws ProviderException {
		String aasId = getId(path);
		MultiSubmodelProvider provider = aas_providers.get(aasId);
		if (provider == null) {
			throw new ResourceNotFoundException("AAS with ID \"" + aasId + "\" does not exist.");
		}
		String subPath = getSubPath(path, aasId);
		provider.createValue(subPath, newEntity);
	}

	@Override
	public void deleteValue(String path) throws ProviderException {
		String aasId = getId(path);
		MultiSubmodelProvider provider = aas_providers.get(aasId);
		if (provider == null) {
			throw new ResourceNotFoundException("AAS with ID \"" + aasId + "\" does not exist.");
		}
		String subPath = getSubPath(path, aasId);
		provider.deleteValue(subPath);
	}

	@Override
	public void deleteValue(String path, Object obj) throws ProviderException {
		String aasId = getId(path);
		MultiSubmodelProvider provider = aas_providers.get(aasId);
		if (provider == null) {
			throw new ResourceNotFoundException("AAS with ID \"" + aasId + "\" does not exist.");
		}
		String subPath = getSubPath(path, aasId);
		provider.deleteValue(subPath, obj);
	}

	@Override
	public Object invokeOperation(String path, Object... parameter) throws ProviderException {
		String aasId = getId(path);
		MultiSubmodelProvider provider = aas_providers.get(aasId);
		if (provider == null) {
			throw new ResourceNotFoundException("AAS with ID \"" + aasId + "\" does not exist.");
		}
		String subPath = getSubPath(path, aasId);
		return provider.invokeOperation(subPath, parameter);
	}

	/**
	 * Returns the requested aas id from a given VAB path. E.g. returns "aas1", if
	 * the path is aas1/aas/
	 * 
	 * @param path
	 *            The requested VAB path
	 * @return The id of the requested Asset Administration Shell. Returns null, if
	 *         the path is invalid or does not contain an AAS id.
	 */
	private String getId(String path) {
		if (path == null) {
			throw new MalformedRequestException("No AASId specified.");
		}

		String[] elements = VABPathTools.splitPath(path);
		if (elements.length >= 1) {
			String aasId = elements[0];
			return aasId;
		} else {
			throw new MalformedRequestException("No AASId specified.");
		}
	}

	/**
	 * Returns the sub path in the context of a given AAS id. E.g. returns
	 * "/aas/submodels", if the path is aas1/aas/submodels/
	 * 
	 * @param path
	 *            The requested VAB path
	 * @param aasId
	 *            The id of the requested Asset Administration Shell
	 * @return The remaining sub-path, when removing the id from the VAB path
	 */
	private String getSubPath(String path, String aasId) {
		return path.substring(aasId.length());
	}
}
