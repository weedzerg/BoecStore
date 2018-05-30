/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesProduct;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nguye
 */
@Entity
@Table(name = "book")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
    , @NamedQuery(name = "Book.findByIdBook", query = "SELECT b FROM Book b WHERE b.idBook = :idBook")
    , @NamedQuery(name = "Book.findByDate", query = "SELECT b FROM Book b WHERE b.date = :date")
    , @NamedQuery(name = "Book.findByProductIdProduct", query = "SELECT b FROM Book b WHERE b.productIdProduct = :productIdProduct")})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdBook")
    private long idBook;
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProductIdProduct")
    private Long productIdProduct;
    @JoinColumn(name = "ProductIdProduct", referencedColumnName = "IdProduct", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Product product;
    @JoinColumn(name = "AuthorIdAuthor", referencedColumnName = "IdAuthor")
    @ManyToOne(optional = false)
    private Author authorIdAuthor;
    @JoinColumn(name = "PublisherIdPub", referencedColumnName = "IdPub")
    @ManyToOne(optional = false)
    private Publisher publisherIdPub;

    public Book() {
    }

    public Book(Long productIdProduct) {
        this.productIdProduct = productIdProduct;
    }

    public Book(Long productIdProduct, long idBook) {
        this.productIdProduct = productIdProduct;
        this.idBook = idBook;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Author getAuthorIdAuthor() {
        return authorIdAuthor;
    }

    public void setAuthorIdAuthor(Author authorIdAuthor) {
        this.authorIdAuthor = authorIdAuthor;
    }

    public Publisher getPublisherIdPub() {
        return publisherIdPub;
    }

    public void setPublisherIdPub(Publisher publisherIdPub) {
        this.publisherIdPub = publisherIdPub;
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
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.productIdProduct == null && other.productIdProduct != null) || (this.productIdProduct != null && !this.productIdProduct.equals(other.productIdProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesProduct.Book[ productIdProduct=" + productIdProduct + " ]";
    }
    
}
