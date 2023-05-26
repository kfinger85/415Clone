package edu.colostate.cs415.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "workers_projects")
public class WorkerProject {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "date_assigned")
    private String dateAssigned;

    public WorkerProject(Worker worker, Project project) {
        this.worker = worker;
        this.project = project;
        this.dateAssigned = java.time.LocalDate.now().toString();
    }
    

    public Project getProject() {
        return this.project;
    }

}

