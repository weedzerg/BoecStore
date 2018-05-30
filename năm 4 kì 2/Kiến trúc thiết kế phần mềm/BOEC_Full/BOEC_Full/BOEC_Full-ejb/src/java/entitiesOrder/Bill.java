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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nguye
 */
@Entity
@Table(name = "bill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b")
    , @NamedQuery(name = "Bill.findByIdBill", query = "SELECT b FROM Bill b WHERE b.idBill = :idBill")
    , @NamedQuery(name = "Bill.findByOrderIdOrder", query = "SELECT b FROM Bill b WHERE b.orderIdOrder = :orderIdOrder")
    , @NamedQuery(name = "Bill.findByEmployeePersonIdPerson", query = "SELECT b FROM Bill b WHERE b.employeePersonIdPerson = :employeePersonIdPerson")
    , @NamedQuery(name = "Bill.findByPrice", query = "SELECT b FROM Bill b WHERE b.price = :price")
    , @NamedQuery(name = "Bill.findByQuantity", query = "SELECT b FROM Bill b WHERE b.quantity = :quantity")})
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdBill")
    private Long idBill;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OrderIdOrder")
    private long orderIdOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EmployeePersonIdPerson")
    private int employeePersonIdPerson;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity")
    private int quantity;

    public Bill() {
    }

    public Bill(Long idBill) {
        this.idBill = idBill;
    }

    public Bill(Long idBill, long orderIdOrder, int employeePersonIdPerson, double price, int quantity) {
        this.idBill = idBill;
        this.orderIdOrder = orderIdOrder;
        this.employeePersonIdPerson = employeePersonIdPerson;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getIdBill() {
        return idBill;
    }

    public void setIdBill(Long idBill) {
        this.idBill = idBill;
    }

    public long getOrderIdOrder() {
        return orderIdOrder;
    }

    public void setOrderIdOrder(long orderIdOrder) {
        this.orderIdOrder = orderIdOrder;
    }

    public int getEmployeePersonIdPerson() {
        return employeePersonIdPerson;
    }

    public void setEmployeePersonIdPerson(int employeePersonIdPerson) {
        this.employeePersonIdPerson = employeePersonIdPerson;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBill != null ? idBill.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.idBill == null && other.idBill != null) || (this.idBill != null && !this.idBill.equals(other.idBill))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesOrder.Bill[ idBill=" + idBill + " ]";
    }
    
}
