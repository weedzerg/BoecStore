/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.Author;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface AuthorFacadeLocal {

    void create(Author author);

    void edit(Author author);

    void remove(Author author);

    Author find(Object id);

    List<Author> findAll();

    List<Author> findRange(int[] range);

    int count();
    
}
