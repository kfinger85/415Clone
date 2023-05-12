package edu.colostate.cs415.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.jetty.client.api.Request.QueuedListener;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs415.dto.QualificationDTO;

import java.util.*;

public class QualificationTest {

	private Qualification q;
	private Set<Worker> workers;

	/*
	 * @Before
	 * public void createWorkers(){
	 * workers = new HashSet<Worker>();
	 * Qualification q = new Qualification("");
	 * }
	 */

	@Test
	public void test() {
		assert (true);
	}

	@Test(expected = Exception.class)
	public void testConstructorOneBlankSpace(){
		Qualification q = new Qualification(" ");
	}

	@Test(expected = Exception.class)
	public void testConstructorMultipleBlankSpace(){
		Qualification q = new Qualification("                ");
	}

	@Test(expected = Exception.class)
	public void testConstructorBadString(){
		Qualification q = new Qualification("");
	}

	@Test(expected = Exception.class)
	public void testConstructorNull(){
		Qualification q = new Qualification(null);
	}

	@Test
	public void testObjectNotNull() {
		q = new Qualification("notNull");
		assertNotNull(q);
	}

	// testing 2 qualifications are equal
	@Test
	public void testEqualsWithEqualQualifications() {
		Qualification q = new Qualification("equal");
		Qualification y = new Qualification("equal");
		assertEquals(q.equals(y), y.equals(q));
	}

	// test non-nullity
	@Test
	public void testEqualsWithNull() {
		q = new Qualification("python");
		boolean result = q.equals(null);
		assertEquals(false, result);
	}

	// Testing if classes are equal with equal classes
	@Test
	public void testEqualsWithSameClassEquality() {
		Qualification q = new Qualification("equal");
		Qualification y = new Qualification("equal");
		assertEquals(q.getClass(), y.getClass());
		assertTrue(q.getClass().equals(y.getClass()));
		assertTrue(y.getClass().equals(q.getClass()));
	}

	// Test equals with one qualification and 1 null qualification.
	@Test(expected = IllegalArgumentException.class)
	public void testEqualsWithNullAndOneCorrectQualification() {
		Qualification no = new Qualification(null);
		Qualification y = new Qualification("python");
		//assertEquals(false, q.equals(y));
	}

