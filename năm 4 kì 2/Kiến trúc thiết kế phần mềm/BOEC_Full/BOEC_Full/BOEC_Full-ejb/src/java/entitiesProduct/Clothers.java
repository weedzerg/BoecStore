/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesProduct;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nguye
 */
@Entity
@Table(name = "clothers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clothers.findAll", query = "SELECT c FROM Clothers c")
    , @NamedQuery(name = "Clothers.findByIdClother", query = "SELECT c FROM Clothers c WHERE c.idClother = :idClother")
    , @NamedQuery(name = "Clothers.findBySize", query = "SELECT c FROM Clothers c WHERE c.size = :size")
    , @NamedQuery(name = "Clothers.findByColor", query = "SELECT c FROM Clothers c WHERE c.color = :color")
    , @NamedQuery(name = "Clothers.findByProductIdProduct", query = "SELECT c FROM Clothers c WHERE c.productIdProduct = :productIdProduct")})
public class Clothers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdClother")
    private long idClother;
    @Size(max = 255)
    @Column(name = "Size")
    private String size;
    @Size(max = 255)
    @Column(name = "Color")
    private String color;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProductIdProduct")
    private Long productIdProduct;
    @JoinColumn(name = "ProductIdProduct", referencedColumnName = "IdProduct", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Product product;

    public Clothers() {
    }

    public Clothers(Long productIdProduct) {
        this.productIdProduct = productIdProduct;
    }

    public Clothers(Long productIdProduct, long idClother) {
        this.productIdProduct = productIdProduct;
        this.idClother = idClother;
    }

    public long getIdClother() {
        return idClother;
    }

    public void setIdClother(long idClother) {
        this.idClother = idClother;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getProductIdProduct() {
        return productIdProduct;
    }

    public void setProductIdProduct(Long productIdProduct) {
        this.productIdProduct = productIdProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productIdProduct != null ? productIdProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clothers)) {
            return false;
        }
        Clothers other = (Clothers) object;
        if ((this.productIdProduct == null && other.productIdProduct != null) || (this.productIdProduct != null && !this.productIdProduct.equals(other.productIdProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesProduct.Clothers[ productIdProduct=" + productIdProduct + " ]";
    }
    
}
