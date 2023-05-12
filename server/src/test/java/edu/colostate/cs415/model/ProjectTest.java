package edu.colostate.cs415.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

import static org.junit.Assert.assertNotEquals;

import edu.colostate.cs415.dto.ProjectDTO;
import edu.colostate.cs415.dto.WorkerDTO;
import org.junit.Test;
import org.junit.Before;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;


public class ProjectTest {

	private Set<Qualification> projectQualifications;
	private Qualification qualificationOne;
	private Worker WorkerOne;
	
	@Before
	public void createProjectObjects(){
		projectQualifications = new HashSet<Qualification>();
    	qualificationOne = new Qualification("python");
		projectQualifications.add(qualificationOne);
		WorkerOne = new Worker("Bob_1", projectQualifications, 10);
	}

	
	@Test
	public void test() {
		assert (true);
	}
	
	@Test
	public void testProjectNotNull(){
		Qualification q = new Qualification("equal");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		Project test = new Project("test",qualifications,ProjectSize.SMALL);
		assertNotNull(test);
	}

	@Test
	public void testProjectNameAssigned(){
		Qualification q = new Qualification("equal");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		Project test = new Project("test",qualifications,ProjectSize.SMALL);
		assertEquals(test.getName(),"test");
	}

	 @Test
	 public void testToStringWithOneWorker(){
		Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
	    qualifications.add(q);
		Worker w = new Worker("Bob_1", qualifications, 10);
		Project test = new Project("project_test",qualifications,ProjectSize.SMALL);

		test.addWorker(w);
		
		String compare = "project_test:1:PLANNED";
		
		assertEquals(compare, test.toString());
	 }          
	
	 @Test
	 public void testToStringWithMultipleWorkersAndSettingDifferentStatus(){
		Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualifications.add(q);

		Worker w = new Worker("Bob_1", qualifications, 10);
		Worker x = new Worker("Bob_2", qualifications, 100);
		Worker y = new Worker("Bob_3", qualifications, 1000);
		Worker z = new Worker("Bob_4", qualifications, 10000);
		
		Project test = new Project("Project Test",qualifications,ProjectSize.MEDIUM);
		test.setStatus(ProjectStatus.ACTIVE);

		test.addWorker(w);
		test.addWorker(x);
		test.addWorker(y);
		
		String compare = "Project Test:3:ACTIVE";
		assertEquals(compare, test.toString());

		test.addWorker(z);

		test.setStatus(ProjectStatus.FINISHED);
		String compareFinished = "Project Test:4:FINISHED";
		assertEquals(compareFinished, test.toString());
	 
		test.removeWorker(w);

		test.setStatus(ProjectStatus.SUSPENDED);
		String compareSuspended = "Project Test:3:SUSPENDED";
		assertEquals(compareSuspended, test.toString());

		test.setStatus(ProjectStatus.FINISHED);
		test.removeAllWorkers();
		String compareNoWorkers = "Project Test:0:FINISHED";
		assertEquals(compareNoWorkers, test.toString());

	}       
   
	// testing getters: Name, Size, Status. Setters: setStatus
	@Test
	public void testGettersNameSizeStatus(){
		Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualifications.add(q);
		Project test = new Project("project_test",qualifications,ProjectSize.SMALL);
		assertEquals("project_test", test.getName());
		assertEquals(1, test.getSize().getValue());
		assertEquals(ProjectStatus.PLANNED, test.getStatus());
		
		test.setStatus(ProjectStatus.ACTIVE);
		assertEquals(ProjectStatus.ACTIVE, test.getStatus());
	}
	@Test
	public void testEquals(){
		Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification q = new Qualification("hello");
		qualifications.add(q);
		Project t1 = new Project("project_test",qualifications,ProjectSize.SMALL);
		Project t2 = new Project("project_test",qualifications,ProjectSize.SMALL);
		Project t3 = new Project("hello", qualifications,ProjectSize.BIG);
		assertTrue(t1.equals(t2));
		assertTrue(t2.equals(t1));
		assertFalse(t3.equals(t2));
	}

