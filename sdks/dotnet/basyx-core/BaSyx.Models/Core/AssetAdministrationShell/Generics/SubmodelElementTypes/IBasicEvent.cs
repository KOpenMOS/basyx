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
using BaSyx.Models.Core.AssetAdministrationShell.Identification;
using BaSyx.Models.Core.AssetAdministrationShell.References;
using System.Runtime.Serialization;

namespace BaSyx.Models.Core.AssetAdministrationShell.Generics.SubmodelElementTypes
{
    /// <summary>
    /// A basic event
    /// </summary>
    public interface IBasicEvent : IEvent
    {
        /// <summary>
        /// Reference to the data or other elements that are being observed.
        /// </summary>
        [DataMember(EmitDefaultValue = false, IsRequired = false, Name = "observed")]
        IReference<IReferable> Observed { get; set; }
    }
}
