/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.Clothers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface ClothersFacadeLocal {

    void create(Clothers clothers);

    void edit(Clothers clothers);

    void remove(Clothers clothers);

    Clothers find(Object id);

    List<Clothers> findAll();

    List<Clothers> findRange(int[] range);

    int count();
    
}
