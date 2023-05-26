package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;


import edu.colostate.cs415.dto.QualificationDTO;
import javax.persistence.*;

@Entity
@Table(name = "qualifications")
public class Qualification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "qualifications")
    private Set<Project> projects = new HashSet<>();


    @ManyToMany(mappedBy = "qualifications")
	private Set<Worker> workers = new HashSet<>();

	@ManyToMany(mappedBy = "qualifications")
	private Set<Company> companies;

    protected Qualification() {}



  public Qualification(String description) { 
	if(description == null || description.isEmpty() || isAllBlankSpace(description)){
		throw new IllegalArgumentException("description must not be null or empty");
	}
		workers = new HashSet<Worker>();
		this.name = description;
	}
	
	public String getName() {
		return name;
	}
	public Long getId() {
		return id;
	}

	public void setName(String description) {
		if(description == null || description.isEmpty() || isAllBlankSpace(description)){
			throw new IllegalArgumentException("description must not be null or empty");
		}
		this.name = description;
	}

	private boolean isAllBlankSpace(String description) {
		Pattern pattern = Pattern.compile("^\\s*$");
		return pattern.matcher(description).find();
	}


	

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if(this.getClass() != other.getClass()){
			return false;
		}
		Qualification q = (Qualification) other;
		return q.name.equals(this.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return name;
	}

	public Set<Worker> getWorkers() {
		return new HashSet<Worker>(this.workers);
	}
	
	public void addWorker(Worker worker) {
		if(worker == null) {
			throw new IllegalArgumentException("worker must not be null");
		}
			workers.add(worker);
	}

	public void removeWorker(Worker worker) {
		if(worker == null) {
			throw new IllegalArgumentException("worker must not be null");
		}
			workers.remove(worker);
	}

	// look at using Java streams 
	public QualificationDTO toDTO() {
		return new QualificationDTO(
				this.name,
				this.workers.stream()
				.map(
				    worker -> worker.getName())
				.toArray(String[]::new)
				);
	}

}
