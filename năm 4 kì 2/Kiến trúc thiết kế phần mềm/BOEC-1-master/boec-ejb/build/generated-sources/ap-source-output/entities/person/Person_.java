package entities.person;

import entities.person.Account;
import entities.person.Customer;
import entities.person.Employee;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-07T15:38:11")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, String> dateofbirth;
    public static volatile SingularAttribute<Person, String> address;
    public static volatile ListAttribute<Person, Employee> employeeList;
    public static volatile ListAttribute<Person, Account> accountList;
    public static volatile ListAttribute<Person, Customer> customerList;
    public static volatile SingularAttribute<Person, Integer> id;
    public static volatile SingularAttribute<Person, String> fullname;

}