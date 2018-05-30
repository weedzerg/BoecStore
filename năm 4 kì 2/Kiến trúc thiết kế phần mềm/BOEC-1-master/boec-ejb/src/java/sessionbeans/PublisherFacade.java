/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.person.Publisher;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author DoHue
 */
@Stateless
public class PublisherFacade extends AbstractFacade<Publisher> implements PublisherFacadeLocal {

    @PersistenceContext(unitName = "boec-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublisherFacade() {
        super(Publisher.class);
    }
    
}
