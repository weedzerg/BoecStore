package entities.order;

import entities.order.Orderdetail;
import entities.person.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-07T15:38:11")
@StaticMetamodel(Order1.class)
public class Order1_ { 

    public static volatile ListAttribute<Order1, Orderdetail> orderdetailList;
    public static volatile SingularAttribute<Order1, Customer> customerId;
    public static volatile SingularAttribute<Order1, Integer> payment;
    public static volatile SingularAttribute<Order1, Integer> id;
    public static volatile SingularAttribute<Order1, String> timeOrder;

}