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

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-11-13T13:43:40")
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