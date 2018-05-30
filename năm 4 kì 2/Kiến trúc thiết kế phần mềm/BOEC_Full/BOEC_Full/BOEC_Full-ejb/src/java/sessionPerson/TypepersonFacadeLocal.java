/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionPerson;

import entitiesPerson.Typeperson;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface TypepersonFacadeLocal {

    void create(Typeperson typeperson);

    void edit(Typeperson typeperson);

    void remove(Typeperson typeperson);

    Typeperson find(Object id);

    List<Typeperson> findAll();

    List<Typeperson> findRange(int[] range);

    int count();
    
}
