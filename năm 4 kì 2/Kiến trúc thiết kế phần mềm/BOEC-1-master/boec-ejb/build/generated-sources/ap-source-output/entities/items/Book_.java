package entities.items;

import entities.items.Item;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-07T15:38:11")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, Item> itemId;
    public static volatile SingularAttribute<Book, String> year;
    public static volatile SingularAttribute<Book, String> author;
    public static volatile SingularAttribute<Book, Integer> id;

}