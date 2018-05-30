/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionPerson;

import entitiesPerson.Account;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nguye
 */
@Local
public interface AccountFacadeLocal {

    void create(Account account);

    void edit(Account account);

    void remove(Account account);

    Account find(Object id);
    Account login(String u, String p);

    List<Account> findAll();

    List<Account> findRange(int[] range);

    int count();
    
    
}
