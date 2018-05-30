/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesPerson;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nguye
 */
@Entity
@Table(name = "employee_stalls")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeStalls.findAll", query = "SELECT e FROM EmployeeStalls e")
    , @NamedQuery(name = "EmployeeStalls.findByIdEmpStall", query = "SELECT e FROM EmployeeStalls e WHERE e.idEmpStall = :idEmpStall")
    , @NamedQuery(name = "EmployeeStalls.findByPersonIdPerson", query = "SELECT e FROM EmployeeStalls e WHERE e.personIdPerson = :personIdPerson")})
public class EmployeeStalls implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Emp_Stall")
    private long idEmpStall;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PersonIdPerson")
    private Integer personIdPerson;
    @JoinColumn(name = "PersonIdPerson", referencedColumnName = "IdPerson", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public EmployeeStalls() {
    }

    public EmployeeStalls(Integer personIdPerson) {
        this.personIdPerson = personIdPerson;
    }

    public EmployeeStalls(Integer personIdPerson, long idEmpStall) {
        this.personIdPerson = personIdPerson;
        this.idEmpStall = idEmpStall;
    }

    public long getIdEmpStall() {
        return idEmpStall;
    }

    public void setIdEmpStall(long idEmpStall) {
        this.idEmpStall = idEmpStall;
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
        if (!(object instanceof EmployeeStalls)) {
            return false;
        }
        EmployeeStalls other = (EmployeeStalls) object;
        if ((this.personIdPerson == null && other.personIdPerson != null) || (this.personIdPerson != null && !this.personIdPerson.equals(other.personIdPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesPerson.EmployeeStalls[ personIdPerson=" + personIdPerson + " ]";
    }
    
}
