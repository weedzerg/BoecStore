/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.items.Book;
import entities.items.Item;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DoHue
 */
@Local
public interface BookFacadeLocal {

    void create(Book book);

    void edit(Book book);

    void remove(Book book);

    Book find(Object id);

    List<Book> findAll();

    List<Book> findByName(String name);

    List<Book> findRange(int[] range);

    int count();
}
