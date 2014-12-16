package ejb;

import ejb.UserComponent;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-16T09:36:51")
@StaticMetamodel(Recensione.class)
public class Recensione_ { 

    public static volatile SingularAttribute<Recensione, String> descrizione;
    public static volatile SingularAttribute<Recensione, UserComponent> autoreID;
    public static volatile SingularAttribute<Recensione, Long> id;
    public static volatile SingularAttribute<Recensione, String> valutazione;

}