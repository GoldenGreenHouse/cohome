/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import ejb.AnnuncioCasa;
import java.util.List;

/**
 *
 * @author Andrea
 */
public class RicercaAnnunciCasa {
    List<AnnuncioCasa> annunci;

    public List<AnnuncioCasa> getAnnunci() {
        return annunci;
    }

    public void setAnnunci(List<AnnuncioCasa> annunci) {
        this.annunci = annunci;
    }

    public AnnuncioCasa getSingleAnnuncio(int index) {
        return(annunci.get(index));
    }
    
}
