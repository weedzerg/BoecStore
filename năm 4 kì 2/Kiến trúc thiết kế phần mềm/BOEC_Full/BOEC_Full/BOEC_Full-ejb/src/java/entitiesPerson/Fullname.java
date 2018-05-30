/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesPerson;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nguye
 */
@Entity
@Table(name = "fullname")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fullname.findAll", query = "SELECT f FROM Fullname f")
    , @NamedQuery(name = "Fullname.findByIdName", query = "SELECT f FROM Fullname f WHERE f.idName = :idName")
    , @NamedQuery(name = "Fullname.findByFirstName", query = "SELECT f FROM Fullname f WHERE f.firstName = :firstName")
    , @NamedQuery(name = "Fullname.findByLastName", query = "SELECT f FROM Fullname f WHERE f.lastName = :lastName")
    , @NamedQuery(name = "Fullname.findByMiddleName", query = "SELECT f FROM Fullname f WHERE f.middleName = :middleName")})
public class Fullname implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdName")
    private Long idName;
    @Size(max = 255)
    @Column(name = "FirstName")
    private String firstName;
    @Size(max = 255)
    @Column(name = "LastName")
    private String lastName;
    @Size(max = 255)
    @Column(name = "MiddleName")
    private String middleName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fullNameIdName")
    private List<Person> personList;

    public Fullname() {
    }

    public Fullname(Long idName) {
        this.idName = idName;
    }

    public Long getIdName() {
        return idName;
    }

    public void setIdName(Long idName) {
        this.idName = idName;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @XmlTransient
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idName != null ? idName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fullname)) {
            return false;
        }
        Fullname other = (Fullname) object;
        if ((this.idName == null && other.idName != null) || (this.idName != null && !this.idName.equals(other.idName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesPerson.Fullname[ idName=" + idName + " ]";
    }
    
}
