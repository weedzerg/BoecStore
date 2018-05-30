/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.BusinessBook;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface BusinessBookFacadeLocal {

    void create(BusinessBook businessBook);

    void edit(BusinessBook businessBook);

    void remove(BusinessBook businessBook);

    BusinessBook find(Object id);

    List<BusinessBook> findAll();

    List<BusinessBook> findRange(int[] range);

    int count();
    
}
