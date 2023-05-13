package edu.colostate.cs415.db;

import java.util.Arrays;
import java.util.HashSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;



import edu.colostate.cs415.model.Company;
import edu.colostate.cs415.model.Project;
import edu.colostate.cs415.model.ProjectSize;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.Worker;

public class DBConnector {

        private static final String JDBC_URL = "jdbc:mysql://localhost:3306/415Db";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "(Ntlsec59!)";

        public static void main(String[] args) {
                DBConnector dbConnector = new DBConnector();
                Company company = dbConnector.loadCompanyData();
                dbConnector.logCompany(company);
        }

        public void logCompany(Company company) {
                System.out.println(company);

                System.out.println("\nQualifications:");
                for (Qualification qualification : company.getQualifications()) {
                        System.out.println(qualification);
                }

                System.out.println("\nWorkers:");
                for (Worker worker : company.getEmployedWorkers()) {
                        System.out.println(worker + ":" + worker.getWorkload());
                }

                System.out.println("\nProjects:");
                for (Project project : company.getProjects()) {
                        System.out.println(project);
                }

                try{
									Class.forName("com.mysql.cj.jdbc.Driver");
      
									Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

									// Create a statement object for executing SQL queries
                        Statement statement = connection.createStatement();
            
                        // Truncate existing tables (optional)
                        // truncateTables(statement);
            
                        // Insert company, qualifications, workers, and projects into the database
                        insertCompanyData(statement, company);
            
                        // Retrieve and display data from the database (optional)
                        retrieveData(statement);
            
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }catch (ClassNotFoundException e) {
												e.printStackTrace();
										}
        }
        private void truncateTables(Statement statement) throws SQLException {
                // Truncate tables or perform any necessary cleanup before inserting data
                statement.executeUpdate("TRUNCATE TABLE company");
                statement.executeUpdate("TRUNCATE TABLE qualifications");
                statement.executeUpdate("TRUNCATE TABLE workers");
                statement.executeUpdate("TRUNCATE TABLE projects");
                statement.executeUpdate("TRUNCATE TABLE project_qualification");
                statement.executeUpdate("TRUNCATE TABLE worker_project");
            }

        private void insertCompanyData(Statement statement, Company company) throws SQLException {
                // Insert the company
                String insertCompanySql = "INSERT INTO company (name) VALUES ('" + company.getName() + "')";
                statement.executeUpdate(insertCompanySql);
            
                // Insert qualifications
                for (Qualification qualification : company.getQualifications()) {
                    String insertQualificationSql = "INSERT INTO qualifications (name) VALUES ('" + qualification.getDescription() + "')";
                    statement.executeUpdate(insertQualificationSql);
                }
            
                // Insert workers
                for (Worker worker : company.getEmployedWorkers()) {
                    String insertWorkerSql = "INSERT INTO workers (name, workload) VALUES ('" + worker.getName() + "', " + worker.getWorkload() + ")";
                    statement.executeUpdate(insertWorkerSql);
                }
            
                // Insert projects
                for (Project project : company.getProjects()) {
                    String insertProjectSql = "INSERT INTO projects (name, size) VALUES ('" + project.getName() + "', '" + project.getSize() + "')";
                    statement.executeUpdate(insertProjectSql);
            
                    // Assign qualifications to projects
                    for (Qualification qualification : project.getRequiredQualifications()) {
                        String assignQualificationSql = "INSERT INTO project_qualification (project_id, qualification_id) SELECT "
                                + "(SELECT id FROM projects WHERE name = '" + project.getName() + "'), "
                                + "(SELECT id FROM qualifications WHERE name = '" + qualification.getDescription() + "')";
                        statement.executeUpdate(assignQualificationSql);
                    }
            
                    // Assign workers to projects
                    for (Worker worker : project.getWorkers()) {
                        String assignWorkerSql = "INSERT INTO worker_project (worker_id, project_id) SELECT "
                                + "(SELECT id FROM workers WHERE name = '" + worker.getName() + "'), "
                                + "(SELECT id FROM projects WHERE name = '" + project.getName() + "')";
                        statement.executeUpdate(assignWorkerSql);
                    }
                }
            }
            
