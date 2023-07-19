package ma.fsa.employeesmanagement.models;



public class Job {
    private String idJob;
    private String jobName;
    private Double salary;
    private String description;
    private String idEmployee;


    public Job(String id, String jobName, String description, double salary, String idEmployee) {
        this.idJob = id;
        this.jobName = jobName;
        this.description =description;
        this.salary = salary;
        this.idEmployee = idEmployee;
    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
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

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }
}
