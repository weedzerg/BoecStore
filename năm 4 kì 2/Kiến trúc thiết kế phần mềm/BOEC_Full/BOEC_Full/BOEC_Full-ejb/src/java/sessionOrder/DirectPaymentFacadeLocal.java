/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionOrder;

import entitiesOrder.DirectPayment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface DirectPaymentFacadeLocal {

    void create(DirectPayment directPayment);

    void edit(DirectPayment directPayment);

    void remove(DirectPayment directPayment);

    DirectPayment find(Object id);

    List<DirectPayment> findAll();

    List<DirectPayment> findRange(int[] range);

    int count();
    
}
