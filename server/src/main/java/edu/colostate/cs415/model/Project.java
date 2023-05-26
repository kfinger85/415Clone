package edu.colostate.cs415.model;

import java.util.Set;
import java.util.HashSet;
import java.util.regex.Pattern;

import javax.persistence.*;

import edu.colostate.cs415.dto.ProjectDTO;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "size")
	@Enumerated(EnumType.STRING)
	private ProjectSize size;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private ProjectStatus status;

	@ManyToMany(mappedBy = "projects", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Set<Worker> workers = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "project_qualification", joinColumns = @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "none")), inverseJoinColumns = @JoinColumn(name = "qualification_id", foreignKey = @ForeignKey(name = "none")))
	private Set<Qualification> qualifications = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@OneToMany(mappedBy = "project")
	private Set<WorkerProject> workerProjects;

	public Project() {
		// Default constructor required by Hibernate
	}
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	// check qualifications. or do that up stream? might need to do a check for this
	// in the constructor too.
	/*
	 * Check for boundary conditions on the qualification set as well as the
	 * requirements on the String reference (not null). These qualifications must be
	 * from the
	 * company's set of qualifications.
	 */
	public Project(String name, Set<Qualification> qualifications, ProjectSize size) {
		checkArgValidity(name, qualifications, size);
		this.name = name;
		this.qualifications = qualifications;
		this.size = size;
		status = ProjectStatus.PLANNED;
		workers = new HashSet<Worker>();
	}

	       public Long getId() {
			 return id;
		   }

		 public void setName(String name) {
			 this.name = name;
		 }
		 public void setQualifications(Set<Qualification> qualifications) {
			 this.qualifications = qualifications;
		 }

		 public void setSize(ProjectSize size) {
			 this.size = size;
		 }

	private void checkArgValidity(String name, Set<Qualification> qualifications, ProjectSize size) {
		if (qualifications == null || qualifications.isEmpty()) {
			throwArgException("Project must have at least one qualification");
		}
		if (size == null) {
			throwArgException("Project must have a size");
		}
		Pattern pattern = Pattern.compile("^\\s*$");
		if (name == null || pattern.matcher(name).find()) {
			throwArgException("Name field must not be null or empty");
		}
	}

	private void throwArgException(String reason) {
		throw new IllegalArgumentException(reason);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
		return this.name.equals(((Project) other).getName());
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return name + ":" + workers.size() + ":" + status;
	}

	public String getName() {
		return name;
	}

	public ProjectSize getSize() {
		return size;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		if (status == null) {
			throw new IllegalArgumentException("Status must not be null");
		}
		this.status = status;
	}

	public void addWorker(Worker worker) {
		if (worker == null) {
			throw new IllegalArgumentException("worker must not be null");
		}
		workers.add(worker);
	}

	public void removeWorker(Worker worker) {
		if (worker == null) {
			throw new IllegalArgumentException("worker must not be null");
		}
		workers.remove(worker);
	}

	public Set<Worker> getWorkers() {
		return workers;
	}

	public void removeAllWorkers() {
		workers.clear();
	}

	public Set<Qualification> getRequiredQualifications() {
		return qualifications;
	}

	public void addQualification(Qualification qualification) {
		if (qualification == null) {
			throw new IllegalArgumentException("Null Qualification");
		}
		qualifications.add(qualification);
		if (this.status == ProjectStatus.ACTIVE && !this.getMissingQualifications().isEmpty()) { // if no longer met,
																									// should either be
																									// Planned or
			// status = ProjectStatus.PLANNED;
			status = ProjectStatus.SUSPENDED;
		}
	}

	public Set<Qualification> getMissingQualifications() {
		Set<Qualification> missingqualifications = new HashSet<Qualification>();
		for (Qualification qualification : qualifications) {
			boolean missing = true;
			for (Worker wrker : workers) {
				if (wrker.getQualifications().contains(qualification)) {
					missing = false;
					break;
				}
			}
			if (missing) {
				missingqualifications.add(qualification);
			}
		}
		return missingqualifications;
	}

	public boolean isHelpful(Worker worker) {
		if (worker == null) {
			return false;
		}
		for (Qualification qual : worker.getQualifications()) {
			if (getMissingQualifications().contains(qual)) {
				return true;
			}
		}
		return false;
	}

	public ProjectDTO toDTO() {
		return new ProjectDTO(
				this.name,
				this.size,
				this.status,
				this.workers.stream()
						.map(
								worker -> worker.getName())
						.toArray(String[]::new),
				this.qualifications.stream()
						.map(
								qualification -> qualification.toDTO().getDescription())
						.toArray(String[]::new),
				this.getMissingQualifications().stream()
						.map(
								missingQ -> missingQ.toDTO().getDescription())
						.toArray(String[]::new));
	}

    public Project orElseThrow(Object object) {
        return null;
    }

}
