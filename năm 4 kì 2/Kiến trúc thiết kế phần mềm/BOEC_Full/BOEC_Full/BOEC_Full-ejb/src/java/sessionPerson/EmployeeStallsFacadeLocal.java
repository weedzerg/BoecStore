/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionPerson;

import entitiesPerson.EmployeeStalls;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface EmployeeStallsFacadeLocal {

    void create(EmployeeStalls employeeStalls);

    void edit(EmployeeStalls employeeStalls);

    void remove(EmployeeStalls employeeStalls);

    EmployeeStalls find(Object id);

    List<EmployeeStalls> findAll();

    List<EmployeeStalls> findRange(int[] range);

    int count();
    
}
