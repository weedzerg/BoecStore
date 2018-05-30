/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionPerson;

import entitiesPerson.CustomerVip;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface CustomerVipFacadeLocal {

    void create(CustomerVip customerVip);

    void edit(CustomerVip customerVip);

    void remove(CustomerVip customerVip);

    CustomerVip find(Object id);

    List<CustomerVip> findAll();

    List<CustomerVip> findRange(int[] range);

    int count();
    
}
