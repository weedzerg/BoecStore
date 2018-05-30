/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.WomanClother;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface WomanClotherFacadeLocal {

    void create(WomanClother womanClother);

    void edit(WomanClother womanClother);

    void remove(WomanClother womanClother);

    WomanClother find(Object id);

    List<WomanClother> findAll();

    List<WomanClother> findRange(int[] range);

    int count();
    
}
