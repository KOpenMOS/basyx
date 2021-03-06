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
using BaSyx.API.Components;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BaSyx.Registry.Server.Http.Pages
{
    public class IndexModel : PageModel
    {
        public IAssetAdministrationShellRegistry Registry { get; }

        public IndexModel(IAssetAdministrationShellRegistry registry)
        {
            Registry = registry;
        }

        public void OnGet()
        {

        }
    }
}
