/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionOrder;

import entitiesOrder.Tranfer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface TranferFacadeLocal {

    void create(Tranfer tranfer);

    void edit(Tranfer tranfer);

    void remove(Tranfer tranfer);

    Tranfer find(Object id);

    List<Tranfer> findAll();

    List<Tranfer> findRange(int[] range);

    int count();
    
}
