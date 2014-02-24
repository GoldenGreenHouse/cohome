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
public interface RichiestaVisitaFacadeLocal {

    void create(RichiestaVisita richiestaVisita);

    void edit(RichiestaVisita richiestaVisita);

    void remove(RichiestaVisita richiestaVisita);

    RichiestaVisita find(Object id);

    List<RichiestaVisita> findAll();

    List<RichiestaVisita> findRange(int[] range);

    int count();
    
}
