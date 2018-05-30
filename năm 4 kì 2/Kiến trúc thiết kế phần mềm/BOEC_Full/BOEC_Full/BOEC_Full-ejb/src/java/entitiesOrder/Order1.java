/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesOrder;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nguye
 */
@Entity
@Table(name = "order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o")
    , @NamedQuery(name = "Order1.findByIdOrder", query = "SELECT o FROM Order1 o WHERE o.idOrder = :idOrder")
    , @NamedQuery(name = "Order1.findByProductIdProduct", query = "SELECT o FROM Order1 o WHERE o.productIdProduct = :productIdProduct")
    , @NamedQuery(name = "Order1.findByPaymentIdPayment", query = "SELECT o FROM Order1 o WHERE o.paymentIdPayment = :paymentIdPayment")
    , @NamedQuery(name = "Order1.findByTranferIdTransfer", query = "SELECT o FROM Order1 o WHERE o.tranferIdTransfer = :tranferIdTransfer")
    , @NamedQuery(name = "Order1.findByCustomerPersonIdPerson", query = "SELECT o FROM Order1 o WHERE o.customerPersonIdPerson = :customerPersonIdPerson")
    , @NamedQuery(name = "Order1.findByStateIdState", query = "SELECT o FROM Order1 o WHERE o.stateIdState = :stateIdState")
    , @NamedQuery(name = "Order1.findByTotal", query = "SELECT o FROM Order1 o WHERE o.total = :total")
    , @NamedQuery(name = "Order1.findByDate", query = "SELECT o FROM Order1 o WHERE o.date = :date")
    , @NamedQuery(name = "Order1.findByAddress", query = "SELECT o FROM Order1 o WHERE o.address = :address")})
public class Order1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdOrder")
    private Long idOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProductIdProduct")
    private long productIdProduct;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PaymentIdPayment")
    private long paymentIdPayment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TranferIdTransfer")
    private long tranferIdTransfer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CustomerPersonIdPerson")
    private int customerPersonIdPerson;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StateIdState")
    private long stateIdState;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Total")
    private double total;
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 255)
    @Column(name = "Address")
    private String address;

    public Order1() {
    }

    public Order1(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Order1(Long idOrder, long productIdProduct, long paymentIdPayment, long tranferIdTransfer, int customerPersonIdPerson, long stateIdState, double total) {
        this.idOrder = idOrder;
        this.productIdProduct = productIdProduct;
        this.paymentIdPayment = paymentIdPayment;
        this.tranferIdTransfer = tranferIdTransfer;
        this.customerPersonIdPerson = customerPersonIdPerson;
        this.stateIdState = stateIdState;
        this.total = total;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public long getProductIdProduct() {
        return productIdProduct;
    }

    public void setProductIdProduct(long productIdProduct) {
        this.productIdProduct = productIdProduct;
    }

    public long getPaymentIdPayment() {
        return paymentIdPayment;
    }

    public void setPaymentIdPayment(long paymentIdPayment) {
        this.paymentIdPayment = paymentIdPayment;
    }

    public long getTranferIdTransfer() {
        return tranferIdTransfer;
    }

    public void setTranferIdTransfer(long tranferIdTransfer) {
        this.tranferIdTransfer = tranferIdTransfer;
    }

    public int getCustomerPersonIdPerson() {
        return customerPersonIdPerson;
    }

    public void setCustomerPersonIdPerson(int customerPersonIdPerson) {
        this.customerPersonIdPerson = customerPersonIdPerson;
    }

    public long getStateIdState() {
        return stateIdState;
    }

    public void setStateIdState(long stateIdState) {
        this.stateIdState = stateIdState;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1)) {
            return false;
        }
        Order1 other = (Order1) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesOrder.Order1[ idOrder=" + idOrder + " ]";
    }
    
}
