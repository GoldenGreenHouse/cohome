/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marco
 */
@Local
public interface AnnuncioFacadeLocal {

    void create(Annuncio annuncio);

    void edit(Annuncio annuncio);

    void remove(Annuncio annuncio);

    Annuncio find(Object id);

    List<Annuncio> findAll();

    List<Annuncio> findRange(int[] range);
    
    int count();
    
}
