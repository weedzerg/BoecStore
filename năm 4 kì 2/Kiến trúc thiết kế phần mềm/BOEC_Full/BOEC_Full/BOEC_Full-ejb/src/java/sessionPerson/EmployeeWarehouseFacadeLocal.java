/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionPerson;

import entitiesPerson.EmployeeWarehouse;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface EmployeeWarehouseFacadeLocal {

    void create(EmployeeWarehouse employeeWarehouse);

    void edit(EmployeeWarehouse employeeWarehouse);

    void remove(EmployeeWarehouse employeeWarehouse);

    EmployeeWarehouse find(Object id);

    List<EmployeeWarehouse> findAll();

    List<EmployeeWarehouse> findRange(int[] range);

    int count();
    
}
