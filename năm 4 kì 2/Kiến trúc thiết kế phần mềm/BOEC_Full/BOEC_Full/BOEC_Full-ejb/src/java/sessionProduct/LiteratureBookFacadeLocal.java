/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.LiteratureBook;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface LiteratureBookFacadeLocal {

    void create(LiteratureBook literatureBook);

    void edit(LiteratureBook literatureBook);

    void remove(LiteratureBook literatureBook);

    LiteratureBook find(Object id);

    List<LiteratureBook> findAll();

    List<LiteratureBook> findRange(int[] range);

    int count();
    
}
