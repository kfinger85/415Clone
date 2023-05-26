package edu.colostate.cs415.db;

import edu.colostate.cs415.model.Company;
import edu.colostate.cs415.model.Project;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.Worker;
import edu.colostate.cs415.model.WorkerProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Service;
import org.hibernate.Transaction;




@Service
public class DBConnector {
private SessionFactory sessionFactory;

public DBConnector() {
       // Create the Hibernate SessionFactory
       Configuration configuration = new Configuration();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(System.getProperty("user.dir") + "/server/src/main/resources/application.properties"));
            configuration.setProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        configuration.addAnnotatedClass(Qualification.class);
        configuration.addAnnotatedClass(Worker.class);
        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(WorkerProject.class);

       // Apply the configuration
       ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
       .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
   }
        public static void main(String[] args) {
                DBConnector dbConnector = new DBConnector();
                Company company = dbConnector.loadCompanyData();
                dbConnector.logCompany(company);
        }

        public Company loadCompanyData() {
                Session session = sessionFactory.openSession();
                Company company = new Company("CS415Startup");
                CompanyInitializer companyInitializer = new CompanyInitializer(company);
                companyInitializer.initialize();
                insertCompanyData(session, company);
                return company;
        }

        public void logCompany(Company company) {
                System.out.println(company.getName());

                System.out.println("\nQualifications:");
                for (Qualification qualification : company.getQualifications()) {
                        System.out.println(qualification.getName());
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