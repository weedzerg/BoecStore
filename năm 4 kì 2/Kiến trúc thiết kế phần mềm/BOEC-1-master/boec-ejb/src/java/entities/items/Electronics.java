/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.items;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DoHue
 */
@Entity
@Table(name = "electronics")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Electronics.findAll", query = "SELECT e FROM Electronics e")
    , @NamedQuery(name = "Electronics.findById", query = "SELECT e FROM Electronics e WHERE e.id = :id")
    , @NamedQuery(name = "Electronics.findBySize", query = "SELECT e FROM Electronics e WHERE e.size = :size")
    , @NamedQuery(name = "Electronics.findByWeight", query = "SELECT e FROM Electronics e WHERE e.weight = :weight")})
public class Electronics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "size")
    private String size;
    @Size(max = 100)
    @Column(name = "weight")
    private String weight;
    @JoinColumn(name = "ItemId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Item itemId;

    public Electronics() {
    }

    public Electronics(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
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
        if (!(object instanceof Electronics)) {
            return false;
        }
        Electronics other = (Electronics) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Electronics[ id=" + id + " ]";
    }
    
}
