/*******************************************************************************
* Copyright (c) 2020 Robert Bosch GmbH
* Author: Constantin Ziesche (constantin.ziesche@bosch.com)
*
* This program and the accompanying materials are made available under the
* terms of the Eclipse Public License 2.0 which is available at
* http://www.eclipse.org/legal/epl-2.0
*
* SPDX-License-Identifier: EPL-2.0
*******************************************************************************/
using BaSyx.Models.Connectivity;

namespace BaSyx.API.Components
{
    public interface IServiceProvider<TModelElement, TServiceDescriptor>
        where TServiceDescriptor : IServiceDescriptor
    {
        TServiceDescriptor ServiceDescriptor { get; }
        void BindTo(TModelElement element);
        TModelElement GetBinding();
    }
}