/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.GeographyBook;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface GeographyBookFacadeLocal {

    void create(GeographyBook geographyBook);

    void edit(GeographyBook geographyBook);

    void remove(GeographyBook geographyBook);

    GeographyBook find(Object id);

    List<GeographyBook> findAll();

    List<GeographyBook> findRange(int[] range);

    int count();
    
}
