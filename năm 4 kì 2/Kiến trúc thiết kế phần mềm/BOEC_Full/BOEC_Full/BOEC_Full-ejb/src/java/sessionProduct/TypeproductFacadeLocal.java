/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.Typeproduct;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface TypeproductFacadeLocal {

    void create(Typeproduct typeproduct);

    void edit(Typeproduct typeproduct);

    void remove(Typeproduct typeproduct);

    Typeproduct find(Object id);

    List<Typeproduct> findAll();
    List<Typeproduct> getAllTypes();
    List<Typeproduct> findRange(int[] range);

    int count();
    
}
