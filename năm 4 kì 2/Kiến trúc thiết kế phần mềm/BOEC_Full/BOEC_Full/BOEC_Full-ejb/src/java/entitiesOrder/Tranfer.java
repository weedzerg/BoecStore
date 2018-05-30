/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesOrder;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nguye
 */
@Entity
@Table(name = "tranfer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tranfer.findAll", query = "SELECT t FROM Tranfer t")
    , @NamedQuery(name = "Tranfer.findByIdTransfer", query = "SELECT t FROM Tranfer t WHERE t.idTransfer = :idTransfer")
    , @NamedQuery(name = "Tranfer.findByName", query = "SELECT t FROM Tranfer t WHERE t.name = :name")})
public class Tranfer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTransfer")
    private Long idTransfer;
    @Size(max = 255)
    @Column(name = "Name")
    private String name;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tranfer")
    private NormalTrans normalTrans;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tranfer")
    private SavingsTrans savingsTrans;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tranfer")
    private FastTrans fastTrans;

    public Tranfer() {
    }

    public Tranfer(Long idTransfer) {
        this.idTransfer = idTransfer;
    }

    public Long getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(Long idTransfer) {
        this.idTransfer = idTransfer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NormalTrans getNormalTrans() {
        return normalTrans;
    }

    public void setNormalTrans(NormalTrans normalTrans) {
        this.normalTrans = normalTrans;
    }

    public SavingsTrans getSavingsTrans() {
        return savingsTrans;
    }

    public void setSavingsTrans(SavingsTrans savingsTrans) {
        this.savingsTrans = savingsTrans;
    }

    public FastTrans getFastTrans() {
        return fastTrans;
    }

    public void setFastTrans(FastTrans fastTrans) {
        this.fastTrans = fastTrans;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransfer != null ? idTransfer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tranfer)) {
            return false;
        }
        Tranfer other = (Tranfer) object;
        if ((this.idTransfer == null && other.idTransfer != null) || (this.idTransfer != null && !this.idTransfer.equals(other.idTransfer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesOrder.Tranfer[ idTransfer=" + idTransfer + " ]";
    }
    
}
