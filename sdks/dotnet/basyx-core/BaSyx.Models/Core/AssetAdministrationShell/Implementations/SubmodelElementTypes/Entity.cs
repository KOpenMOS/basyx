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
using BaSyx.Models.Core.AssetAdministrationShell.Generics;
using BaSyx.Models.Core.AssetAdministrationShell.Generics.SubmodelElementTypes;
using BaSyx.Models.Core.AssetAdministrationShell.References;
using BaSyx.Models.Core.Common;
using System.Runtime.Serialization;

namespace BaSyx.Models.Core.AssetAdministrationShell.Implementations.SubmodelElementTypes
{
    [DataContract]
    public class Entity : SubmodelElement, IEntity
    {
        public override ModelType ModelType => ModelType.Entity;

        public IElementContainer<ISubmodelElement> Statements { get; set; }

        public EntityType EntityType { get; set; }

        public IReference<IAsset> Asset { get; set; }

        public Entity() : base()
        {
            Statements = new ElementContainer<ISubmodelElement>();
        }
    }
}
