package ma.fsa.employeesmanagement.dao.entities;

public class Job {
    private int idJob;
    private String jobName;
    private Double salary;
    private String description;
    private int idEmployee;

    public Job(int idJob, String jobName, String description, double salary, int idEmployee) {
        this.idJob = idJob;
        this.jobName = jobName;
        this.description =description;
        this.salary = salary;
        this.idEmployee = idEmployee;
    }

    public int getIdJob() {
        return idJob;
    }

    public void setIdJob(int idJob) {
        this.idJob = idJob;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
}
