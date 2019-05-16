package org.eclipse.basyx.examples.scenarios.device;

import static org.junit.Assert.assertTrue;

import org.eclipse.basyx.aas.backend.connector.http.HTTPConnectorProvider;
import org.eclipse.basyx.examples.contexts.BaSyxExamplesContext_1MemoryAASServer_1SQLDirectory;
import org.eclipse.basyx.examples.deployment.BaSyxDeployment;
import org.eclipse.basyx.examples.examplescenario.BaSyxExampleScenario;
import org.eclipse.basyx.examples.mockup.application.ReceiveDeviceStatusApplication;
import org.eclipse.basyx.examples.mockup.device.SimpleTCPDeviceMockup;
import org.eclipse.basyx.examples.mockup.devicemanager.BaSyxTCPManufacturingDeviceManager;
import org.eclipse.basyx.examples.support.directory.ExamplesPreconfiguredDirectory;
import org.eclipse.basyx.vab.core.VABConnectionManager;
import org.junit.ClassRule;
import org.junit.Test;



/**
 * Example that illustrates a simple device (no control component), a manager, an application, Asset Administration Shells, and sub models
 * 
 * This example instantiates a device (mockup), device manager, and an application
 * - Device manager registers device AAS and sub models with directory server. 
 *   - It also provides a TCP server that receives the device status and forwards this to device status sub model 
 * - Device uses a simple, string based TCP connection to the device manager
 *   - It communicates its status and an invocation counter
 * - Application connects to device AAS and device status sub model and queries device status
 * 
 * @author kuhn
 *
 */
public class RunSimpleTCPDevice extends BaSyxExampleScenario {

	
	/**
	 * VAB connection manager backend
	 */
	protected VABConnectionManager connManager = new VABConnectionManager(new ExamplesPreconfiguredDirectory(), new HTTPConnectorProvider());


	/**
	 * Instantiate and start context elements for this example. BaSyxDeployment contexts instantiate all
	 * components on the IP address of the host. Therefore, all components use the same IP address. 
	 */
	@ClassRule
	public static BaSyxDeployment context = new BaSyxDeployment(
				// Simulated servlets
				// - BaSys topology with one AAS Server and one SQL directory
				new BaSyxExamplesContext_1MemoryAASServer_1SQLDirectory(),
				
				// Simulated runnables
				// - Manufacturing device manager, e.g. deployed to additonal device
				new BaSyxTCPManufacturingDeviceManager(9998).setName("DeviceManager"),
				
				// Simulated mockups
				new SimpleTCPDeviceMockup(9998).setName("Device"),
				new ReceiveDeviceStatusApplication().setName("Application")
			);



	/**
	 * Test sequence: 
	 * - Initialize device --> Device status update (ready)
	 * - Read device status from AAS
	 * - Invoke device service --> Device status update (running)
	 * - Read device status from AAS
	 * - Device service completes execution --> Device status update (complete)
	 * - Read device status from AAS
	 * - Device completes reset --> Device status update (idle)
	 * - Read device status from AAS
	 */
	@Test 
	public void test() throws Exception {
		// Device updates status to ready
		((SimpleTCPDeviceMockup) context.getRunnable("Device")).deviceInitialized();
System.out.println("XXXXXX-1");
		
		// Application waits for status change
		waitfor( () -> ((ReceiveDeviceStatusApplication) context.getRunnable("Application")).getDeviceStatus().equals("IDLE") );
		assertTrue( ((ReceiveDeviceStatusApplication) context.getRunnable("Application")).getDeviceStatus().equals("IDLE") );
System.out.println("XXXXXX-2");

		// Application checks invocation counter
		assertTrue( ((ReceiveDeviceStatusApplication) context.getRunnable("Application")).getDeviceInvocationCounter() == 0 );		
System.out.println("XXXXXX-3");

		// Device updates status to running
		// - The device indicates that a process step is running
		((SimpleTCPDeviceMockup) context.getRunnable("Device")).serviceRunning();
System.out.println("XXXXXX-4");
		
		// Application waits for status change
		waitfor( () -> ((ReceiveDeviceStatusApplication) context.getRunnable("Application")).getDeviceStatus().equals("EXECUTE") );
		assertTrue( ((ReceiveDeviceStatusApplication) context.getRunnable("Application")).getDeviceStatus().equals("EXECUTE") );
System.out.println("XXXXXX-5");

		// Device updates status to complete
		// - The device indicates that process step did finish
		((SimpleTCPDeviceMockup) context.getRunnable("Device")).serviceCompleted();
System.out.println("XXXXXX-6");
		
		// Application waits for status change
		waitfor( () -> ((ReceiveDeviceStatusApplication) context.getRunnable("Application")).getDeviceStatus().equals("COMPLETE") );
		assertTrue( ((ReceiveDeviceStatusApplication) context.getRunnable("Application")).getDeviceStatus().equals("COMPLETE") );
System.out.println("XXXXXX-7");

		// Device updates status to ready again, next process step may be invoked
		((SimpleTCPDeviceMockup) context.getRunnable("Device")).resetCompleted();
System.out.println("XXXXXX-8");
		
		// Application waits for status change
		waitfor( () -> ((ReceiveDeviceStatusApplication) context.getRunnable("Application")).getDeviceStatus().equals("IDLE") );
		assertTrue( ((ReceiveDeviceStatusApplication) context.getRunnable("Application")).getDeviceStatus().equals("IDLE") );
System.out.println("XXXXXX-9");

		// Application checks invocation counter
		assertTrue( ((ReceiveDeviceStatusApplication) context.getRunnable("Application")).getDeviceInvocationCounter() == 1 );
System.out.println("XXXXXX-10");
	}
}
