package edu.colostate.cs415.model;

import edu.colostate.cs415.dto.WorkerDTO;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


import java.util.HashSet;
import java.util.Set;

public class WorkerTest {

	private Set<Qualification> qualificationSet;
	private Qualification qualificationOne;
	Worker workerOne;

	@Before
	public void createWorkerObjects() {
		qualificationSet = new HashSet<Qualification>();
		qualificationOne = new Qualification("chicken");
		qualificationSet.add(qualificationOne);

		workerOne = new Worker("Bob_1", qualificationSet, 10.90);
	}


	@Test
	public void test() {
		assert (true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorEmptyQualifications() {
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		new Worker("Jack", qualifications, 100);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullQualifications() {
		new Worker("Jack", null, 100);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullName() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);
		new Worker(null, qualificationSet, 100);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorEmptyName() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);
		new Worker("", qualificationSet, 100);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorSpacesName() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);
		new Worker("        ", qualificationSet, 100);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegativeSalary() {
		Set<Qualification> qualificationSet = new HashSet<Qualification>();
		Qualification q = new Qualification("python");
		qualificationSet.add(q);
		new Worker("Jack", qualificationSet, -1);
	}

	@Test
	public void testWorkerConstructor() {
		Qualification eating = new Qualification("eating");
		Qualification running = new Qualification("running");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(eating);
		qualifications.add(running);
		final Worker w = new Worker("Jack", qualifications, 100);
		assertEquals("Jack", w.getName());
		assertEquals(qualifications, w.getQualifications()); 
		assertEquals(100, w.getSalary(), 0);
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
	public void testWorkerEquals() {
		Qualification eating = new Qualification("went");
		Qualification running = new Qualification("up the hill");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(eating);
		qualifications.add(running);
		final Worker Jill1 = new Worker("Jill", qualifications, 1);
		final Worker Jill2 = new Worker("Jill", qualifications, 100000);
		assertTrue(Jill1.equals(Jill2));
	}
	
	@Test
	public void test_equals_compares_to_strings() {
		Qualification q = new Qualification("python");
		Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		Worker worker = new Worker("Bob_1", qualifications, 1);
		String workerSameName = "Bob_1";
		String workerDifferentName = "Sue";

		assertFalse(worker.equals(workerSameName));
		assertFalse(worker.equals(workerDifferentName));

	}

	@Test
	public void testWorkerEqualsToCompleteBranchCoverage() {
		Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification q = new Qualification("hello");
		qualifications.add(q);
		Project t1 = new Project("project_test",qualifications,ProjectSize.SMALL);
		
		Worker w = new Worker("Bob_1", qualifications, 10);
		Worker x = new Worker("Bob_2", qualifications, 10);
		
		assertFalse(w.equals(t1));
		assertEquals(w.getClass().equals(x.getClass()), x.getClass().equals(w.getClass()));
		assertFalse(w.equals(null));
		assertFalse(t1.getClass().equals(w.getClass()));
	}



	@Test
	public void testWorkerToString() {
		Qualification eating = new Qualification("went");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(eating);
		final Worker Nick = new Worker("Nick", qualifications, 10000.20);
		String expected = "Nick:0:1:10000";
		assertEquals(expected, Nick.toString());
	}
	@Test
	public void setAndGetWorkerSalary() {
		Qualification eating = new Qualification("went");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(eating);
		final Worker Nick = new Worker("Jack", qualifications, 10000.20);
		double expectedBefore = 10000.20;
		assertEquals(expectedBefore, Nick.getSalary(),0);
		double expectedAfter =  10;
		Nick.setSalary(10);
		assertEquals(expectedAfter, Nick.getSalary(),0);
	}
	@Test
	public void celeryMutation() {
		Qualification eating = new Qualification("went");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(eating);
		final Worker Nick = new Worker("Jack", qualifications, 1);
		double expected1 = 1;
		assertEquals(expected1, Nick.getSalary(),1);
		Nick.setSalary(2);
		double expected2 = 2;
		assertEquals(expected2, Nick.getSalary(),2);
	}
	@Test
	public void setCeleryMutationBounds() {
		Qualification eating = new Qualification("went");
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(eating);
		Worker w = new Worker("Jack", qualifications, 20);
		w.setSalary(0);
		assertEquals(0,w.getSalary(),1);
	}
	@Test
	public void testWorkerAddAndGetQualification() {
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification singing = new Qualification("singing");
		Qualification guitar = new Qualification("guitar");
		qualifications.add(singing);
		qualifications.add(guitar);
		final Worker w = new Worker("Gerard Way", qualifications, 2147483648.0);
		w.addQualification(singing);
		w.addQualification(guitar);

		assertEquals(qualifications, w.getQualifications());
	}

	@Test
	public void testWorkerAddAndGetProject() {
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification singing = new Qualification("singing");
		Qualification guitar = new Qualification("guitar");
		qualifications.add(singing);
		qualifications.add(guitar);
		final Worker w = new Worker("Gerrard Way", qualifications, 2147483648.0);
		Project album = new Project("TBP", qualifications, ProjectSize.BIG);
		w.addProject(album);

		final Set<Project> projects = new HashSet<Project>();
		projects.add(album);
		assertEquals(projects, w.getProjects());
	}

	@Test(expected = Exception.class)
	public void testAddProject_null() {
		workerOne.addProject(null);
	}


	@Test(expected = Exception.class)
	public void testAddQualification_null() {
		workerOne.addQualification(null);
	}

	@Test
	public void testWorkerRemoveProject() {
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification singing = new Qualification("singing");
		Qualification guitar = new Qualification("guitar");
		qualifications.add(singing);
		qualifications.add(guitar);
		final Worker w = new Worker("Gerrard Way", qualifications, 2147483648.0);
		Project album = new Project("TBP", qualifications, ProjectSize.BIG);
		w.addProject(album);
		w.removeProject(album);

		final Set<Project> projects = new HashSet<Project>();
		projects.add(album);
		assertEquals(0, w.getProjects().size());
	}

	@Test
	public void testWorkerGetWorkload() {
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification singing = new Qualification("singing");
		Qualification guitar = new Qualification("guitar");
		qualifications.add(singing);
		qualifications.add(guitar);
		final Worker w = new Worker("Victor Vincent Fuentes the 13th Way", qualifications, 0.0);
		Project album = new Project("Jaws of Life", qualifications, ProjectSize.BIG);
		Project single = new Project("F", qualifications, ProjectSize.SMALL);
		Project finished = new Project("Finished", qualifications, ProjectSize.BIG);
		finished.setStatus(ProjectStatus.FINISHED);
		w.addProject(album);
		w.addProject(single);
		w.addProject(finished);

		assertEquals(4, w.getWorkload());
	}

	@Test
	public void testWorkerWillOverload() {
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification singing = new Qualification("singing");
		Qualification guitar = new Qualification("guitar");
		qualifications.add(singing);
		qualifications.add(guitar);
		final Worker w = new Worker("Victor Vincent Fuentes the 13th Way", qualifications, 0.0);
		Project album1 = new Project("1", qualifications, ProjectSize.BIG);
		Project album2 = new Project("2", qualifications, ProjectSize.BIG);
		Project album3 = new Project("3", qualifications, ProjectSize.BIG);
		Project album4 = new Project("4", qualifications, ProjectSize.BIG);
		Project single = new Project("5", qualifications, ProjectSize.SMALL);
		w.addProject(album1);

		assertEquals(false, w.willOverload(album2));
		w.addProject(album2);
		w.addProject(album3);
		w.addProject(album4);

		assertEquals(true, w.willOverload(single));
		assertEquals(false, w.willOverload(album2));
	}

	@Test
	public void testWorkerIsAvailable() {
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification singing = new Qualification("singing");
		Qualification guitar = new Qualification("guitar");
		qualifications.add(singing);
		qualifications.add(guitar);
		final Worker w = new Worker("Victor Vincent Fuentes the 13th Way", qualifications, 0.0);
		Project album1 = new Project("1", qualifications, ProjectSize.BIG);
		Project album2 = new Project("2", qualifications, ProjectSize.BIG);
		Project album3 = new Project("3", qualifications, ProjectSize.BIG);
		Project album4 = new Project("4", qualifications, ProjectSize.MEDIUM);
		Project single = new Project("5", qualifications, ProjectSize.SMALL);
		w.addProject(album1);
		w.addProject(album2);
		w.addProject(album3);
		w.addProject(album4);

		assertEquals(true, w.isAvailable());

		w.addProject(single);

		assertEquals(false, w.isAvailable());

	}

	@Test
	public void testWorkerToDTO() {
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification singing = new Qualification("singing");
		Qualification guitar = new Qualification("guitar");
		qualifications.add(singing);
		qualifications.add(guitar);
		final Worker w = new Worker("Victor Vincent Fuentes the 13th Way", qualifications, 200000);
		Project album1 = new Project("1", qualifications, ProjectSize.BIG);
		w.addProject(album1);

		WorkerDTO worker_dto = w.toDTO();
		assertEquals(w.getName(), worker_dto.getName());
		assertEquals(w.getSalary(), worker_dto.getSalary(), 0);
		assertEquals(w.getProjects().size(), worker_dto.getProjects().length);
		assertEquals(w.getQualifications().size(), worker_dto.getQualifications().length);

		String[] qualification_strings = new String[w.getQualifications().size()];
		String[] project_strings = new String[w.getProjects().size()];

		int i = 0;
		for(Qualification qual:w.getQualifications()){
			qualification_strings[i] = qual.toString();
			i++;
		}

		i = 0;
		for(Project proj:w.getProjects()){
			project_strings[i] = proj.getName();
			i++;
		}

		assertEquals(worker_dto.getQualifications(), qualification_strings);
		assertEquals(worker_dto.getProjects(), project_strings);

	}
	@Test
	public void testWorkerHash() {
		final Set<Qualification> qualifications = new HashSet<Qualification>();
		Qualification singing = new Qualification("singing");
		Qualification guitar = new Qualification("guitar");
		qualifications.add(singing);
		qualifications.add(guitar);
		String name = "Victor Vincent Fuentes the 13th Way";
		final Worker w = new Worker(name, qualifications, 200000);
		int expected =name.hashCode();
		assertEquals(w.hashCode(), expected);
	}

	@Test(expected = Exception.class)
	public void testAddWorkerWithNull() {
		workerOne.addProject(null);
	}

	@Test(expected = Exception.class)
	public void testRemoveWorkerWithNull() {
		workerOne.removeProject(null);
	}

	@Test(expected = Exception.class)
	public void testAddQualificationWithNull() {
		workerOne.addQualification(null);
	}
	
	@Test(expected = Exception.class)
	public void testSetSalaryNegative() {
		workerOne.setSalary(-10.90);;
	}

	@Test
	public void test_will_overload(){
		Project project_1 = new Project("project_1", qualificationSet, ProjectSize.BIG);
		Project project_2 = new Project("project_2", qualificationSet, ProjectSize.BIG);
		Project project_3 = new Project("project_3", qualificationSet, ProjectSize.BIG);
		Project project_4 = new Project("project_4", qualificationSet, ProjectSize.SMALL);
		Project project_5 = new Project("project_5", qualificationSet, ProjectSize.BIG);
		Project project_6 = new Project("project_6", qualificationSet, ProjectSize.SMALL);
		Project project_7 = new Project("project_7", qualificationSet, ProjectSize.SMALL);
		Project project_8 = new Project("project_8", qualificationSet, ProjectSize.SMALL);

		workerOne.addProject(project_1);
		assertFalse(workerOne.willOverload(project_2));

		workerOne.addProject(project_2);
		assertFalse(workerOne.willOverload(project_3));
		
		workerOne.addProject(project_3);
		assertTrue(workerOne.getWorkload() == 9);
		
		assertFalse(workerOne.willOverload(project_4));
		assertFalse(workerOne.willOverload(project_5));
		
		workerOne.addProject(project_4);
		assertTrue(workerOne.getWorkload() == 10);
		
		assertTrue(workerOne.willOverload(project_5));
		assertFalse(workerOne.willOverload(project_6));

		workerOne.addProject(project_6);
		assertTrue(workerOne.getWorkload() == 11);

		workerOne.addProject(project_7);
		assertTrue(workerOne.getWorkload() == 12);

		assertTrue(workerOne.willOverload(project_8));
	}
}