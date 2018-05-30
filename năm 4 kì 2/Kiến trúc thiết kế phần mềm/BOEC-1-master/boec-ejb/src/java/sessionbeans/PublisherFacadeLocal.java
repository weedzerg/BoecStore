/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.person.Publisher;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DoHue
 */
@Local
public interface PublisherFacadeLocal {

    void create(Publisher publisher);

    void edit(Publisher publisher);

    void remove(Publisher publisher);

    Publisher find(Object id);

    List<Publisher> findAll();

    List<Publisher> findRange(int[] range);

    int count();
    
}
