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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nguye
 */
@Entity
@Table(name = "girl_clother")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GirlClother.findAll", query = "SELECT g FROM GirlClother g")
    , @NamedQuery(name = "GirlClother.findById", query = "SELECT g FROM GirlClother g WHERE g.id = :id")
    , @NamedQuery(name = "GirlClother.findByProductIdProduct", query = "SELECT g FROM GirlClother g WHERE g.productIdProduct = :productIdProduct")})
public class GirlClother implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private long id;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProductIdProduct")
    private Long productIdProduct;
    @JoinColumn(name = "ProductIdProduct", referencedColumnName = "IdProduct", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Product product;

    public GirlClother() {
    }

    public GirlClother(Long productIdProduct) {
        this.productIdProduct = productIdProduct;
    }

    public GirlClother(Long productIdProduct, long id) {
        this.productIdProduct = productIdProduct;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        if (!(object instanceof GirlClother)) {
            return false;
        }
        GirlClother other = (GirlClother) object;
        if ((this.productIdProduct == null && other.productIdProduct != null) || (this.productIdProduct != null && !this.productIdProduct.equals(other.productIdProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesProduct.GirlClother[ productIdProduct=" + productIdProduct + " ]";
    }
    
}
