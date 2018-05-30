/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionOrder;

import entitiesOrder.OnlinePay;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface OnlinePayFacadeLocal {

    void create(OnlinePay onlinePay);

    void edit(OnlinePay onlinePay);

    void remove(OnlinePay onlinePay);

    OnlinePay find(Object id);

    List<OnlinePay> findAll();

    List<OnlinePay> findRange(int[] range);

    int count();
    
}
