package entities.person;

import entities.items.Item;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-07T15:38:11")
@StaticMetamodel(Publisher.class)
public class Publisher_ { 

    public static volatile SingularAttribute<Publisher, String> address;
    public static volatile SingularAttribute<Publisher, String> name;
    public static volatile ListAttribute<Publisher, Item> itemList;
    public static volatile SingularAttribute<Publisher, Integer> id;

}