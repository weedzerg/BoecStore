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
@Table(name = "normal_trans")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NormalTrans.findAll", query = "SELECT n FROM NormalTrans n")
    , @NamedQuery(name = "NormalTrans.findById", query = "SELECT n FROM NormalTrans n WHERE n.id = :id")
    , @NamedQuery(name = "NormalTrans.findByTranferIdTransfer", query = "SELECT n FROM NormalTrans n WHERE n.tranferIdTransfer = :tranferIdTransfer")})
public class NormalTrans implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private long id;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TranferIdTransfer")
    private Long tranferIdTransfer;
    @JoinColumn(name = "TranferIdTransfer", referencedColumnName = "IdTransfer", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Tranfer tranfer;

    public NormalTrans() {
    }

    public NormalTrans(Long tranferIdTransfer) {
        this.tranferIdTransfer = tranferIdTransfer;
    }

    public NormalTrans(Long tranferIdTransfer, long id) {
        this.tranferIdTransfer = tranferIdTransfer;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getTranferIdTransfer() {
        return tranferIdTransfer;
    }

    public void setTranferIdTransfer(Long tranferIdTransfer) {
        this.tranferIdTransfer = tranferIdTransfer;
    }

    public Tranfer getTranfer() {
        return tranfer;
    }

    public void setTranfer(Tranfer tranfer) {
        this.tranfer = tranfer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tranferIdTransfer != null ? tranferIdTransfer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NormalTrans)) {
            return false;
        }
        NormalTrans other = (NormalTrans) object;
        if ((this.tranferIdTransfer == null && other.tranferIdTransfer != null) || (this.tranferIdTransfer != null && !this.tranferIdTransfer.equals(other.tranferIdTransfer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesOrder.NormalTrans[ tranferIdTransfer=" + tranferIdTransfer + " ]";
    }
    
}
