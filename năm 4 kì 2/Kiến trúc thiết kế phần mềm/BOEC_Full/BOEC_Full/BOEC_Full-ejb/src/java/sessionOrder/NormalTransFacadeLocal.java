/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionOrder;

import entitiesOrder.NormalTrans;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface NormalTransFacadeLocal {

    void create(NormalTrans normalTrans);

    void edit(NormalTrans normalTrans);

    void remove(NormalTrans normalTrans);

    NormalTrans find(Object id);

    List<NormalTrans> findAll();

    List<NormalTrans> findRange(int[] range);

    int count();
    
}
