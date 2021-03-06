/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.cms.core.entity.content;

import com.x.base.core.entity.SliceJpaObject_;
import com.x.base.core.entity.item.ItemPrimitiveType;
import com.x.base.core.entity.item.ItemStringValueType;
import com.x.base.core.entity.item.ItemType;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.cms.core.entity.content.DataItem.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Sat May 06 19:34:34 CST 2017")
public class DataItem_ extends SliceJpaObject_  {
    public static volatile SingularAttribute<DataItem,String> appId;
    public static volatile SingularAttribute<DataItem,String> appName;
    public static volatile SingularAttribute<DataItem,Boolean> booleanValue;
    public static volatile SingularAttribute<DataItem,String> categoryId;
    public static volatile SingularAttribute<DataItem,String> categoryName;
    public static volatile SingularAttribute<DataItem,Date> createTime;
    public static volatile SingularAttribute<DataItem,String> creatorCompany;
    public static volatile SingularAttribute<DataItem,String> creatorDepartment;
    public static volatile SingularAttribute<DataItem,String> creatorIdentity;
    public static volatile SingularAttribute<DataItem,String> creatorPerson;
    public static volatile SingularAttribute<DataItem,Date> dateTimeValue;
    public static volatile SingularAttribute<DataItem,Date> dateValue;
    public static volatile SingularAttribute<DataItem,String> docId;
    public static volatile SingularAttribute<DataItem,String> docStatus;
    public static volatile SingularAttribute<DataItem,String> id;
    public static volatile SingularAttribute<DataItem,ItemPrimitiveType> itemPrimitiveType;
    public static volatile SingularAttribute<DataItem,ItemStringValueType> itemStringValueType;
    public static volatile SingularAttribute<DataItem,ItemType> itemType;
    public static volatile SingularAttribute<DataItem,String> lobItem;
    public static volatile SingularAttribute<DataItem,Double> numberValue;
    public static volatile SingularAttribute<DataItem,String> path0;
    public static volatile SingularAttribute<DataItem,Integer> path0Location;
    public static volatile SingularAttribute<DataItem,String> path1;
    public static volatile SingularAttribute<DataItem,Integer> path1Location;
    public static volatile SingularAttribute<DataItem,String> path2;
    public static volatile SingularAttribute<DataItem,Integer> path2Location;
    public static volatile SingularAttribute<DataItem,String> path3;
    public static volatile SingularAttribute<DataItem,Integer> path3Location;
    public static volatile SingularAttribute<DataItem,String> path4;
    public static volatile SingularAttribute<DataItem,Integer> path4Location;
    public static volatile SingularAttribute<DataItem,String> path5;
    public static volatile SingularAttribute<DataItem,Integer> path5Location;
    public static volatile SingularAttribute<DataItem,String> path6;
    public static volatile SingularAttribute<DataItem,Integer> path6Location;
    public static volatile SingularAttribute<DataItem,String> path7;
    public static volatile SingularAttribute<DataItem,Integer> path7Location;
    public static volatile SingularAttribute<DataItem,Date> publishTime;
    public static volatile SingularAttribute<DataItem,String> sequence;
    public static volatile SingularAttribute<DataItem,String> stringLobValue;
    public static volatile SingularAttribute<DataItem,String> stringValue;
    public static volatile SingularAttribute<DataItem,Date> timeValue;
    public static volatile SingularAttribute<DataItem,String> title;
    public static volatile SingularAttribute<DataItem,Date> updateTime;
}
