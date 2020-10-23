package org.eclipse.basyx.regression.aasx;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.eclipse.basyx.components.aasx.SubmodelFileEndpointLoader;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.ISubmodelElement;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.dataelement.IFile;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.SubmodelElementCollection;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.File;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the SubmodelFileEndpointLoader 
 * 
 * @author espen
 *
 */
public class TestFileEndpointLoader {
	private SubModel submodel;
	private final String relativePath = "/file/root/text.txt";
	private final String absolutePath = "http://localhost:1234/file/root/text.txt";
	private final String relativeTargetPath = "http://localhost:4321/new/file/root/text.txt";
	
	@Before
	public void setup() {
		File fRel = new File(relativePath, "application/json");
		fRel.setIdShort("fRel");
		File fAbs = new File(absolutePath, "application/json");
		fAbs.setIdShort("fAbs");
		SubmodelElementCollection col = new SubmodelElementCollection();
		col.setIdShort("fileCollection");
		File fCol = new File(relativePath, "application/json");
		fCol.setIdShort("fInside");
		col.addElement(fCol);
		submodel = new SubModel();
		submodel.setIdShort("FileTestSubmodel");
		submodel.addSubModelElement(fRel);
		submodel.addSubModelElement(fAbs);
		submodel.addSubModelElement(col);
	}
	
	/**
	 * Tests setting a static string endpoint (relative to the given path in the existing value)
	 */
	@Test
	public void testRelativePaths1() {
		SubmodelFileEndpointLoader.setRelativeFileEndpoints(submodel, "http://localhost:4321/new");
		checkRelativeTargetPaths();
	}

	/**
	 * Tests setting a endpoint via host, port and root path (relative to the given path in the existing value)
	 */
	@Test
	public void testRelativePaths2() {
		SubmodelFileEndpointLoader.setRelativeFileEndpoints(submodel, "localhost", 4321, "/new");
		checkRelativeTargetPaths();
	}

	/**
	 * Tests elements inside of collections
	 */
	@Test
	public void testCollections() {
		SubmodelFileEndpointLoader.setRelativeFileEndpoints(submodel, "localhost", 4321, "/new");

		Map<String, ISubmodelElement> elements = submodel.getSubmodelElements();
		SubmodelElementCollection col = (SubmodelElementCollection) elements.get("fileCollection");
		IFile file = (IFile) col.getSubmodelElements().get("fInside");
		assertEquals(relativeTargetPath, file.getValue());
	}

	private void checkRelativeTargetPaths() {
		Map<String, ISubmodelElement> elements = submodel.getSubmodelElements();

		String fromRelative = ((IFile) elements.get("fRel")).getValue();
		assertEquals(relativeTargetPath, fromRelative);

		String fromAbsolute = ((IFile) elements.get("fAbs")).getValue();
		assertEquals(relativeTargetPath, fromAbsolute);
	}
}