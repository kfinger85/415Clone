package edu.colostate.cs415.server;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.when;

import edu.colostate.cs415.model.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.dto.QualificationDTO;
import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.dto.AssignmentDTO;
import edu.colostate.cs415.dto.ProjectDTO;


import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.ParseException;


import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.google.gson.Gson;

public class RestControllerTest {

    private Gson gson = new Gson();
    private static DBConnector dbConnector = mock(DBConnector.class);
    private static RestController restController = new RestController(4567, dbConnector);
    private static Company testCompany;  


    // default behavior for when() method. Can be overridden in individual test methods
    // Changes made to when() in one test method do not affect others, each test runs in isolation.
    @BeforeClass
    public static void init(){
        when(dbConnector.loadCompanyData()).thenAnswer((i) -> testCompany);
    }

    // Be a good citizen: Explicitly stop the server after all tests are done.
    @AfterClass
    public static void tearDown() {
        restController.stop();
    }


    // Each test gets a fresh copy of the Company object
    @Before
    public void setUp() {
        testCompany = new Company("Test Company");
    }

    @Test
    public void testGetQualifications() throws IOException {
        testCompany.createQualification("Python");
        testCompany.createQualification("Java");
        restController.start();
        QualificationDTO[] qualifications = gson.fromJson(Request.get("http://localhost:4567/api/qualifications").execute().returnContent().asString(), QualificationDTO[].class);
        QualificationDTO[] expectedQualifications = new QualificationDTO[] {
            new QualificationDTO("Java", new String[] {}),
            new QualificationDTO("Python", new String[] {})
        };
        assertArrayEquals(expectedQualifications, qualifications);

    }
    
    @Test
    public void testGetQualification() throws IOException {
        testCompany.createQualification("Python");
        testCompany.createQualification("Java");
        restController.start();
        QualificationDTO qualifications = gson.fromJson(Request.get("http://localhost:4567/api/qualifications/Java").execute().returnContent().asString(), QualificationDTO.class);
        QualificationDTO expectedQualifications = new QualificationDTO("Java", new String[] {});
        assertEquals(expectedQualifications, qualifications);

    }

    @Test
    public void testGetQualification2() throws IOException {
        testCompany.createQualification("Python");
        testCompany.createQualification("Java");
        restController.start();
        QualificationDTO qualifications = gson.fromJson(Request.get("http://localhost:4567/api/qualifications/Python").execute().returnContent().asString(), QualificationDTO.class);
        QualificationDTO expectedQualifications = new QualificationDTO("Python", new String[] {});
        assertEquals(expectedQualifications, qualifications);

    }

    @Test
    public void testGetQualification_Exception() throws IOException{
        testCompany.createQualification("Python");

        restController.start();

        // starting with good response
        HttpResponse goodResponse = Request.get("http://localhost:4567/api/qualifications/Python").execute().returnResponse();
        int statusCodeGood = goodResponse.getCode();

        assertEquals(200, statusCodeGood);

        //bad
        HttpResponse badResponse = Request.get("http://localhost:4567/api/qualifications/python").execute().returnResponse();
        int statusCodeBad = badResponse.getCode();

        assertEquals(500, statusCodeBad);

        //bad
        HttpResponse badResponseTwo = Request.get("http://localhost:4567/api/qualifications/%20%20Python").execute().returnResponse();
        int statusCodeBadTwo = badResponseTwo.getCode();

        assertEquals(500, statusCodeBadTwo);

        //bad
        HttpResponse badResponseThree = Request.get("http://localhost:4567/api/qualifications/Python%20%20").execute().returnResponse();
        int statusCodeBadThree = badResponseThree.getCode();

        assertEquals(500, statusCodeBadThree);
    }

    // names don't match
    @Test (expected = Exception.class)
    public void testGetQualification_request_exception() throws IOException {
        testCompany.createQualification("Python");
        restController.start();

        QualificationDTO qualifications = gson.fromJson(Request.get("http://localhost:4567/api/qualifications/Java").execute().returnContent().asString(), QualificationDTO.class);
    }

