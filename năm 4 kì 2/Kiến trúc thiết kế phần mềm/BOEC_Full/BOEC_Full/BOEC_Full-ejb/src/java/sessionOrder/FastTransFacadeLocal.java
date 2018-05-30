/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionOrder;

import entitiesOrder.FastTrans;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface FastTransFacadeLocal {

    void create(FastTrans fastTrans);

    void edit(FastTrans fastTrans);

    void remove(FastTrans fastTrans);

    FastTrans find(Object id);

    List<FastTrans> findAll();

    List<FastTrans> findRange(int[] range);

    int count();
    
}
