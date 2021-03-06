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
using BaSyx.Models.Core.Common;
using System.Runtime.Serialization;

namespace BaSyx.Models.Core.AssetAdministrationShell.Generics.SubmodelElementTypes
{
    /// <summary>
    /// A range data element is a data element that defines a range with min and max
    /// </summary>
    public interface IRange : ISubmodelElement
    {
        /// <summary>
        /// The minimum value of the range. If the min value is missing then the value is assumed to be negative infinite.
        /// </summary>
        [DataMember(EmitDefaultValue = false, IsRequired = false, Name = "min")]
        IValue Min { get; }

        /// <summary>
        /// The maximum value of the range. If the max value is missing then the value is assumed to be positive infinite.
        /// </summary>
        [DataMember(EmitDefaultValue = false, IsRequired = false, Name = "max")]
        IValue Max { get; }

        /// <summary>
        /// Data type of the min und max.
        /// </summary>
        [DataMember(EmitDefaultValue = false, IsRequired = false, Name = "valueType")]
        DataType ValueType { get; set; }
    }
}
