package edu.colostate.cs415;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.model.Company;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication(scanBasePackages = "edu.colostate.cs415") // Adjust the package name here
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
