/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author marco
 */
@Stateless
@LocalBean
public class GestoreUtenti {
    @EJB
    private GuestFacadeLocal guestFacade;
    @EJB
    private RegisteredFacadeLocal registeredFacade;
    @EJB
    private ModeratoreFacadeLocal moderatoreFacade;
    @EJB
    private UserComponentFacadeLocal userComponentFacade;
    
    @PersistenceContext(unitName = "CoHome-ejbPU")
    private EntityManager em;
    
    public void addUtente(UserComponent c) {
        userComponentFacade.create(c);
    }
    public UserComponent findUtente(Long id){
        //registered? guest?
        return userComponentFacade.find(id);
    }
     public List<UserComponent> findAllUtenti(){
        //registered? guest?
        return userComponentFacade.findAll();
    }

    public UserComponent findUtenteFromName(String name) {
        Query query;
        query = em.createQuery("select u from UserComponent u where u.username='"+name+"'");
        UserComponent l = (UserComponent) query.getSingleResult(); 
        return l;
    }
}
