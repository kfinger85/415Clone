package edu.colostate.cs415.server;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.model.Company;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.colostate.cs415.server", "edu.colostate.cs415.db"})
public class CS415Application implements ApplicationListener<ContextRefreshedEvent> {

    private DBConnector dbConnector;

    private Company company;

    public CS415Application(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public static void main(String[] args) {
        SpringApplication.run(CS415Application.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Load data from DB
        company = dbConnector.loadCompanyData();
    }
}
