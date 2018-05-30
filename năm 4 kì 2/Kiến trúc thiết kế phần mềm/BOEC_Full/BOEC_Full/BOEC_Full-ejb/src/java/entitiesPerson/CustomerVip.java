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
@Table(name = "customer_vip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerVip.findAll", query = "SELECT c FROM CustomerVip c")
    , @NamedQuery(name = "CustomerVip.findByIdCusVip", query = "SELECT c FROM CustomerVip c WHERE c.idCusVip = :idCusVip")
    , @NamedQuery(name = "CustomerVip.findByPersonIdPerson", query = "SELECT c FROM CustomerVip c WHERE c.personIdPerson = :personIdPerson")})
public class CustomerVip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Cus_Vip")
    private long idCusVip;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PersonIdPerson")
    private Integer personIdPerson;
    @JoinColumn(name = "PersonIdPerson", referencedColumnName = "IdPerson", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public CustomerVip() {
    }

    public CustomerVip(Integer personIdPerson) {
        this.personIdPerson = personIdPerson;
    }

    public CustomerVip(Integer personIdPerson, long idCusVip) {
        this.personIdPerson = personIdPerson;
        this.idCusVip = idCusVip;
    }

    public long getIdCusVip() {
        return idCusVip;
    }

    public void setIdCusVip(long idCusVip) {
        this.idCusVip = idCusVip;
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
        if (!(object instanceof CustomerVip)) {
            return false;
        }
        CustomerVip other = (CustomerVip) object;
        if ((this.personIdPerson == null && other.personIdPerson != null) || (this.personIdPerson != null && !this.personIdPerson.equals(other.personIdPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesPerson.CustomerVip[ personIdPerson=" + personIdPerson + " ]";
    }
    
}
