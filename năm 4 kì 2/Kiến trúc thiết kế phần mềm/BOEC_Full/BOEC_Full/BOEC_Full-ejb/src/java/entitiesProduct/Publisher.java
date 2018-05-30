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
@Table(name = "publisher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publisher.findAll", query = "SELECT p FROM Publisher p")
    , @NamedQuery(name = "Publisher.findByIdPub", query = "SELECT p FROM Publisher p WHERE p.idPub = :idPub")
    , @NamedQuery(name = "Publisher.findByNamePub", query = "SELECT p FROM Publisher p WHERE p.namePub = :namePub")
    , @NamedQuery(name = "Publisher.findByAddress", query = "SELECT p FROM Publisher p WHERE p.address = :address")})
public class Publisher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPub")
    private Integer idPub;
    @Size(max = 255)
    @Column(name = "NamePub")
    private String namePub;
    @Size(max = 255)
    @Column(name = "Address")
    private String address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publisherIdPub")
    private List<Book> bookList;

    public Publisher() {
    }

    public Publisher(Integer idPub) {
        this.idPub = idPub;
    }

    public Integer getIdPub() {
        return idPub;
    }

    public void setIdPub(Integer idPub) {
        this.idPub = idPub;
    }

    public String getNamePub() {
        return namePub;
    }

    public void setNamePub(String namePub) {
        this.namePub = namePub;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPub != null ? idPub.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publisher)) {
            return false;
        }
        Publisher other = (Publisher) object;
        if ((this.idPub == null && other.idPub != null) || (this.idPub != null && !this.idPub.equals(other.idPub))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesProduct.Publisher[ idPub=" + idPub + " ]";
    }
    
}
