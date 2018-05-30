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
@Table(name = "typeperson")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typeperson.findAll", query = "SELECT t FROM Typeperson t")
    , @NamedQuery(name = "Typeperson.findByIdType", query = "SELECT t FROM Typeperson t WHERE t.idType = :idType")
    , @NamedQuery(name = "Typeperson.findByNameType", query = "SELECT t FROM Typeperson t WHERE t.nameType = :nameType")})
public class Typeperson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdType")
    private Long idType;
    @Size(max = 255)
    @Column(name = "NameType")
    private String nameType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typePersonIdType")
    private List<Person> personList;

    public Typeperson() {
    }

    public Typeperson(Long idType) {
        this.idType = idType;
    }

    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
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
        hash += (idType != null ? idType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typeperson)) {
            return false;
        }
        Typeperson other = (Typeperson) object;
        if ((this.idType == null && other.idType != null) || (this.idType != null && !this.idType.equals(other.idType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesPerson.Typeperson[ idType=" + idType + " ]";
    }
    
}
