package org.eclipse.basyx.testsuite.regression.aas.backend.connected.facade;

import static org.junit.Assert.assertEquals;

import org.eclipse.basyx.aas.backend.connected.facades.ConnectedReferableFacade;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.qualifier.Referable;
import org.eclipse.basyx.testsuite.support.vab.stub.VABConnectionManagerStub;
import org.eclipse.basyx.vab.core.VABConnectionManager;
import org.eclipse.basyx.vab.provider.hashmap.VABHashmapProvider;
import org.junit.Before;
import org.junit.Test;

public class TestConnectedReferableFacade {
	
	Referable local;
	ConnectedReferableFacade remote;
	@Before
	public void build() {
		local = new Referable("idShort", "category", "description");
		  
		// Create a dummy connection manager containing the created SubModel map
		VABConnectionManager manager = new VABConnectionManagerStub(new VABHashmapProvider(local));

		// Create the ConnectedSubModel based on the manager stub
		remote = new ConnectedReferableFacade("", manager.connectToVABElement(""));
	}
	@Test
	public void test() {

		assertEquals(local.getCategory(), remote.getCategory());
		assertEquals(local.getIdshort(), remote.getIdshort());
		assertEquals(local.getDescription(), remote.getDescription());
		//TODO: getParent() has to be tested in future
		
		
	}

}