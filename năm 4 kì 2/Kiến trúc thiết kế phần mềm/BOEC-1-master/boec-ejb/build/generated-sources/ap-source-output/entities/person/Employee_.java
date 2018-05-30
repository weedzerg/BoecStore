package entities.person;

import entities.order.Orderdetail;
import entities.person.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-07T15:38:11")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile ListAttribute<Employee, Orderdetail> orderdetailList;
    public static volatile SingularAttribute<Employee, Person> personId;
    public static volatile SingularAttribute<Employee, Integer> id;
    public static volatile SingularAttribute<Employee, String> position;
    public static volatile SingularAttribute<Employee, Integer> salary;

}