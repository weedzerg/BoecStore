/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionPerson;

import entitiesPerson.EmployeeOnline;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface EmployeeOnlineFacadeLocal {

    void create(EmployeeOnline employeeOnline);

    void edit(EmployeeOnline employeeOnline);

    void remove(EmployeeOnline employeeOnline);

    EmployeeOnline find(Object id);

    List<EmployeeOnline> findAll();

    List<EmployeeOnline> findRange(int[] range);

    int count();
    
}
