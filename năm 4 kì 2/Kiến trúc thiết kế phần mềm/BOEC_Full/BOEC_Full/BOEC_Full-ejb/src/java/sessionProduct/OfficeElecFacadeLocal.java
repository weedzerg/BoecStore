/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionProduct;

import entitiesProduct.OfficeElec;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface OfficeElecFacadeLocal {

    void create(OfficeElec officeElec);

    void edit(OfficeElec officeElec);

    void remove(OfficeElec officeElec);

    OfficeElec find(Object id);

    List<OfficeElec> findAll();

    List<OfficeElec> findRange(int[] range);

    int count();
    
}
