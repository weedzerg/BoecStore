/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesPerson;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nguye
 */
@Entity
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
    , @NamedQuery(name = "Employee.findByIdEmp", query = "SELECT e FROM Employee e WHERE e.idEmp = :idEmp")
    , @NamedQuery(name = "Employee.findBySalary", query = "SELECT e FROM Employee e WHERE e.salary = :salary")
    , @NamedQuery(name = "Employee.findByDate", query = "SELECT e FROM Employee e WHERE e.date = :date")
    , @NamedQuery(name = "Employee.findByPersonIdPerson", query = "SELECT e FROM Employee e WHERE e.personIdPerson = :personIdPerson")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdEmp")
    private long idEmp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Salary")
    private double salary;
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PersonIdPerson")
    private Integer personIdPerson;
    @JoinColumn(name = "PersonIdPerson", referencedColumnName = "IdPerson", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public Employee() {
    }

    public Employee(Integer personIdPerson) {
        this.personIdPerson = personIdPerson;
    }

    public Employee(Integer personIdPerson, long idEmp, double salary) {
        this.personIdPerson = personIdPerson;
        this.idEmp = idEmp;
        this.salary = salary;
    }

    public long getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(long idEmp) {
        this.idEmp = idEmp;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPersonIdPerson() {
        return personIdPerson;
    }

    public void setPersonIdPerson(Integer personIdPerson) {
        this.personIdPerson = personIdPerson;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personIdPerson != null ? personIdPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.personIdPerson == null && other.personIdPerson != null) || (this.personIdPerson != null && !this.personIdPerson.equals(other.personIdPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesPerson.Employee[ personIdPerson=" + personIdPerson + " ]";
    }
    
}
