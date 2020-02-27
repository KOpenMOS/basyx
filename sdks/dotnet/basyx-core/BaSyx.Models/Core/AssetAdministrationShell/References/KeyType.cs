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
using System.Runtime.Serialization;

namespace BaSyx.Models.Core.AssetAdministrationShell.References
{
    [DataContract]
    public enum KeyType
    {
        [EnumMember(Value = "Undefined")]
        Undefined,
        [EnumMember(Value = "Custom")]
        Custom,
        [EnumMember(Value = "IRI")]
        IRI,
        [EnumMember(Value = "URI")]
        URI,
        [EnumMember(Value = "IRDI")]
        IRDI,
        [EnumMember(Value = "IdShort")]
        IdShort,
        [EnumMember(Value = "FragmentId")]
        FragmentId
    }
}
