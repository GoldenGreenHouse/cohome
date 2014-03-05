/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author marco
 */
@Entity 
public abstract class Annuncio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String titolo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataInizio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataFine;
    private String descrizione;
    private String localita;
    @OneToMany(cascade = ALL)
    private List<Commento> commenti = new ArrayList();
    @OneToMany(cascade = ALL)
    private List<PropostaPrenotazione> propostaPrenotazione = new ArrayList();
    private boolean attivo = false;
    @OneToMany(cascade = ALL)
    private List<Opzione> opzioni = new ArrayList();
    
    private String lat;
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
    
    public List<Opzione> getOpzioni() {
        return opzioni;
    }

    public void setOpzioni(List<Opzione> opzioni) {
        this.opzioni = opzioni;
    }

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

    public List<PropostaPrenotazione> getPropostaPrenotazione() {
        return propostaPrenotazione;
    }

    public void setPropostaPrenotazione(List<PropostaPrenotazione> propostaPrenotazione) {
        this.propostaPrenotazione = propostaPrenotazione;
    }
    
    public List<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getLocalita() {
        return localita;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }
    
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
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
        if (!(object instanceof Annuncio)) {
            return false;
        }
        Annuncio other = (Annuncio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Annuncio[ id=" + id + " ]";
    }
    
}
