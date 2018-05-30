/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesPerson;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nguye
 */
@Entity
@Table(name = "person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findByIdPerson", query = "SELECT p FROM Person p WHERE p.idPerson = :idPerson")
    , @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email = :email")
    , @NamedQuery(name = "Person.findByPhone", query = "SELECT p FROM Person p WHERE p.phone = :phone")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPerson")
    private Integer idPerson;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Phone")
    private int phone;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private EmployeeOnline employeeOnline;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private CustomerVip customerVip;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private CustomerNormal customerNormal;
    @JoinColumn(name = "AddressNumber", referencedColumnName = "Number")
    @ManyToOne(optional = false)
    private Address addressNumber;
    @JoinColumn(name = "TypePersonIdType", referencedColumnName = "IdType")
    @ManyToOne(optional = false)
    private Typeperson typePersonIdType;
    @JoinColumn(name = "AccountUsername", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Account accountUsername;
    @JoinColumn(name = "FullNameIdName", referencedColumnName = "IdName")
    @ManyToOne(optional = false)
    private Fullname fullNameIdName;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Employee employee;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private EmployeeStalls employeeStalls;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Customer customer;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private EmployeeWarehouse employeeWarehouse;

    public Person() {
    }

    public Person(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Person(Integer idPerson, int phone) {
        this.idPerson = idPerson;
        this.phone = phone;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public EmployeeOnline getEmployeeOnline() {
        return employeeOnline;
    }

    public void setEmployeeOnline(EmployeeOnline employeeOnline) {
        this.employeeOnline = employeeOnline;
    }

    public CustomerVip getCustomerVip() {
        return customerVip;
    }

    public void setCustomerVip(CustomerVip customerVip) {
        this.customerVip = customerVip;
    }

    public CustomerNormal getCustomerNormal() {
        return customerNormal;
    }

    public void setCustomerNormal(CustomerNormal customerNormal) {
        this.customerNormal = customerNormal;
    }

    public Address getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(Address addressNumber) {
        this.addressNumber = addressNumber;
    }

    public Typeperson getTypePersonIdType() {
        return typePersonIdType;
    }

    public void setTypePersonIdType(Typeperson typePersonIdType) {
        this.typePersonIdType = typePersonIdType;
    }

    public Account getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(Account accountUsername) {
        this.accountUsername = accountUsername;
    }

    public Fullname getFullNameIdName() {
        return fullNameIdName;
    }

    public void setFullNameIdName(Fullname fullNameIdName) {
        this.fullNameIdName = fullNameIdName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeStalls getEmployeeStalls() {
        return employeeStalls;
    }

    public void setEmployeeStalls(EmployeeStalls employeeStalls) {
        this.employeeStalls = employeeStalls;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public EmployeeWarehouse getEmployeeWarehouse() {
        return employeeWarehouse;
    }

    public void setEmployeeWarehouse(EmployeeWarehouse employeeWarehouse) {
        this.employeeWarehouse = employeeWarehouse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerson != null ? idPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesPerson.Person[ idPerson=" + idPerson + " ]";
    }
    
}
