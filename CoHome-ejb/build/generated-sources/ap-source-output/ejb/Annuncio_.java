package ejb;

import ejb.Commento;
import ejb.Opzione;
import ejb.PropostaPrenotazione;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-16T09:59:58")
@StaticMetamodel(Annuncio.class)
public abstract class Annuncio_ { 

    public static volatile SingularAttribute<Annuncio, String> localita;
    public static volatile ListAttribute<Annuncio, Opzione> opzioni;
    public static volatile SingularAttribute<Annuncio, Calendar> dataInizio;
    public static volatile SingularAttribute<Annuncio, String> descrizione;
    public static volatile SingularAttribute<Annuncio, String> titolo;
    public static volatile SingularAttribute<Annuncio, Calendar> dataFine;
    public static volatile SingularAttribute<Annuncio, Long> id;
    public static volatile ListAttribute<Annuncio, Commento> commenti;
    public static volatile ListAttribute<Annuncio, PropostaPrenotazione> propostaPrenotazione;
    public static volatile SingularAttribute<Annuncio, Boolean> attivo;

}