		private void retrieveData(Statement statement) throws SQLException {
			// Retrieve and display qualifications
			String qualificationsSql = "SELECT * FROM qualifications";
			ResultSet qualificationsResult = statement.executeQuery(qualificationsSql);
			System.out.println("\nQualifications:");
			while (qualificationsResult.next()) {
							String qualificationName = qualificationsResult.getString("name");
							System.out.println(qualificationName);
			}
			
			// Retrieve and display workers
			String workersSql = "SELECT * FROM workers";
			ResultSet workersResult = statement.executeQuery(workersSql);
			System.out.println("\nWorkers:");
			while (workersResult.next()) {
							String workerName = workersResult.getString("name");
							double workload = workersResult.getDouble("workload");
							System.out.println(workerName + ": " + workload);
			}
			
			// Retrieve and display projects
			String projectsSql = "SELECT * FROM projects";
			ResultSet projectsResult = statement.executeQuery(projectsSql);
			System.out.println("\nProjects:");
			while (projectsResult.next()) {
							String projectName = projectsResult.getString("name");
							String projectSize = projectsResult.getString("size");
							System.out.println(projectName + " (" + projectSize + ")");
			}
		}
        public Company loadCompanyData() {
                // Company
                Company company = new Company("CS415Startup");

                // Qualifications
                Qualification angular = company.createQualification("Angular");
                Qualification cyberSecurity = company.createQualification("Cyber Security");
                Qualification java = company.createQualification("Java");
                Qualification javaScript = company.createQualification("JavaScript");
                Qualification mongoDB = company.createQualification("MongoDB");
                Qualification python = company.createQualification("Python");
                Qualification react = company.createQualification("React");
                Qualification spark = company.createQualification("Spark");
                Qualification spring = company.createQualification("Spring");
                Qualification sql = company.createQualification("Sql");
                Qualification tensorflow = company.createQualification("Tensorflow");
                Qualification typeScript = company.createQualification("TypeScript");

                // Projects
                Project aIShoppingSystem = company.createProject("AI Shopping System", new HashSet<Qualification>(
                                Arrays.asList(python, tensorflow)), ProjectSize.BIG);
                Project androidTaskMonitoring = company.createProject("Android Task Monitoring",
                                new HashSet<Qualification>(
                                                Arrays.asList(java, mongoDB, spring, sql)),
                                ProjectSize.SMALL);
                Project fingerprintBasedATMSystem = company.createProject("Fingerprint-Based ATM System",
                                new HashSet<Qualification>(Arrays.asList(angular, java, javaScript, spring)),
                                ProjectSize.MEDIUM);
                Project creditCardFraudDetection = company.createProject("Credit Card Fraud Detection",
                                new HashSet<Qualification>(
                                                Arrays.asList(cyberSecurity, python, react, sql, tensorflow)),
                                ProjectSize.BIG);
                Project weatherForecastingSystem = company.createProject("Weather Forecasting System",
                                new HashSet<Qualification>(Arrays.asList(spark, typeScript)),
                                ProjectSize.SMALL);
                Project smartChatbot = company.createProject("Smart Chatbot", new HashSet<Qualification>(
                                Arrays.asList(python, tensorflow)), ProjectSize.MEDIUM);
                Project financialBankingSystem = company.createProject("Financial Banking System",
                                new HashSet<Qualification>(
                                                Arrays.asList(angular, cyberSecurity, java, javaScript, spring,
                                                                typeScript)),
                                ProjectSize.BIG);
                Project faceDetector = company.createProject("Face Detector", new HashSet<Qualification>(
                                Arrays.asList(angular, python, tensorflow, typeScript)), ProjectSize.BIG);
                Project signatureVerificationSystem = company.createProject("Signature Verification System",
                                new HashSet<Qualification>(Arrays.asList(python, tensorflow, sql, spark)),
                                ProjectSize.BIG);
                Project lagacySoftwareMaintanance = company.createProject("Legacy Software Maintanance",
                                new HashSet<Qualification>(Arrays.asList(java, javaScript, python, sql, typeScript)),
                                ProjectSize.MEDIUM);
                Project ecommerceFakeProductReviewsDetectionSystem = company.createProject(
                                "Ecommerce Fake Product Reviews Detection System", new HashSet<Qualification>(
                                                Arrays.asList(cyberSecurity, java, javaScript, python, tensorflow,
                                                                react)),
                                ProjectSize.BIG);
                Project employeesDB = company.createProject("Employees DB", new HashSet<Qualification>(
                                Arrays.asList(java, javaScript, mongoDB, python, sql, typeScript)), ProjectSize.BIG);

                // Workers
                Worker geneRobertson = company.createWorker("Gene Robertson", new HashSet<Qualification>(
                                Arrays.asList(python, tensorflow)), 100000.0);
                Worker terryHampton = company.createWorker("Terry Hampton", new HashSet<Qualification>(
                                Arrays.asList(java, python, spring)), 120000.0);
                Worker ninaBanks = company.createWorker("Nina Banks", new HashSet<Qualification>(
                                Arrays.asList(angular, typeScript)), 180000.0);
                Worker omarWilliamson = company.createWorker("Omar Williamson", new HashSet<Qualification>(
                                Arrays.asList(cyberSecurity)), 200000.0);
                Worker benjaminGuzman = company.createWorker("Benjamin Guzman", new HashSet<Qualification>(
                                Arrays.asList(java, spring, sql)), 150000.0);
                Worker marcusSchneider = company.createWorker("Marcus Schneider", new HashSet<Qualification>(
                                Arrays.asList(javaScript, typeScript)), 145000.0);
                Worker erikaJohnston = company.createWorker("Erika Johnston", new HashSet<Qualification>(
                                Arrays.asList(mongoDB, spark, sql)), 90000.0);
                Worker jamieBurgess = company.createWorker("Jamie Burgess", new HashSet<Qualification>(
                                Arrays.asList(javaScript, python, tensorflow)), 150000.0);
                Worker nickHubbard = company.createWorker("Nick Hubbard", new HashSet<Qualification>(
                                Arrays.asList(javaScript, react)), 110000.0);
                Worker robertLambert = company.createWorker("Robert Lambert", new HashSet<Qualification>(
                                Arrays.asList(java, spark)), 180000.0);
                Worker ronLogan = company.createWorker("Ron Logan", new HashSet<Qualification>(
                                Arrays.asList(java, spring, tensorflow)), 200000.0);
                Worker timConner = company.createWorker("Tim Conner", new HashSet<Qualification>(
                                Arrays.asList(java, python, sql)), 130000.0);

                // Assignments

                // Finished
                company.assign(geneRobertson, aIShoppingSystem);
                company.start(aIShoppingSystem);
                company.finish(aIShoppingSystem);

                // Planned
                company.assign(terryHampton, androidTaskMonitoring);
                company.assign(benjaminGuzman, androidTaskMonitoring);

                // Suspended
                company.assign(ninaBanks, fingerprintBasedATMSystem);
                company.assign(benjaminGuzman, fingerprintBasedATMSystem);
                company.assign(marcusSchneider, fingerprintBasedATMSystem);
                company.start(fingerprintBasedATMSystem);
                company.unassign(marcusSchneider, fingerprintBasedATMSystem);

                // Active
                company.assign(omarWilliamson, creditCardFraudDetection);
                company.assign(jamieBurgess, creditCardFraudDetection);
                company.assign(nickHubbard, creditCardFraudDetection);
                company.assign(timConner, creditCardFraudDetection);
                company.start(creditCardFraudDetection);

                // Planned
                company.assign(robertLambert, weatherForecastingSystem);
                company.assign(ninaBanks, weatherForecastingSystem);

                // Active
                company.assign(terryHampton, smartChatbot);
                company.assign(ronLogan, smartChatbot);
                company.start(smartChatbot);

                // Active
                company.assign(ninaBanks, financialBankingSystem);
                company.assign(omarWilliamson, financialBankingSystem);
                company.assign(terryHampton, financialBankingSystem);
                company.assign(marcusSchneider, financialBankingSystem);
                company.start(financialBankingSystem);

                // Planned
                company.assign(geneRobertson, faceDetector);
                company.assign(ninaBanks, faceDetector);

                // Planned
                company.assign(geneRobertson, signatureVerificationSystem);
                company.assign(erikaJohnston, signatureVerificationSystem);

                // Active
                company.assign(ronLogan, lagacySoftwareMaintanance);
                company.assign(nickHubbard, lagacySoftwareMaintanance);
                company.assign(marcusSchneider, lagacySoftwareMaintanance);
                company.assign(benjaminGuzman, lagacySoftwareMaintanance);
                company.assign(timConner, lagacySoftwareMaintanance);
                company.start(lagacySoftwareMaintanance);

                // Active
                company.assign(omarWilliamson, ecommerceFakeProductReviewsDetectionSystem);
                company.assign(timConner, ecommerceFakeProductReviewsDetectionSystem);
                company.assign(ronLogan, ecommerceFakeProductReviewsDetectionSystem);
                company.assign(nickHubbard, ecommerceFakeProductReviewsDetectionSystem);
                company.start(ecommerceFakeProductReviewsDetectionSystem);

                // Planned
                company.assign(nickHubbard, employeesDB);
                company.assign(ninaBanks, employeesDB);
                company.assign(timConner, employeesDB);
                company.assign(erikaJohnston, employeesDB);

                return company;
        }
}