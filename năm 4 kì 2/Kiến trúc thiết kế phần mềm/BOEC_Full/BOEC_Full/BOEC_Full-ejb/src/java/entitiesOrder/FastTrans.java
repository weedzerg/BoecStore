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
@Table(name = "fast_trans")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FastTrans.findAll", query = "SELECT f FROM FastTrans f")
    , @NamedQuery(name = "FastTrans.findById", query = "SELECT f FROM FastTrans f WHERE f.id = :id")
    , @NamedQuery(name = "FastTrans.findByTranferIdTransfer", query = "SELECT f FROM FastTrans f WHERE f.tranferIdTransfer = :tranferIdTransfer")})
public class FastTrans implements Serializable {

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

    public FastTrans() {
    }

    public FastTrans(Long tranferIdTransfer) {
        this.tranferIdTransfer = tranferIdTransfer;
    }

    public FastTrans(Long tranferIdTransfer, long id) {
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
        if (!(object instanceof FastTrans)) {
            return false;
        }
        FastTrans other = (FastTrans) object;
        if ((this.tranferIdTransfer == null && other.tranferIdTransfer != null) || (this.tranferIdTransfer != null && !this.tranferIdTransfer.equals(other.tranferIdTransfer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesOrder.FastTrans[ tranferIdTransfer=" + tranferIdTransfer + " ]";
    }
    
}
