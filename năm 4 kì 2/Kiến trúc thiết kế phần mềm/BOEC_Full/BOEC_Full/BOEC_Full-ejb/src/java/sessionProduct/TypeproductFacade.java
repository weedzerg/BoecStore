/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.Typeproduct;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nguye
 */
@Stateless
public class TypeproductFacade extends AbstractFacade<Typeproduct> implements TypeproductFacadeLocal {

    @PersistenceContext(unitName = "BOEC_Full-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeproductFacade() {
        super(Typeproduct.class);
    }
    public List getAllTypes(){
        return em.createNamedQuery("Typeproduct.findAll").getResultList();
    }

    
}
