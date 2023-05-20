package edu.colostate.cs415.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;

import junitparams.*;

import java.util.HashSet;
import java.util.Set;



@RunWith(JUnitParamsRunner.class)
public class CompanyTest {


	private Company companyOne;
	private Project projectOne;
	private Worker workerOne;
	private Qualification qualificationOne;
	private Qualification qualificationTwo;
	private Qualification qualificationThree;
	private Qualification qualificationFour;
	private Set<Qualification> qualificationSetOne;
	private Set<Qualification> qualificationSetTwo;
	private Set<Qualification> qualificationSetThree;
	private Set<Qualification> qualificationSetFour;
	private Set<Qualification> projectQualificationsSet;  //containg above for qualifications

	@Before  
	public void createCompanyObjects(){
		companyOne = new Company("Company Name");
		qualificationSetOne = new HashSet<Qualification>();
        qualificationOne = new Qualification("svelte");
		qualificationSetOne.add(qualificationOne);
		workerOne = new Worker("Bob_1", qualificationSetOne, 10);
		projectOne = new Project("project_1", qualificationSetOne, ProjectSize.SMALL);

		qualificationSetTwo = new HashSet<Qualification>();
		qualificationTwo = new Qualification("python");
		qualificationSetTwo.add(qualificationTwo);

		qualificationSetThree = new HashSet<Qualification>();
		qualificationThree = new Qualification("ook!");
		qualificationSetThree.add(qualificationThree);

		qualificationSetFour = new HashSet<Qualification>();
		qualificationFour = new Qualification("chicken");
		qualificationSetFour.add(qualificationFour);
		
		projectQualificationsSet = new HashSet<Qualification>();
		projectQualificationsSet.add(qualificationOne);
		projectQualificationsSet.add(qualificationTwo);
		projectQualificationsSet.add(qualificationThree);
		projectQualificationsSet.add(qualificationFour);
	}



	@Test
	public void test() {
		assert (true);
	}

	@Test
	public void testStart() {
		companyOne.createQualification("chicken");
		companyOne.createProject("project1", qualificationSetFour, ProjectSize.MEDIUM);
		Project project1 = (Project)companyOne.getProjects().toArray()[0];
		companyOne.createWorker("w1",qualificationSetFour,200);
		Worker w1 = (Worker)companyOne.getEmployedWorkers().toArray()[0];
		companyOne.assign(w1,project1);
		companyOne.start(project1);
	}

	@Test (expected = Exception.class)
	public void testStartNotFulfilled() {
		companyOne.createQualification("chicken");
		companyOne.createQualification("python");
		qualificationSetFour.add(qualificationTwo);
		companyOne.createProject("project1", qualificationSetFour, ProjectSize.MEDIUM);
		Project project1 = (Project)companyOne.getProjects().toArray()[0];
		companyOne.createWorker("w1",qualificationSetTwo,200);
		Worker w1 = (Worker)companyOne.getEmployedWorkers().toArray()[0];
		companyOne.assign(w1,project1);
		companyOne.start(project1);
	}
	@Test(expected = Exception.class)
	public void testStartWrongStatusFinished() {
		companyOne.createQualification("chicken");
		companyOne.createProject("project1", qualificationSetFour, ProjectSize.MEDIUM);
		Project project1 = (Project)companyOne.getProjects().toArray()[0];
		companyOne.createWorker("w1",qualificationSetFour,200);
		Worker w1 = (Worker)companyOne.getEmployedWorkers().toArray()[0];
		companyOne.assign(w1,project1);
		project1.setStatus(ProjectStatus.FINISHED);
		companyOne.start(project1);
	}

	@Test(expected = Exception.class)
	public void testStartWrongStatusActive() {
		companyOne.createQualification("chicken");
		companyOne.createProject("project1", qualificationSetFour, ProjectSize.MEDIUM);
		Project project1 = (Project)companyOne.getProjects().toArray()[0];
		companyOne.createWorker("w1",qualificationSetFour,200);
		Worker w1 = (Worker)companyOne.getEmployedWorkers().toArray()[0];
		companyOne.assign(w1,project1);
		project1.setStatus(ProjectStatus.ACTIVE);
		companyOne.start(project1);
	}

	@Test(expected = Exception.class)
	public void testStartNotOurJob() {
		companyOne.createProject("project1", qualificationSetFour, ProjectSize.MEDIUM);
		Project notMyJob = new Project("notOurJob", qualificationSetOne, ProjectSize.BIG);
		companyOne.start(notMyJob);
	}

	@Test
	public void testFinish() {
		companyOne.createQualification("chicken");
		companyOne.createProject("project1", qualificationSetFour, ProjectSize.MEDIUM);
		Project project1 = (Project)companyOne.getProjects().toArray()[0];
		companyOne.createWorker("w1",qualificationSetFour,200);
		Worker w1 = (Worker)companyOne.getEmployedWorkers().toArray()[0];
		companyOne.assign(w1,project1);
		companyOne.start(project1);
		companyOne.finish(project1);
		assertTrue(((Project) companyOne.getProjects().toArray()[0]).getStatus() == ProjectStatus.FINISHED);
	}
	
	@Test 
	public void testFinishMultipleWorkers() {
		Qualification java =  companyOne.createQualification("java");
		Qualification html = companyOne.createQualification("html");
		Qualification css = companyOne.createQualification("css");
		Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(java);
		qualifications.add(html);
		qualifications.add(css);

		Project project1 = companyOne.createProject("project1", qualifications, ProjectSize.MEDIUM);

		Worker w1 = companyOne.createWorker("w1", new HashSet<Qualification>() {{
			add(java);
		}}, 200);
		Worker w2 = companyOne.createWorker("w2", new HashSet<Qualification>() {{
			add(html);
		}}, 200);		
		Worker w3 = companyOne.createWorker("w3", new HashSet<Qualification>() {{
			add(css);
		}}, 200);

		companyOne.assign(w1,project1);
		companyOne.assign(w2,project1);
		companyOne.assign(w3,project1);
		companyOne.start(project1);
		companyOne.finish(project1);
		assertTrue(project1.getStatus() == ProjectStatus.FINISHED);
		assertTrue(project1.getWorkers().size() == 0);
		assertTrue(companyOne.getUnassignedWorkers().size() == 3);
		
	}



