package entities.order;

import entities.items.Item;
import entities.order.Order1;
import entities.person.Employee;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-07T15:38:11")
@StaticMetamodel(Orderdetail.class)
public class Orderdetail_ { 

    public static volatile SingularAttribute<Orderdetail, Item> itemId;
    public static volatile SingularAttribute<Orderdetail, Integer> amount;
    public static volatile SingularAttribute<Orderdetail, Order1> orderId;
    public static volatile SingularAttribute<Orderdetail, Employee> employeeId;
    public static volatile SingularAttribute<Orderdetail, Integer> id;

}