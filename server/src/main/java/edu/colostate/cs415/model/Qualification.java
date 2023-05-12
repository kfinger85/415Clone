package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import edu.colostate.cs415.dto.QualificationDTO;

public class Qualification {

	private String description;
	private Set<Worker> workers;


  public Qualification(String description) { 
	if(description == null || description.isEmpty() || isAllBlankSpace(description)){
		throw new IllegalArgumentException("description must not be null or empty");
	}
		workers = new HashSet<Worker>();
		this.description = description;
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
		return q.description.equals(this.description);
	}

	@Override
	public int hashCode() {
		return description.hashCode();
	}

	@Override
	public String toString() {
		return description;
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
				this.description,
				this.workers.stream()
				.map(
				    worker -> worker.getName())
				.toArray(String[]::new)
				);
	}

}