	@Test
	public void test_equals_compare_to_strings(){
		Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		qualifications.add(q);
		
		Project p = new Project("project",qualifications,ProjectSize.SMALL);
		
		String matchSame = "poject";
		String matchDifferent = "other project";
		
		assertFalse(p.equals(matchSame));
		assertFalse(p.equals(matchDifferent));
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

	@Test
	public void testEqualForCompleteCoverage(){
		Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification q = new Qualification("hello");
		qualifications.add(q);
		Project t1 = new Project("project_test",qualifications,ProjectSize.SMALL);
		Project t2 = new Project("project_test",qualifications,ProjectSize.SMALL);
		Worker w = new Worker("Bob_1", qualifications, 10);
		
		assertFalse(w.equals(t1));
		assertTrue(t1.getClass().equals(t2.getClass()));
		assertTrue(t2.getClass().equals(t1.getClass()));
		assertFalse(t1.equals(null));
		assertFalse(t1.getClass().equals(w.getClass()));
		assertFalse(t1.equals(w));
 	}

	@Test
	public void testHashCodeWithEqualName() {
		Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualifications.add(q);
		Project t1 = new Project("project_same",qualifications,ProjectSize.SMALL);
		Project t2 = new Project("project_same",qualifications,ProjectSize.SMALL);
		String match = t1.getName();
		String matchSame = t2.getName();
		assertEquals(match.hashCode(), matchSame.hashCode());
	}

	@Test
	public void testHashCodeWithDifferentName() {
		Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualifications.add(q);
		Project t1 = new Project("project_test",qualifications,ProjectSize.SMALL);
		Project t2 = new Project("project_different",qualifications,ProjectSize.SMALL);
		String match = t1.getName();
		String matchDifferent = t2.getName();
		assertEquals(false, match.hashCode() == matchDifferent.hashCode());
	}
	@Test
	public void testHashCodeWithDifferentName2() {
		Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualifications.add(q);
		Project t1 = new Project("project_test",qualifications,ProjectSize.SMALL);
		Project t2 = new Project("project_different",qualifications,ProjectSize.SMALL);
		assertEquals(false, t1.hashCode() == t2.hashCode());
	}


	// successfully add and remove 1 worker.
	@Test
	public void testRemoveWorkerRemovingAndAddingOneWorker() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Worker A = new Worker("Bob_1", qualificationSet, 10);
		Project t1 = new Project("project_test",qualificationSet,ProjectSize.SMALL);

		t1.addWorker(A);
 		assertEquals(true, t1.getWorkers().size() == 1);

		t1.removeWorker(A);
		assertEquals(true, t1.getWorkers().size() == 0);
	}

	// successfully add and remove multiple workers.
	@Test
	public void testRemoveWorkerRemoveandAddMultipleWorkers() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		Qualification y = new Qualification("React");
		qualificationSet.add(q);
		qualificationSet.add(y);

		Worker A = new Worker("Bob_1", qualificationSet, 10);
		Worker B = new Worker("Bob_2", qualificationSet, 100);
		Worker C = new Worker("Bob_3", qualificationSet, 1000);
		Worker D = new Worker("Bob_4", qualificationSet, 10000);
		Project t1 = new Project("project_test",qualificationSet,ProjectSize.SMALL);

		t1.addWorker(A);
 		assertTrue(t1.getWorkers().size() == 1);
		t1.addWorker(B);
		t1.addWorker(C);
		t1.addWorker(D);

		assertTrue(t1.getWorkers().containsAll(Arrays.asList(A, B, C, D)));
	    assertTrue(t1.getWorkers().size() == 4);

		t1.removeWorker(A);
		assertTrue(t1.getWorkers().size() == 3);
		
		t1.removeWorker(B);
		t1.removeWorker(C);
		t1.removeWorker(D);
	   
