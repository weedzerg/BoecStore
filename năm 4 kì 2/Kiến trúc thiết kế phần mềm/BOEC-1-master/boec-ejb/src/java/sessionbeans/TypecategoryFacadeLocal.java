/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.items.Typecategory;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DoHue
 */
@Local
public interface TypecategoryFacadeLocal {

    void create(Typecategory typecategory);

    void edit(Typecategory typecategory);

    void remove(Typecategory typecategory);

    Typecategory find(Object id);

    List<Typecategory> findAll();

    List<Typecategory> findRange(int[] range);

    int count();
    
}
