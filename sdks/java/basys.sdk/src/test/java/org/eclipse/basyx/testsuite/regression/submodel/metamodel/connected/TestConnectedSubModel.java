package org.eclipse.basyx.testsuite.regression.submodel.metamodel.connected;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.dataelement.IDataElement;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.dataelement.property.ISingleProperty;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.operation.IOperation;
import org.eclipse.basyx.submodel.metamodel.connected.ConnectedSubModel;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.metamodel.map.reference.Key;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation.Operation;
import org.eclipse.basyx.submodel.restapi.SubModelProvider;
import org.eclipse.basyx.testsuite.regression.vab.manager.VABConnectionManagerStub;
import org.eclipse.basyx.vab.modelprovider.lambda.VABLambdaProvider;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests if a SubModel can be created and used correctly
 *
 * @author schnicke
 *
 */
public class TestConnectedSubModel {

	// String constants used in this test case
	private final static String OP = "add";
	private final static String PROP = "prop1";
	private final static String ID = "TestId";

	private final static Reference testSemanticIdRef = new Reference(new Key(KeyElements.CONCEPTDESCRIPTION, false, "testVal", IdentifierType.CUSTOM));

	ConnectedSubModel submodel;

	@Before
	public void build() {
		// Create a simple value property
		Property propertyMeta = new Property(100);
		propertyMeta.setIdShort(PROP);

		// Create an operation
		Operation op = new Operation((Function<Object[], Object> & Serializable) obj -> {
			return (int) obj[0] + (int) obj[1];
		});
		op.setIdShort(OP);

		// Create the SubModel using the created property and operation
		SubModel sm = new SubModel();
		sm.addSubModelElement(propertyMeta);
		sm.addSubModelElement(op);
		sm.setIdShort(ID);
		sm.setSemanticId(testSemanticIdRef);

		SubModelProvider provider = new SubModelProvider(new VABLambdaProvider(sm));

		// Create the ConnectedSubModel based on the manager
		submodel = new ConnectedSubModel(new VABConnectionManagerStub(provider).connectToVABElement(""));
	}

	/**
	 * Tests if a SubModel's id can be retrieved correctly
	 */
	@Test
	public void getIdTest() {
		assertEquals(ID, submodel.getIdShort());
	}

	/**
	 * Tests if a SubModel's properties can be used correctly
	 */
	@Test
	public void propertiesTest() throws Exception {
		// Retrieve all properties
		Map<String, IDataElement> props = submodel.getDataElements();

		// Check if number of properties is as expected
		assertEquals(1, props.size());

		// Check the value of the property
		ISingleProperty prop = (ISingleProperty) props.get(PROP);
		assertEquals(100, prop.get());
	}

	/**
	 * Tests if a SubModel's operations can be used correctly
	 * 
	 * @throws Exception
	 */
	@Test
	public void operationsTest() throws Exception {
		// Retrieve all operations
		Map<String, IOperation> ops = submodel.getOperations();

		// Check if number of operations is as expected
		assertEquals(1, ops.size());

		// Check the operation itself
		IOperation op = ops.get(OP);
		assertEquals(5, op.invoke(2, 3));
	}
	
	@Test
	public void saveAndLoadPropertyTest() throws Exception {
		
		// Construct test data
		Property property = new Property();
		property.setIdShort("test1");
		property.set("test2");
		
		// Save it
		submodel.addSubModelElement(property);
		
		// Load it
		Map<String, IDataElement> map = submodel.getDataElements();
		
		// Check if it loaded correctly
		assertNotNull(map);
		ISingleProperty loadedProp = (ISingleProperty) map.get(property.getIdShort());
		assertNotNull(loadedProp);
		assertEquals(property.get(), loadedProp.get());
	}
	
	@Test
	public void saveAndLoadOperationTest() throws Exception {
		
		// Construct test data
		Operation operation = new Operation();
		operation.setIdShort("test1");
		
		// Save it
		submodel.addSubModelElement(operation);
		
		// Load it
		Map<String, IOperation> map = submodel.getOperations();
		
		// Check if it loaded correctly
		assertNotNull(map);
		IOperation loadedOp = map.get(operation.getIdShort());
		assertNotNull(loadedOp);
		assertEquals(operation.getIdShort(), loadedOp.getIdShort());
	}

	/**
	 * Tests if the semantic Id can be retrieved correctly
	 */
	@Test
	public void semanticIdRetrievalTest() {
		IReference ref = submodel.getSemanticId();
		assertEquals(testSemanticIdRef, ref);
	}
}