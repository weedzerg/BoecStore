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
@Table(name = "payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")
    , @NamedQuery(name = "Payment.findByIdPayment", query = "SELECT p FROM Payment p WHERE p.idPayment = :idPayment")
    , @NamedQuery(name = "Payment.findByName", query = "SELECT p FROM Payment p WHERE p.name = :name")})
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPayment")
    private Long idPayment;
    @Size(max = 255)
    @Column(name = "Name")
    private String name;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "payment")
    private OnlinePay onlinePay;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "payment")
    private Creditcrard creditcrard;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "payment")
    private Mastercard mastercard;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "payment")
    private Debitcard debitcard;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "payment")
    private DirectPayment directPayment;

    public Payment() {
    }

    public Payment(Long idPayment) {
        this.idPayment = idPayment;
    }

    public Long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OnlinePay getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(OnlinePay onlinePay) {
        this.onlinePay = onlinePay;
    }

    public Creditcrard getCreditcrard() {
        return creditcrard;
    }

    public void setCreditcrard(Creditcrard creditcrard) {
        this.creditcrard = creditcrard;
    }

    public Mastercard getMastercard() {
        return mastercard;
    }

    public void setMastercard(Mastercard mastercard) {
        this.mastercard = mastercard;
    }

    public Debitcard getDebitcard() {
        return debitcard;
    }

    public void setDebitcard(Debitcard debitcard) {
        this.debitcard = debitcard;
    }

    public DirectPayment getDirectPayment() {
        return directPayment;
    }

    public void setDirectPayment(DirectPayment directPayment) {
        this.directPayment = directPayment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPayment != null ? idPayment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.idPayment == null && other.idPayment != null) || (this.idPayment != null && !this.idPayment.equals(other.idPayment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesOrder.Payment[ idPayment=" + idPayment + " ]";
    }
    
}
