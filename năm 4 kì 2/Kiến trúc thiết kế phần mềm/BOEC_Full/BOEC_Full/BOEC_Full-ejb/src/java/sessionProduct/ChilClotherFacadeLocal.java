/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.ChilClother;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface ChilClotherFacadeLocal {

    void create(ChilClother chilClother);

    void edit(ChilClother chilClother);

    void remove(ChilClother chilClother);

    ChilClother find(Object id);

    List<ChilClother> findAll();

    List<ChilClother> findRange(int[] range);

    int count();
    
}