		assertTrue(t1.getWorkers().size() == 0);
	}

	@Test(expected = Exception.class)
	public void testAddWorker_Null() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Project t1 = new Project("project_test",qualificationSet,ProjectSize.SMALL);
		Worker A = new Worker("Joe", qualificationSet, 10);
		
		t1.addWorker(A);
		t1.addWorker(null);
	}

	@Test(expected = Exception.class)
	public void testAddWorker_Null_again() {
		Project t1 = new Project("project_test",projectQualifications,ProjectSize.SMALL);
		t1.addWorker(null);
	}

	@Test(expected = Exception.class)
	public void testRemoveWorkerWithNull() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Project t1 = new Project("project_test",qualificationSet,ProjectSize.SMALL);
		Worker A = new Worker("Joe", qualificationSet, 10);

		t1.removeWorker(null);
	}

	@Test(expected = Exception.class)
	public void testRemoveWorker_Null_again() {
		Project t1 = new Project("project_test",projectQualifications,ProjectSize.SMALL);
		t1.removeWorker(null);
	}

	// test getWorkers with empty set correctly return true
	@Test
	public void testGetWorkersReturnEmptySet() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Project t1 = new Project("project_test",qualificationSet,ProjectSize.SMALL);

 		assertTrue(t1.getWorkers().size() == 0);
		assertTrue(t1.getWorkers().isEmpty());
	}

	// getWorkers Correctly removes multiple workers
	@Test
	public void testGetWorkersCorrectlyRemovesAllWorkers() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Project t1 = new Project("project_test",qualificationSet,ProjectSize.SMALL);

		Worker A = new Worker("Bob_1", qualificationSet, 10);
		Worker B = new Worker("Bob_2", qualificationSet, 10);
		Worker C = new Worker("Bob_3", qualificationSet, 10);
		Worker D = new Worker("Bob_4", qualificationSet, 10);
		Worker E = new Worker("Bob_5", qualificationSet, 10);

		t1.addWorker(A);
		t1.addWorker(B);
		t1.addWorker(C);
		t1.addWorker(D);
		t1.addWorker(E);

		assertTrue(t1.getWorkers().size() == 5);

		t1.removeAllWorkers();
	
		assertTrue(t1.getWorkers().isEmpty());		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorEmptyQualifications() {
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		new Worker("Jack", qualifications, 100);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullQualifications() {
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		new Project("Jack", null, ProjectSize.MEDIUM);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullName() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Project t1 = new Project(null,qualificationSet,ProjectSize.SMALL);

	}
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorEmptyName() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Project t1 = new Project("",qualificationSet,ProjectSize.SMALL);

	}
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorSpacesName() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Project t1 = new Project("       ",qualificationSet,ProjectSize.SMALL);

	}
	@Test
	public void testGetQualificationsOne(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Project t1 = new Project("test",qualificationSet,ProjectSize.SMALL);
		assertTrue(t1.getRequiredQualifications().contains(q));
	}
 
	
	/* 
	I don't think we need this test beacuse of hashset. and the 'assumptions' saying
	all name will be unique
	@Test
	public void testAddQualificationTwice() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Qualification python = new Qualification("python");
		Project t1 = new Project("test",qualificationSet,ProjectSize.SMALL);
		t1.addQualification(python);
		
		assertTrue(qualificationSet.contains(q));
	}
	*/
    

	@Test
	public void testAddQualification() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		Qualification q2 = new Qualification("C");
		qualificationSet.add(q);

		Project t1 = new Project("test",qualificationSet,ProjectSize.SMALL);
		t1.addQualification(q2);
		assertTrue(t1.getRequiredQualifications().contains(q2));
	}

	@Test(expected = Exception.class)
	public void testAddQualification_null() {
		Project t1 = new Project("project",projectQualifications,ProjectSize.SMALL);
		t1.addQualification(null);
	}

	@Test
	public void testMissingQualificationsNoWorkers(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Project t1 = new Project("test",qualificationSet,ProjectSize.SMALL);
		assertTrue(t1.getMissingQualifications().contains(q));
	}

	@Test
	public void testMissingQualificationsWorkerWithQual(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);
		Project t1 = new Project("test",qualificationSet,ProjectSize.SMALL);
		Worker A = new Worker("Bob_1", qualificationSet, 10);
		t1.addWorker(A);
		assertEquals(false, t1.getMissingQualifications().contains(q));
	}
	@Test
	public void testMissingQualificationsWorkerWithoutQual(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);
		Set<Qualification> qualificationSet2 = new HashSet<Qualification>();
		Qualification q2 = new Qualification("C");
		qualificationSet2.add(q2);

		Project t1 = new Project("test",qualificationSet,ProjectSize.SMALL);
		Worker A = new Worker("Bob_1", qualificationSet2, 10);
		t1.addWorker(A);
		assertTrue(t1.getMissingQualifications().contains(q));
	}

	@Test
	public void testMissingQualificationsWorkersWithQual(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);
		Set<Qualification> qualificationSet2 = new HashSet<Qualification>();
		Qualification q2 = new Qualification("C");
		qualificationSet2.add(q2);
		qualificationSet.add(q2);
		Project t1 = new Project("test",qualificationSet,ProjectSize.SMALL);
		Worker A = new Worker("Bob_1", qualificationSet2, 10);
		Worker B = new Worker("Bob_2", qualificationSet, 10);
		t1.addWorker(A);
		t1.addWorker(B);
		assertEquals(false,t1.getMissingQualifications().contains(q));
		assertEquals(false,t1.getMissingQualifications().contains(q2));
	}

	@Test
	public void testMissingQualificationsWorkersWithoutQual(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		Set<Qualification> qualificationSet2 = new HashSet<Qualification>();
		Qualification q2 = new Qualification("C");
		Qualification q3 = new Qualification("C++");
		qualificationSet2.add(q2);
		qualificationSet2.add(q);
		qualificationSet.add(q);
		qualificationSet.add(q2);
		qualificationSet.add(q3);
		Project t1 = new Project("test",qualificationSet,ProjectSize.SMALL);
		Worker A = new Worker("Bob_1", qualificationSet2, 10);
		Worker B = new Worker("Bob_2", qualificationSet2, 10);
		t1.addWorker(A);
		t1.addWorker(B);
		assertTrue(t1.getMissingQualifications().contains(q3));
	}
		
	
	@Test
	public void testIsHelpfulNotHelpful(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);
		Project t1 = new Project("project_test",qualificationSet,ProjectSize.SMALL);

		Worker A = new Worker("Bob_1", qualificationSet, 10);
		Worker B = new Worker("Bob_2", qualificationSet, 10);
		t1.addWorker(B);
		assertFalse(t1.isHelpful(A));
	}

	@Test
	public void testIsHelpfulNull(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);
		Project t1 = new Project("project_test",qualificationSet,ProjectSize.SMALL);
		assertFalse(t1.isHelpful(null));
	}

	@Test
	public void testIsHelpfulHelpful(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);
		Project t1 = new Project("project_test",qualificationSet,ProjectSize.SMALL);

		Worker A = new Worker("Bob_1", qualificationSet, 10);
		assertTrue(t1.isHelpful(A));
	}

	@Test
	public void testIsHelpfulManyQuals(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		Qualification q2 = new Qualification("c");
		Qualification q3 = new Qualification("c++");
		qualificationSet.add(q);
		qualificationSet.add(q2);
		qualificationSet.add(q3);
		Set<Qualification> qualificationSet2 = new HashSet<Qualification>();
		qualificationSet2.add(q2);
		Project t1 = new Project("project_test",qualificationSet,ProjectSize.SMALL);

		Worker A = new Worker("Bob_1", qualificationSet2, 10);
		Set<Qualification> qualificationSet3 = new HashSet<Qualification>();
		qualificationSet3.add(q);
		qualificationSet3.add(q3);
		Worker B = new Worker("Bob_2", qualificationSet3, 10);
		t1.addWorker(B);
		assertTrue(t1.isHelpful(A));
	}
	@Test
	public void testIsHelpfulManyQualsWorker(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		Qualification q2 = new Qualification("c");
		Qualification q3 = new Qualification("c++");
		qualificationSet.add(q);
		qualificationSet.add(q2);
		qualificationSet.add(q3);
		Set<Qualification> qualificationSet2 = new HashSet<Qualification>();
		qualificationSet2.add(q2);
		Project t1 = new Project("project_test",qualificationSet2,ProjectSize.SMALL);
		Worker A = new Worker("Bob_1", qualificationSet, 10);
		Set<Qualification> qualificationSet3 = new HashSet<Qualification>();
		qualificationSet3.add(q);
		qualificationSet3.add(q3);
		Worker B = new Worker("Bob_2", qualificationSet3, 10);
		t1.addWorker(B);
		assertTrue(t1.isHelpful(A));
	}