    @Test
    public void testCreateQualification() throws IOException {
        QualificationDTO svelte = new QualificationDTO("svelte", new String[] { "John Walker" });
        Qualification svelteMatch = new Qualification("svelte");
        String svelteString = gson.toJson(svelte);

        restController.start();
    
        String response = Request.post("http://localhost:4567/api/qualifications/svelte")
                .bodyString(svelteString, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
    
        // response returns ok.
        assertEquals("OK", response);
        assertEquals(1, testCompany.getQualifications().size());
        assertTrue(testCompany.getQualifications().contains(svelteMatch));
    }

    // names don't match
    @Test(expected = Exception.class)
    public void testCreateQualification_Exception() throws IOException {
        QualificationDTO ook = new QualificationDTO("ook!", new String[] { "Jim Bob" });
        String ookString = gson.toJson(ook);

        restController.start();

            Request.post("http://localhost:4567/api/qualifications/notOk")
                .bodyString(ookString, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();  
    }
    
    @Test(expected = Exception.class)
    public void testCreateQualification_bodyString_null() throws IOException {
        QualificationDTO ook = new QualificationDTO("ook!", new String[] { "Jim Bob" });

        restController.start();

            Request.post("http://localhost:4567/api/qualifications/ook")
                .bodyString(null, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();  
    }

    @Test(expected = Exception.class)
    public void testCreateQualification_bodyString_empty() throws IOException {
        QualificationDTO ook = new QualificationDTO("ook!", new String[] { "Jim Bob" });
        String ookString = gson.toJson("");

        restController.start();

            Request.post("http://localhost:4567/api/qualifications/ook")
                .bodyString(ookString, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();  
    }

    @Test(expected = Exception.class)
    public void testCreateQualification_bodyString_null2() throws IOException {
        QualificationDTO ook = new QualificationDTO("ook!", new String[] { "Jim Bob" });
        String ookString = gson.toJson(null);

        restController.start();

            Request.post("http://localhost:4567/api/qualifications/ook")
                .bodyString(ookString, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();  
    }

    @Test
    public void testHello() throws IOException {
        restController.start();
		String response = Request.get("http://localhost:4567/helloworld").execute().returnContent().asString();
		assertEquals("Hello World!", response);
	}

    @Test
    public void testGetWorkers_one() throws IOException {
        Qualification ook = testCompany.createQualification("ook!");
        testCompany.createWorker("Bob_1", new HashSet<Qualification>(Arrays.asList(ook)), 10);
        restController.start();
        WorkerDTO[] workers = gson.fromJson(
                        Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(),
                        WorkerDTO[].class); 
        assertEquals(1, workers.length);
        assertEquals("Bob_1", workers[0].getName());
        assertEquals(10, workers[0].getSalary(), 0);
        assertEquals(0, workers[0].getWorkload());
    }

    @Test
    public void testGetWorkers_three() throws IOException {
        Qualification ook = testCompany.createQualification("ook!");
        testCompany.createWorker("Bob_1", new HashSet<Qualification>(Arrays.asList(ook)), 10);
        testCompany.createWorker("Sue", new HashSet<Qualification>(Arrays.asList(ook)), 11);
        testCompany.createWorker("Big John", new HashSet<Qualification>(Arrays.asList(ook)), 12);
        restController.start();
        WorkerDTO[] workers = gson.fromJson(
                        Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(),
                        WorkerDTO[].class); 
        
        assertEquals(3, workers.length);
        Arrays.sort(workers, Comparator.comparing(workerDTO -> workerDTO.getName()));


        assertEquals("Big John", workers[0].getName());
        assertEquals(12, workers[0].getSalary(), 0);
        assertEquals(0, workers[0].getWorkload());

        assertEquals("Bob_1", workers[1].getName());
        assertEquals(10, workers[1].getSalary(), 0);
        assertEquals(0, workers[1].getWorkload());

        assertEquals("Sue", workers[2].getName());
        assertEquals(11, workers[2].getSalary(), 0);
        assertEquals(0, workers[2].getWorkload());


    }

    @Test(expected = Exception.class)
    public void testGetWorker_Exception() throws IOException {
        Qualification ook = testCompany.createQualification("ook!");
        testCompany.createWorker("Bob", new HashSet<Qualification>(Arrays.asList(ook)), 10);
        restController.start();
        WorkerDTO workerBob = gson.fromJson(
                        Request.get("http://localhost:4567/api/workers/NotBob").execute().returnContent().asString(),
                        WorkerDTO.class); 
    }

    @Test
    public void testGetWorker() throws IOException {
        Qualification ook = testCompany.createQualification("ook!");
        testCompany.createWorker("Bob", new HashSet<Qualification>(Arrays.asList(ook)), 10);
        testCompany.createWorker("Sue", new HashSet<Qualification>(Arrays.asList(ook)), 11);
        testCompany.createWorker("Big John", new HashSet<Qualification>(Arrays.asList(ook)), 12);
        testCompany.createWorker("A E I O U sometimes Y", new HashSet<Qualification>(Arrays.asList(ook)), 12);
        restController.start();
        WorkerDTO workerBob = gson.fromJson(
                        Request.get("http://localhost:4567/api/workers/Bob").execute().returnContent().asString(),
                        WorkerDTO.class); 
        assertEquals("Bob", workerBob.getName());
        assertEquals(10, workerBob.getSalary(), 0);

        WorkerDTO workerSue = gson.fromJson(
                        Request.get("http://localhost:4567/api/workers/Sue").execute().returnContent().asString(),
               WorkerDTO.class); 
        assertEquals("Sue", workerSue.getName());
        assertEquals(11, workerSue.getSalary(), 0);

        WorkerDTO workerBigJohn = gson.fromJson(
                        Request.get("http://localhost:4567/api/workers/Big%20John").execute().returnContent().asString(),
               WorkerDTO.class); 
        assertEquals("Big John", workerBigJohn.getName());
        assertEquals(12, workerBigJohn.getSalary(), 0);

        WorkerDTO workerVowel = gson.fromJson(
                        Request.get("http://localhost:4567/api/workers/A%20E%20I%20O%20U%20sometimes%20Y").execute().returnContent().asString(),
               WorkerDTO.class); 
        assertEquals("A E I O U sometimes Y", workerVowel.getName());
        assertEquals(12, workerVowel.getSalary(), 0);
    }

    @Test
    public void testCreateWorker() throws IOException {
        testCompany.createQualification("Java");
        Worker workerBob = new Worker("Jim Bob",new HashSet<Qualification>(Arrays.asList(new Qualification("Java"))),10);
        String bobString = gson.toJson(workerBob.toDTO());

        restController.start();

        String response = Request.post("http://localhost:4567/api/workers/Jim%20Bob")
                .bodyString(bobString, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();

        // response returns ok.
        assertEquals("OK", response);
        assertTrue(testCompany.getEmployedWorkers().contains(workerBob));
        assertEquals(1, testCompany.getEmployedWorkers().size());
    }

     // worker name different from url
     @Test(expected = Exception.class)
     public void testCreateWorker_Exception() throws IOException {
         testCompany.createQualification("Java");
         Worker workerBob = new Worker("Jim Bob",new HashSet<Qualification>(Arrays.asList(new Qualification("Java"))),10);
         String bobString = gson.toJson(workerBob.toDTO());
 
         restController.start();
 
         Request.post("http://localhost:4567/api/workers/Not%20Bob")
                 .bodyString(bobString, ContentType.APPLICATION_JSON)
                 .execute()
                 .returnContent()
                 .asString();
     }

      @Test (expected = Exception.class)
      public void testCreateWorker_bodyString_null() throws IOException {
          testCompany.createQualification("Java");
          Worker workerBob = new Worker("Jim Bob",new HashSet<Qualification>(Arrays.asList(new Qualification("Java"))),10);
  
          restController.start();
  
          Request.post("http://localhost:4567/api/workers/Jim%20Bob")
                  .bodyString(null, ContentType.APPLICATION_JSON)
                  .execute()
                  .returnContent()
                  .asString();
      }

      @Test (expected = Exception.class)
      public void testCreateWorker_bodyString_empty() throws IOException {
          testCompany.createQualification("Java");
          Worker workerBob = new Worker("Jim Bob",new HashSet<Qualification>(Arrays.asList(new Qualification("Java"))),10);
          String bobString = gson.toJson("");
  
          restController.start();
  
          Request.post("http://localhost:4567/api/workers/Jim%20Bob")
                  .bodyString(bobString, ContentType.APPLICATION_JSON)
                  .execute()
                  .returnContent()
                  .asString();
      }

      @Test (expected = Exception.class)
      public void testCreateWorker_bodyString_null2() throws IOException {
          testCompany.createQualification("Java");
          Worker workerBob = new Worker("Jim Bob",new HashSet<Qualification>(Arrays.asList(new Qualification("Java"))),10);
          String bobString = gson.toJson(null);
  
          restController.start();
  
          Request.post("http://localhost:4567/api/workers/Jim%20Bob")
                  .bodyString(bobString, ContentType.APPLICATION_JSON)
                  .execute()
                  .returnContent()
                  .asString();
      }

    @Test
    public void testCreateWorker_get_post() throws IOException {
        testCompany.createQualification("ook!");
        testCompany.createQualification("chicken");
     
        Worker bob = new Worker("Jim Bob",new HashSet<Qualification>(Arrays.asList(new Qualification("ook!"))),10);
        String bobString = gson.toJson(bob.toDTO());

        Worker sue = new Worker("Sue",new HashSet<Qualification>(Arrays.asList(new Qualification("chicken"))),11);
        String sueString = gson.toJson(sue.toDTO());

        restController.start();

        String responseJimBob = Request.post("http://localhost:4567/api/workers/Jim%20Bob")
                .bodyString(bobString, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
        
        assertEquals(1, testCompany.getEmployedWorkers().size());
        assertEquals("OK", responseJimBob);
        assertTrue(testCompany.getEmployedWorkers().contains(bob));

        String responseSue = Request.post("http://localhost:4567/api/workers/Sue")
                .bodyString(sueString, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();

        // response returns ok.
        assertEquals("OK", responseSue);
        assertTrue(testCompany.getEmployedWorkers().contains(sue));
        assertEquals(2, testCompany.getEmployedWorkers().size());

        
        
        // now test 'get' correctly works after creating a worker with 'post'
        WorkerDTO workerBob = gson.fromJson(
                        Request.get("http://localhost:4567/api/workers/Jim%20Bob").execute().returnContent().asString(),
                        WorkerDTO.class);
        
       
        assertEquals("Jim Bob", workerBob.getName());
        assertEquals(10, workerBob.getSalary(), 0);
        assertEquals(1, workerBob.getQualifications().length);
        assertEquals("ook!", workerBob.getQualifications()[0]);
        assertEquals(0, workerBob.getProjects().length);
        
        // test get Sue
        WorkerDTO workerSue = gson.fromJson(
                        Request.get("http://localhost:4567/api/workers/Sue").execute().returnContent().asString(),
               WorkerDTO.class);

        assertEquals("Sue", workerSue.getName());
        assertEquals(11, workerSue.getSalary(), 0);
        assertEquals(1, workerSue.getQualifications().length);
        assertEquals("chicken", workerSue.getQualifications()[0]);
        assertEquals(0, workerSue.getProjects().length);
    }

    @Test 
    public void testGetWorkers_get_post() throws IOException {
        Qualification ook = testCompany.createQualification("ook!");
        testCompany.createWorker("Bob", new HashSet<Qualification>(Arrays.asList(ook)), 10);
        testCompany.createWorker("Sue", new HashSet<Qualification>(Arrays.asList(ook)), 11);
        testCompany.createWorker("Big John", new HashSet<Qualification>(Arrays.asList(ook)), 12);

        Worker bob = new Worker("Bob", new HashSet<Qualification>(Arrays.asList(new Qualification("ook!"))), 10);
        String bobString = gson.toJson(bob.toDTO());

        Worker sue = new Worker("Sue", new HashSet<Qualification>(Arrays.asList(new Qualification("ook!"))), 11);
        String sueString = gson.toJson(sue.toDTO());

        Worker bigJohn = new Worker("Big John", new HashSet<Qualification>(Arrays.asList(new Qualification("ook!"))),
                12);
        String bigJohnString = gson.toJson(bigJohn.toDTO());

        restController.start();

        Request.post("http://localhost:4567/api/workers/Bob")
                .bodyString(bobString, ContentType.APPLICATION_JSON).execute().returnContent().asString();

        Request.post("http://localhost:4567/api/workers/Sue")
                .bodyString(sueString, ContentType.APPLICATION_JSON).execute().returnContent().asString();

        Request.post("http://localhost:4567/api/workers/Big%20John")
                .bodyString(bigJohnString, ContentType.APPLICATION_JSON).execute().returnContent().asString();
        
        // check size after post      
        assertEquals(3, testCompany.getEmployedWorkers().size());

        WorkerDTO[] workers = gson.fromJson(
                    Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(),
                    WorkerDTO[].class); 
    
        // check size after get
        assertEquals(3, workers.length);


        String[] workerNameArray = { workers[0].getName(), workers[1].getName(), workers[2].getName() };
		Arrays.sort(workerNameArray);

        double[] workerSalaryArray = { workers[0].getSalary(), workers[1].getSalary(), workers[2].getSalary() };
		Arrays.sort(workerSalaryArray);

        //check workers are there. 
        assertTrue(testCompany.getEmployedWorkers().contains(bob));
        assertTrue(testCompany.getEmployedWorkers().contains(sue));
        assertTrue(testCompany.getEmployedWorkers().contains(bigJohn));

        assertEquals("Big John", workerNameArray[0]);
        assertEquals("Bob", workerNameArray[1]);
        assertEquals("Sue", workerNameArray[2]);

        assertTrue(workerSalaryArray[0] == 10);
        assertTrue(workerSalaryArray[1] == 11);
        assertTrue(workerSalaryArray[2] == 12);
    
    }

    @Test
    public void testGetProjects() throws IOException {
        Qualification java = testCompany.createQualification("Java");
        Qualification rest = testCompany.createQualification("Rest");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(java);
        qualificationSet.add(rest);
        testCompany.createProject("P3", qualificationSet, ProjectSize.SMALL);
        Project p4 = testCompany.createProject("P4", qualificationSet, ProjectSize.MEDIUM);
        Worker bob = testCompany.createWorker("Bob_1", new HashSet<Qualification>(Arrays.asList(java, rest)), 10);
        testCompany.assign(bob, p4);
        restController.start();
        ProjectDTO[] projects = gson.fromJson(
                        Request.get("http://localhost:4567/api/projects").execute().returnContent().asString(),
                        ProjectDTO[].class); 
        assertEquals(2, projects.length);

        Arrays.sort(projects, Comparator.comparing(projectDTO -> projectDTO.getName()));

        assertEquals("P3", projects[0].getName());
        assertEquals("P4", projects[1].getName());
        assertEquals(ProjectSize.SMALL, projects[0].getSize());
        assertEquals(ProjectSize.MEDIUM, projects[1].getSize());
        assertEquals(0, projects[0].getWorkers().length);
        assertEquals(1, projects[1].getWorkers().length);
    }

    @Test
    public void testGetProject() throws IOException {
        Qualification java = testCompany.createQualification("Java");
        Qualification rest = testCompany.createQualification("Rest");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(java);
        qualificationSet.add(rest);
        testCompany.createProject("P3", qualificationSet, ProjectSize.SMALL);
        Project p4 = testCompany.createProject("P4", qualificationSet, ProjectSize.MEDIUM);
        restController.start();
        ProjectDTO project = gson.fromJson(
                        Request.get("http://localhost:4567/api/projects/P4").execute().returnContent().asString(),
                        ProjectDTO.class); 
        assertEquals(p4.toDTO(), project);
    }

    @Test
    public void testGetProject2() throws IOException {
        Qualification java = testCompany.createQualification("Java");
        Qualification rest = testCompany.createQualification("Rest");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(java);
        qualificationSet.add(rest);
        Project p3 = testCompany.createProject("P3", qualificationSet, ProjectSize.SMALL);
        testCompany.createProject("P4", qualificationSet, ProjectSize.MEDIUM);
        restController.start();
        ProjectDTO project = gson.fromJson(
                        Request.get("http://localhost:4567/api/projects/P3").execute().returnContent().asString(),
                        ProjectDTO.class); 
        assertEquals(p3.toDTO(), project);
    }

    @Test (expected = Exception.class)
    public void testGetProject_projectNotFound() throws IOException {
        Qualification ook = testCompany.createQualification("ook!");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(ook);
        testCompany.createProject("project", qualificationSet, ProjectSize.SMALL);
        restController.start();
        
       gson.fromJson(Request.get("http://localhost:4567/api/projects/projectNotFound")
                    .execute()
                    .returnContent()
                    .asString(),
                    ProjectDTO.class); 
    }

    @Test
    public void testCreateProject() throws IOException {
        testCompany.createQualification("Java");
        Project p4Match = new Project("P4",new HashSet<Qualification>(Arrays.asList(new Qualification("Java"))),ProjectSize.MEDIUM);
        ProjectDTO p4 = p4Match.toDTO();
        String p4String = gson.toJson(p4);

        restController.start();

        String response = Request.post("http://localhost:4567/api/projects/P4")
                .bodyString(p4String, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();

        // response returns ok.
        assertEquals("OK", response);
        assertTrue(testCompany.getProjects().contains(p4Match));
        assertEquals(1, testCompany.getProjects().size());
    }

    @Test(expected = Exception.class)
    public void testCreateProject_null() throws IOException {
        testCompany.createQualification("ook!");
        Project project = new Project("project",new HashSet<Qualification>(Arrays.asList(new Qualification("ook!"))),ProjectSize.MEDIUM);

        restController.start();

        String response = Request.post("http://localhost:4567/api/projects/project")
                .bodyString(null, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
    }

    @Test(expected = Exception.class)
    public void testCreateProject_nullTwo() throws IOException {
        testCompany.createQualification("ook!");
        Project project = new Project("project",new HashSet<Qualification>(Arrays.asList(new Qualification("ook!"))),ProjectSize.MEDIUM);
        String projectString = gson.toJson(null);

        restController.start();

        String response = Request.post("http://localhost:4567/api/projects/project")
                .bodyString(projectString, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
    }

    @Test(expected = Exception.class)
    public void testCreateProject_empty() throws IOException {
        testCompany.createQualification("ook!");
        Project project = new Project("project",new HashSet<Qualification>(Arrays.asList(new Qualification("ook!"))),ProjectSize.MEDIUM);
        String projectString = gson.toJson("");

        restController.start();

        String response = Request.post("http://localhost:4567/api/projects/project")
                .bodyString(projectString, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
    }

    @Test(expected = Exception.class)
    public void testCreateProject_differentNames() throws IOException {
        testCompany.createQualification("ook!");
        Project project = new Project("project",new HashSet<Qualification>(Arrays.asList(new Qualification("ook!"))),ProjectSize.MEDIUM);
        String projectString = gson.toJson(project.toDTO());

        restController.start();

        String response = Request.post("http://localhost:4567/api/projects/project2")
                .bodyString(projectString, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
    }

    @Test
    public void testAssignOneWorker() throws IOException, ParseException {
        Qualification java = testCompany.createQualification("Java");
        Qualification rest = testCompany.createQualification("Rest");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(java);
        qualificationSet.add(rest);
        String workerName = "Test Worker";
        String projectName = "Test Project";
        Worker worker = testCompany.createWorker(workerName, qualificationSet, 50000);
        Project project = testCompany.createProject(projectName, qualificationSet, ProjectSize.SMALL);


        AssignmentDTO assignmentDTO = new AssignmentDTO(workerName, projectName);
        String requestBody = gson.toJson(assignmentDTO);

        // Pre-condition
        assertTrue(testCompany.getAssignedWorkers().isEmpty());
        
        restController.start();


        Map<String, Object> response = putRequestHelper(requestBody, "assign");
        int statusCode = (int) response.get("statusCode");
        String responseString = (String) response.get("responseString");

        Worker assignedWorker = testCompany.getEmployedWorkers().stream()
                .filter(findWorker -> worker.getName().equals(workerName))
                .findFirst().orElse(null);

        Project assignedProject = testCompany.getProjects().stream()
                .filter(findproject -> project.getName().equals(projectName))
                .findFirst().orElse(null);

            assertEquals(200, statusCode);
            assertEquals("\"OK\"", responseString);



                assertTrue(testCompany.getAssignedWorkers().contains(worker));
                assertTrue(assignedProject.getWorkers().contains(worker));
                assertTrue(assignedWorker.getProjects().contains(assignedProject));
    }

    //testing assign to different company
    @Test(expected = Exception.class)
    public void testAssign_different_company() throws IOException{
        Company companyTwo = new Company("Company Two");

        Qualification java = testCompany.createQualification("Java");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(java);
        String workerName = "Bob";
        String projectName = "project";
        Worker worker = testCompany.createWorker(workerName, qualificationSet, 50000);
        Project project = companyTwo.createProject(projectName, qualificationSet, ProjectSize.SMALL);


        AssignmentDTO assignmentDTO = new AssignmentDTO(workerName, projectName);
        String requestBody = gson.toJson(assignmentDTO);

        // Pre-condition
        assertTrue(testCompany.getAssignedWorkers().isEmpty());
        
        restController.start();


        Request.put("http://localhost:4567/api/assign")
        .bodyString(requestBody, ContentType.APPLICATION_JSON)
        .execute().returnContent().asString();
    }
 
    @Test
    public void testAassign_multiple() throws IOException{
    
        Qualification java = testCompany.createQualification("Java");
        Qualification python = testCompany.createQualification("Python");
        Qualification ook = testCompany.createQualification("ook!");
        Qualification chicken = testCompany.createQualification("chicken");
        
        Set<Qualification> qualificationSet = new HashSet<Qualification>(Arrays.asList(java, python, ook, chicken));

        String workerBob = "Bob_1";
        String workerBobTwo = "Bob_2";
        String workerBobThree = "Bob_3";
        String workerBobFour = "Bob_4";
        
        Worker worker_1 = testCompany.createWorker(workerBob, new HashSet<Qualification>(Arrays.asList(java)), 50000);
        Worker worker_2 = testCompany.createWorker(workerBobTwo, new HashSet<Qualification>(Arrays.asList(python)), 50000);
        Worker worker_3 = testCompany.createWorker(workerBobThree, new HashSet<Qualification>(Arrays.asList(ook)), 50000);
        Worker worker_4 = testCompany.createWorker(workerBobFour, new HashSet<Qualification>(Arrays.asList(chicken)), 50000);
        
        String projectName = "project";
        Project testProject = testCompany.createProject(projectName, qualificationSet, ProjectSize.SMALL);

        AssignmentDTO assignmentDTO_1 = new AssignmentDTO(workerBob, projectName);
        AssignmentDTO assignmentDTO_2 = new AssignmentDTO(workerBobTwo, projectName);
        AssignmentDTO assignmentDTO_3 = new AssignmentDTO(workerBobThree, projectName);
        AssignmentDTO assignmentDTO_4 = new AssignmentDTO(workerBobFour, projectName);
        
        String requestOne = gson.toJson(assignmentDTO_1);
        String requestTwo = gson.toJson(assignmentDTO_2);
        String requestThree = gson.toJson(assignmentDTO_3);
        String requestFour = gson.toJson(assignmentDTO_4);

        assertTrue(testCompany.getAssignedWorkers().isEmpty());

        restController.start();

        assertTrue(testCompany.getAssignedWorkers().isEmpty());

        // correctly assign
        Request.put("http://localhost:4567/api/assign")
        .bodyString(requestOne, ContentType.APPLICATION_JSON)
        .execute().returnContent().asString();

        assertTrue(testCompany.getAssignedWorkers().contains(worker_1));
        assertTrue(testCompany.getAssignedWorkers().size() == 1);

        Request.put("http://localhost:4567/api/assign")
        .bodyString(requestTwo, ContentType.APPLICATION_JSON)
        .execute().returnContent().asString();

        assertTrue(testCompany.getAssignedWorkers().contains(worker_2));
        assertTrue(testCompany.getAssignedWorkers().size() == 2);

        Request.put("http://localhost:4567/api/assign")
        .bodyString(requestThree, ContentType.APPLICATION_JSON)
        .execute().returnContent().asString();

        assertTrue(testCompany.getAssignedWorkers().contains(worker_3));
        assertTrue(testCompany.getAssignedWorkers().size() == 3);

        Request.put("http://localhost:4567/api/assign")
        .bodyString(requestFour, ContentType.APPLICATION_JSON)
        .execute().returnContent().asString();

        assertTrue(testCompany.getAssignedWorkers().contains(worker_4));
        assertTrue(testCompany.getAssignedWorkers().size() == 4);
    }

    @Test
    public void testAssignBadRequest() throws IOException, ParseException {
        String workerName = "Test Worker";
        String projectName = "Test Project";
        AssignmentDTO assignmentDTO = new AssignmentDTO(workerName, projectName);
        String requestBody = gson.toJson(assignmentDTO);
    
        // Pre-condition
        assertTrue(testCompany.getAssignedWorkers().isEmpty());
    
        restController.start();
    
        Map<String, Object> response = putRequestHelper(requestBody, "assign");
        int statusCode = (int) response.get("statusCode");
        String responseString = (String) response.get("responseString");
    
        assertEquals(500, statusCode);
        assertEquals("KO", responseString);
    }

    @Test
    public void testAssignEmptyRequest() throws IOException, ParseException {
        String requestBody = " "; 
        
        restController.start();
    
        Map<String, Object> response = putRequestHelper(requestBody, "assign");
        int statusCode = (int) response.get("statusCode");
        String responseString = (String) response.get("responseString");
    
        assertEquals(500, statusCode);
        assertEquals("KO", responseString);
    }

    @Test //(expected = Exception.class)
    public void testUnassign_different_company() throws IOException{
        int statusCode = -1;

        Qualification java = testCompany.createQualification("Java");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(java);
        String workerName = "Bob";
        String projectName = "project";
        Worker worker = testCompany.createWorker(workerName, qualificationSet, 50000);
        Project projectSame = testCompany.createProject(projectName, qualificationSet, ProjectSize.SMALL);

        AssignmentDTO assignmentDTO = new AssignmentDTO(workerName, projectName);
        String requestBody = gson.toJson(assignmentDTO);

         // second company
         Company companyTwo = new Company("Company Two");
         String projectNameTwo = "project two";
         Project projectDifferent = companyTwo.createProject(projectNameTwo, qualificationSet, ProjectSize.SMALL);
 
         AssignmentDTO assignmentDTO_Two = new AssignmentDTO(workerName, projectNameTwo);
         String requestBodyTwo = gson.toJson(assignmentDTO_Two);

        restController.start();

        // correctly assign
        Request.put("http://localhost:4567/api/assign")
        .bodyString(requestBody, ContentType.APPLICATION_JSON)
        .execute().returnContent().asString();

        assertTrue(testCompany.getAssignedWorkers().contains(worker));

        //try unassign worker from different company 
        try {
            Request.put("http://localhost:4567/api/unassign")
        .bodyString(requestBodyTwo, ContentType.APPLICATION_JSON)
        .execute().returnContent().asString();
        }
        catch(Exception e){
            statusCode = 500;
        }
        
        assertTrue(statusCode == 500);
        
        //worker should still be assigned in testCompany
        assertTrue(testCompany.getAssignedWorkers().contains(worker));
    }

    @Test
    public void testUnassignOneWorker() throws IOException, ParseException {
        Qualification java = testCompany.createQualification("Java");
        Qualification rest = testCompany.createQualification("Rest");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(java);
        qualificationSet.add(rest);
        String workerName = "Test Worker";
        String projectName = "Test Project";
        Worker worker = testCompany.createWorker(workerName, qualificationSet, 50000);
        Project project = testCompany.createProject(projectName, qualificationSet, ProjectSize.SMALL);
        testCompany.assign(worker, project);

        AssignmentDTO assignmentDTO = new AssignmentDTO(workerName, projectName);
        String requestBody = gson.toJson(assignmentDTO);

        // Pre-condition
        assertFalse(testCompany.getAssignedWorkers().isEmpty());
        
        restController.start();


        Map<String, Object> response = putRequestHelper(requestBody, "unassign");
        int statusCode = (int) response.get("statusCode");
        String responseString = (String) response.get("responseString");

        Worker assignedWorker = testCompany.getEmployedWorkers().stream()
                .filter(findWorker -> worker.getName().equals(workerName))
                .findFirst().orElse(null);

                assertEquals(200, statusCode);
                assertEquals("\"OK\"", responseString);
                assertFalse(testCompany.getAssignedWorkers().contains(assignedWorker));
    }    

    @Test
    public void testUnassignBadRequest() throws IOException, ParseException {
        Qualification java = testCompany.createQualification("Java");
        Qualification rest = testCompany.createQualification("Rest");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(java);
        qualificationSet.add(rest);
        String workerName = "Test Worker";
        String projectName = "Test Project";
        testCompany.createWorker(workerName, qualificationSet, 50000);
        testCompany.createProject(projectName, qualificationSet, ProjectSize.SMALL);
        AssignmentDTO assignmentDTO = new AssignmentDTO(workerName, projectName);
        String requestBody = gson.toJson(assignmentDTO);
   
        restController.start();
    
        Map<String, Object> response = putRequestHelper(requestBody, "unassign");
        int statusCode = (int) response.get("statusCode");
        String responseString = (String) response.get("responseString");

    
        assertEquals(500, statusCode);
        assertEquals("KO", responseString);
    }

    @Test
    public void testUnassignEmptyBody() throws IOException, ParseException {
        String requestBody = ""; 
        
        restController.start();
    
        Map<String, Object> response = putRequestHelper(requestBody, "unassign");
        int statusCode = (int) response.get("statusCode");
        String responseString = (String) response.get("responseString");
    
        assertEquals(500, statusCode);
        assertEquals("KO", responseString);
    }
    @Test
    public void testStartEmptyBody() throws IOException, ParseException{
        String rb = "";
        restController.start();
        Map<String, Object> res = putRequestHelper(rb, "start");
        assertEquals(500, res.get("statusCode"));
        assertEquals("KO", res.get("responseString"));
    }
    @Test
    public void testStartMalformedBody() throws IOException, ParseException{
        String rb = "Garbage";
        restController.start();
        Map<String, Object> res = putRequestHelper(rb, "start");
        assertEquals(500, res.get("statusCode"));
        assertEquals("KO", res.get("responseString"));
    }
    @Test
    public void testStartNoName() throws IOException, ParseException{
        Qualification java = testCompany.createQualification("Java");
        Qualification rest = testCompany.createQualification("Rest");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(java);
        qualificationSet.add(rest);
        String name = gson.toJson(new Project("NotInCompany",qualificationSet,ProjectSize.BIG).toDTO());
        String rb = name.replace("NotInCompany","");
        restController.start();
        Map<String, Object> res = putRequestHelper(rb, "start");
        assertEquals("KO", res.get("responseString"));
        assertEquals(500, res.get("statusCode"));
    }
    @Test
    public void testStart() throws IOException, ParseException {
        Qualification java = testCompany.createQualification("Java");
        Qualification rest = testCompany.createQualification("Rest");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(java);
        qualificationSet.add(rest);
        Project p4 = testCompany.createProject("P4", qualificationSet, ProjectSize.MEDIUM);
        Worker bob = testCompany.createWorker("Bob_1", new HashSet<Qualification>(Arrays.asList(java, rest)), 10);
        testCompany.assign(bob, p4);
        restController.start();

        String rb = gson.toJson(p4.toDTO());
        assertTrue(p4.getStatus() != ProjectStatus.ACTIVE);
        Map<String, Object> res = putRequestHelper(rb, "start");
        assertEquals("\"OK\"", res.get("responseString"));
        assertEquals(200, res.get("statusCode"));
        assertTrue(p4.getStatus() == ProjectStatus.ACTIVE);
    }
    @Test
    public void testStartCantStart() throws IOException, ParseException {
        Qualification java = testCompany.createQualification("Java");
        Qualification rest = testCompany.createQualification("Rest");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(java);
        qualificationSet.add(rest);
        Project p4 = testCompany.createProject("P4", qualificationSet, ProjectSize.MEDIUM);
        Worker bob = testCompany.createWorker("Bob_1", new HashSet<Qualification>(Arrays.asList(rest)), 10);
        testCompany.assign(bob, p4);
        restController.start();

        String rb = gson.toJson(p4.toDTO());
        Map<String, Object> res = putRequestHelper(rb, "start");
        assertEquals("KO", res.get("responseString"));
        assertEquals(500, res.get("statusCode"));
    }
    @Test
    public void testStartNoProj() throws IOException, ParseException {
        Qualification java = testCompany.createQualification("Java");
        Qualification rest = testCompany.createQualification("Rest");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(java);
        qualificationSet.add(rest);
        Project p4 = testCompany.createProject("P4", qualificationSet, ProjectSize.MEDIUM);
        Worker bob = testCompany.createWorker("Bob_1", new HashSet<Qualification>(Arrays.asList(rest)), 10);
        testCompany.assign(bob, p4);
        restController.start();

        String rb = gson.toJson(new Project("NotInCompany",qualificationSet,ProjectSize.BIG).toDTO());
        Map<String, Object> res = putRequestHelper(rb, "start");
        assertEquals("KO", res.get("responseString"));
        assertEquals(500, res.get("statusCode"));
    }

    @Test
    public void testFinishStatus() throws IOException, ParseException {
		Qualification java =  testCompany.createQualification("java");
		Qualification html = testCompany.createQualification("html");
		Qualification css = testCompany.createQualification("css");
		Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(java);
		qualifications.add(html);
		qualifications.add(css);

		Project project = testCompany.createProject("project", qualifications, ProjectSize.MEDIUM);

		Worker w1 = testCompany.createWorker("w1", new HashSet<Qualification>() {{
			add(java);
		}}, 200);
		Worker w2 = testCompany.createWorker("w2", new HashSet<Qualification>() {{
			add(html);
		}}, 200);		
		Worker w3 = testCompany.createWorker("w3", new HashSet<Qualification>() {{
			add(css);
		}}, 200);

		testCompany.assign(w1,project);
		testCompany.assign(w2,project);
		testCompany.assign(w3,project);
		testCompany.start(project);

        ProjectDTO ProjectDTO = project.toDTO();
        String requestBody = gson.toJson(ProjectDTO);

        // Pre-condition
        assertTrue(project.getStatus() == ProjectStatus.ACTIVE);
        
        restController.start();

        Map<String, Object> response = putRequestHelper(requestBody, "finish");
        int statusCode = (int) response.get("statusCode");
        String responseString = (String) response.get("responseString");


        assertEquals(200, statusCode);
        assertEquals("\"OK\"", responseString);
        assertTrue(project.getStatus() == ProjectStatus.FINISHED);
    }
    
    @Test
    public void testFinishEmptyBody() throws IOException, ParseException{
        String requestBody = "";
        restController.start();
        Map<String, Object> res = putRequestHelper(requestBody, "finish");
        assertEquals(500, res.get("statusCode"));
        assertEquals("KO", res.get("responseString"));
    }

    @Test 
    public void testFinishNonexistentProject() throws IOException, ParseException{
        Qualification java =  testCompany.createQualification("java");
		Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(java);
		Project project =  new Project("Non-Existent-Project", qualifications, ProjectSize.MEDIUM);
        ProjectDTO ProjectDTO = project.toDTO();
        String requestBody = gson.toJson(ProjectDTO);

        restController.start();
        Map<String, Object> res = putRequestHelper(requestBody, "finish");
        assertEquals(500, res.get("statusCode"));
        assertEquals("KO", res.get("responseString"));
    }

    @Test
    public void testWorkerAssignmentAssignProjectNull() throws IOException, ParseException {
        workerAssignmentHelper(false, "assign");

    }

    @Test
    public void testWorkerAssignmentAssignWorkerNull() throws IOException, ParseException {
        workerAssignmentHelper(true, "assign");
    }

    @Test
    public void testWorkerAssignmentUnassignProjectNull() throws IOException, ParseException {
        workerAssignmentHelper(false, "unassign");
    }

    @Test
    public void testWorkerAssignmentUnassignWorkerNull() throws IOException, ParseException {
        workerAssignmentHelper(true, "unassign");
    }

    // Helper methods

    // Branch coverage for workerAssignment()
    private void workerAssignmentHelper(Boolean workerNull, String path) throws IOException, ParseException
    {
        AssignmentDTO assignmentDTO; 
        Qualification java = testCompany.createQualification("Java");
        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(java);
        if(workerNull)
        {
            Project project = testCompany.createProject("WheresTheWorkers?", qualificationSet, ProjectSize.MEDIUM);
            assignmentDTO = new AssignmentDTO(null, project.getName());
        }
        else
        {
            Worker worker = testCompany.createWorker("Test Worker", qualificationSet, 50000);
            assignmentDTO = new AssignmentDTO(worker.getName(), null);
        }
        String requestBody = gson.toJson(assignmentDTO);
        restController.start();
        Map<String, Object> response = putRequestHelper(requestBody, path);
        assertEquals("KO", response.get("responseString"));
        assertEquals(500, response.get("statusCode"));
    }

    private Map<String, Object> putRequestHelper(String requestBody, String path ) throws IOException, ParseException {
        Map<String, Object> result = new HashMap<>();
        int statusCode;
        String responseString;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(String.format("http://localhost:4567/api/%s", path));
            httpPut.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
    
            ClassicHttpResponse httpResponse = httpClient.execute(httpPut);
            HttpEntity entity = httpResponse.getEntity();
            responseString = EntityUtils.toString(entity, "UTF-8");
    
            statusCode = httpResponse.getCode();
            result.put("statusCode", statusCode);
            result.put("responseString", responseString);
            return result;
        }    
    }

    @Test
    public void testStart_ld() throws IOException, ParseException {
         // create qualification request
         String chickenString = "chicken";
         QualificationDTO chickenDTO = new QualificationDTO(chickenString, new String[] {});
         String qualificationRequest = gson.toJson(chickenDTO);
 
         restController.start();
 
         Request.post("http://localhost:4567/api/qualifications/chicken")
                 .bodyString(qualificationRequest, ContentType.APPLICATION_JSON)
                 .execute().returnContent().asString();
 
         assertTrue(testCompany.getQualifications().size() == 1);

        Set<Qualification> qualificationSet = new HashSet<Qualification>();
        qualificationSet.add(new Qualification(chickenString));
        Project p4 = testCompany.createProject("project", qualificationSet, ProjectSize.MEDIUM);
        
          // create worker request
          Worker workerBob = new Worker("Jim Bob",qualificationSet,10);
          String bobString = gson.toJson(workerBob.toDTO());
  
          Request.post("http://localhost:4567/api/workers/Jim%20Bob")
                  .bodyString(bobString, ContentType.APPLICATION_JSON)
                  .execute()
                  .returnContent()
                  .asString();
  
          assertTrue(testCompany.getEmployedWorkers().contains(workerBob));

         //create project request
         String projectRequest = gson.toJson(p4.toDTO());
 
 
         Request.post("http://localhost:4567/api/projects/project")
                 .bodyString(projectRequest, ContentType.APPLICATION_JSON)
                 .execute()
                 .returnContent()
                 .asString();
 
         assertTrue(testCompany.getProjects().contains(p4));
         

        //assign request
        String workerName = "Jim Bob";
        String projectName = "project";
        AssignmentDTO assignmentDTO = new AssignmentDTO(workerName, projectName);
        String assignRequest = gson.toJson(assignmentDTO);

        Request.put("http://localhost:4567/api/assign")
        .bodyString(assignRequest, ContentType.APPLICATION_JSON)
        .execute().returnContent().asString();

        assertTrue(testCompany.getAssignedWorkers().contains(workerBob));
    
        //start request
        String startRequest = gson.toJson(p4.toDTO());
        assertTrue(p4.getStatus() != ProjectStatus.ACTIVE);
        Map<String, Object> resStart = putRequestHelper(startRequest, "start");
        assertEquals("\"OK\"", resStart.get("responseString"));
        assertEquals(200, resStart.get("statusCode"));
        assertTrue(p4.getStatus() == ProjectStatus.ACTIVE);

        //unassign
        Request.put("http://localhost:4567/api/unassign")
        .bodyString(assignRequest, ContentType.APPLICATION_JSON)
        .execute().returnContent().asString();

        assertTrue(testCompany.getAssignedWorkers().isEmpty());

        //assign
        Request.put("http://localhost:4567/api/assign")
        .bodyString(assignRequest, ContentType.APPLICATION_JSON)
        .execute().returnContent().asString();

        assertTrue(testCompany.getAssignedWorkers().contains(workerBob));

         //start request (starting again because removed worker and added back)
         assertTrue(p4.getStatus() != ProjectStatus.ACTIVE);
         putRequestHelper(startRequest, "start");
         assertEquals("\"OK\"", resStart.get("responseString"));
         assertEquals(200, resStart.get("statusCode"));
         assertTrue(p4.getStatus() == ProjectStatus.ACTIVE);

         //finish request
         String finishRequest = gson.toJson(p4.toDTO());
         assertTrue(p4.getStatus() != ProjectStatus.FINISHED);
         Map<String, Object> resFinish = putRequestHelper(finishRequest, "finish");
         assertEquals("\"OK\"", resFinish.get("responseString"));
         assertEquals(200, resFinish.get("statusCode"));
         assertTrue(p4.getStatus() == ProjectStatus.FINISHED);

         //worker should be removed from assigned workers because project is finished
         assertFalse(testCompany.getAssignedWorkers().contains(workerBob));
         //but should still be in employed
         assertTrue(testCompany.getEmployedWorkers().contains(workerBob));
    }

    
}