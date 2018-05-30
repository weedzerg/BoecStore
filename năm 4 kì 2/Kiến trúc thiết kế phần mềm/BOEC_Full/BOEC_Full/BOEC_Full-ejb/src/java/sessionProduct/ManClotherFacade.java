/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.ManClother;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nguye
 */
@Stateless
public class ManClotherFacade extends AbstractFacade<ManClother> implements ManClotherFacadeLocal {

    @PersistenceContext(unitName = "BOEC_Full-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManClotherFacade() {
        super(ManClother.class);
    }
    
}
