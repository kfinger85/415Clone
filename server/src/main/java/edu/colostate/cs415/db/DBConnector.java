package edu.colostate.cs415.db;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import edu.colostate.cs415.model.Company;
import edu.colostate.cs415.model.Project;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.Worker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.Transaction;

import org.springframework.stereotype.Service;

@Service
public class DBConnector {



private SessionFactory sessionFactory;
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/415Db";
private static final String USERNAME = "root";
private static final String PASSWORD = "(Ntlsec59!)";
private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQL8Dialect";
private static final String HIBERNATE_HBM2DDL_AUTO = "create";

public DBConnector() {
// Create the Hibernate SessionFactory
       // Create the Hibernate SessionFactory
       Configuration configuration = new Configuration();

       // Set the necessary properties
        configuration.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", JDBC_URL);
        configuration.setProperty("hibernate.connection.username", USERNAME);
        configuration.setProperty("hibernate.connection.password", PASSWORD);
        configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        configuration.addAnnotatedClass(Qualification.class);
        configuration.addAnnotatedClass(Worker.class);
        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(Company.class);

       // Apply the configuration
       ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
       sessionFactory = configuration.buildSessionFactory(serviceRegistry);
   }
        public static void main(String[] args) {
                DBConnector dbConnector = new DBConnector();
                Company company = dbConnector.loadCompanyData();
                dbConnector.logCompany(company);
        }

        public Company loadCompanyData() {

                // Company
                Session session = sessionFactory.openSession();

                Company company = new Company("CS415Startup");

                company.createQualification("Testing"); 

                insertCompanyData(session, company);

                return company;
        }

        public void logCompany(Company company) {
                System.out.println(company.getName());

                System.out.println("\nQualifications:");
                for (Qualification qualification : company.getQualifications()) {
                        System.out.println(qualification.getDescription());
                }

                System.out.println("\nWorkers:");
                for (Worker worker : company.getEmployedWorkers()) {
                        System.out.println(worker + ":" + worker.getWorkload());
                }

                System.out.println("\nProjects:");
                for (Project project : company.getProjects()) {
                        System.out.println(project);
                }
            }

            private void insertCompanyData(Session session, Company company) {
                Transaction transaction = null;
                try {
                    transaction = session.beginTransaction();
            
                    // Save the company
                    session.save(company);
            
                    for (Project project : company.getProjects()) {
                        session.save(project);
                    }
                    

                    // Save workers
                    for (Worker worker : company.getEmployedWorkers()) {
                        session.save(worker);
                    }

                // Save qualifications
                for (Qualification qualification : company.getQualifications()) {
                        session.save(qualification);
                        }
                    
                    // Now, assign qualifications and workers to projects
                    for (Project project : company.getProjects()) {
                        // Assign qualifications to projects
                        for (Qualification qualification : project.getRequiredQualifications()) {
                            project.addQualification(qualification);
                        }
                    
                        // Assign workers to projects
                        for (Worker worker : project.getWorkers()) {
                            project.addWorker(worker);
                        }
                    }
                    // Commit the transaction
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    e.printStackTrace();
                    return;
                }
            }
}