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
@Table(name = "mastercard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mastercard.findAll", query = "SELECT m FROM Mastercard m")
    , @NamedQuery(name = "Mastercard.findById", query = "SELECT m FROM Mastercard m WHERE m.id = :id")
    , @NamedQuery(name = "Mastercard.findByPaymentIdPayment", query = "SELECT m FROM Mastercard m WHERE m.paymentIdPayment = :paymentIdPayment")})
public class Mastercard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private long id;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PaymentIdPayment")
    private Long paymentIdPayment;
    @JoinColumn(name = "PaymentIdPayment", referencedColumnName = "IdPayment", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Payment payment;

    public Mastercard() {
    }

    public Mastercard(Long paymentIdPayment) {
        this.paymentIdPayment = paymentIdPayment;
    }

    public Mastercard(Long paymentIdPayment, long id) {
        this.paymentIdPayment = paymentIdPayment;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getPaymentIdPayment() {
        return paymentIdPayment;
    }

    public void setPaymentIdPayment(Long paymentIdPayment) {
        this.paymentIdPayment = paymentIdPayment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentIdPayment != null ? paymentIdPayment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mastercard)) {
            return false;
        }
        Mastercard other = (Mastercard) object;
        if ((this.paymentIdPayment == null && other.paymentIdPayment != null) || (this.paymentIdPayment != null && !this.paymentIdPayment.equals(other.paymentIdPayment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesOrder.Mastercard[ paymentIdPayment=" + paymentIdPayment + " ]";
    }
    
}