	@Test
	public void testFinishedWorkersUnassigned() {
		companyOne.createQualification("chicken");
		companyOne.createProject("project1", qualificationSetFour, ProjectSize.MEDIUM);
		Project project1 = (Project)companyOne.getProjects().toArray()[0];
		companyOne.createWorker("w1",qualificationSetFour,200);
		Worker w1 = (Worker)companyOne.getEmployedWorkers().toArray()[0];
		companyOne.assign(w1,project1);
		companyOne.start(project1);
		companyOne.finish(project1);
		assertTrue(companyOne.getUnassignedWorkers().contains(w1));
	}
	@Test (expected = Exception.class)
	public void testFinishedWrongStatusSuspended() {
		companyOne.createQualification("chicken");
		companyOne.createProject("project1", qualificationSetFour, ProjectSize.MEDIUM);
		Project project1 = (Project)companyOne.getProjects().toArray()[0];
		companyOne.createWorker("w1",qualificationSetFour,200);
		Worker w1 = (Worker)companyOne.getEmployedWorkers().toArray()[0];
		companyOne.assign(w1,project1);
		project1.setStatus(ProjectStatus.SUSPENDED);
		companyOne.finish(project1);
	}
	@Test (expected = Exception.class)
	public void testFinishedWrongStatusFinished() {
		companyOne.createQualification("chicken");
		companyOne.createProject("project1", qualificationSetFour, ProjectSize.MEDIUM);
		Project project1 = (Project)companyOne.getProjects().toArray()[0];
		companyOne.createWorker("w1",qualificationSetFour,200);
		Worker w1 = (Worker)companyOne.getEmployedWorkers().toArray()[0];
		companyOne.assign(w1,project1);
		project1.setStatus(ProjectStatus.FINISHED);
		companyOne.finish(project1);
	}
	@Test (expected = Exception.class)
	public void testFinishedWrongStatusPlanned() {
		companyOne.createQualification("chicken");
		companyOne.createProject("project1", qualificationSetFour, ProjectSize.MEDIUM);
		Project project1 = (Project)companyOne.getProjects().toArray()[0];
		companyOne.createWorker("w1",qualificationSetFour,200);
		Worker w1 = (Worker)companyOne.getEmployedWorkers().toArray()[0];
		companyOne.assign(w1,project1);
		project1.setStatus(ProjectStatus.PLANNED);
		companyOne.finish(project1);
	}
	@Test (expected = Exception.class)
	public void testFinishedWrongStatusNotOurJob() {
		companyOne.createQualification("chicken");
		companyOne.createProject("project1", qualificationSetFour, ProjectSize.MEDIUM);
		Project project1 = (Project)companyOne.getProjects().toArray()[0];
		companyOne.createWorker("w1",qualificationSetFour,200);
		Worker w1 = (Worker)companyOne.getEmployedWorkers().toArray()[0];
		companyOne.assign(w1,project1);
		Project project2 = new Project("not our job", qualificationSetFour, ProjectSize.MEDIUM);
		project2.setStatus(ProjectStatus.ACTIVE);
		companyOne.finish(project2);
	}
	@Test(expected = Exception.class)
	public void testConstructorNull() {
		Company company = new Company(null);
	}

	@Test
	public void testConstructorNotNull() {
		Company company = new Company("Not null");
		assertNotNull(company);
	}

	@Test(expected = Exception.class)
	public void testConstructorEmptyString() {
		Company company = new Company("");
	}
	
	@Test(expected = Exception.class)
	public void testConstructorOneBlankSpace() {
		Company company = new Company(" ");
	}

	@Test(expected = Exception.class)
	public void testConstructorMultipleBlankSpace() {
		Company company = new Company("                                               ");
		assertNotNull(company);
	}

	@Test
	public void testConstructorSingleLetterAndMultipleWordString() {
		Company c = new Company("A");
		Company d = new Company("A B C D E F G H I J K L M N O P Q R S T U V W X Y X");
		assertEquals("A", c.getName());
		assertEquals("A B C D E F G H I J K L M N O P Q R S T U V W X Y X", d.getName());
	}

	@Test
	public void testHashCode() {
		Company company = new Company("equalCompany");
		assertEquals(company.hashCode(), "equalCompany".hashCode());
	}

