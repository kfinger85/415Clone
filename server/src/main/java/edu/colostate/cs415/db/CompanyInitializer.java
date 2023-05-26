package edu.colostate.cs415.db;

import edu.colostate.cs415.model.*;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class CompanyInitializer {

        private BCryptPasswordEncoder passwordEncoder;



    private final Company company;
    // Declare the qualifications as instance variables
    private Qualification angular;
    private Qualification cyberSecurity;
    private Qualification java;
    private Qualification javaScript;
    private Qualification mongoDB;
    private Qualification python;
    private Qualification react;
    private Qualification spark;
    private Qualification spring;
    private Qualification sql;
    private Qualification tensorflow;
    private Qualification typeScript;

    // Declare the workers as instance variables
    private Worker kevinFinger;
    private Worker geneRobertson;
    private Worker terryHampton;
    private Worker ninaBanks;
    private Worker omarWilliamson;
    private Worker benjaminGuzman;
    private Worker marcusSchneider;
    private Worker erikaJohnston;
    private Worker jamieBurgess;
    private Worker nickHubbard;
    private Worker robertLambert;
    private Worker ronLogan;
    private Worker timConner;

    // Declare the projects as instance variables
    private Project aIShoppingSystem;
    private Project androidTaskMonitoring;
    private Project fingerprintBasedATMSystem;
    private Project creditCardFraudDetection;
    private Project weatherForecastingSystem;
    private Project smartChatbot;
    private Project financialBankingSystem;
    private Project faceDetector;
    private Project signatureVerificationSystem;
    private Project lagacySoftwareMaintanance;
    private Project ecommerceFakeProductReviewsDetectionSystem;
    private Project employeesDB;






    public CompanyInitializer(Company company) {
        this.company = company;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public void initialize() {
        createQualifications();
        createProjects();
        createWorkers();
        assignWorkersToProjects();
    }

    private void createQualifications() {
        // Create the qualifications and assign them to the instance variables
        angular = company.createQualification("Angular");
        cyberSecurity = company.createQualification("Cyber Security");
        java = company.createQualification("Java");
        javaScript = company.createQualification("JavaScript");
        mongoDB = company.createQualification("MongoDB");
        python = company.createQualification("Python");
        react = company.createQualification("React");
        spark = company.createQualification("Spark");
        spring = company.createQualification("Spring");
        sql = company.createQualification("Sql");
        tensorflow = company.createQualification("Tensorflow");
        typeScript = company.createQualification("TypeScript");
    }

    private void createProjects() {
        // Create the projects and assign them to the instance variables
        aIShoppingSystem = company.createProject("AI Shopping System",
                new HashSet<>(Arrays.asList(python, tensorflow)), ProjectSize.BIG);
        aIShoppingSystem.setCompany(company);

        androidTaskMonitoring = company.createProject("Android Task Monitoring",
                new HashSet<>(Arrays.asList(java, mongoDB, spring, sql)),
                ProjectSize.SMALL);
                androidTaskMonitoring.setCompany(company);

        fingerprintBasedATMSystem = company.createProject("Fingerprint-Based ATM System",
                new HashSet<>(Arrays.asList(angular, java, javaScript, spring)),
                ProjectSize.MEDIUM);
                fingerprintBasedATMSystem.setCompany(company);
        creditCardFraudDetection = company.createProject("Credit Card Fraud Detection",
                new HashSet<>(Arrays.asList(cyberSecurity, python, react, sql, tensorflow)),
                ProjectSize.BIG);
                creditCardFraudDetection.setCompany(company);
        weatherForecastingSystem = company.createProject("Weather Forecasting System",
                new HashSet<>(Arrays.asList(spark, typeScript)),
                ProjectSize.SMALL);
                weatherForecastingSystem.setCompany(company);
                
                smartChatbot = company.createProject("Smart Chatbot",
                new HashSet<>(Arrays.asList(python, tensorflow)), ProjectSize.MEDIUM);
                smartChatbot.setCompany(company);       
        financialBankingSystem = company.createProject("Financial Banking System",
                new HashSet<>(Arrays.asList(angular, cyberSecurity, java, javaScript, spring, typeScript)),
                ProjectSize.BIG);
                financialBankingSystem.setCompany(company);
        faceDetector = company.createProject("Face Detector",
                new HashSet<>(Arrays.asList(angular, python, tensorflow, typeScript)), ProjectSize.BIG);
                faceDetector.setCompany(company);
        signatureVerificationSystem = company.createProject("Signature Verification System",
                new HashSet<>(Arrays.asList(python, tensorflow, sql, spark)), ProjectSize.BIG);
                signatureVerificationSystem.setCompany(company);
        lagacySoftwareMaintanance = company.createProject("Legacy Software Maintenance",
                new HashSet<>(Arrays.asList(java, javaScript, python, sql, typeScript)), ProjectSize.MEDIUM);
                lagacySoftwareMaintanance.setCompany(company);
        ecommerceFakeProductReviewsDetectionSystem = company.createProject(
                "Ecommerce Fake Product Reviews Detection System",
                new HashSet<>(Arrays.asList(cyberSecurity, java, javaScript, python, tensorflow, react)),
                ProjectSize.BIG);
                ecommerceFakeProductReviewsDetectionSystem.setCompany(company);
        employeesDB = company.createProject("Employees DB",
                new HashSet<>(Arrays.asList(java, javaScript, mongoDB, python, sql, typeScript)), ProjectSize.BIG);
                employeesDB.setCompany(company);
        }
    
    private void createWorkers() {
        // Create the workers and assign them to the instance variables

        kevinFinger = company.createWorker("Kevin Finger",
                new HashSet<>(Arrays.asList(java, javaScript, spring, sql)), 150000.0);
        kevinFinger.setCompany(company);
        kevinFinger.setUsername("kfinger");
        kevinFinger.setPassword(passwordEncoder.encode("12345678"));  // Encode the password

        geneRobertson = company.createWorker("Gene Robertson",
                new HashSet<>(Arrays.asList(python, tensorflow)), 100000.0);
        geneRobertson.setCompany(company);
        geneRobertson.setUsername("grobertson");
        geneRobertson.setPassword(passwordEncoder.encode("12345678"));  // Encode the password
    
        terryHampton = company.createWorker("Terry Hampton",
                new HashSet<>(Arrays.asList(java, python, spring)), 120000.0);
        terryHampton.setCompany(company);
        terryHampton.setUsername("thampton");
        terryHampton.setPassword(passwordEncoder.encode("12345678"));  // Encode the password

        ninaBanks = company.createWorker("Nina Banks",
                new HashSet<>(Arrays.asList(angular, typeScript)), 180000.0);
        ninaBanks.setCompany(company);
        ninaBanks.setUsername("nbanks");
        ninaBanks.setPassword(passwordEncoder.encode("12345678"));

        benjaminGuzman = company.createWorker("Benjamin Guzman",
                new HashSet<>(Arrays.asList(java, spring, sql)), 150000.0);
        benjaminGuzman.setCompany(company);
        benjaminGuzman.setUsername("bguzman");
        benjaminGuzman.setPassword(passwordEncoder.encode("12345678"));  // Encode the password

        marcusSchneider = company.createWorker("Marcus Schneider",
                new HashSet<>(Arrays.asList(javaScript, typeScript)), 145000.0);
        marcusSchneider.setCompany(company);
        marcusSchneider.setUsername("mschneider");
        marcusSchneider.setPassword(passwordEncoder.encode("12345678"));  // Encode the password

        erikaJohnston = company.createWorker("Erika Johnston",
                new HashSet<>(Arrays.asList(mongoDB, spark, sql)), 90000.0);
        erikaJohnston.setCompany(company);
        erikaJohnston.setUsername("ejohnston");
        erikaJohnston.setPassword(passwordEncoder.encode("12345678"));  // Encode the password

        jamieBurgess = company.createWorker("Jamie Burgess",
                new HashSet<>(Arrays.asList(javaScript, python, tensorflow)), 150000.0);
        jamieBurgess.setCompany(company);
        jamieBurgess.setUsername("jburgess");
        jamieBurgess.setPassword(passwordEncoder.encode("12345678"));  // Encode the password
        nickHubbard = company.createWorker("Nick Hubbard",
                new HashSet<>(Arrays.asList(javaScript, react)), 110000.0);
        nickHubbard.setCompany(company);
        nickHubbard.setUsername("nhubbard");
        nickHubbard.setPassword(passwordEncoder.encode("12345678"));  // Encode the password

        robertLambert = company.createWorker("Robert Lambert",
                new HashSet<>(Arrays.asList(java, spark)), 180000.0);
        robertLambert.setCompany(company);
        robertLambert.setUsername("rlambert");
        robertLambert.setPassword(passwordEncoder.encode("12345678"));  // Encode the password

        ronLogan = company.createWorker("Ron Logan",
                new HashSet<>(Arrays.asList(java, spring, tensorflow)), 200000.0);
        ronLogan.setCompany(company);
        ronLogan.setUsername("rlogan");
        ronLogan.setPassword(passwordEncoder.encode("12345678"));
        
        timConner = company.createWorker("Tim Conner",
                new HashSet<>(Arrays.asList(java, python, sql)), 130000.0);
        timConner.setCompany(company);
        timConner.setUsername("tconner");
        timConner.setPassword(passwordEncoder.encode("12345678"));  // Encode the password
    
        omarWilliamson = company.createWorker("Omar Williamson",
                new HashSet<>(Arrays.asList(cyberSecurity)), 200000.0);
        omarWilliamson.setCompany(company);
        omarWilliamson.setUsername("owilliamson");
        omarWilliamson.setPassword(passwordEncoder.encode("12345678"));  // Encode the password
        }
    
    private void assignWorkersToProjects() {
        // Assign workers to projects
        company.assign(geneRobertson, aIShoppingSystem);
        company.start(aIShoppingSystem);
        company.finish(aIShoppingSystem);
    
        company.assign(terryHampton, androidTaskMonitoring);
        company.assign(benjaminGuzman, androidTaskMonitoring);
    
        company.assign(ninaBanks, fingerprintBasedATMSystem);
        company.assign(benjaminGuzman, fingerprintBasedATMSystem);
        company.assign(marcusSchneider, fingerprintBasedATMSystem);
        company.start(fingerprintBasedATMSystem);
        company.unassign(marcusSchneider, fingerprintBasedATMSystem);
        company.assign(omarWilliamson, creditCardFraudDetection);
        company.assign(jamieBurgess, creditCardFraudDetection);
        company.assign(nickHubbard, creditCardFraudDetection);
        company.assign(timConner, creditCardFraudDetection);
        company.start(creditCardFraudDetection);
    
        company.assign(robertLambert, weatherForecastingSystem);
        company.assign(ninaBanks, weatherForecastingSystem);
    
        company.assign(terryHampton, smartChatbot);
        company.assign(ronLogan, smartChatbot);
        company.start(smartChatbot);
    
        company.assign(ninaBanks, financialBankingSystem);
        company.assign(omarWilliamson, financialBankingSystem);
        company.assign(terryHampton, financialBankingSystem);
        company.assign(marcusSchneider, financialBankingSystem);
        company.start(financialBankingSystem);
    
        company.assign(geneRobertson, faceDetector);
        company.assign(ninaBanks, faceDetector);
    
        company.assign(geneRobertson, signatureVerificationSystem);
        company.assign(erikaJohnston, signatureVerificationSystem);
    
        company.assign(ronLogan, lagacySoftwareMaintanance);
        company.assign(nickHubbard, lagacySoftwareMaintanance);
        company.assign(marcusSchneider, lagacySoftwareMaintanance);
        company.assign(benjaminGuzman, lagacySoftwareMaintanance);
        company.assign(timConner, lagacySoftwareMaintanance);
        company.start(lagacySoftwareMaintanance);
    
        company.assign(omarWilliamson, ecommerceFakeProductReviewsDetectionSystem);
        company.assign(timConner, ecommerceFakeProductReviewsDetectionSystem);
        company.assign(ronLogan, ecommerceFakeProductReviewsDetectionSystem);
        company.assign(nickHubbard, ecommerceFakeProductReviewsDetectionSystem);
        company.start(ecommerceFakeProductReviewsDetectionSystem);
    
        company.assign(nickHubbard, employeesDB);
        company.assign(ninaBanks, employeesDB);
        company.assign(timConner, employeesDB);
        company.assign(erikaJohnston, employeesDB);
    }
}
    
    
