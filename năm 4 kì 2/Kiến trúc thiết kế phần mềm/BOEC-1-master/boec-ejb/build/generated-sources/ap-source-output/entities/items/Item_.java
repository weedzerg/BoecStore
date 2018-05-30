package entities.items;

import entities.items.Book;
import entities.items.Category;
import entities.items.Clothes;
import entities.items.Electronics;
import entities.order.Orderdetail;
import entities.person.Publisher;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-07T15:38:11")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, String> image;
    public static volatile SingularAttribute<Item, Integer> amount;
    public static volatile SingularAttribute<Item, Publisher> publisherId;
    public static volatile ListAttribute<Item, Electronics> electronicsList;
    public static volatile SingularAttribute<Item, Integer> price;
    public static volatile ListAttribute<Item, Orderdetail> orderdetailList;
    public static volatile SingularAttribute<Item, String> name;
    public static volatile SingularAttribute<Item, Integer> id;
    public static volatile ListAttribute<Item, Clothes> clothesList;
    public static volatile SingularAttribute<Item, Category> categoryId;
    public static volatile ListAttribute<Item, Book> bookList;

}