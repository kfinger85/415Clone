package edu.colostate.cs415.server;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.redirect;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;
import java.util.Arrays;
import java.util.logging.Logger;
// import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.gson.Gson;

// import clover.it.unimi.dsi.fastutil.Arrays;
import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.dto.AssignmentDTO;
import edu.colostate.cs415.dto.ProjectDTO;
import edu.colostate.cs415.dto.QualificationDTO;
import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.model.Company;
import edu.colostate.cs415.model.Project;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.Worker;
import spark.Request;
import spark.Response;
import spark.Spark;

public class RestController {

	private static Logger log = Logger.getLogger(RestController.class.getName());
	private static String OK = "OK";
	private static String KO = "KO";

	private DBConnector dbConnector;
	private Company company;
	private Gson gson;

	public RestController(int port, DBConnector dbConnector) {
		port(port);
		this.dbConnector = dbConnector;
		gson = new Gson();
	}

	public void start() {
		// Load data from DB
		company = dbConnector.loadCompanyData();

		// Redirect
		redirect.get("/", "/helloworld");

		// Logging
		after("/*", (req, res) -> logRequest(req, res));
		exception(Exception.class, (exc, req, res) -> handleException(exc, res));

		// Hello World
		get("/helloworld", (req, res) -> helloWorld());

		// API
		path("/api", () -> {
			// Enable CORS
			options("/*", (req, res) -> optionsCORS(req, res));
			after("/*", (req, res) -> enableCORS(res));

			// Qualifications
			path("/qualifications", () -> {
				get("", (req, res) -> getQualifications(), gson::toJson);
				get("/:description", (req, res) -> getQualification(req.params("description")),
						gson::toJson);
				post("/:description", (req, res) -> createQualification(req));
			});
			// Workers
			path("/workers", () -> {
			get("", (req, res) -> getWorkers(), gson::toJson);
			get("/:name", (req, res) -> getWorker(req.params("name")), gson::toJson);
			post("/:name", (req, res) -> createWorker(req));
			});
			// Projects
			path("/projects", () -> {
				get("", (req, res) -> getProjects(), gson::toJson);
				get("/:name", (req, res) -> getProject(req.params("name")), gson::toJson);
				post("/:name", (req, res) -> createProject(req));
			});
			path("/assign", () -> {
				put("", (req, res) -> assign(req), gson::toJson);
			});
			path("/unassign", () -> {
				put("", (req, res) -> unassign(req), gson::toJson);
			});
			path("/start",() ->{
				put("",(req,res) -> startProj(req), gson::toJson);
			});

			path("/finish", () -> {
				put("", (req, res) -> finish(req), gson::toJson);
			});
		});
	}

	public void stop() {
		Spark.stop();
	}
	

	private String helloWorld() {
		return "Hello World!";
	}

	private String startProj(Request req){
		ProjectDTO project = gson.fromJson(req.body(), ProjectDTO.class);
		if(project==null){
			throw new IllegalArgumentException();
		}
		String name = project.getName();
		Project proj = company.getProjects()
                              .stream()
                              .filter(q -> q.getName().equals(name))
                              .findFirst().orElseThrow(()->new RuntimeException());
		company.start(proj);
		return OK;
	}

    private QualificationDTO[] getQualifications() {
        Set<Qualification> qualifications = company.getQualifications();
		// Convert each Qualification in 'qualifications' to a QualificationDTO and collect them into an array
		QualificationDTO[] qualificationDTOs = qualifications.stream()
				.map(Qualification::toDTO)
				.toArray(QualificationDTO[]::new);
	    return qualificationDTOs;

	}
	private QualificationDTO getQualification(String description) {
		return company.getQualifications().stream().filter(q -> q.toString().equals(description))
					   .map(q -> q.toDTO()).findFirst().orElseThrow(()-> new RuntimeException("Qualification names do not match."));
	}

