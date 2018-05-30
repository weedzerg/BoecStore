/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.AdultClother;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface AdultClotherFacadeLocal {

    void create(AdultClother adultClother);

    void edit(AdultClother adultClother);

    void remove(AdultClother adultClother);

    AdultClother find(Object id);

    List<AdultClother> findAll();

    List<AdultClother> findRange(int[] range);

    int count();
    
}
