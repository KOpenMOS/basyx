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
using System;
using System.Linq;

namespace BaSyx.Utils.PathHandling
{
    public static class Path
    {
        public static Uri Append(this Uri uri, params string[] pathElements)
        {
            return new Uri(pathElements.Aggregate(uri.AbsoluteUri, (currentElement, pathElement) => string.Format("{0}/{1}", currentElement.TrimEnd('/'), pathElement.TrimStart('/'))));
        }

        public static string GetFormattedEndpoint(string endpoint, string aggregateId, string entityId, string separator = "/")
        {
            if (endpoint[endpoint.Length - 1] == separator[0])
            {
                if (!endpoint.Contains(aggregateId))
                    endpoint += aggregateId + separator + entityId;
                else
                    endpoint += entityId;
            }
            else
            {
                if (!endpoint.Contains(aggregateId))
                    endpoint += separator + aggregateId + separator + entityId;
                else
                    endpoint += separator + entityId;
            }

            return endpoint;
        }
    }
}