	private String createQualification(Request request) {
		QualificationDTO assignmentDTO = gson.fromJson(request.body(), QualificationDTO.class);
		if (request.params("description").equals(assignmentDTO.getDescription())) {
			company.createQualification(assignmentDTO.getDescription());
		} else
			throw new RuntimeException("Qualification descriptions do not match.");
		return OK;
	}

// return all workers
private WorkerDTO[] getWorkers() {
	return company.getEmployedWorkers().stream().map(w -> w.toDTO()).toArray(WorkerDTO[]::new);
}

// return only the worker that maches the name
private WorkerDTO getWorker(String name) {
	return company.getEmployedWorkers().stream().filter(w -> w.getName().equals(name))
					   .map(w -> w.toDTO()).findFirst().orElseThrow(()-> new RuntimeException("Worker names do not match."));
}

private String createWorker(Request request){
	WorkerDTO assignmentDTO = gson.fromJson(request.body(), WorkerDTO.class);
	//same as createQualification above with different param
	if(assignmentDTO == null || !request.params("name").equals(assignmentDTO.getName())){
		throw new RuntimeException();
	}
		Worker w = company.createWorker(assignmentDTO.getName(), Arrays.stream(assignmentDTO.getQualifications())
				.map(Qualification::new)
				.collect(Collectors.toSet())
				,assignmentDTO.getSalary());
		if(w == null){
			throw new RuntimeException();
		}
	return OK;
}

// return all projects
private ProjectDTO[] getProjects() {
	return company.getProjects().stream().map(p -> p.toDTO()).toArray(ProjectDTO[]::new);
}

// return only the project that maches the name
private ProjectDTO getProject(String name) {
	return company.getProjects().stream().filter(p -> p.getName().equals(name))
			.map(p -> p.toDTO()).findFirst().orElseThrow(()-> new RuntimeException("Project names do not match."));
}

private String createProject(Request request){
	ProjectDTO assignmentDTO = gson.fromJson(request.body(), ProjectDTO.class);
	//same as createQualification above with different param
	if(request.params("name").equals(assignmentDTO.getName())){
		company.createProject(assignmentDTO.getName(), Arrays.stream(assignmentDTO.getQualifications())
				.map(Qualification::new)
				.collect(Collectors.toSet())
				,assignmentDTO.getSize());
	} else 
		throw new RuntimeException("Project names do not match.");
	return OK;
}

private String assign(Request req) {
        if (req.body().trim().isEmpty()) {
            throw new IllegalArgumentException("Request body is empty.");
        }
		AssignmentDTO assignmentDTO = gson.fromJson(req.body(), AssignmentDTO.class);
		workerAssignment(assignmentDTO, true);
		return OK;
}

private String unassign(Request req) {
        if (req.body().trim().isEmpty()) {
            throw new IllegalArgumentException("Request body is empty.");
        }
		AssignmentDTO assignmentDTO = gson.fromJson(req.body(), AssignmentDTO.class);
		workerAssignment(assignmentDTO, false);
        return OK;
}

private String finish(Request req) {
	if (req.body().trim().isEmpty()) {
		throw new IllegalArgumentException("Request body is empty.");
	}
	ProjectDTO projectDTO = gson.fromJson(req.body(), ProjectDTO.class);

	Project projectToFinish = findProjectByName(projectDTO.getName());
	if (projectToFinish != null) {
		company.finish(projectToFinish);
	} else {
		throw new IllegalArgumentException("Project not found");
	}
	return OK;
}

private void workerAssignment(AssignmentDTO assignmentDTO, boolean assign) {
	Worker workerToAssign = findWorkerByName(assignmentDTO.getWorker());
	Project projectToAssign = findProjectByName(assignmentDTO.getProject());
	if (projectToAssign != null || workerToAssign != null) {
		if(assign){
			company.assign(workerToAssign, projectToAssign);
		} else {
			company.unassign(workerToAssign, projectToAssign);
		}
	} else {
		throw new IllegalArgumentException("Worker or Project not found");
	}
} 

	private Worker findWorkerByName(String workerName) {
		return company.getEmployedWorkers().stream()
				.filter(worker -> worker.getName().equals(workerName))
				.findFirst()
				.orElse(null);
	}

	private Project findProjectByName(String projectName) {
		return company.getProjects().stream()
				.filter(project -> project.getName().equals(projectName))
				.findFirst()
				.orElse(null);
	}


	// Logs every request received
	private void logRequest(Request request, Response response) {
		log.info(request.requestMethod() + " " + request.pathInfo() + "\nREQUEST:\n" + request.body() + "\nRESPONSE:\n"
				+ response.body());
	}

	// Exception handling
	private void handleException(Exception exception, Response response) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace();
		exception.printStackTrace(pw);
		log.severe(sw.toString());
		response.body(KO);
		response.status(500);
	}

	// Enable CORS
	private void enableCORS(Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	}

	// Enable CORS
	private String optionsCORS(Request request, Response response) {
		String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
		if (accessControlRequestHeaders != null)
			response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);

		String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
		if (accessControlRequestMethod != null)
			response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
		return OK;
	}
}