/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.GirlClother;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface GirlClotherFacadeLocal {

    void create(GirlClother girlClother);

    void edit(GirlClother girlClother);

    void remove(GirlClother girlClother);

    GirlClother find(Object id);

    List<GirlClother> findAll();

    List<GirlClother> findRange(int[] range);

    int count();
    
}
