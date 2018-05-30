/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.items.Item;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DoHue
 */
@Local
public interface ItemFacadeLocal {

    void create(Item item);

    void edit(Item item);

    void remove(Item item);

    Item find(Object id);

    List<Item> findByName(String id);

    List<Item> findAll();

    List<Item> findRange(int[] range);

    int count();

}
