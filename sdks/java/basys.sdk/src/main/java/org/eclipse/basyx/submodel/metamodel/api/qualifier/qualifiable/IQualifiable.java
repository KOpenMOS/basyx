/*******************************************************************************
 * Copyright (C) 2021 the Eclipse BaSyx Authors
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.basyx.submodel.metamodel.api.qualifier.qualifiable;

import java.util.Collection;

/**
 * Interface for Qualifiable
 * @author rajashek
 *
*/

public interface IQualifiable {
	public Collection<IConstraint> getQualifiers();
}
