/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.MathBook;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface MathBookFacadeLocal {

    void create(MathBook mathBook);

    void edit(MathBook mathBook);

    void remove(MathBook mathBook);

    MathBook find(Object id);

    List<MathBook> findAll();

    List<MathBook> findRange(int[] range);

    int count();
    
}
