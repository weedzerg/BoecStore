/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nguye
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> implements ProductFacadeLocal {

    @PersistenceContext(unitName = "BOEC_Full-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    @Override
    public List<Product> getAll() {
        return em.createNamedQuery("Product.findAll").getResultList();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getStudent(long id) {
       return em.find(Product.class, id); //To change body of generated methods, choose Tools | Templates.
    }
   

    
}
