package org.eclipse.basyx.regression.support.processengine.submodel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.eclipse.basyx.aas.impl.metamodel.factory.MetaModelElementFactory;
import org.eclipse.basyx.aas.impl.metamodel.hashmap.aas.SubModel;
import org.eclipse.basyx.aas.impl.metamodel.hashmap.aas.submodelelement.operation.Operation;
import org.eclipse.basyx.aas.impl.metamodel.hashmap.aas.submodelelement.property.SingleProperty;
import org.eclipse.basyx.regression.support.processengine.stubs.ICoilcar;


public class DeviceSubmodelFactory {
	public SubModel create(String id, ICoilcar coilcar) {
		MetaModelElementFactory factory = new MetaModelElementFactory();
		// create a single value property
		SingleProperty property1 = new SingleProperty(0);
		property1.setId("currentPosition");
		
		SingleProperty property2 = new SingleProperty(0);
		property2.setId("lifterPosition");
		
		SingleProperty property3 = new SingleProperty(false);
		property3.setId("physicalSpeed");
		
		// create 2 opertations
		Operation op1 = factory.createOperation(new Operation(), (Function<Object[], Object>) (obj) -> {
			return coilcar.liftTo((int)obj[0]);
		});
		op1.setId("liftTo");
		
		Operation op2 = factory.createOperation(new Operation(), (Function<Object[], Object> )(obj)->{
			coilcar.moveTo((int)obj[0]);
			return true;
		});
		op2.setId("moveTo");
		
		// create a list for defined operations
		List<Operation> oplist = new ArrayList<>();
		oplist.add(op1);
		oplist.add(op2);
		// create a list for defined properties
		List<SingleProperty> propList = new ArrayList<>();
		propList.add(property1);
		propList.add(property2);
		propList.add(property3);
		// create the sub-model and add the property and operations to the sub-model
		SubModel sm = factory.create(new SubModel(),  propList, oplist);
		sm.setId(id);
		return sm;
	}
}
