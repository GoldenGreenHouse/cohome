package ejb;

import ejb.UserComponent;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-10T16:23:47")
@StaticMetamodel(Commento.class)
public class Commento_ { 

    public static volatile SingularAttribute<Commento, String> commento;
    public static volatile SingularAttribute<Commento, Long> id;
    public static volatile SingularAttribute<Commento, UserComponent> autore;

}