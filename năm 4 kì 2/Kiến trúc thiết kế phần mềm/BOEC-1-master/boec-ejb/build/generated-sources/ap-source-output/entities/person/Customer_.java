package entities.person;

import entities.order.Order1;
import entities.person.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-07T15:38:11")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> phone;
    public static volatile SingularAttribute<Customer, Person> personId;
    public static volatile ListAttribute<Customer, Order1> order1List;
    public static volatile SingularAttribute<Customer, Integer> id;

}