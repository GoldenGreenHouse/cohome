package ejb;

import ejb.Annuncio;
import ejb.Commento;
import ejb.Prenotazione;
import ejb.PropostaPrenotazione;
import ejb.Recensione;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-07-28T21:43:56")
@StaticMetamodel(UserComponent.class)
public class UserComponent_ { 

    public static volatile SingularAttribute<UserComponent, Long> id;
    public static volatile ListAttribute<UserComponent, Commento> commenti;
    public static volatile ListAttribute<UserComponent, Prenotazione> prenotazioni;
    public static volatile SingularAttribute<UserComponent, String> username;
    public static volatile ListAttribute<UserComponent, Recensione> recensioni;
    public static volatile SingularAttribute<UserComponent, String> ruolo;
    public static volatile SingularAttribute<UserComponent, String> email;
    public static volatile ListAttribute<UserComponent, Annuncio> annunci;
    public static volatile SingularAttribute<UserComponent, String> name;
    public static volatile SingularAttribute<UserComponent, String> password;
    public static volatile ListAttribute<UserComponent, PropostaPrenotazione> propostaPrenotazione;

}