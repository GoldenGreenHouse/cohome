/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author marco
 */
@NamedNativeQuery(
        name="getProposteByAnnuncio",
        query= "select * "+
               "from cohome.PROPOSTAPRENOTAZIONE as p "+
               "where p.ANNUNCIO_ID = Annuncio_Id AND ATTIVO=1",
        resultClass=PropostaPrenotazione.class
        
)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PropostaPrenotazione implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int numeroPosti;
    private String descrizione;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataInizio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataFine;
    private boolean attivo = false;
    @OneToOne
    private UserComponent utente ;
    @OneToOne
    private Annuncio annuncio;

    public Calendar getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Calendar dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Calendar getDataFine() {
        return dataFine;
    }

    public void setDataFine(Calendar dataFine) {
        this.dataFine = dataFine;
    }

    
    public UserComponent getUtente() {
        return utente;
    }

    public void setUtente(UserComponent utente) {
        this.utente = utente;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public Annuncio getAnnuncio() {
        return annuncio;
    }

    public void setAnnuncio(Annuncio annuncio) {
        this.annuncio = annuncio;
    }
    

    public int getNumeroPosti() {
        return numeroPosti;
    }

    public void setNumeroPosti(int numeroPosti) {
        this.numeroPosti = numeroPosti;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropostaPrenotazione)) {
            return false;
        }
        PropostaPrenotazione other = (PropostaPrenotazione) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.PropostaPrenotazione[ id=" + id + " ]";
    }
    
}
