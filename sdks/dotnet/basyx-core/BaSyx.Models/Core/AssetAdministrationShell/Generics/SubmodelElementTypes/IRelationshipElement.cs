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
using BaSyx.Models.Core.AssetAdministrationShell.References;
using BaSyx.Models.Core.AssetAdministrationShell.Identification;
using System.Runtime.Serialization;

namespace BaSyx.Models.Core.AssetAdministrationShell.Generics.SubmodelElementTypes
{
    public interface IRelationshipElement : ISubmodelElement
    {
        [DataMember(EmitDefaultValue = false, IsRequired = false, Name = "first")]
        IReference<IReferable> First { get; }
        [DataMember(EmitDefaultValue = false, IsRequired = false, Name = "second")]
        IReference<IReferable> Second { get; }
    }
}