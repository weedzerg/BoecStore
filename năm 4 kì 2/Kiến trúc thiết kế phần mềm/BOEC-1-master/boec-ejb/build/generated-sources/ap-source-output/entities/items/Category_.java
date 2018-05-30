package entities.items;

import entities.items.Item;
import entities.items.Typecategory;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-07T15:38:11")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile SingularAttribute<Category, String> name;
    public static volatile ListAttribute<Category, Item> itemList;
    public static volatile SingularAttribute<Category, Typecategory> typeCategoryId;
    public static volatile SingularAttribute<Category, Integer> id;

}