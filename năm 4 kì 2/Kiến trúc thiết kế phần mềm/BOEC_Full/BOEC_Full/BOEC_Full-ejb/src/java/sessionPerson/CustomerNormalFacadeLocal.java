/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionPerson;

import entitiesPerson.CustomerNormal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface CustomerNormalFacadeLocal {

    void create(CustomerNormal customerNormal);

    void edit(CustomerNormal customerNormal);

    void remove(CustomerNormal customerNormal);

    CustomerNormal find(Object id);

    List<CustomerNormal> findAll();

    List<CustomerNormal> findRange(int[] range);

    int count();
    
}
