package org.eclipse.basyx.testsuite.regression.aas.metamodel.map.parts;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.basyx.aas.metamodel.map.parts.ConceptDictionary;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.parts.IConceptDescription;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.eclipse.basyx.submodel.metamodel.map.parts.ConceptDescription;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests constructor, setter and getter of {@link ConceptDescription} for their
 * correctness
 * 
 * @author haque
 *
 */
public class TestConceptDictionary {
	private static final Reference REFERENCE = new Reference(new Identifier(IdentifierType.CUSTOM, "testValue"), KeyElements.ASSET, false);
	private static final Reference REFERENCE2 = new Reference(new Identifier(IdentifierType.IRDI, "testNewId"), KeyElements.ASSET, true);
	
	private ConceptDictionary dictionary;
	
	@Before
	public void buildConceptDictionary() {
		dictionary = new ConceptDictionary(Collections.singleton(REFERENCE));
	}
	
	@Test
	public void testConstructor() {
		assertEquals(Collections.singleton(REFERENCE), dictionary.getConceptDescriptionReferences());
		assertEquals(new ArrayList<IConceptDescription>(), dictionary.getConceptDescriptions());
	}
	
	@Test
	public void testSetIdShort() {
		String newIdString = "newId";
		dictionary.setIdShort(newIdString);
		assertEquals(newIdString, dictionary.getIdShort());
	}
	
	@Test
	public void testSetCategory() {
		String newCategoryString = "newCategory";
		dictionary.setCategory(newCategoryString);
		assertEquals(newCategoryString, dictionary.getCategory());
	}
	
	@Test
	public void testSetDescription() {
		LangStrings newDescriptionString = new LangStrings("DE", "newTest");
		dictionary.setDescription(newDescriptionString);
		assertEquals(newDescriptionString, dictionary.getDescription());
	}
	
	@Test
	public void testSetParent() {
		dictionary.setParent(REFERENCE2);
		assertEquals(REFERENCE2, dictionary.getParent());
	}
	
	@Test
	public void testSetConceptDescriptionReferences() {
		Collection<IReference> references = new HashSet<IReference>();
		references.add(REFERENCE);
		references.add(REFERENCE2);
		dictionary.setConceptDescriptionReferences(references);
		assertEquals(references, dictionary.getConceptDescriptionReferences());
	}
	
	@Test
	public void testSetConceptDescriptions() {
		ConceptDescription description1 = new ConceptDescription();
		description1.setCategory("cat1");
		ConceptDescription description2 = new ConceptDescription();
		description2.setCategory("cat2");
		Collection<IConceptDescription> descriptions = new ArrayList<IConceptDescription>();
		descriptions.add(description1);
		descriptions.add(description2);
		dictionary.setConceptDescriptions(descriptions);
		assertEquals(descriptions, dictionary.getConceptDescriptions());
	} 
	
}
