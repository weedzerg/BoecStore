/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.Publisher;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nguye
 */
@Stateless
public class PublisherFacade extends AbstractFacade<Publisher> implements PublisherFacadeLocal {

    @PersistenceContext(unitName = "BOEC_Full-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublisherFacade() {
        super(Publisher.class);
    }
    
}
