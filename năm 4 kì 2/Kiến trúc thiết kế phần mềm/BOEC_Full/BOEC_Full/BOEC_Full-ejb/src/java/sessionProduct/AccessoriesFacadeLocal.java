/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.Accessories;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface AccessoriesFacadeLocal {

    void create(Accessories accessories);

    void edit(Accessories accessories);

    void remove(Accessories accessories);

    Accessories find(Object id);

    List<Accessories> findAll();

    List<Accessories> findRange(int[] range);

    int count();
    
}