	// Testing if classes are equal with non-equal classes
	@Test
	public void testEqualsWithDifferentClassEquality() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);
		Worker y = new Worker("BoB", qualificationSet, 10);
		//assertEquals(false, q.getClass() != y.getClass());
		assertEquals(false, q.getClass().equals(y.getClass()));
		assertEquals(false, y.getClass().equals(q.getClass()));
	}

	// testing with different qualifications and same class
	@Test
	public void testEqualsWithDifferentQualificationsButSameClass() {
		Qualification q = new Qualification("python");
		Qualification y = new Qualification("1234567");
		assertEquals(true, q.getClass().equals(y.getClass()));
		assertEquals(false, q.equals(y));
	}

	@Test
	public void testEqualsWithExactSameQualificationObject(){
		Qualification q = new Qualification("I am the exact same");

		assertTrue(q.equals(q));
	}
	
	
	@Test
	public void testEqualsWithAnythingToCoverAllBranches() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		Qualification y = new Qualification("1234567");
		qualificationSet.add(q);
		Worker z = new Worker("BoB", qualificationSet, 10);
		String snack = "snack";
		
		assertEquals(false, q.getClass().equals(snack.getClass()));
		assertEquals(false, q.equals(y));
		assertFalse(q.equals(z));
	}

	@Test
	public void test_equals_compare_Objects_same_name(){
		Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification qualification = new Qualification("same");
		qualifications.add(qualification);
		
		Worker worker = new Worker("same", qualifications, 100);
		Company company = new Company("same");
		Project project = new Project("same", qualifications, ProjectSize.SMALL);
		
		assertFalse(worker.equals(company));
		assertFalse(worker.equals(project));
		assertFalse(worker.equals(qualification));
		assertFalse(project.equals(qualification));
        assertFalse(project.equals(company));
		assertFalse(qualification.equals(company));
	}

	public void test_equals_to_strings_same_name(){
		Qualification q = new Qualification("python");
		String match = "python";
		assertFalse(q.equals(match));	
	}

	@Test
	public void test_equals_to_strings_different_name(){
		Qualification q = new Qualification("python");
		String match = "match";
		assertFalse(q.equals(match));	

	}

	// TODO: might need more hashcode tests. Not sure what else to do yet.
	@Test
	public void testHashCodeWithEqualQualfications() {
		Qualification q = new Qualification("hash");
		Qualification y = new Qualification("hash");
		assertEquals(q.toString().hashCode(), y.toString().hashCode());
	}

	//test hascode with different qualifications
	@Test
	public void testHashCodeWithDifferentQualfications() {
		Qualification q = new Qualification("python");
		Qualification y = new Qualification("React");
		assertEquals(false, q.toString().hashCode() == y.toString().hashCode());
	}

	// TODO: might need more toString tests. Not sure what else to do yet.
	@Test
	public void testToStringWithMultipleWords() {
		q = new Qualification("This is my qualification");
		String match = q.toString();
		assertEquals("This is my qualification", match);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testToStringEmptyString() {
		q = new Qualification("");
		String match = q.toString();
		assertEquals("", match);
	}

	@Test
	public void testToStringSingleWord() {
		q = new Qualification("one");
		String match = q.toString();
		assertEquals("one", match);
	}

	// Testing method correctly returns empty set
	@Test
	public void testGetWorkersReturnEmptySet() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Set<Worker> workers = new HashSet<Worker>();
		q = new Qualification("funny");

		q.getWorkers();
		assertEquals(true, q.getWorkers().size() == 0);
	}

	// Testing method correctly returns set with multiple workers
	@Test
	public void testGetWorkersAndAddWorkerCorrectlyGettingAndAddingWorkers() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Set<Worker> workers = new HashSet<Worker>();
		q = new Qualification("funny");
		qualificationSet.add(q);
		Worker A = new Worker("Joe", qualificationSet, 10);
		Worker B = new Worker("Jim", qualificationSet, 4000);
		Worker C = new Worker("Bob", qualificationSet, 100);

		q.addWorker(A);
		q.addWorker(B);
		q.addWorker(C);

		assertEquals(true, q.getWorkers().containsAll(Arrays.asList(A, B, C)));
	}

	// Testing addWorker() and getWorker() with 1 Worker
	@Test
	public void testAddWorkersAndGetWorkerAddingOneWorker() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Set<Worker> workers = new HashSet<Worker>();
		q = new Qualification("funny");
		qualificationSet.add(q);

		Worker A = new Worker("Jim Bob", qualificationSet, 10);

		assertEquals(false, A == null);
		assertEquals(false, q == null);
		assertEquals(false, qualificationSet == null);

		q.addWorker(A);
		assertEquals(true, q.getWorkers().contains(A));

	}

	@Test(expected = Exception.class)
	public void tesAddWorkerWithNull() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Worker A = new Worker("Joe", qualificationSet, 10);
		
		q.addWorker(A);
		q.addWorker(null);
	}
	
	@Test(expected = Exception.class)
	public void testRemoveWorkerWithNull() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Worker A = new Worker("Joe", qualificationSet, 10);

		q.removeWorker(null);
	}

	// test addWorker() with multiple additions
	@Test
	public void testAddWorkerWithMultipleAddition() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();

		Qualification q = new Qualification("experience");
		Qualification r = new Qualification("funny");
		Qualification s = new Qualification("C");

		qualificationSet.add(q);
		qualificationSet.add(r);
		qualificationSet.add(s);

		Worker A = new Worker("Joe", qualificationSet, 10);
		Worker B = new Worker("Jim", qualificationSet, 4000);
		Worker C = new Worker("Bob", qualificationSet, 0);

		q.addWorker(A);
		q.addWorker(B);
		q.addWorker(C);

		assertEquals(true, q.getWorkers().size() == 3);
		
        assertEquals(true, q.getWorkers().contains(A));
		assertEquals(true, q.getWorkers().contains(B));
		assertEquals(true, q.getWorkers().contains(C));
	}

	// successfully add one worker, check size, removeWorker, check size.
	@Test
	public void testRemoveWorkerRemovingOne() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		q = new Qualification("python");
		qualificationSet.add(q);

		Worker A = new Worker("Bob", qualificationSet, 10);

		q.addWorker(A);

		assertEquals(true, q.getWorkers().size() == 1);

		q.removeWorker(A);

		assertEquals(true, q.getWorkers().size() == 0);
	}

	// successfully remove multiple workers.
	@Test
	public void testRemoveWorkerSuccessfullyRemovingMultiple() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		q = new Qualification("python");
		qualificationSet.add(q);

		Worker A = new Worker("Bob_1", qualificationSet, 10);
		Worker B = new Worker("Bob_2", qualificationSet, 10);
		Worker C = new Worker("Bob_3", qualificationSet, 10);
		Worker D = new Worker("Bob_4", qualificationSet, 10);
		Worker E = new Worker("Bob_5", qualificationSet, 10);

		q.addWorker(A);
		q.addWorker(B);
		q.addWorker(C);
		q.addWorker(D);
		q.addWorker(E);

		assertTrue(q.getWorkers().size() == 5);

		q.removeWorker(A);
		q.removeWorker(B);
		q.removeWorker(C);

		assertTrue(q.getWorkers().size() == 2);
		assertTrue(q.getWorkers().contains(D));
		assertTrue(q.getWorkers().contains(E));
	}

	// testing not adding duplicates to set of Qualifications.
	@Test
	public void testNotAddingDuplicateQualificationsToSet(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		q = new Qualification("python");
		Qualification r = new Qualification("python");
		Qualification s = new Qualification("React");

		qualificationSet.add(q);
		qualificationSet.add(r);

		assertTrue(qualificationSet.size() == 1);

		qualificationSet.add(s);

		assertTrue(qualificationSet.size() == 2);
}

	// test that description match for DTO and Qualification
	@Test
	public void testDTOMatchDescriptions() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		q = new Qualification("python");
		qualificationSet.add(q);

		Worker A = new Worker("Bob_1", qualificationSet, 10);

		q.addWorker(A);

		QualificationDTO dto = q.toDTO();

		assertEquals("python", dto.getDescription());

	}

	// testing dto getWorkers with single matches
	@Test
	public void testDTOMatchWithSingleWorkers() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		q = new Qualification("python");
		qualificationSet.add(q);

		Worker A = new Worker("Bob_1", qualificationSet, 10);

		q.addWorker(A);

		String[] match = { A.getName() };
		QualificationDTO dto = q.toDTO();


		assertArrayEquals(match, dto.getWorkers());
	}

	// testing dto getWorkers with multiple match
	@Test
	public void testDTOMatchMultipleWorkers() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		q = new Qualification("python");
		qualificationSet.add(q);

		Worker A = new Worker("Bob_1", qualificationSet, 1);
		Worker B = new Worker("Bob_2", qualificationSet, 2);
		Worker C = new Worker("Bob_3", qualificationSet, 3);
		Worker D = new Worker("Bob_4", qualificationSet, 4);
		Worker E = new Worker("Bob_5", qualificationSet, 5);

		q.addWorker(A);
		q.addWorker(B);
		q.addWorker(C);
		q.addWorker(D);
		q.addWorker(E);

		String[] match = { A.getName(), B.getName(), C.getName(), D.getName(), E.getName() };
		QualificationDTO dto = q.toDTO();
		String[] res = dto.getWorkers();
		Arrays.sort(match);
		Arrays.sort(res);
		assertArrayEquals(match, dto.getWorkers());
	}
}