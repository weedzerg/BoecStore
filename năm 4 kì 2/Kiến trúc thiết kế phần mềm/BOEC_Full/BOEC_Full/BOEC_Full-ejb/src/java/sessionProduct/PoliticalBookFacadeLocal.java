/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.PoliticalBook;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface PoliticalBookFacadeLocal {

    void create(PoliticalBook politicalBook);

    void edit(PoliticalBook politicalBook);

    void remove(PoliticalBook politicalBook);

    PoliticalBook find(Object id);

    List<PoliticalBook> findAll();

    List<PoliticalBook> findRange(int[] range);

    int count();
    
}
