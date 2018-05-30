/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesProduct;

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
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findByIdProduct", query = "SELECT p FROM Product p WHERE p.idProduct = :idProduct")
    , @NamedQuery(name = "Product.findByIdTypeProduct", query = "SELECT p FROM Product p WHERE p.typeProductIdtypeProduct = :typeProductIdtypeProduct")
    , @NamedQuery(name = "Product.findByNameProduct", query = "SELECT p FROM Product p WHERE p.nameProduct = :nameProduct")
    , @NamedQuery(name = "Product.findByImage", query = "SELECT p FROM Product p WHERE p.image = :image")
    , @NamedQuery(name = "Product.findByInformation", query = "SELECT p FROM Product p WHERE p.information = :information")
    , @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdProduct")
    private Long idProduct;
    @Size(max = 255)
    @Column(name = "NameProduct")
    private String nameProduct;
    @Size(max = 255)
    @Column(name = "Image")
    private String image;
    @Size(max = 255)
    @Column(name = "Information")
    private String information;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private double price;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private ChilClother chilClother;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Electronics electronics;
    @JoinColumn(name = "TypeProductId_typeProduct", referencedColumnName = "Id_typeProduct")
    @ManyToOne(optional = false)
    private Typeproduct typeProductIdtypeProduct;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private GeographyBook geographyBook;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private AdultClother adultClother;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Accessories accessories;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Book book;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private BoyClother boyClother;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private MathBook mathBook;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private LiteratureBook literatureBook;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private WomanClother womanClother;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private BusinessBook businessBook;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private HistoryBook historyBook;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private ManClother manClother;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private OfficeElec officeElec;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private GirlClother girlClother;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private HousewareElec housewareElec;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Clothers clothers;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private PoliticalBook politicalBook;

    public Product() {
    }

    public Product(Long idProduct, String nameProduct, String image, String information, double price) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.image = image;
        this.information = information;
        this.price = price;
    }

    public Product(Long idProduct, String nameProduct, String image, String information, double price, Typeproduct typeProductIdtypeProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.image = image;
        this.information = information;
        this.price = price;
        this.typeProductIdtypeProduct = typeProductIdtypeProduct;
    }

    public Product(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Product(Long idProduct, double price) {
        this.idProduct = idProduct;
        this.price = price;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ChilClother getChilClother() {
        return chilClother;
    }

    public void setChilClother(ChilClother chilClother) {
        this.chilClother = chilClother;
    }

    public Electronics getElectronics() {
        return electronics;
    }

    public void setElectronics(Electronics electronics) {
        this.electronics = electronics;
    }

    public Typeproduct getTypeProductIdtypeProduct() {
        return typeProductIdtypeProduct;
    }

    public void setTypeProductIdtypeProduct(Typeproduct typeProductIdtypeProduct) {
        this.typeProductIdtypeProduct = typeProductIdtypeProduct;
    }

    public GeographyBook getGeographyBook() {
        return geographyBook;
    }

    public void setGeographyBook(GeographyBook geographyBook) {
        this.geographyBook = geographyBook;
    }

    public AdultClother getAdultClother() {
        return adultClother;
    }

    public void setAdultClother(AdultClother adultClother) {
        this.adultClother = adultClother;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BoyClother getBoyClother() {
        return boyClother;
    }

    public void setBoyClother(BoyClother boyClother) {
        this.boyClother = boyClother;
    }

    public MathBook getMathBook() {
        return mathBook;
    }

    public void setMathBook(MathBook mathBook) {
        this.mathBook = mathBook;
    }

    public LiteratureBook getLiteratureBook() {
        return literatureBook;
    }

    public void setLiteratureBook(LiteratureBook literatureBook) {
        this.literatureBook = literatureBook;
    }

    public WomanClother getWomanClother() {
        return womanClother;
    }

    public void setWomanClother(WomanClother womanClother) {
        this.womanClother = womanClother;
    }

    public BusinessBook getBusinessBook() {
        return businessBook;
    }

    public void setBusinessBook(BusinessBook businessBook) {
        this.businessBook = businessBook;
    }

    public HistoryBook getHistoryBook() {
        return historyBook;
    }

    public void setHistoryBook(HistoryBook historyBook) {
        this.historyBook = historyBook;
    }

    public ManClother getManClother() {
        return manClother;
    }

    public void setManClother(ManClother manClother) {
        this.manClother = manClother;
    }

    public OfficeElec getOfficeElec() {
        return officeElec;
    }

    public void setOfficeElec(OfficeElec officeElec) {
        this.officeElec = officeElec;
    }

    public GirlClother getGirlClother() {
        return girlClother;
    }

    public void setGirlClother(GirlClother girlClother) {
        this.girlClother = girlClother;
    }

    public HousewareElec getHousewareElec() {
        return housewareElec;
    }

    public void setHousewareElec(HousewareElec housewareElec) {
        this.housewareElec = housewareElec;
    }

    public Clothers getClothers() {
        return clothers;
    }

    public void setClothers(Clothers clothers) {
        this.clothers = clothers;
    }

    public PoliticalBook getPoliticalBook() {
        return politicalBook;
    }

    public void setPoliticalBook(PoliticalBook politicalBook) {
        this.politicalBook = politicalBook;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduct != null ? idProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.idProduct == null && other.idProduct != null) || (this.idProduct != null && !this.idProduct.equals(other.idProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesProduct.Product[ idProduct=" + idProduct + " ]";
    }
    
}
