package ejb;

import ejb.Annuncio;
import ejb.UserComponent;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-07-23T12:11:41")
@StaticMetamodel(PropostaPrenotazione.class)
public class PropostaPrenotazione_ { 

    public static volatile SingularAttribute<PropostaPrenotazione, Long> id;
    public static volatile SingularAttribute<PropostaPrenotazione, String> descrizione;
    public static volatile SingularAttribute<PropostaPrenotazione, Boolean> attivo;
    public static volatile SingularAttribute<PropostaPrenotazione, Calendar> dataFine;
    public static volatile SingularAttribute<PropostaPrenotazione, Annuncio> annuncio;
    public static volatile SingularAttribute<PropostaPrenotazione, Calendar> dataInizio;
    public static volatile SingularAttribute<PropostaPrenotazione, UserComponent> utente;
    public static volatile SingularAttribute<PropostaPrenotazione, Integer> numeroPosti;

}