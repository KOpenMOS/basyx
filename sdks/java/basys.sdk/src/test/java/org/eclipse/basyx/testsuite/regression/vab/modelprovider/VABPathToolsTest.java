package org.eclipse.basyx.testsuite.regression.vab.modelprovider;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.basyx.vab.modelprovider.VABPathTools;
import org.junit.Test;

/**
 * Tests the functionality of the VABPathTools utility class
 * 
 * @author espen
 *
 */
public class VABPathToolsTest {
	// test all cases of "empty" paths
	String[] empty = new String[] { "/", "" };

	// test all cases of /-placement for single elements
	String[] single = new String[] { "element", "/element", "element/", "/element/" };

	// test all cases of /-placement for multiple elements
	String[] pair = new String[] { "parent/child", "/parent/child", "parent/child/", "/parent/child/" };

	// Test strings
	String pathWithoutAddress = "a/b/c";
	String pathWithOneAddress = "http://AASServer//a/b/c";
	String pathWithTwoAddress = "basyx://127.0.0.1:6889//http://AASServer//a/b/c";
	String onlyAddress = "basyx://127.0.0.1:6889";
	String pathWithoutAddressMultislash = "a//b///c";

	@Test
	public void testStripSlashes() {
		assertEquals("test", VABPathTools.stripSlashes("/test"));
		assertEquals("test", VABPathTools.stripSlashes("//test"));
		assertEquals("test", VABPathTools.stripSlashes("test/"));
		assertEquals("test", VABPathTools.stripSlashes("/test//"));
		assertEquals("test", VABPathTools.stripSlashes("/test/"));
		assertEquals("test", VABPathTools.stripSlashes("//test//"));
	}

	/**
	 * Tests remove address
	 */
	@Test
	public void testRemoveAddress() {
		assertEquals(pathWithoutAddress, VABPathTools.removeFirstEndpoint(pathWithoutAddress));
		assertEquals(pathWithoutAddress, VABPathTools.removeFirstEndpoint(pathWithOneAddress));
		assertEquals("", VABPathTools.removeFirstEndpoint(onlyAddress));
		assertNull(VABPathTools.removeFirstEndpoint(null));
	}

	/**
	 * Tests get address
	 */
	@Test
	public void testGetAddress() {
		assertEquals("", VABPathTools.getFirstEndpoint(pathWithoutAddress));
		assertEquals("basyx://127.0.0.1:6889", VABPathTools.getFirstEndpoint(pathWithTwoAddress));
		assertEquals(onlyAddress, VABPathTools.getFirstEndpoint(onlyAddress));
		assertNull(VABPathTools.getFirstEndpoint(null));
	}

	/**
	 * For each element in the path, exactly one string element should be present in
	 * the resulting array
	 */
	@Test
	public void testSplitPath() {
		for (String test : empty) {
			assertEquals(0, VABPathTools.splitPath(test).length);
		}
		for (String test : single) {
			assertEquals(1, VABPathTools.splitPath(test).length);
			assertEquals("element", VABPathTools.splitPath(test)[0]);
		}
		for (String test : pair) {
			assertEquals(2, VABPathTools.splitPath(test).length);
			assertEquals("parent", VABPathTools.splitPath(test)[0]);
			assertEquals("child", VABPathTools.splitPath(test)[1]);
		}
		assertNull(VABPathTools.splitPath(null));

		// Assert that independent of number of slashs, splitting is always handled the
		// same
		assertArrayEquals(VABPathTools.splitPath(pathWithoutAddressMultislash), VABPathTools.splitPath(pathWithoutAddress));
	}

	/**
	 * Test concenating multiple paths
	 */
	@Test
	public void concatenatePaths() {
		for (String a : single) {
			assertEquals("element", VABPathTools.concatenatePaths(a));
			for (String b : pair) {
				assertEquals("element/parent/child", VABPathTools.concatenatePaths(a, b));
			}
		}
		assertEquals("", VABPathTools.concatenatePaths(""));
		assertNull(VABPathTools.concatenatePaths());
		assertNull(VABPathTools.concatenatePaths(null, ""));
		assertNull(VABPathTools.concatenatePaths("", null));
	}

	@Test
	public void testGetParentPath() {
		for (String test : empty) {
			assertEquals("", VABPathTools.getParentPath(test));
		}
		for (String test : single) {
			assertEquals("", VABPathTools.getParentPath(test));
		}
		for (String test : pair) {
			assertEquals("parent", VABPathTools.getParentPath(test));
		}
		assertNull(VABPathTools.getParentPath(null));
	}

	@Test
	public void testGetLastElement() {
		for (String test : empty) {
			assertEquals("", VABPathTools.getLastElement(test));
		}
		for (String test : single) {
			assertEquals("element", VABPathTools.getLastElement(test));
		}
		for (String test : pair) {
			assertEquals("child", VABPathTools.getLastElement(test));
		}
		assertNull(VABPathTools.getLastElement(null));
	}

	@Test
	public void testRemovePrefix() {
		for (String test : empty) {
			assertEquals("", VABPathTools.removePrefix(test, "/"));
		}
		for (String test : single) {
			assertTrue(VABPathTools.removePrefix(test, "/").startsWith("element"));
		}
		for (String test : pair) {
			assertTrue(VABPathTools.removePrefix(test, "/").startsWith("parent"));
		}
		assertNull(VABPathTools.removePrefix(null, "/"));
	}

	@Test
	public void testBuildPath() {
		assertEquals("", VABPathTools.buildPath(new String[] {}, 0));
		assertEquals("b", VABPathTools.buildPath(new String[] { "a", "b" }, 1));
		assertEquals("b/c", VABPathTools.buildPath(new String[] { "a", "b", "c" }, 1));
		assertEquals("a/b/c", VABPathTools.buildPath(new String[] { "a", "b", "c" }, 0));
		assertNull(VABPathTools.buildPath(null, 0));
	}

	@Test
	public void testAppend() {
		assertEquals("/parent/child", VABPathTools.append("/parent", "child"));
		assertEquals("/parent/child", VABPathTools.append("/parent/", "child"));
		assertEquals("/parent/x/child", VABPathTools.append("/parent/x", "child"));
		assertEquals("/parent/x/child", VABPathTools.append("/parent/x/", "child"));
		assertNull(VABPathTools.append(null, ""));
		assertNull(VABPathTools.append("", null));
	}

	@Test
	public void testIsOperationPath() {
		String[] positive = { "submodelElements/id/invoke", "submodelElements/id/invoke/",
				"operations/id/invoke", "operations/id/invoke/"};
		String[] negative = { "", "/submodelElementsX/", "/myoperations/", "/submodelElementsFake/",
				"/submodelElementsFake/operationX/",  "submodelElements/id/", "operations/id"};
		for (String test : positive) {
			assertTrue(test, VABPathTools.isOperationInvokationPath(test));
		}
		for (String test : negative) {
			assertFalse(test, VABPathTools.isOperationInvokationPath(test));
		}
		assertFalse(VABPathTools.isOperationInvokationPath(null));
	}
	
	@Test
	public void testStripInvokeFromPath() {
		assertEquals("id", VABPathTools.stripInvokeFromPath("id/invoke"));
		assertEquals("", VABPathTools.stripInvokeFromPath("invoke"));
		assertEquals("", VABPathTools.stripInvokeFromPath("/invoke"));
		assertEquals("id/value", VABPathTools.stripInvokeFromPath("id/value"));
		assertEquals("", VABPathTools.stripInvokeFromPath(""));
	}
}