	/* ========== START: equals Tests ================== */
	@Test
	public void test_equals_compare_to_strings(){
		Company c = new Company("company");
		String matchSame = "company";
		String matchDifferent = "other company";
		assertFalse(c.equals(matchSame));
		assertFalse(c.equals(matchDifferent));
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
	public void testEqualsWithEqual(){
		Company c = new Company("equalCompany");
		Company d = new Company("equalCompany");
		assertTrue(c.equals(d));
		assertTrue(d.equals(c));
	}

	@Test
	public void testEqualsReflexive(){
		assertTrue(companyOne.equals(companyOne));
	}

	@Test
	public void testEqualsConsistent(){
		Company c = new Company("equalCompany");
		Company d = new Company("equalCompany");
		Company e = new Company("Not Equal");

		for(int i = 0; i < 7; i++){
			assertTrue(c.equals(d));
		}

		for(int i = 0; i < 7; i++){
			assertFalse(d.equals(e));
		}
	}

	@Test
	public void testEqualsTransitive(){
		Company c = new Company("Company Name");
		Company d = new Company("Company Name");
		Company e = new Company("Company Name");
		assertTrue(c.equals(d));
		assertTrue(d.equals(e));
		assertTrue(c.equals(e));
	}

	@Test
	public void testEqualsWithNull() {
		boolean result = companyOne.equals(null);
		assertEquals(false, result);
	}

	@Test
	public void testEqualsWithSameClassEquality() {
		Company c = new Company("equalClass");
		Company d = new Company("equalClass");
		assertTrue(c.getClass().equals(d.getClass()));
		assertTrue(d.getClass().equals(c.getClass()));
	}

	@Test
	public void testEqualsWithDifferentObjects() {
		assertFalse(companyOne.equals(workerOne));
		assertFalse(companyOne.equals(projectOne));
		assertFalse(companyOne.equals(qualificationOne));
	}
	/* ========== END: equals Tests ================== */

	/*===================== START: assign() tests =============================*/ 

	@Test(expected = Exception.class)
	public void testAssignProjectisNull(){		
		Qualification q = companyOne.createQualification("svelte");
		assertTrue(companyOne.getQualifications().contains(q));
		Worker workerOne = companyOne.createWorker("Bob_1", qualificationSetOne, 10.30);

		companyOne.assign(workerOne, null);
	}

	@Test(expected = Exception.class)
	public void testAssignWorkerisNull(){	
		companyOne.createQualification("svelte");
		Project project = companyOne.createProject("project name", qualificationSetOne, ProjectSize.SMALL);

		companyOne.assign(null, project);
	}

	@Test
	public void testAssignCorrectlyNotAssigningIfWorkerDoesNotHaveAnyMissingQualifications(){
		Set<Qualification> ProjectQualificationSet = new HashSet<Qualification>();
		Qualification projectQualificationOne = new Qualification("svelte");
		Qualification projectQualificationTwo = new Qualification("python");
		ProjectQualificationSet.add(projectQualificationOne);
		ProjectQualificationSet.add(projectQualificationTwo);

		companyOne.createQualification("svelte");
		companyOne.createQualification("python");

		Set<Qualification> qualificationSetThree = new HashSet<Qualification>();
		qualificationSetThree.add(qualificationOne);
		qualificationSetThree.add(qualificationTwo);
		
		Project project = companyOne.createProject("project name", ProjectQualificationSet, ProjectSize.SMALL);
		
		Worker workerOne = companyOne.createWorker("Bob_1", qualificationSetOne, 10.30);
		Worker workerTwo = companyOne.createWorker("Bob_2", qualificationSetTwo, 10.30);
		// all qualifications have now been filled so workerThree should not be assigned
		Worker workerThree = companyOne.createWorker("Bob_3", qualificationSetThree, 10);

		assertTrue(companyOne.getAvailableWorkers().contains(workerOne));
		assertTrue(companyOne.getAvailableWorkers().contains(workerTwo));
		assertTrue(companyOne.getAvailableWorkers().contains(workerThree));

		companyOne.assign(workerOne, project);
	    assertTrue(companyOne.getAssignedWorkers().contains(workerOne));
		assertTrue(project.getMissingQualifications().contains(qualificationTwo));
		assertTrue(workerOne.getProjects().contains(project));
		assertTrue(project.getWorkers().contains(workerOne));

		companyOne.assign(workerTwo, project);
	    assertTrue(companyOne.getAssignedWorkers().contains(workerTwo));
		assertTrue(project.getMissingQualifications().isEmpty());
		assertTrue(workerTwo.getProjects().contains(project));
		assertTrue(project.getWorkers().contains(workerTwo));

		
		// this throws an exception
		try {
			companyOne.assign(workerThree, project);
		}
		catch(Exception e){

		}

		// correctly not assigning workerThree who has no helpful qualifications 
		// because all missing qualifications have already been filled
		assertFalse(companyOne.getAssignedWorkers().contains(workerThree));
		assertFalse(workerThree.getProjects().contains(project));
		assertFalse(project.getWorkers().contains(workerThree));
}

	
	@Test
	public void testAssignCorrectlyAssignsWorkerFromAvailableToAssigned(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		qualificationSet.add(q);

		Set<Qualification> someSkills = new HashSet<Qualification>();
		Qualification s = new Qualification("svelte");
		someSkills.add(s);

		companyOne.createQualification("svelte");
		
		Project project = companyOne.createProject("project name", qualificationSet, ProjectSize.SMALL);
		Worker worker = companyOne.createWorker("Bob", someSkills, 10.30);

		assertTrue(companyOne.getAvailableWorkers().contains(worker));
		companyOne.assign(worker, project);
	    assertTrue(companyOne.getAssignedWorkers().contains(worker));
	}

	@Test(expected = Exception.class)
	public void testAssignIsNotHelpful(){
		companyOne.createQualification("svelte"); 
		companyOne.createQualification("python"); 

		Project projectOne = companyOne.createProject("project name One", qualificationSetOne, ProjectSize.SMALL);
		Project projectTwo = companyOne.createProject("project name Two", qualificationSetTwo, ProjectSize.SMALL);
		
		Worker workerOne = companyOne.createWorker("Bob_1", qualificationSetOne, 10.30);
		Worker workerTwo = companyOne.createWorker("Bob_2", qualificationSetTwo, 10.30);
		
		assertTrue(companyOne.getAvailableWorkers().contains(workerOne));
		assertTrue(companyOne.getAvailableWorkers().contains(workerTwo));
		
		companyOne.assign(workerOne, projectOne);
		companyOne.assign(workerTwo, projectTwo);

		assertTrue(companyOne.getAssignedWorkers().contains(workerOne));
		assertTrue(companyOne.getAssignedWorkers().contains(workerTwo));	
		
		// catching exception here so can keep going with test.
		try {
			companyOne.assign(workerTwo, projectOne);
		}
		catch(Exception e) {
		}

		assertFalse(projectOne.getWorkers().contains(workerTwo));

		companyOne.assign(workerOne, projectTwo); // throwing exception here for not being helpful
	
	}	

	@Test
	public void testAssignIsHelpful(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		Qualification r = new Qualification("c");
		qualificationSet.add(q);
		qualificationSet.add(r);
		
		Set<Qualification> someSkills = new HashSet<Qualification>();
		someSkills.add(r);
		someSkills.add(q);

		companyOne.createQualification("svelte");
		companyOne.createQualification("c");

		Project project = companyOne.createProject("project name", qualificationSet, ProjectSize.SMALL);
		Worker worker = companyOne.createWorker("Bob", someSkills, 10.30);
		
		companyOne.assign(worker, project);

		assertTrue(companyOne.getAssignedWorkers().contains(worker));
		assertTrue(project.getWorkers().contains(worker));
	}

	@Test(expected = Exception.class)
	public void testAssignCorrectlyDoesNotAssignWorkerBecaseActiveAndFinished(){
		companyOne.createQualification("svelte");

		Project project = companyOne.createProject("project name", qualificationSetOne, ProjectSize.SMALL);
		Worker worker = companyOne.createWorker("Bob_1", qualificationSetOne, 10.30);
		
		assertEquals(1, companyOne.getAvailableWorkers().size());
		
		project.setStatus(ProjectStatus.ACTIVE);

		// this catches exception
		try {
			companyOne.assign(worker, project);
		}
		catch(Exception e) {
		}
		
		assertFalse(companyOne.getAssignedWorkers().contains(worker));

		project.setStatus(ProjectStatus.FINISHED);
		
		// this will throw exception
		companyOne.assign(worker, project);
		 
	}

	@Test(expected = Exception.class)
	public void testAssignWorkerIsNotInAvailable(){
		companyOne.createQualification("svelte");
		Worker workerOne = new Worker("Bob", qualificationSetOne, 10.30);
		Project projectOne = companyOne.createProject("project name", qualificationSetOne, ProjectSize.SMALL);
		
		companyOne.assign(workerOne, projectOne);
	}

	@Test(expected = Exception.class)
	public void testAssignWorker_not_available(){
		companyOne.createQualification("svelte");
		Worker worker = companyOne.createWorker("Bob", qualificationSetOne, 10.30);
		Project projectOne = companyOne.createProject("project_1", qualificationSetOne, ProjectSize.BIG);
		Project projectTwo = companyOne.createProject("project_2", qualificationSetOne, ProjectSize.BIG);
		Project projectThree = companyOne.createProject("project_3", qualificationSetOne, ProjectSize.BIG);
		Project projectFour = companyOne.createProject("project_4", qualificationSetOne, ProjectSize.BIG);
		Project projectFive = companyOne.createProject("project_5", qualificationSetOne, ProjectSize.BIG);

		companyOne.assign(worker, projectOne);
		companyOne.assign(worker, projectTwo);  // second assign to same project throws exception
		companyOne.assign(worker, projectThree); 
		companyOne.assign(worker, projectFour); 

		companyOne.assign(worker, projectFive);
	}

	@Test(expected = Exception.class)
	public void testAssignWorkerWillOverloadProject(){
		
		companyOne.createQualification("svelte");
		Worker worker = companyOne.createWorker("Bob", qualificationSetOne, 10.30);

		Project projectOne = companyOne.createProject("project_1", qualificationSetOne, ProjectSize.BIG);
		Project projectTwo = companyOne.createProject("project_2", qualificationSetOne, ProjectSize.BIG);
		Project projectThree = companyOne.createProject("project_3", qualificationSetOne, ProjectSize.SMALL);
		Project projectFour = companyOne.createProject("project_4", qualificationSetOne, ProjectSize.SMALL);
		Project projectFive = companyOne.createProject("project_5", qualificationSetOne, ProjectSize.MEDIUM);
		Project projectSix = companyOne.createProject("project_6", qualificationSetOne, ProjectSize.BIG);

		companyOne.assign(worker, projectOne);
		assertTrue(worker.isAvailable());
		assertFalse(worker.willOverload(projectOne));

		companyOne.assign(worker, projectTwo);
		assertTrue(worker.isAvailable());
		assertFalse(worker.willOverload(projectTwo));
		
		companyOne.assign(worker, projectThree);
		assertTrue(worker.isAvailable());
		assertFalse(worker.willOverload(projectThree));
		
		companyOne.assign(worker, projectFour);
		assertTrue(worker.isAvailable());
		assertFalse(worker.willOverload(projectFour));
		
		companyOne.assign(worker, projectFive);
		assertTrue(worker.isAvailable());
		assertFalse(worker.willOverload(projectFive));

		companyOne.assign(worker, projectSix);  // throws exception here because project added will overload

	}

	@Test(expected = Exception.class)
	public void testAssignAvailableDoesNotContainWorker(){
		companyOne.createQualification("svelte");
		
		Project projectOne = companyOne.createProject("project_1", qualificationSetOne, ProjectSize.BIG);

		companyOne.assign(workerOne, projectOne);
	}

	@Test(expected = Exception.class)
	public void testAssignWorkerAlreadyInProject(){
		companyOne.createQualification("svelte");
		Worker worker = companyOne.createWorker("Bob", qualificationSetOne, 10.30);
		Project projectOne = companyOne.createProject("project_1", qualificationSetOne, ProjectSize.BIG);

		companyOne.assign(worker, projectOne);
		companyOne.assign(worker, projectOne);
	}

	@Test(expected = Exception.class)
	public void testAssign_Project_status_ACTIVE(){
		companyOne.createQualification("svelte");
		Worker worker = companyOne.createWorker("Bob", qualificationSetOne, 10.30);
		Project projectOne = companyOne.createProject("project_1", qualificationSetOne, ProjectSize.BIG);
		projectOne.setStatus(ProjectStatus.FINISHED);

		companyOne.assign(worker, projectOne);
	}

	@Test(expected = Exception.class)
	public void testAssign_Project_status_FINISHED(){
		companyOne.createQualification("svelte");
		Worker worker = companyOne.createWorker("Bob", qualificationSetOne, 10.30);
		Project projectOne = companyOne.createProject("project_1", qualificationSetOne, ProjectSize.BIG);
		projectOne.setStatus(ProjectStatus.ACTIVE);

		companyOne.assign(worker, projectOne);
	}

	@Test(expected = Exception.class)
	public void testAssign_Worker_Already_in_project(){
		companyOne.createQualification("svelte");
		Worker worker = companyOne.createWorker("Bob", qualificationSetOne, 10.30);
		Project projectOne = companyOne.createProject("project_1", qualificationSetOne, ProjectSize.BIG);
		projectOne.setStatus(ProjectStatus.ACTIVE);

		companyOne.assign(worker, projectOne);
	}

	@Test(expected = Exception.class)
	public void testAssign_Worker_not_Available(){
		companyOne.createQualification("svelte");
		Worker worker = companyOne.createWorker("Bob", qualificationSetOne, 10.30);
		Project projectOne = companyOne.createProject("project_1", qualificationSetOne, ProjectSize.BIG);
		projectOne.setStatus(ProjectStatus.ACTIVE);

		companyOne.assign(worker, projectOne);
	}

/*================== END assign() tests ====================================*/


	@Test
	public void testGetName(){
		Company company = new Company("name");
		assertTrue("name".equals(company.getName()));
	}

	@Test
	public void testCreateQualificationNull() {
		Company company = new Company("company name");
		assertNull(company.createQualification(null));
	}

	@Test
	public void testCreateQualificationEmpty() {
		Company company = new Company("company name");
		assertNull(company.createQualification(""));
	}

	@Test
	public void testCreateQualificationThatCompanyAlreadyHas() {
		Company company = new Company("company name");
		company.createQualification("qual");
		assertNull(company.createQualification("qual"));
	}

	@Test
	public void testCreateQualification(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		qualificationSet.add(q);
		
		Company c = new Company("company name");

		assertEquals(c.createQualification("svelte"), q);
		assertEquals(c.getQualifications(), qualificationSet);
	}

	@Test
	public void testGetEmptyQualification(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Company c = new Company("company name");

		assertEquals(qualificationSet, c.getQualifications());
	}

	@Test
	public void testCompanyToStringZeroZero(){
		Company c = new Company("company name");
		assertEquals("company name:0:0", c.toString());
	}
	@Test
	public void testCompanyToStringThreeWorkers(){
		
		Company c = new Company("company name");

		Qualification q = c.createQualification("C");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		Qualification q1 = c.createQualification("T");
		final Set<Qualification> qualifications1 = new HashSet<Qualification>();
		qualifications1.add(q1);
		Qualification q2 = c.createQualification("G");
		final Set<Qualification> qualifications2 = new HashSet<Qualification>();
		qualifications2.add(q2);
		c.createWorker("jeff", qualifications, 10000);
		c.createWorker("jeff2", qualifications1, 10000);
		c.createWorker("jeff3", qualifications2, 10000);

		assertEquals("company name:3:0", c.toString());
	}
	@Test
	public void testCompanyToStringOneWorker(){
		
		Company c = new Company("company name");

		Qualification q = c.createQualification("C");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		c.createWorker("jeff", qualifications, 10000);

		assertEquals("company name:1:0", c.toString());
	}

	@Test
	public void testCompanyToStringOneWorkerOneProject(){
		
		Company c = new Company("company name");

		Qualification q = c.createQualification("C");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		c.createWorker("jeff", qualifications, 10000);
		c.createProject("Test", qualifications, ProjectSize.SMALL);
		assertEquals("company name:1:1", c.toString());
		
	}
	@Test
	public void testCompanyToStringOneProject(){
		
		Company c = new Company("company name");

		Qualification q = c.createQualification("C");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		c.createProject("Test", qualifications, ProjectSize.SMALL);
		assertEquals("company name:0:1", c.toString());
		
	}

	@Test
	public void testCompanyToStringThreeProject(){
		
		Company c = new Company("company name");

		Qualification q = c.createQualification("C");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		c.createProject("Test", qualifications, ProjectSize.SMALL);
		c.createProject("Testy", qualifications, ProjectSize.SMALL);
		c.createProject("Tester", qualifications, ProjectSize.SMALL);
		assertEquals("company name:0:3", c.toString());
		
	}


	@Test
	public void testCompanyToStringThreeProjectThreeWorker(){
		
		Company c = new Company("company name");

		Qualification q = c.createQualification("C");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		Qualification q1 = c.createQualification("T");
		final Set<Qualification> qualifications1 = new HashSet<Qualification>();
		qualifications1.add(q1);
		Qualification q2 = c.createQualification("G");
		final Set<Qualification> qualifications2 = new HashSet<Qualification>();
		qualifications2.add(q2);
		c.createWorker("jeff", qualifications, 10000);
		c.createWorker("jeff2", qualifications1, 10000);
		c.createWorker("jeff3", qualifications2, 10000);
		c.createProject("Test", qualifications, ProjectSize.SMALL);
		c.createProject("Testy", qualifications, ProjectSize.SMALL);
		c.createProject("Tester", qualifications, ProjectSize.SMALL);
		assertEquals("company name:3:3", c.toString());
		
	}

	@Test
	public void testCompanyToStringThreeProjectOneWorker(){
		
		Company c = new Company("company name");

		Qualification q = c.createQualification("C");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		c.createWorker("jeff", qualifications, 10000);
		c.createProject("Test", qualifications, ProjectSize.SMALL);
		c.createProject("Testy", qualifications, ProjectSize.SMALL);
		c.createProject("Tester", qualifications, ProjectSize.SMALL);
		assertEquals("company name:1:3", c.toString());
		
	}

	@Test
	public void testCompanyCreateWorkerNullCases(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		qualificationSet.add(q);
		Company c = new Company("company name");
		c.createQualification("svelte");
		
		
		assertNull(c.createWorker(null, qualificationSet, 100));
		assertNull(c.createWorker("", qualificationSet, 100));
		assertNull(c.createWorker("name", null, 100));
	}

	public void testCompanyCreateWorkerEmptyName(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		qualificationSet.add(q);
		Company c = new Company("company name");
		c.createQualification("svelte");
		
		assertNull(c.createWorker("", qualificationSet, 100));
	}

	public void testCompanyCreateWorkerNegativeSalary(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		qualificationSet.add(q);
		Company c = new Company("company name");
		c.createQualification("svelte");
		
		assertNull(c.createWorker("name", qualificationSet, -100));
	}

	@Test
	public void testCompanyCreateWorkerMissingQualification(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		qualificationSet.add(q);
		Company c = new Company("company name");
		
		assertNull(c.createWorker("name", qualificationSet, 100));
	}

	@Test
	public void testCompanyUnassigned(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		qualificationSet.add(q);
		Company c = new Company("company name");
		c.createQualification("svelte");
		c.createWorker("unassigned",qualificationSet,100);
		HashSet unassigned = new HashSet<Worker>();
		unassigned.add(new Worker("unassigned", qualificationSet, 100));
		assertEquals(unassigned,c.getUnassignedWorkers());
	}

	@Test
	public void testCompanyCreateWorkerExpected(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("1stQualification");
		qualificationSet.add(q);
		Company c = new Company("company name");
		c.createQualification("1stQualification");
		Worker worker = new Worker("BoB", qualificationSet, 10);
		Set<Worker> workers = new HashSet<Worker>();
		workers.add(worker);
		
		assertEquals(worker, c.createWorker("BoB", qualificationSet, 10));
		assertEquals(1, c.getAvailableWorkers().size());
		assertEquals(1, c.getEmployedWorkers().size());


		Set<Qualification> qualificationSet2 = new HashSet<Qualification>();
		Qualification q2 = new Qualification("2ndQualificatoin");
		qualificationSet2.add(q2);
		c.createQualification("2ndQualificatoin");
		
		Worker worker2 = new Worker("Mark", qualificationSet2, 10);
		Set<Worker> workers2 = new HashSet<Worker>();
		workers2.add(worker2);
		c.createWorker("Mark", qualificationSet2, 10);


		Set<Qualification> qualificationSet3 = new HashSet<Qualification>();
		Qualification q3 = new Qualification("2ndQualificatoin");
		q3.addWorker(worker2);
		qualificationSet3.add(q);

		for (Qualification qual : c.getQualifications()){
			if(q2.equals(qual)){
				assertEquals(qual.getWorkers(), workers2);
			}
			else{
				assertEquals(qual.getWorkers(), workers);
			}
		}
	}


	@Test
	public void testCompanyCreateProjectNormal(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		qualificationSet.add(q);
		Company c = new Company("company name");
		c.createQualification("svelte");
		Project p = c.createProject("Test", qualificationSet, ProjectSize.SMALL);
		assertEquals(p.getName(), "Test");
	}

	@Test
	public void testCompanyCreateProjectNullName(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		qualificationSet.add(q);
		Company c = new Company("company name");
		assertNull(c.createProject(null, qualificationSet, ProjectSize.SMALL));
	}

	@Test
	public void testCompanyCreateProjectNullQual(){
		Company c = new Company("company name");
		assertNull(c.createProject("test", null, ProjectSize.SMALL));
	}

	@Test
	public void testCompanyCreateProjectEmptyQual(){
		Company c = new Company("company name");
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		assertNull(c.createProject("test", qualificationSet, ProjectSize.SMALL));
	}

	@Test
	public void testCompanyCreateProjectNullSize(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		qualificationSet.add(q);
		Company c = new Company("company name");
		assertNull(c.createProject("Test", qualificationSet, null));
	}

	@Test
	public void testCompanyCreateProjectEmptyName(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		qualificationSet.add(q);
		Company c = new Company("company name");
		assertNull(c.createProject("", qualificationSet, null));
	}

	@Test
	public void testCompanyCreateProjectBlankName(){
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("svelte");
		qualificationSet.add(q);
		Company c = new Company("company name");
		assertNull(c.createProject("    ", qualificationSet, null));
	}

   /*================= START UNASSIGN TESTS ==================== */

   @Test(expected = Exception.class)
   public void testUnassignWorkerIsNullProjectIsValid(){
		companyOne.createQualification("svelte");
		Project project = companyOne.createProject("project name", qualificationSetOne, ProjectSize.SMALL);
		assertNotNull(project);
		companyOne.unassign(null, project);
   }

   @Test(expected = Exception.class)
   public void testUnassignProjectIsNullWorkerIsValid(){
		companyOne.createQualification("svelte");
		Worker worker = companyOne.createWorker("Bob_1", qualificationSetOne, 10.30);
		assertNotNull(worker);
		companyOne.unassign(worker, null);
   }

   @Test(expected = Exception.class)
   public void testUnassignProject_and_Worker_Null(){
		companyOne.unassign(null, null);
   }

   @Test
   public void testUnassignChangeToSuspendedWithOneWorker(){
		companyOne.createQualification("svelte");
		Project project = companyOne.createProject("project", qualificationSetOne, ProjectSize.SMALL);
		Worker worker = companyOne.createWorker("Bob_1", qualificationSetOne, 10.30);


		companyOne.assign(worker, project);
		assertTrue(companyOne.getAssignedWorkers().contains(worker));

		project.setStatus(ProjectStatus.ACTIVE);
		assertTrue(project.getStatus() == ProjectStatus.ACTIVE);

		companyOne.unassign(worker, project);
		assertFalse(companyOne.getAssignedWorkers().contains(worker));
		assertTrue(project.getStatus() == ProjectStatus.SUSPENDED);
   }

   @Test(expected = Exception.class)
   public void test_Unassign_project_from_different_company(){
		companyOne.createQualification("svelte");
		Project projectOne = companyOne.createProject("project one", qualificationSetOne, ProjectSize.SMALL);
		Worker workerOne = companyOne.createWorker("Bob_in_company_one", qualificationSetOne, 10.30);

		Company companyTwo = new Company("Company Two");
		companyTwo.createQualification("svelte");
		Project projectTwo = companyTwo.createProject("project two", qualificationSetOne, ProjectSize.SMALL);
		Worker workerTwo = companyTwo.createWorker("Bob_in_company_two", qualificationSetOne, 10.30);

		companyOne.assign(workerOne, projectOne);
		companyTwo.assign(workerTwo, projectTwo);

		companyOne.unassign(workerOne, projectTwo);
   }

   @Test(expected = Exception.class)
   public void test_Unassign_worker_from_different_company(){
	companyOne.createQualification("svelte");
	Project projectOne = companyOne.createProject("project one", qualificationSetOne, ProjectSize.SMALL);
	Worker workerOne = companyOne.createWorker("Bob_in_company_one", qualificationSetOne, 10.30);

	Company companyTwo = new Company("Company Two");
	companyTwo.createQualification("svelte");
	Project projectTwo = companyTwo.createProject("project two", qualificationSetOne, ProjectSize.SMALL);
	Worker workerTwo = companyTwo.createWorker("Bob_in_company_two", qualificationSetOne, 10.30);

	companyOne.assign(workerOne, projectOne);
	companyTwo.assign(workerTwo, projectTwo);

	companyOne.unassign(workerTwo, projectOne);
   }

   @Test(expected = Exception.class)
   public void test_Unassign_both_worker_and_project_from_different_company(){
	companyOne.createQualification("svelte");
	Project projectOne = companyOne.createProject("project one", qualificationSetOne, ProjectSize.SMALL);
	Worker workerOne = companyOne.createWorker("Bob_in_company_one", qualificationSetOne, 10.30);

	Company companyTwo = new Company("Company Two");
	companyTwo.createQualification("svelte");
	Project projectTwo = companyTwo.createProject("project two", qualificationSetOne, ProjectSize.SMALL);
	Worker workerTwo = companyTwo.createWorker("Bob_in_company_two", qualificationSetOne, 10.30);

	Company companyThree = new Company("Company Three");
	companyTwo.createQualification("svelte");
	Project projectThree = companyThree.createProject("project three", qualificationSetOne, ProjectSize.SMALL);
	Worker workerThree = companyThree.createWorker("Bob_in_company_three", qualificationSetOne, 10.30);

	companyOne.assign(workerOne, projectOne);
	companyTwo.assign(workerTwo, projectTwo);

	companyThree.unassign(workerTwo, projectOne);
   }

   @Test
   public void testUnassignDoesNotChangeToSuspendedWithRemovingWorkersBecauseWorkerLeftStillCoversAllQualificationsRequired(){
		Set<Qualification> qualificationSetTwo = new HashSet<Qualification>();
		Qualification qualificationA = new Qualification("svelte");
		Qualification qualificationB = new Qualification("python");
		qualificationSetTwo.add(qualificationA);
		qualificationSetTwo.add(qualificationB);

		companyOne.createQualification("svelte");
		companyOne.createQualification("python");
		Project project = companyOne.createProject("project", qualificationSetTwo, ProjectSize.SMALL);
		
		Worker workerOne = companyOne.createWorker("Bob_1", qualificationSetOne, 10.30);
		Worker workerTwo = companyOne.createWorker("Bob_2", qualificationSetTwo, 10.30);

		companyOne.assign(workerOne, project);
		assertTrue(project.getRequiredQualifications().size() == 2);
		assertTrue(project.getMissingQualifications().size() == 1);

		companyOne.assign(workerTwo, project);
		assertTrue(project.getRequiredQualifications().size() == 2);
		assertTrue(project.getMissingQualifications().size() == 0);

		companyOne.unassign(workerOne, project);  // workerTwo still has all required qualifications so 0 missing.
		assertTrue(project.getRequiredQualifications().size() == 2);
		assertTrue(project.getMissingQualifications().size() == 0);

		try {
			companyOne.assign(workerOne, project);   //try to put WorkerOne back in but can't because all qual are met
		} catch(Exception e) {

		}
		
		companyOne.unassign(workerTwo, project); // now remove workerTwo

		assertTrue(project.getRequiredQualifications().size() == 2);
		assertTrue(project.getMissingQualifications().size() == 2);
   }

   // Do this test again with Multiple Workers and make sure correctly changing to suspended

   @Test
   public void testWorkerBecomesAvailableAfterUnassign(){
		companyOne.createQualification("svelte");
		
		Project projectOne = companyOne.createProject("project_1", qualificationSetOne, ProjectSize.BIG);
		Project projectTwo = companyOne.createProject("project_2", qualificationSetOne, ProjectSize.BIG);
		Project projectThree = companyOne.createProject("project_3", qualificationSetOne, ProjectSize.BIG);
		Project projectFour = companyOne.createProject("project_4", qualificationSetOne, ProjectSize.BIG);
		
		Worker worker = companyOne.createWorker("Bob_1", qualificationSetOne, 10.30);

		companyOne.assign(worker, projectOne);
		assertTrue(companyOne.getAvailableWorkers().contains(worker));

		companyOne.assign(worker, projectTwo);
		assertTrue(companyOne.getAvailableWorkers().contains(worker));

		companyOne.assign(worker, projectThree);
		assertTrue(companyOne.getAvailableWorkers().contains(worker));

		companyOne.assign(worker, projectFour); // worker should not be available after this
		assertFalse(companyOne.getAvailableWorkers().contains(worker));
		
		companyOne.unassign(worker, projectFour); // worker should now be available again
		assertTrue(companyOne.getAvailableWorkers().contains(worker));
		assertTrue(companyOne.getAssignedWorkers().contains(worker));
		assertTrue(companyOne.getEmployedWorkers().contains(worker)); 
 
		companyOne.unassign(worker, projectThree); // should not try to add duplicate to set of available
		assertTrue(companyOne.getAvailableWorkers().contains(worker));
		assertTrue(companyOne.getAvailableWorkers().size() == 1);
   }

   @Test
   public void testWorkerIsNoLongerAssignedOrAvailableAfterUnassign(){

		companyOne.createQualification("svelte");
		
		Project projectOne = companyOne.createProject("project_1", qualificationSetOne, ProjectSize.BIG);
		Project projectTwo = companyOne.createProject("project_2", qualificationSetOne, ProjectSize.BIG);
		Project projectThree = companyOne.createProject("project_3", qualificationSetOne, ProjectSize.BIG);
		
		Worker worker = companyOne.createWorker("Bob_1", qualificationSetOne, 10.30);

		companyOne.assign(worker, projectOne);
		companyOne.unassign(worker, projectOne);

		assertTrue(companyOne.getAvailableWorkers().contains(worker));
		assertFalse(companyOne.getAssignedWorkers().contains(worker));

		companyOne.assign(worker, projectTwo);
		companyOne.assign(worker, projectThree);

		assertTrue(companyOne.getAvailableWorkers().contains(worker));
		assertTrue(companyOne.getAssignedWorkers().contains(worker));

		companyOne.unassign(worker, projectTwo);
		companyOne.unassign(worker, projectThree);

		assertTrue(companyOne.getAvailableWorkers().contains(worker));
		assertFalse(companyOne.getAssignedWorkers().contains(worker));
   }

   @Test(expected = Exception.class)
   public void testWorkerCanNotBeUnasssigned(){
		companyOne.createQualification("svelte");
		
		Project projectOne = companyOne.createProject("project_1", qualificationSetOne, ProjectSize.BIG);
		Worker worker = companyOne.createWorker("Bob_1", qualificationSetOne, 10.30);

		companyOne.unassign(worker, projectOne);

   }


    /*================= END UNASSIGN TESTS ==================== */


	@Test
	public void testgetUnavailable(){

		companyOne.createQualification("svelte");
		companyOne.createQualification("python");
		companyOne.createQualification("ook!");
		companyOne.createQualification("chicken");
		
		Worker workerOne = companyOne.createWorker("Bob_1",qualificationSetOne,1);
		Worker workerTwo = companyOne.createWorker("Bob_2",qualificationSetTwo,2);
		Worker workerThree = companyOne.createWorker("Bob_3",qualificationSetThree,3);
		Worker workerFour = companyOne.createWorker("Bob_4",qualificationSetFour,4);

		Project projectOne = companyOne.createProject("project_1", projectQualificationsSet, ProjectSize.BIG);
		Project projectTwo = companyOne.createProject("project_2", projectQualificationsSet, ProjectSize.BIG);
		Project projectThree = companyOne.createProject("project_3", projectQualificationsSet, ProjectSize.BIG);
		Project projectFour = companyOne.createProject("project_4", projectQualificationsSet, ProjectSize.BIG);

		companyOne.assign(workerOne, projectOne);
		companyOne.assign(workerOne, projectTwo);
		companyOne.assign(workerOne, projectThree);
		companyOne.assign(workerOne, projectFour);

		companyOne.assign(workerTwo, projectOne);
		companyOne.assign(workerTwo, projectTwo);
		companyOne.assign(workerTwo, projectThree);
		companyOne.assign(workerTwo, projectFour);

		companyOne.assign(workerThree, projectOne);
		companyOne.assign(workerThree, projectTwo);
		companyOne.assign(workerThree, projectThree);
		companyOne.assign(workerThree, projectFour);

		companyOne.assign(workerFour, projectOne);
		companyOne.assign(workerFour, projectTwo);
		companyOne.assign(workerFour, projectThree);
		companyOne.assign(workerFour, projectFour);

		assertTrue(companyOne.getUnavailableWorkers().size() == 4);
		assertTrue(companyOne.getUnavailableWorkers().contains(workerOne));
		assertTrue(companyOne.getUnavailableWorkers().contains(workerTwo));
		assertTrue(companyOne.getUnavailableWorkers().contains(workerThree));
		assertTrue(companyOne.getUnavailableWorkers().contains(workerFour));

	}


	@Test // correctly assigning and unassigning
	public void testAssignAndUnassignCorrectlyMultipleCompanies(){ 
		Company companyTwo = new Company("Company two");

		companyOne.createQualification("svelte");
		companyOne.createQualification("python");
		companyOne.createQualification("ook!");
		companyOne.createQualification("chicken");
		
		companyTwo.createQualification("ook!");
		companyTwo.createQualification("chicken");
		companyTwo.createQualification("svelte");
		companyTwo.createQualification("python");
		
		Worker workerOne = companyOne.createWorker("Bob_1",qualificationSetOne,1);  // qual = svelte
		Worker workerTwo = companyOne.createWorker("Bob_2",qualificationSetTwo,2);  // qual = python
		
		Worker workerThree = companyTwo.createWorker("Bob_3",qualificationSetThree,3); // qual = ook!
		Worker workerFour = companyTwo.createWorker("Bob_4",qualificationSetFour,4);  // qual = chicken

		Project projectOne = companyOne.createProject("project_1", projectQualificationsSet, ProjectSize.BIG);
		Project projectTwo = companyOne.createProject("project_2", projectQualificationsSet, ProjectSize.BIG);
		
		Project projectThree = companyTwo.createProject("project_3", projectQualificationsSet, ProjectSize.BIG);
		Project projectFour = companyTwo.createProject("project_4", projectQualificationsSet, ProjectSize.BIG);

		companyOne.assign(workerOne, projectOne);
		companyOne.assign(workerTwo, projectTwo);
		
		companyTwo.assign(workerThree, projectThree);
		companyTwo.assign(workerFour, projectFour);

        companyOne.unassign(workerOne, projectOne);
		companyOne.unassign(workerTwo, projectTwo);
		
		companyTwo.unassign(workerThree, projectThree);
		companyTwo.unassign(workerFour, projectFour);

	}

	@Test // throwing exceptions while assigning and unassigning
	public void testAssignAndUnassignMultipleCompaniesThrowingExceptions(){ 
		Company companyTwo = new Company("Company two");

		companyOne.createQualification("svelte");
		companyOne.createQualification("python");
		companyOne.createQualification("ook!");
		companyOne.createQualification("chicken");
		
		companyTwo.createQualification("ook!");
		companyTwo.createQualification("chicken");
		companyTwo.createQualification("svelte");
		companyTwo.createQualification("python");
		
		Worker workerOne = companyOne.createWorker("Bob_1",qualificationSetOne,1);  // qual = svelte
		Worker workerTwo = companyOne.createWorker("Bob_2",qualificationSetTwo,2);  // qual = python
		
		Worker workerThree = companyTwo.createWorker("Bob_3",qualificationSetThree,3); // qual = ook!
		Worker workerFour = companyTwo.createWorker("Bob_4",qualificationSetFour,4);  // qual = chicken

		Project projectOne = companyOne.createProject("project_1", projectQualificationsSet, ProjectSize.BIG);
		Project projectTwo = companyOne.createProject("project_2", projectQualificationsSet, ProjectSize.BIG);
		
		Project projectThree = companyTwo.createProject("project_3", projectQualificationsSet, ProjectSize.BIG);
		Project projectFour = companyTwo.createProject("project_4", projectQualificationsSet, ProjectSize.BIG);


		// these all are currently throwing exceptions
		try {
			companyOne.assign(workerOne, projectFour);
		}catch(Exception e){
		}
		
		try {
			companyOne.assign(workerTwo, projectThree);
		}catch(Exception e){
		}
		
		try {
			companyTwo.assign(workerThree, projectTwo);
		}catch(Exception e){
		}

		try {
			companyTwo.assign(workerFour, projectOne);
		}catch(Exception e){		
		}

		assertTrue(companyOne.getAssignedWorkers().isEmpty());
		assertTrue(companyTwo.getAssignedWorkers().isEmpty());

		companyOne.assign(workerOne, projectOne);
		companyOne.assign(workerTwo, projectTwo);
		companyTwo.assign(workerThree, projectThree);
		companyTwo.assign(workerFour, projectFour);

		assertTrue(companyOne.getAssignedWorkers().size() == 2);
		assertTrue(companyTwo.getAssignedWorkers().size() == 2);
        
		try {
        companyOne.unassign(workerOne, projectFour);
		}catch(Exception e){
		}

		try {
			companyOne.unassign(workerTwo, projectThree);
		}catch(Exception e){
		}
		
		try {
			companyTwo.unassign(workerThree, projectTwo);
		}catch(Exception e){
		}
		
		try {
			companyTwo.unassign(workerFour, projectOne);
		}catch(Exception e){
		}

		assertTrue(companyOne.getAssignedWorkers().size() == 2);
		assertTrue(companyTwo.getAssignedWorkers().size() == 2);

		companyOne.unassign(workerOne, projectOne);
		companyOne.unassign(workerTwo, projectTwo);
		companyTwo.unassign(workerThree, projectThree);
		companyTwo.unassign(workerFour, projectFour);

		assertTrue(companyOne.getAssignedWorkers().isEmpty());
		assertTrue(companyTwo.getAssignedWorkers().isEmpty());

	}

	@Test
	public void testgetUnavailableEmpty(){
		companyOne.createQualification("svelte");
		companyOne.createQualification("chicken");
		companyOne.createQualification("ook!");
		companyOne.createQualification("python");
		
		Project projectOne = companyOne.createProject("project_1", projectQualificationsSet, ProjectSize.BIG);

		Worker workerOne = companyOne.createWorker("Bob_1",qualificationSetOne,1);
		Worker workerTwo = companyOne.createWorker("Bob_2",qualificationSetTwo,2);

		companyOne.assign(workerOne, projectOne);
		companyOne.assign(workerTwo, projectOne);
		
		assertTrue(companyOne.getUnavailableWorkers().isEmpty());
	}


	@Test
	public void testgetUnassignedEmpty(){
		companyOne.createQualification("svelte");
		companyOne.createQualification("chicken");
		companyOne.createQualification("ook!");
		companyOne.createQualification("python");
		
		Project projectOne = companyOne.createProject("project_1", projectQualificationsSet, ProjectSize.BIG);

		Worker workerOne = companyOne.createWorker("Bob_1",qualificationSetOne,1);
		Worker workerTwo = companyOne.createWorker("Bob_2",qualificationSetTwo,2);

		companyOne.assign(workerOne, projectOne);
		companyOne.assign(workerTwo, projectOne);
		
		assertTrue(companyOne.getUnassignedWorkers().isEmpty());
	}

	@Test(expected = Exception.class)
	public void test_UnassignAndAssign_Workers_and_Project_not_in_company(){
		int countException = 0;
		
		companyOne.createQualification("svelte");
		companyOne.createQualification("chicken");
		companyOne.createQualification("ook!");
		companyOne.createQualification("python");

		Project projectOne_c1 = companyOne.createProject("project_1", projectQualificationsSet, ProjectSize.BIG);
		Worker workerOne_c1 = companyOne.createWorker("Bob_1",qualificationSetOne,1);

		Company companyTwo = new Company("company two");

		companyTwo.createQualification("svelte");
		companyTwo.createQualification("chicken");
		companyTwo.createQualification("ook!");
		companyTwo.createQualification("python");

		Project projectTwo_c2 = companyTwo.createProject("Project_2", projectQualificationsSet, ProjectSize.BIG);
		Worker workerTwo_c2 = companyTwo.createWorker("Bob_1",qualificationSetOne,1);

		// these should all catch exceptions
		try{ 
			companyOne.unassign(workerOne_c1, projectTwo_c2);
		} catch(Exception e) {
			countException++;
		}

		try{ 
			companyOne.unassign(workerTwo_c2, projectOne_c1);
		} catch(Exception e) {
			countException++;
		}
		
		try{
			companyTwo.unassign(workerTwo_c2, projectOne_c1);
		} catch(Exception e){
			countException++;
		}

		try{
			companyTwo.unassign(workerOne_c1, projectTwo_c2);
		} catch(Exception e){
			countException++;
		}

		companyOne.assign(workerOne_c1, projectOne_c1);
		companyTwo.assign(workerTwo_c2, projectTwo_c2);

		companyOne.unassign(workerOne_c1, projectOne_c1);
		companyTwo.unassign(workerTwo_c2, projectTwo_c2);

		companyOne.assign(workerOne_c1, projectOne_c1);
		companyTwo.assign(workerTwo_c2, projectTwo_c2);

		// should also all catch exceptions

		try{ 
			companyOne.assign(workerOne_c1, projectTwo_c2);
		} catch(Exception e) {
			countException++;
		}

		try{ 
			companyOne.assign(workerTwo_c2, projectOne_c1);
		} catch(Exception e) {
			countException++;
		}
		
		try{
			companyTwo.assign(workerTwo_c2, projectOne_c1);
		} catch(Exception e){
			countException++;
		}

		try{
			companyTwo.assign(workerOne_c1, projectTwo_c2);
		} catch(Exception e){
			countException++;
		}

		assertTrue(countException == 8); // counted exceptions in previoud try catch

		//This next line of code should throw an exception. also doing as an extra check in case something changes down the line.
		companyOne.unassign(workerOne_c1, projectTwo_c2);
	}

	/*================= START UNASSIGN ALL TESTS ==================== */

	@Test(expected = Exception.class)
	public void testUnAssignAssignedDoesNotContainWorker(){
		companyOne.createQualification("svelte");
		Worker worker = companyOne.createWorker("Bob", qualificationSetOne, 10.30);
		Project projectOne = companyOne.createProject("project_1", qualificationSetOne, ProjectSize.BIG);

		companyOne.unassign(worker, projectOne);
		
	}

	@Test(expected = Exception.class)
	public void testUnassignAllNullWorker(){

		companyOne.createQualification("chicken");
		companyOne.createQualification("ook!");
		companyOne.createQualification("python");
		companyOne.createQualification("svelte");

		Project projectOne = companyOne.createProject("project_1", projectQualificationsSet, ProjectSize.BIG);

		companyOne.unassignAll(null);
	}

	@Test
	public void testUnassignALLCorrectlyUnassignsMultiple(){
		companyOne.createQualification("svelte");
		companyOne.createQualification("chicken");
		companyOne.createQualification("ook!");
		companyOne.createQualification("python");

		Project projectOne = companyOne.createProject("project_1", qualificationSetOne, ProjectSize.BIG);
		Project projectTwo = companyOne.createProject("project_2", qualificationSetTwo, ProjectSize.BIG);
		Project projectThree = companyOne.createProject("project_3", qualificationSetThree, ProjectSize.BIG);
		Project projectFour = companyOne.createProject("project_4", qualificationSetFour, ProjectSize.BIG);

		Worker workerOne = companyOne.createWorker("Bob_1",projectQualificationsSet,1);
		
		companyOne.assign(workerOne, projectOne);
		companyOne.assign(workerOne, projectTwo);
		companyOne.assign(workerOne, projectThree);
		companyOne.assign(workerOne, projectFour);

		assertTrue(workerOne.getProjects().size() == 4);
		assertTrue(companyOne.getAssignedWorkers().contains(workerOne));

		companyOne.unassignAll(workerOne);

		assertTrue(workerOne.getProjects().isEmpty());
		assertFalse(companyOne.getAssignedWorkers().contains(workerOne));
	}

	@Test
	public void testUnassignALLCorrectlyUnassignsOne(){
		companyOne.createQualification("svelte");
		companyOne.createQualification("chicken");
		companyOne.createQualification("ook!");
		companyOne.createQualification("python");

		Project projectOne = companyOne.createProject("project_1", projectQualificationsSet, ProjectSize.BIG);

		Worker workerOne = companyOne.createWorker("Bob_1",qualificationSetOne,1);
		
		companyOne.assign(workerOne, projectOne);

		assertTrue(workerOne.getProjects().size() == 1);
		assertTrue(companyOne.getAssignedWorkers().contains(workerOne));

		companyOne.unassignAll(workerOne);

		assertTrue(workerOne.getProjects().isEmpty());
		assertFalse(companyOne.getAssignedWorkers().contains(workerOne));
	}

	/*================= END UNASSIGN ALL TESTS ==================== */


	// testing projectOne is null because qualification conditions not met
	// testing projectTwo is ' not ' null because qualification conditions are satisfied
	@Test 
	public void test_create_project_company_does_not_have_all_qualifications(){
		companyOne.createQualification("svelte");
		companyOne.createQualification("chicken");

		Company companyTwo = new Company("company two");
		companyTwo.createQualification("svelte");
		companyTwo.createQualification("chicken");
		companyTwo.createQualification("ook!");
		companyTwo.createQualification("python");

		Project projectOne = companyOne.createProject("project_1", projectQualificationsSet, ProjectSize.BIG);
		Project projectTwo = companyTwo.createProject("project_1", projectQualificationsSet, ProjectSize.BIG);
		
		assertNull(projectOne);
		assertNotNull(projectTwo);
	}

}
