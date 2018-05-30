/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionOrder;

import entitiesOrder.Creditcrard;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface CreditcrardFacadeLocal {

    void create(Creditcrard creditcrard);

    void edit(Creditcrard creditcrard);

    void remove(Creditcrard creditcrard);

    Creditcrard find(Object id);

    List<Creditcrard> findAll();

    List<Creditcrard> findRange(int[] range);

    int count();
    
}
