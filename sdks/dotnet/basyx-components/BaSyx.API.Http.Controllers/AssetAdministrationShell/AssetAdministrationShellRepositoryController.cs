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
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using BaSyx.Models.Core.AssetAdministrationShell.Generics;
using BaSyx.Utils.ResultHandling;
using BaSyx.API.Components;
using BaSyx.Models.Core.AssetAdministrationShell.Implementations;
using BaSyx.Models.Communication;

namespace BaSyx.API.Http.Controllers
{
    /// <summary>
    /// The Asset Administration Shell Repository Controller
    /// </summary>
    public class AssetAdministrationShellRepositoryController : Controller
    {
        private readonly IAssetAdministrationShellRepositoryServiceProvider serviceProvider;

        /// <summary>
        /// The constructor for the Asset Administration Shell Repository Controller
        /// </summary>
        /// <param name="assetAdministrationShellRepositoryServiceProvider"></param>
        public AssetAdministrationShellRepositoryController(IAssetAdministrationShellRepositoryServiceProvider assetAdministrationShellRepositoryServiceProvider)
        {
            serviceProvider = assetAdministrationShellRepositoryServiceProvider;
        }

        /// <summary>
        /// Retrieves all Asset Administration Shells from the Asset Administration Shell repository
        /// </summary>
        /// <returns></returns>
        /// <response code="200">Returns a list of found Asset Administration Shells</response>
        [HttpGet("shells", Name = "GetAllAssetAdministrationShells")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(List<BaSyx.Models.Core.AssetAdministrationShell.Implementations.AssetAdministrationShell>), 200)]
        public IActionResult GetAllAssetAdministrationShells()
        {
            var result = serviceProvider.RetrieveAssetAdministrationShells();
            return result.CreateActionResult(CrudOperation.Retrieve);
        }
        /// <summary>
        /// Retrieves a specific Asset Administration Shell from the Asset Administration Shell repository
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <returns></returns>
        /// <response code="200">Returns the requested Asset Administration Shell</response>
        /// <response code="404">No Asset Administration Shell found</response>           
        [HttpGet("shells/{aasId}")]
        [HttpGet("shells/{aasId}/aas", Name = "GetAssetAdministrationShellById")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(BaSyx.Models.Core.AssetAdministrationShell.Implementations.AssetAdministrationShell), 200)]
        public IActionResult GetAssetAdministrationShellById(string aasId)
        {
            if (string.IsNullOrEmpty(aasId))
                return ResultHandling.NullResult(nameof(aasId));

            var result = serviceProvider.RetrieveAssetAdministrationShell(aasId);
            return result.CreateActionResult(CrudOperation.Retrieve);
        }

        /// <summary>
        /// Creates or updates a Asset Administration Shell at the Asset Administration Shell repository 
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="aas">The Asset Administration Shell</param>
        /// <returns></returns>
        /// <response code="201">Asset Administration Shell created successfully</response>
        /// <response code="400">Bad Request</response>             
        [HttpPut("shells/{aasId}", Name = "PutAssetAdministrationShell")]
        [Produces("application/json")]
        [Consumes("application/json")]
        [ProducesResponseType(typeof(BaSyx.Models.Core.AssetAdministrationShell.Implementations.AssetAdministrationShell), 201)]
        public IActionResult PutAssetAdministrationShell(string aasId, [FromBody] IAssetAdministrationShell aas)
        {
            if (string.IsNullOrEmpty(aasId))
                return ResultHandling.NullResult(nameof(aasId));
            if (aas == null)
                return ResultHandling.NullResult(nameof(aas));

            var result = serviceProvider.CreateAssetAdministrationShell(aas);
            return result.CreateActionResult(CrudOperation.Create);
        }
        /// <summary>
        /// Deletes a specific Asset Administration Shell at the Asset Administration Shell repository
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <returns></returns>
        /// <response code="200">Asset Administration Shell deleted successfully</response>
        [HttpDelete("shells/{aasId}", Name = "DeleteAssetAdministrationShellById")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(Result), 200)]
        public IActionResult DeleteAssetAdministrationShellById(string aasId)
        {
            if (string.IsNullOrEmpty(aasId))
                return ResultHandling.NullResult(nameof(aasId));

            var result = serviceProvider.DeleteAssetAdministrationShell(aasId);
            return result.CreateActionResult(CrudOperation.Delete);            
        }

        #region AssetAdministrationShell-Services

        /// <summary>
        /// Retrieves all Submodels from the  Asset Administration Shell
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <returns></returns>
        /// <response code="200">Returns a list of found Submodels</response>
        /// <response code="404">No Submodel Service Providers found</response>       
        [HttpGet("shells/{aasId}/aas/submodels", Name = "ShellRepo_GetSubmodelsFromShell")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(Result), 200)]
        public IActionResult ShellRepo_GetSubmodelsFromShell(string aasId)
        {
            if (IsNullOrNotFound(aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider))
                return result;

            var service = new AssetAdministrationShellController(provider);
            return service.GetSubmodelsFromShell();
        }

        /// <summary>
        /// Creates or updates a Submodel to an existing Asset Administration Shell
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="submodelIdShort">The Submodel's short id</param>
        /// <param name="submodel">The serialized Submodel object</param>
        /// <returns></returns>
        /// <response code="201">Submodel created successfully</response>
        /// <response code="400">Bad Request</response>               
        [HttpPut("shells/{aasId}/aas/submodels/{submodelIdShort}", Name = "ShellRepo_PutSubmodelToShell")]
        [Produces("application/json")]
        [Consumes("application/json")]
        [ProducesResponseType(typeof(Submodel), 201)]
        [ProducesResponseType(typeof(Result), 400)]
        public IActionResult ShellRepo_PutSubmodelToShell(string aasId, string submodelIdShort, [FromBody] ISubmodel submodel)
        {
            if (IsNullOrNotFound(aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider))
                return result;

            var service = new AssetAdministrationShellController(provider);
            return service.PutSubmodelToShell(submodelIdShort, submodel);
        }

        /// <summary>
        /// Retrieves the Submodel from the Asset Administration Shell
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="submodelIdShort">The Submodel's short id</param>
        /// <returns></returns>
        /// <response code="200">Submodel retrieved successfully</response>
        /// <response code="404">No Submodel Service Provider found</response>    
        [HttpGet("shells/{aasId}/aas/submodels/{submodelIdShort}")]
        [HttpGet("shells/{aasId}/aas/submodels/{submodelIdShort}/submodel", Name = "ShellRepo_GetSubmodelFromShellByIdShort")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(Submodel), 200)]
        [ProducesResponseType(typeof(Result), 400)]
        [ProducesResponseType(typeof(Result), 404)]
        public IActionResult ShellRepo_GetSubmodelFromShellByIdShort(string aasId, string submodelIdShort)
        {
            if (IsNullOrNotFound(aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider))
                return result;

            var service = new AssetAdministrationShellController(provider);
            return service.GetSubmodelFromShellByIdShort(submodelIdShort);
        }

        /// <summary>
        /// Deletes a specific Submodel from the Asset Administration Shell
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="submodelIdShort">The Submodel's short id</param>
        /// <returns></returns>
        /// <response code="204">Submodel deleted successfully</response>
        /// <response code="400">Bad Request</response>    
        [HttpDelete("shells/{aasId}/aas/submodels/{submodelIdShort}", Name = "ShellRepo_DeleteSubmodelFromShellByIdShort")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(Result), 400)]
        public IActionResult ShellRepo_DeleteSubmodelFromShellByIdShort(string aasId, string submodelIdShort)
        {
            if (IsNullOrNotFound(aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider))
                return result;

            var service = new AssetAdministrationShellController(provider);
            return service.DeleteSubmodelFromShellByIdShort(submodelIdShort);
        }

        /// <summary>
        /// Retrieves the minimized version of a Submodel, i.e. only the values of SubmodelElements are serialized and returned
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="submodelIdShort">The Submodel's short id</param>
        /// <returns></returns>
        /// <response code="200">Success</response>
        /// <response code="404">Submodel not found</response>       
        [HttpGet("shells/{aasId}/aas/submodels/{submodelIdShort}/submodel/values", Name = "ShellRepo_GetSubmodelValues")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(Result), 404)]
        public IActionResult ShellRepo_GetSubmodelValues(string aasId, string submodelIdShort)
        {
            if (IsNullOrNotFound(aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider))
                return result;

            var service = new AssetAdministrationShellController(provider);
            return service.Shell_GetSubmodelValues(submodelIdShort);
        }

        /// <summary>
        /// Retrieves all Submodel-Elements from the Submodel
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="submodelIdShort">The Submodel's short id</param>
        /// <returns></returns>
        /// <response code="200">Returns a list of found Submodel-Elements</response>
        /// <response code="404">Submodel not found</response>       
        [HttpGet("shells/{aasId}/aas/submodels/{submodelIdShort}/submodel/submodelElements", Name = "ShellRepo_GetSubmodelElements")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(SubmodelElement[]), 200)]
        [ProducesResponseType(typeof(Result), 404)]
        public IActionResult ShellRepo_GetSubmodelElements(string aasId, string submodelIdShort)
        {
            if (IsNullOrNotFound(aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider))
                return result;

            var service = new AssetAdministrationShellController(provider);
            return service.Shell_GetSubmodelElements(submodelIdShort);
        }

        /// <summary>
        /// Creates or updates a Submodel-Element at the Submodel
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="submodelIdShort">The Submodel's short id</param>
        /// <param name="seIdShortPath">The Submodel-Element's IdShort-Path</param>
        /// <param name="submodelElement">The Submodel-Element object</param>
        /// <returns></returns>
        /// <response code="201">Submodel-Element created successfully</response>
        /// <response code="400">Bad Request</response>
        /// <response code="404">Submodel not found</response>
        [HttpPut("shells/{aasId}/aas/submodels/{submodelIdShort}/submodel/submodelElements/{*seIdShortPath}", Name = "ShellRepo_PutSubmodelElement")]
        [Produces("application/json")]
        [Consumes("application/json")]
        [ProducesResponseType(typeof(SubmodelElement), 201)]
        [ProducesResponseType(typeof(Result), 400)]
        [ProducesResponseType(typeof(Result), 404)]
        public IActionResult ShellRepo_PutSubmodelElement(string aasId, string submodelIdShort, string seIdShortPath, [FromBody] ISubmodelElement submodelElement)
        {
            if (IsNullOrNotFound(aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider))
                return result;

            var service = new AssetAdministrationShellController(provider);
            return service.Shell_PutSubmodelElement(submodelIdShort, seIdShortPath, submodelElement);
        }

        /// <summary>
        /// Retrieves a specific Submodel-Element from the Submodel
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="submodelIdShort">The Submodel's short id</param>
        /// <param name="seIdShortPath">The Submodel-Element's IdShort-Path</param>
        /// <returns></returns>
        /// <response code="200">Returns the requested Submodel-Element</response>
        /// <response code="404">Submodel / Submodel-Element not found</response>     
        [HttpGet("shells/{aasId}/aas/submodels/{submodelIdShort}/submodel/submodelElements/{seIdShortPath}", Name = "ShellRepo_GetSubmodelElementByIdShort")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(SubmodelElement), 200)]
        [ProducesResponseType(typeof(Result), 404)]
        public IActionResult ShellRepo_GetSubmodelElementByIdShort(string aasId, string submodelIdShort, string seIdShortPath)
        {
            if (IsNullOrNotFound(aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider))
                return result;

            var service = new AssetAdministrationShellController(provider);
            return service.Shell_GetSubmodelElementByIdShort(submodelIdShort, seIdShortPath);
        }

        /// <summary>
        /// Retrieves the value of a specific Submodel-Element from the Submodel
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="submodelIdShort">The Submodel's short id</param>
        /// <param name="seIdShortPath">The Submodel-Element's IdShort-Path</param>
        /// <returns></returns>
        /// <response code="200">Returns the value of a specific Submodel-Element</response>
        /// <response code="404">Submodel / Submodel-Element not found</response>  
        /// <response code="405">Method not allowed</response>  
        [HttpGet("shells/{aasId}/aas/submodels/{submodelIdShort}/submodel/submodelElements/{seIdShortPath}/value", Name = "ShellRepo_GetSubmodelElementValueByIdShort")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(object), 200)]
        [ProducesResponseType(typeof(Result), 404)]
        [ProducesResponseType(typeof(Result), 405)]
        public IActionResult ShellRepo_GetSubmodelElementValueByIdShort(string aasId, string submodelIdShort, string seIdShortPath)
        {
            if (IsNullOrNotFound(aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider))
                return result;

            var service = new AssetAdministrationShellController(provider);
            return service.Shell_GetSubmodelElementValueByIdShort(submodelIdShort, seIdShortPath);
        }

        /// <summary>
        /// Updates the Submodel-Element's value
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="submodelIdShort">The Submodel's short id</param>
        /// <param name="seIdShortPath">The Submodel-Element's IdShort-Path</param>
        /// <param name="value">The new value</param>
        /// <returns></returns>
        /// <response code="200">Submodel-Element's value changed successfully</response>
        /// <response code="404">Submodel / Submodel-Element not found</response>     
        /// <response code="405">Method not allowed</response>  
        [HttpPut("shells/{aasId}/aas/submodels/{submodelIdShort}/submodel/submodelElements/{seIdShortPath}/value", Name = "ShellRepo_PutSubmodelElementValueByIdShort")]
        [Produces("application/json")]
        [Consumes("application/json")]
        [ProducesResponseType(typeof(ElementValue), 200)]
        [ProducesResponseType(typeof(Result), 404)]
        public IActionResult ShellRepo_PutSubmodelElementValueByIdShort(string aasId, string submodelIdShort, string seIdShortPath, [FromBody] object value)
        {
            if (IsNullOrNotFound(aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider))
                return result;

            var service = new AssetAdministrationShellController(provider);
            return service.Shell_PutSubmodelElementValueByIdShort(submodelIdShort, seIdShortPath, value);
        }

        /// <summary>
        /// Deletes a specific Submodel-Element from the Submodel
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="submodelIdShort">The Submodel's short id</param>
        /// <param name="seIdShortPath">The Submodel-Element's IdShort-Path</param>
        /// <returns></returns>
        /// <response code="204">Submodel-Element deleted successfully</response>
        /// <response code="404">Submodel / Submodel-Element not found</response>
        [HttpDelete("shells/{aasId}/aas/submodels/{submodelIdShort}/submodel/submodelElements/{seIdShortPath}", Name = "ShellRepo_DeleteSubmodelElementByIdShort")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(Result), 200)]
        public IActionResult ShellRepo_DeleteSubmodelElementByIdShort(string aasId, string submodelIdShort, string seIdShortPath)
        {
            if (IsNullOrNotFound(aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider))
                return result;

            var service = new AssetAdministrationShellController(provider);
            return service.Shell_DeleteSubmodelElementByIdShort(submodelIdShort, seIdShortPath);
        }

        /// <summary>
        /// Invokes a specific operation from the Submodel synchronously or asynchronously
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="submodelIdShort">Submodel's short id</param>
        /// <param name="idShortPathToOperation">The IdShort path to the Operation</param>
        /// <param name="invocationRequest">The parameterized request object for the invocation</param>
        /// <param name="async">Determines whether the execution of the operation is asynchronous (true) or not (false)</param>
        /// <returns></returns>
        /// <response code="200">Operation invoked successfully</response>
        /// <response code="400">Bad Request</response>
        /// <response code="404">Submodel / Method handler not found</response>
        [HttpPost("shells/{aasId}/aas/submodels/{submodelIdShort}/submodel/submodelElements/{idShortPathToOperation}/invoke", Name = "ShellRepo_InvokeOperationByIdShort")]
        [Produces("application/json")]
        [Consumes("application/json")]
        [ProducesResponseType(typeof(Result), 400)]
        [ProducesResponseType(typeof(Result), 404)]
        public IActionResult ShellRepo_InvokeOperationByIdShort(string aasId, string submodelIdShort, string idShortPathToOperation, [FromBody] InvocationRequest invocationRequest, [FromQuery] bool async)
        {
            if (IsNullOrNotFound(aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider))
                return result;

            var service = new AssetAdministrationShellController(provider);
            return service.Shell_InvokeOperationByIdShort(submodelIdShort, idShortPathToOperation, invocationRequest, async);
        }

        /// <summary>
        /// Retrieves the result of an asynchronously started operation
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="submodelIdShort">Submodel's short id</param>
        /// <param name="idShortPathToOperation">The IdShort path to the Operation</param>
        /// <param name="requestId">The request id</param>
        /// <returns></returns>
        /// <response code="200">Result found</response>
        /// <response code="400">Bad Request</response>
        /// <response code="404">Submodel / Operation / Request not found</response>
        [HttpGet("shells/{aasId}/aas/submodels/{submodelIdShort}/submodel/submodelElements/{idShortPathToOperation}/invocationList/{requestId}", Name = "ShellRepo_GetInvocationResultByIdShort")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(InvocationResponse), 200)]
        [ProducesResponseType(typeof(Result), 400)]
        [ProducesResponseType(typeof(Result), 404)]
        public IActionResult ShellRepo_GetInvocationResultByIdShort(string aasId, string submodelIdShort, string idShortPathToOperation, string requestId)
        {
            if (IsNullOrNotFound(aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider))
                return result;

            var service = new AssetAdministrationShellController(provider);
            return service.Shell_GetInvocationResultByIdShort(submodelIdShort, idShortPathToOperation, requestId);
        }

        #endregion

        #region Helper
        /// <summary>
        /// Checks whether aasId is null or Asset Administration Shell Service Provider cannot be found
        /// </summary>
        /// <param name="aasId">The Asset Administration Shell's unique id</param>
        /// <param name="result">The IActionResult in case aasId is null or the provider cannot be found</param>
        /// <param name="provider">The Asset Administration Shell Service Provider</param>
        /// <returns></returns>
        public bool IsNullOrNotFound(string aasId, out IActionResult result, out IAssetAdministrationShellServiceProvider provider)
        {
            if (string.IsNullOrEmpty(aasId))
            {
                result = ResultHandling.NullResult(nameof(aasId));
                provider = null;
                return true;
            }
            provider = serviceProvider.GetAssetAdministrationShellServiceProvider(aasId);
            if (provider == null)
            {
                result = NotFound(new Result(false, new NotFoundMessage("Asset Administration Shell Provider")));
                return true;
            }
            result = null;
            return false;
        }

        #endregion

    }
}