@Test
	public void testIsHelpfulManyQualsWorkerUnhelpful(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		Qualification q2 = new Qualification("c");
		Qualification q3 = new Qualification("c++");
		qualificationSet.add(q);
		qualificationSet.add(q2);
		qualificationSet.add(q3);
		Set<Qualification> qualificationSet2 = new HashSet<Qualification>();
		Qualification q4 = new Qualification("JAVA");
		qualificationSet2.add(q4);
		Project t1 = new Project("project_test",qualificationSet2,ProjectSize.SMALL);
		Worker A = new Worker("Bob_1", qualificationSet, 10);
		Worker B = new Worker("Bob_2", qualificationSet2, 10);
		t1.addWorker(B);
		assertFalse(t1.isHelpful(A));
	}
	@Test
	public void testProjectToDTO() {
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		final Set<Qualification> projectQualifications = new HashSet<Qualification>();
		Qualification singing = new Qualification("singing");
		Qualification guitar = new Qualification("guitar");
		Qualification missingQualification = new Qualification("missing");
		qualifications.add(singing);
		qualifications.add(guitar);
		final Worker w1 = new Worker("13th Way", qualifications, 200000);
		final Worker w2 = new Worker("14th Way", qualifications, 200000);
		final Worker w3 = new Worker("15th Way", qualifications, 200000);
		projectQualifications.add(missingQualification);
		projectQualifications.add(singing);
		projectQualifications.add(guitar);
		Project album1 = new Project("1", projectQualifications, ProjectSize.BIG);
		album1.addWorker(w1);
		album1.addWorker(w2);
		album1.addWorker(w3);


		ProjectDTO project_dto = album1.toDTO();
		assertEquals(album1.getName(), project_dto.getName());
		assertEquals(album1.getSize(), project_dto.getSize());
		assertEquals(album1.getStatus(), project_dto.getStatus());


		String[] workersArr = {w1.getName(), w2.getName(), w3.getName()};
		String[] dtoWorkerArr = project_dto.getWorkers();
		Arrays.sort(workersArr);
		Arrays.sort(dtoWorkerArr);
		assertArrayEquals(workersArr, dtoWorkerArr);

		String[] qualificationsArr = {singing.toDTO().getDescription(),
									  guitar.toDTO().getDescription(),
								      missingQualification.toDTO().getDescription()};
		String[] dtoQualificationsArr = project_dto.getQualifications();
		Arrays.sort(qualificationsArr);
		Arrays.sort(dtoQualificationsArr);
		assertArrayEquals(qualificationsArr, dtoQualificationsArr);

	}
	@Test(expected = IllegalArgumentException.class)
	public void testProjectSizeNull() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Project t1 = new Project("test",qualificationSet,null);
	}

	@Test(expected = Exception.class)
	public void testProjectSizeValNull() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);
		ProjectSize s = ProjectSize.valueOf(null);
		Project t1 = new Project("test",qualificationSet,s);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testProjectSetStatusNull(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);

		Project t1 = new Project("test",qualificationSet, ProjectSize.SMALL);
		t1.setStatus(null);
	}

