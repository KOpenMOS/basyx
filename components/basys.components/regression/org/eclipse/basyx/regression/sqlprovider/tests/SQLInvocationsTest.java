package org.eclipse.basyx.regression.sqlprovider.tests;

import java.util.Collection;
import java.util.LinkedList;
import org.eclipse.basyx.aas.backend.connector.http.HTTPConnectorProvider;
import org.eclipse.basyx.regression.support.directory.ComponentsTestsuiteDirectory;
import org.eclipse.basyx.vab.VABConnectionManager;
import org.eclipse.basyx.vab.proxy.VABElementProxy;
import org.junit.jupiter.api.Test;



/**
 * Test SQL invocations
 * 
 * @author kuhn
 *
 */
class SQLInvocationsTest {

	
	/**
	 * Store HTTP asset administration shell manager backend
	 */
	protected VABConnectionManager connManager = new VABConnectionManager(new ComponentsTestsuiteDirectory(), new HTTPConnectorProvider());

	
	/**
	 * Test basic queries
	 */
	@Test
	void test() throws Exception {

		// Connect to sub model "CfgFileTestAAS"
		VABElementProxy connSubModel = this.connManager.connectToVABElement("SQLTestSubmodel");

		
		// Get property value (1)
		Object value1 = connSubModel.invoke("/aas/submodels/SQLTestSubmodel/operations/sensorIDForName", "VS_0001");
		System.out.println("Value:"+value1);
		
		// Get property value (2)
		Object value2 = connSubModel.invoke("/aas/submodels/SQLTestSubmodel/operations/sensorIDForName", "VS_0002");
		System.out.println("Value:"+value2);

		
		// Call operation that inserts a value into the database
		// - Insert line into table
		connSubModel.invoke("/aas/submodels/SQLTestSubmodel/operations/addSensorID", "sensorname, sensorid", "'VS_0005', '321'");

		// Get property value (3)
		Object value3 = connSubModel.invoke("/aas/submodels/SQLTestSubmodel/operations/sensorIDForName", "VS_0005");
		System.out.println("Value:"+value3);
		
		
		// Delete property 'VS_0005'
		// - Collection that contains call values
		Collection<String> callValues4 = new LinkedList<>();
		callValues4.add("VS_0005");
		// - Delete sensor from table
		connSubModel.deleteElement("/aas/submodels/SQLTestSubmodel/properties/sensorNames", callValues4);

		// Get property value (4)
		Object value4 = connSubModel.invoke("/aas/submodels/SQLTestSubmodel/operations/sensorIDForName", "VS_0005");
		System.out.println("Value:"+value4);
	}
}

