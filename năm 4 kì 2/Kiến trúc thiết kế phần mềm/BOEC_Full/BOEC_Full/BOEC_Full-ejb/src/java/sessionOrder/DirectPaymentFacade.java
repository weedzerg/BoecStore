/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionOrder;

import entitiesOrder.DirectPayment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nguye
 */
@Stateless
public class DirectPaymentFacade extends AbstractFacade<DirectPayment> implements DirectPaymentFacadeLocal {

    @PersistenceContext(unitName = "BOEC_Full-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DirectPaymentFacade() {
        super(DirectPayment.class);
    }
    
}
