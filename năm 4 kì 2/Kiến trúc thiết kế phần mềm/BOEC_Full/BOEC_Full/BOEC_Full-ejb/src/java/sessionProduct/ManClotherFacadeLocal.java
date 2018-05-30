/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.ManClother;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface ManClotherFacadeLocal {

    void create(ManClother manClother);

    void edit(ManClother manClother);

    void remove(ManClother manClother);

    ManClother find(Object id);

    List<ManClother> findAll();

    List<ManClother> findRange(int[] range);

    int count();
    
}
