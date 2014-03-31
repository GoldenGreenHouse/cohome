/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.Serializable;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author marco
 */
 @NamedNativeQuery(
    name="findAllCommenti",
    query= "SELECT c.* "+
            "FROM Commento AS c, Annuncio_Commento AS ac "+
            "WHERE ac.Commenti_Id=c.id AND ac.Annuncio_Id = Annuncio_Id",
    resultClass=Commento.class
    )
@Entity
public class Commento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String commento;
    @OneToOne(cascade = ALL, orphanRemoval=true)
    private UserComponent autore;
    
    
    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }
    
    public UserComponent getAutore() {
        return autore;
    }

    public void setAutore(UserComponent autore) {
        this.autore = autore;
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
        if (!(object instanceof Commento)) {
            return false;
        }
        Commento other = (Commento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Commento[ id=" + id + " ]";
    }
    
}
