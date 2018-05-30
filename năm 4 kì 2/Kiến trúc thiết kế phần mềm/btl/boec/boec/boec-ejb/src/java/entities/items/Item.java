/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.items;

import entities.order.Orderdetail;
import entities.person.Publisher;
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DaiPhongPC
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
    , @NamedQuery(name = "Item.findById", query = "SELECT i FROM Item i WHERE i.id = :id")
    , @NamedQuery(name = "Item.findByName", query = "SELECT i FROM Item i WHERE i.name = :name")
    , @NamedQuery(name = "Item.findByImage", query = "SELECT i FROM Item i WHERE i.image = :image")
    , @NamedQuery(name = "Item.findByPrice", query = "SELECT i FROM Item i WHERE i.price = :price")
    , @NamedQuery(name = "Item.findByAmount", query = "SELECT i FROM Item i WHERE i.amount = :amount")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "Name")
    private String name;
    @Size(max = 100)
    @Column(name = "Image")
    private String image;
    @Column(name = "Price")
    private Integer price;
    @Column(name = "Amount")
    private Integer amount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId")
    private Collection<Electronics> electronicsCollection;
    @JoinColumn(name = "PublisherId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Publisher publisherId;
    @JoinColumn(name = "CategoryId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Category categoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId")
    private Collection<Book> bookCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId")
    private Collection<Clothes> clothesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId")
    private Collection<Orderdetail> orderdetailCollection;

    public Item() {
    }

    public Item(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @XmlTransient
    public Collection<Electronics> getElectronicsCollection() {
        return electronicsCollection;
    }

    public void setElectronicsCollection(Collection<Electronics> electronicsCollection) {
        this.electronicsCollection = electronicsCollection;
    }

    public Publisher getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Publisher publisherId) {
        this.publisherId = publisherId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    @XmlTransient
    public Collection<Book> getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(Collection<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    @XmlTransient
    public Collection<Clothes> getClothesCollection() {
        return clothesCollection;
    }

    public void setClothesCollection(Collection<Clothes> clothesCollection) {
        this.clothesCollection = clothesCollection;
    }

    @XmlTransient
    public Collection<Orderdetail> getOrderdetailCollection() {
        return orderdetailCollection;
    }

    public void setOrderdetailCollection(Collection<Orderdetail> orderdetailCollection) {
        this.orderdetailCollection = orderdetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Item[ id=" + id + " ]";
    }
    
}