/*================ Start Missed Faults Checklist ====================== */

	@Test(expected = Exception.class)
	public void testAddQualificationNull() {
		Project project = new Project("test", projectQualifications, ProjectSize.SMALL);
		project.addQualification(null);
	}

	/* --- I don't think this should throw an exception ----
	@Test(expected = Exception.class)
	public void testAddQualificationActiveProject() {
		Project project = new Project("test", projectQualifications, ProjectSize.SMALL);
		project.setStatus(ProjectStatus.ACTIVE);
		project.addQualification(new Qualification("fishy"));
	}
	*/

	@Test
	public void testAddQualificationActive_Project_1() {
		Set <Qualification> workerQualifications = new HashSet<Qualification>();
		Qualification one = new Qualification("python");
		workerQualifications.add(one);

		Worker worker = new Worker("Bob_1", workerQualifications, 10.99);
		Project project = new Project("test", projectQualifications, ProjectSize.SMALL);
		
		assertTrue(project.getMissingQualifications().contains(one));
		assertTrue(project.getStatus() == ProjectStatus.PLANNED);
		
		project.addWorker(worker);
		
		assertTrue(project.getMissingQualifications().isEmpty());
		
		project.setStatus(ProjectStatus.ACTIVE);
		assertTrue(project.getStatus() == ProjectStatus.ACTIVE);
		
		Qualification projectQual = new Qualification("ook!");
		project.addQualification(projectQual);
		assertFalse(project.getMissingQualifications().isEmpty());
		assertTrue(project.getMissingQualifications().contains(projectQual));
	}

	@Test
	public void testAddQualificationActive_Project_Still_Active() {
		Set <Qualification> workerQualifications = new HashSet<Qualification>();
		Qualification one = new Qualification("python");
		workerQualifications.add(one);

		Worker worker = new Worker("Bob_1", workerQualifications, 10.99);
		worker.addQualification(new Qualification("chicken"));
		worker.addQualification(new Qualification("ook!"));
		
		Project project = new Project("test", projectQualifications, ProjectSize.SMALL);
		project.addWorker(worker);
		assertTrue(project.getStatus() == ProjectStatus.PLANNED);
		
		project.setStatus(ProjectStatus.ACTIVE);
		assertTrue(project.getStatus() == ProjectStatus.ACTIVE);
		
		project.addQualification(new Qualification("chicken"));
		assertTrue(project.getStatus() == ProjectStatus.ACTIVE);

		project.addQualification(new Qualification("ook!"));
		assertTrue(project.getStatus() == ProjectStatus.ACTIVE);
	}

	@Test
	public void testAddQualificationActiveProjectWhereTheQualAreStillMet() {
		Set workerQualifications = new HashSet();
		workerQualifications.addAll(projectQualifications);
		workerQualifications.add(new Qualification("fishy"));
		Project project = new Project("test", projectQualifications, ProjectSize.SMALL);
		Worker w = new Worker("w", workerQualifications,200);
		project.addWorker(w);
		project.setStatus(ProjectStatus.ACTIVE);
		project.addQualification(new Qualification("fishy"));
		assertTrue(workerQualifications.equals(project.getRequiredQualifications()));
	}

	@Test(expected = Exception.class)
	public void testConstructorNullSize() {
		Project project = new Project("test", projectQualifications, null);
	}

	@Test(expected = Exception.class)
	public void test_constructor_name_only()  {
		Project project = new Project("test", null, null);
	}

	@Test(expected = Exception.class)
	public void test_constructor_empty_name()  {
		Project project = new Project("", projectQualifications, ProjectSize.SMALL);
	}

	@Test(expected = Exception.class)
	public void test_constructor_everything_null()  {
		Project project = new Project(null, null, null);
	}

	@Test
	public void test_to_string_wrong_name()  {
		Project project = new Project("test", projectQualifications, ProjectSize.SMALL);
		String match = "test_2:1:PLANNED";
		assertFalse(project.toString().equals(match));
	}

	@Test
	public void test_to_string_empty_name()  {
		Project project = new Project("test", projectQualifications, ProjectSize.SMALL);
		String match = ":1:PLANNED";
		assertFalse(project.toString().equals(match));
	}

	@Test
	public void test_to_string_bad_name()  {
		Project project = new Project("test", projectQualifications, ProjectSize.SMALL);
		String match = "                      :1:PLANNED";
		assertFalse(project.toString().equals(match));
	}


}
