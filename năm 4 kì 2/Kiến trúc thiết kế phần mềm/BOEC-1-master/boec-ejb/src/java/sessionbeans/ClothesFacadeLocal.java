/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.items.Clothes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DoHue
 */
@Local
public interface ClothesFacadeLocal {

    void create(Clothes clothes);

    void edit(Clothes clothes);

    void remove(Clothes clothes);

    Clothes find(Object id);

    List<Clothes> findAll();

    List<Clothes> findRange(int[] range);

    int count();
    
}
