/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionOrder;

import entitiesOrder.SavingsTrans;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface SavingsTransFacadeLocal {

    void create(SavingsTrans savingsTrans);

    void edit(SavingsTrans savingsTrans);

    void remove(SavingsTrans savingsTrans);

    SavingsTrans find(Object id);

    List<SavingsTrans> findAll();

    List<SavingsTrans> findRange(int[] range);

    int count();
    
}
