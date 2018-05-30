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
@Table(name = "electronics")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Electronics.findAll", query = "SELECT e FROM Electronics e")
    , @NamedQuery(name = "Electronics.findByIdElec", query = "SELECT e FROM Electronics e WHERE e.idElec = :idElec")
    , @NamedQuery(name = "Electronics.findByUse", query = "SELECT e FROM Electronics e WHERE e.use = :use")
    , @NamedQuery(name = "Electronics.findByProductIdProduct", query = "SELECT e FROM Electronics e WHERE e.productIdProduct = :productIdProduct")})
public class Electronics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdElec")
    private long idElec;
    @Size(max = 255)
    @Column(name = "Use")
    private String use;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProductIdProduct")
    private Long productIdProduct;
    @JoinColumn(name = "ProductIdProduct", referencedColumnName = "IdProduct", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Product product;

    public Electronics() {
    }

    public Electronics(Long productIdProduct) {
        this.productIdProduct = productIdProduct;
    }

    public Electronics(Long productIdProduct, long idElec) {
        this.productIdProduct = productIdProduct;
        this.idElec = idElec;
    }

    public long getIdElec() {
        return idElec;
    }

    public void setIdElec(long idElec) {
        this.idElec = idElec;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
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
        if (!(object instanceof Electronics)) {
            return false;
        }
        Electronics other = (Electronics) object;
        if ((this.productIdProduct == null && other.productIdProduct != null) || (this.productIdProduct != null && !this.productIdProduct.equals(other.productIdProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesProduct.Electronics[ productIdProduct=" + productIdProduct + " ]";
    }
    
}
