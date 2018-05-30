/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesOrder;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nguye
 */
@Entity
@Table(name = "state")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "State.findAll", query = "SELECT s FROM State s")
    , @NamedQuery(name = "State.findByIdState", query = "SELECT s FROM State s WHERE s.idState = :idState")
    , @NamedQuery(name = "State.findByName", query = "SELECT s FROM State s WHERE s.name = :name")})
public class State implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdState")
    private Long idState;
    @Size(max = 255)
    @Column(name = "Name")
    private String name;

    public State() {
    }

    public State(Long idState) {
        this.idState = idState;
    }

    public Long getIdState() {
        return idState;
    }

    public void setIdState(Long idState) {
        this.idState = idState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idState != null ? idState.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof State)) {
            return false;
        }
        State other = (State) object;
        if ((this.idState == null && other.idState != null) || (this.idState != null && !this.idState.equals(other.idState))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesOrder.State[ idState=" + idState + " ]";
    }
    
}
