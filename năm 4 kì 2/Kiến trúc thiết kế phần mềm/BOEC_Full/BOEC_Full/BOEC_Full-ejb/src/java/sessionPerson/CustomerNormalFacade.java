/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionPerson;

import entitiesPerson.CustomerNormal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nguye
 */
@Stateless
public class CustomerNormalFacade extends AbstractFacade<CustomerNormal> implements CustomerNormalFacadeLocal {

    @PersistenceContext(unitName = "BOEC_Full-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerNormalFacade() {
        super(CustomerNormal.class);
    }
    
}
