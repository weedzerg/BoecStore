/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesProduct;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nguye
 */
@Entity
@Table(name = "typeproduct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typeproduct.findAll", query = "SELECT t FROM Typeproduct t")
    , @NamedQuery(name = "Typeproduct.findByIdtypeProduct", query = "SELECT t FROM Typeproduct t WHERE t.idtypeProduct = :idtypeProduct")
    , @NamedQuery(name = "Typeproduct.findByNameType", query = "SELECT t FROM Typeproduct t WHERE t.nameType = :nameType")})
public class Typeproduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_typeProduct")
    private Long idtypeProduct;
    @Size(max = 255)
    @Column(name = "NameType")
    private String nameType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeProductIdtypeProduct")
    private List<Product> productList;

    public Typeproduct() {
    }

    public Typeproduct(Long idtypeProduct, String nameType) {
        this.idtypeProduct = idtypeProduct;
        this.nameType = nameType;
    }

    public Typeproduct(Long idtypeProduct) {
        this.idtypeProduct = idtypeProduct;
    }

    public Typeproduct(String idtypeProduct, String nameType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long getIdtypeProduct() {
        return idtypeProduct;
    }

    public void setIdtypeProduct(Long idtypeProduct) {
        this.idtypeProduct = idtypeProduct;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypeProduct != null ? idtypeProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typeproduct)) {
            return false;
        }
        Typeproduct other = (Typeproduct) object;
        if ((this.idtypeProduct == null && other.idtypeProduct != null) || (this.idtypeProduct != null && !this.idtypeProduct.equals(other.idtypeProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesProduct.Typeproduct[ idtypeProduct=" + idtypeProduct + " ]";
    }
    
}
