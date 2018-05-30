/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.HousewareElec;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface HousewareElecFacadeLocal {

    void create(HousewareElec housewareElec);

    void edit(HousewareElec housewareElec);

    void remove(HousewareElec housewareElec);

    HousewareElec find(Object id);

    List<HousewareElec> findAll();

    List<HousewareElec> findRange(int[] range);

    int count();
    
}
