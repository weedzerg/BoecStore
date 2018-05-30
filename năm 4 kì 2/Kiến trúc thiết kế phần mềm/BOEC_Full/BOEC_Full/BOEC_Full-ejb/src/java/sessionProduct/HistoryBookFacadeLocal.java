/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.HistoryBook;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface HistoryBookFacadeLocal {

    void create(HistoryBook historyBook);

    void edit(HistoryBook historyBook);

    void remove(HistoryBook historyBook);

    HistoryBook find(Object id);

    List<HistoryBook> findAll();

    List<HistoryBook> findRange(int[] range);

    int count();
    
}
