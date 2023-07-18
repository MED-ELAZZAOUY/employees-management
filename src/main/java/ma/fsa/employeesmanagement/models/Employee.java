package ma.fsa.employeesmanagement.models;

import java.util.UUID;

public class Employee {
    private String idEmployee;
    private String firstName;
    private String lastName;
    private String CIN;
    private Double solde;

    public Employee(String id, String firstName, String lastName, String CIN, Double solde) {
        this.idEmployee = id ;
        this.firstName = firstName;
        this.lastName = lastName;
        this.CIN = CIN;
        this.solde = solde;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }
}
