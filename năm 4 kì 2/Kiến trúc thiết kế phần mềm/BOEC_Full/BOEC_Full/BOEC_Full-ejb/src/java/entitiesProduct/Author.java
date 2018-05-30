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
@Table(name = "author")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a")
    , @NamedQuery(name = "Author.findByIdAuthor", query = "SELECT a FROM Author a WHERE a.idAuthor = :idAuthor")
    , @NamedQuery(name = "Author.findByNameAutgoe", query = "SELECT a FROM Author a WHERE a.nameAutgoe = :nameAutgoe")
    , @NamedQuery(name = "Author.findByInformation", query = "SELECT a FROM Author a WHERE a.information = :information")})
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdAuthor")
    private Integer idAuthor;
    @Size(max = 255)
    @Column(name = "NameAutgoe")
    private String nameAutgoe;
    @Size(max = 255)
    @Column(name = "Information")
    private String information;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorIdAuthor")
    private List<Book> bookList;

    public Author() {
    }

    public Author(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Integer getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getNameAutgoe() {
        return nameAutgoe;
    }

    public void setNameAutgoe(String nameAutgoe) {
        this.nameAutgoe = nameAutgoe;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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
        hash += (idAuthor != null ? idAuthor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Author)) {
            return false;
        }
        Author other = (Author) object;
        if ((this.idAuthor == null && other.idAuthor != null) || (this.idAuthor != null && !this.idAuthor.equals(other.idAuthor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesProduct.Author[ idAuthor=" + idAuthor + " ]";
    }
    
}
