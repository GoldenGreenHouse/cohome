/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import javax.persistence.PersistenceContext;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author marco
 */
@Stateless
@LocalBean


public class GestoreAnnunci {
    @EJB
    private RichiestaVisitaFacadeLocal richiestaVisitaFacade;
    @EJB
    private RichiestaCasaFacadeLocal richiestaCasaFacade;
    @EJB
    private AnnuncioVisitaFacadeLocal annuncioVisitaFacade;
    @EJB
    private PropostaPrenotazioneFacadeLocal propostaPrenotazioneFacade;
    @EJB
    private AnnuncioFacadeLocal annuncioFacade1;
    @EJB
    private AnnuncioFacadeLocal annuncioFacade;
    @EJB
    private AnnuncioCasaFacadeLocal annuncioCasaFacade;
    
    @PersistenceContext(unitName = "CoHome-ejbPU")
    private EntityManager em;
   

   public void addAnnuncioCasa(AnnuncioCasa annuncioCasa){
        annuncioCasaFacade.create(annuncioCasa);
        //Controlla mapping con tabella UserComponent_Annuncio
   }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<AnnuncioCasa> trovaAnnunciCasa(double lat, double lng) {
        Query query;
        query = em.createNamedQuery("findAllAnnunciCasa");
               /* .setParameter("minLat", lat-0.2)
                .setParameter("minLng", lng-0.2)
                .setParameter("maxLat", lat+0.2)
                .setParameter("maxLng", lng+0.2)*/
               
        List<AnnuncioCasa> l = query.getResultList(); 
        return l;
    }
    
    public void getCoordinate(String location) throws JSONException{
        String html="";
        try {
            String keyGoogle="AIzaSyBrpTzhnCt1GJVFXEfwSpj5_mV0iUsC51o";
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+location+"&sensor=true&key="+keyGoogle);              
            HttpURLConnection connection = null;

            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(2000);
            int status=connection.getResponseCode();

            BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = read.readLine();
            while(line!=null) {
                 html += line;
                 line = read.readLine();
            }
        } catch(MalformedURLException ex) {
                        ex.printStackTrace();
                        
        } catch(IOException ioex) {
                        ioex.printStackTrace();
                       
        }
       JSONObject o =  new JSONObject(html).getJSONObject("feed");     
    }
   
    public void persist(Object object) {
        em.persist(object);
    }
}
