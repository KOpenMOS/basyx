package org.eclipse.basyx.testsuite.support.gateways.common.directory;

import org.eclipse.basyx.aas.impl.services.PreconfiguredDirectory;




/**
 * Implement the test suite directory service with pre-configured directory entries
 * 
 * @author kuhn
 *
 */
public class GatewayTestsuiteDirectoryLine2InternalIESE extends PreconfiguredDirectory {

	
	/**
	 * Constructor - load all directory entries
	 */
	public GatewayTestsuiteDirectoryLine2InternalIESE() {
		// Define mappings for line 2 internal in domain manufacturing.de
		// - Asset administration shells
		addMapping("line2int.line2.manufacturing.de/device10/aas", "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/"); //Device10/BaSys/1.0/provider");
		addMapping("line2int.line2.manufacturing.de/device10", "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/"); //Device10/BaSys/1.0/provider");
		addMapping("line2int.line2/device10",                  "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/"); //Device10/BaSys/1.0/provider");
		addMapping("line2int/device10/aas",                 	   "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/"); //Device10/BaSys/1.0/provider");
		addMapping("line2int/device10",                 	   "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/"); //Device10/BaSys/1.0/provider");
		addMapping("device10/aas",                           	   "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/"); //Device10/BaSys/1.0/provider");
		addMapping("device10",                           	   "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/"); //Device10/BaSys/1.0/provider");
	//	addMapping("device10",                               "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/Device10/BaSys/1.0/provider");
		
		// - Sub models of device10 in line 2 internal in domain manufacturing.de
		addMapping("line2int.line2.manufacturing.de/device10/aas/submodels/description", "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/"); //Device10/Submodels/BaSys/1.0/provider");
		addMapping("line2int.line2.manufacturing.de/device10/aas/submodels/status",      "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/"); //Device10/Submodels/BaSys/1.0/provider");
		addMapping("line2int.line2/device10/aas/submodels/description",                  "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/"); //Device10/Submodels/BaSys/1.0/provider");
		addMapping("line2int.line2/device10/aas/submodels/status",                       "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/"); //Device10/Submodels/BaSys/1.0/provider");
		addMapping("device10/aas/submodels/description",                    		     "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/"); //Device10/Submodels/BaSys/1.0/provider");
		addMapping("device10/aas/submodels/status",                             		 "http://localhost:8080/basys.sdk/Testsuite/GW/Manufacturing/line2int/"); //Device10/Submodels/BaSys/1.0/provider");
	
	}
}