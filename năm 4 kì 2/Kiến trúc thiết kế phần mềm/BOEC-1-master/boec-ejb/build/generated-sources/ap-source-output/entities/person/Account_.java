package entities.person;

import entities.person.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-07T15:38:11")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, Integer> password;
    public static volatile SingularAttribute<Account, Person> personId;
    public static volatile SingularAttribute<Account, Integer> id;
    public static volatile SingularAttribute<Account, String> username;

}