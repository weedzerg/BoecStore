/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.BoyClother;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface BoyClotherFacadeLocal {

    void create(BoyClother boyClother);

    void edit(BoyClother boyClother);

    void remove(BoyClother boyClother);

    BoyClother find(Object id);

    List<BoyClother> findAll();

    List<BoyClother> findRange(int[] range);

    int count();
    
}
