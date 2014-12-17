/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author marco
 */
@Entity

@NamedNativeQuery(
    name="getUtenteByIdAnnuncio",
    query="SELECT ua.usercomponent_id FROM usercomponent_annuncio AS ua "
       + "WHERE ua.annunci_id = ?id"
    )

public class UserComponent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String name;
    private String password;
    private String email;
    private String ruolo;
    
    @OneToMany(cascade = ALL)
    private List<Commento> commenti = new ArrayList();
    
    @OneToMany(cascade = ALL)
    private List<PropostaPrenotazione> propostaPrenotazione = new ArrayList();
    
    @OneToMany(cascade = ALL)
    private List<Annuncio> annunci = new ArrayList();
    
    @OneToMany(cascade = ALL)
    private List<Recensione> recensioni = new ArrayList();
    
    @OneToMany(cascade = ALL)
    private List<Prenotazione> prenotazioni = new ArrayList();
    
    private String avatar = new String();

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    public List<Annuncio> getAnnunci() {
        return annunci;
    }

    public void setAnnunci(List<Annuncio> annunci) {
        this.annunci = annunci;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
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
        if (!(object instanceof UserComponent)) {
            return false;
        }
        UserComponent other = (UserComponent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Utente[ id=" + id + " ]";
    }
    
}
