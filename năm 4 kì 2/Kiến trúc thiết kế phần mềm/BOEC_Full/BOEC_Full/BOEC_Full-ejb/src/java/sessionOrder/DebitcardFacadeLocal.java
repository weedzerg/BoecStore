/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionOrder;

import entitiesOrder.Debitcard;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface DebitcardFacadeLocal {

    void create(Debitcard debitcard);

    void edit(Debitcard debitcard);

    void remove(Debitcard debitcard);

    Debitcard find(Object id);

    List<Debitcard> findAll();

    List<Debitcard> findRange(int[] range);

    int count();
    
}
