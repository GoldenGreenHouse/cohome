/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;


import bean.AnnuncioCasaBean;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import javax.persistence.PersistenceContext;
import org.json.JSONArray;
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
    @EJB
    private UserComponentFacadeLocal userComponentFacade;
    
    @PersistenceContext(unitName = "CoHome-ejbPU")
    private EntityManager em;
    

   public Annuncio findAnnuncio(Long idAnnuncio){
       return annuncioFacade.find(idAnnuncio);
   }
   

   public void addAnnuncioCasa(AnnuncioCasa annuncioCasa){
        long j = 601;
        UserComponent userComponent = userComponentFacade.find(j);
        List<Annuncio> annunci1 = userComponent.getAnnunci();
        annunci1.add(annuncioCasa);
        userComponent.setAnnunci(annunci1);
        userComponentFacade.create(userComponent);
       
   }
    public void addAnnuncioCasa(AnnuncioCasaBean annuncioCasaBean){
        int numeroPosti = 0;
        String informazione = null;
        String[] data = null;
        Calendar dataGCI = new GregorianCalendar();
        Calendar dataGCF= new GregorianCalendar();
        AnnuncioCasa annuncioCasa = new AnnuncioCasa();
        
        annuncioCasa.setTitolo(annuncioCasaBean.getTitolo());
        annuncioCasa.setIndirizzo(annuncioCasaBean.getIndirizzo());
        
        numeroPosti = Integer.parseInt(annuncioCasaBean.getNumeroPosti());
        annuncioCasa.setNumeroPosti(numeroPosti);
        
        annuncioCasa.setDescrizione(annuncioCasaBean.getDescrizione());
        annuncioCasa.setLocalita(annuncioCasaBean.getLocalita());
        
        informazione = annuncioCasaBean.getDataInizio();
        data = informazione.split("/");
        dataGCI.set(Integer.parseInt(data[2]),(Integer.parseInt(data[0]) -1),Integer.parseInt(data[1])); 
        annuncioCasa.setDataInizio(dataGCI);
        
        informazione = annuncioCasaBean.getDataFine();
        data = informazione.split("/");
        dataGCF.set(Integer.parseInt(data[2]),(Integer.parseInt(data[0]) -1),Integer.parseInt(data[1]));
        annuncioCasa.setDataFine(dataGCF);
        
        String lat = annuncioCasaBean.getLat();
        String lng = annuncioCasaBean.getLng();
        float f_lat = 0;
        float f_lng = 0;
        try{
            f_lat = Float.parseFloat(lat);
            f_lng = Float.parseFloat(lng);
        }catch(NumberFormatException ex){};
        annuncioCasa.setLat(f_lat);
        annuncioCasa.setLng(f_lng);
        
        //gestione opzioni
        List<Opzione> opzioni = annuncioCasa.getOpzioni();
        String[] opzioniStr = annuncioCasaBean.getOpzioniStr();
        int len = opzioniStr.length;//rivedere
        for(int j=0; j<len;j++){
            data = opzioniStr[j].split("/");
            Opzione opzione = new Opzione();
            opzione.setNome(data[1]);
            opzione.setValore(data[0]);
            opzioni.add(opzione);
        }
        annuncioCasa.setOpzioni(opzioni);
        
        //salvataggio su db dell annuncio
        long id = annuncioCasaBean.getUserID();
        UserComponent userComponent = userComponentFacade.find(id);
        List<Annuncio> annunci = userComponent.getAnnunci();
        annunci.add(annuncioCasa);
        //userComponent.setAnnunci(annunci);
        userComponentFacade.create(userComponent);
        
        //decompressione file
        Long idAnnuncioCasa = annuncioCasa.getId();
        System.out.println("idAnnuncioCasa: " + idAnnuncioCasa);
        String zipName = annuncioCasaBean.getPathFile();
        if(!zipName.equals("")){
                try {
                    FileInputStream fis = new FileInputStream(zipName);
                    ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
                    ZipEntry entry;
                    String Dir = annuncioCasaBean.getPathDir() + "\\ann" + idAnnuncioCasa;
                    boolean success = (new File(Dir)).mkdirs();
                    if (success){
                        // Read each entry from the ZipInputStream until no
                        // more entry found indicated by a null return value
                        // of the getNextEntry() method.
                        while ((entry = zis.getNextEntry()) != null) {
                            System.out.println("Unzipping: " + entry.getName());
                            int size;
                            byte[] buffer = new byte[2048];
                            FileOutputStream fos = new FileOutputStream(Dir + "/" + entry.getName());
                            System.out.println("path dezippati: " + Dir + "\\" + entry.getName());
                            BufferedOutputStream bos =new BufferedOutputStream(fos, buffer.length);
                            while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
                                bos.write(buffer, 0, size);
                            }
                            bos.flush();
                            bos.close();
                        }
                    }
                    zis.close();
                    fis.close();
                    //File fileZip = new File("C:/immagini/" + nomeFile);
                    File fileZip = new File(zipName);
                    fileZip.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
   } 
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<AnnuncioCasa> trovaAnnunciCasa(double lat, double lng) {
        Query query;
        query = em.createNamedQuery("findAllAnnunciCasa")
               .setParameter("minLat", lat-0.2)
               .setParameter("minLng", lng-0.2)
               .setParameter("maxLat", lat+0.2)
               .setParameter("maxLng", lng+0.2);
               
        List<AnnuncioCasa> l = query.getResultList(); 
        return l;
    }
    
    public JSONObject getCoordinate(String location) throws JSONException{
        System.out.println(location.trim());
        String html="";
        try {
            String keyGoogle="AIzaSyBrpTzhnCt1GJVFXEfwSpj5_mV0iUsC51o";
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+location.replaceAll(" ", "")+"&sensor=true&key="+keyGoogle);                     
            HttpURLConnection connection = null;

            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(2000);
            connection.setRequestProperty("Content-Length", "0");
            int status=connection.getResponseCode();
            //System.out.println(status);
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
        JSONObject o =  new JSONObject(html);
        JSONObject a = new JSONObject(o.optJSONArray("results").optString(0));
 
        return(a.getJSONObject("geometry").getJSONObject("location"));
    }
   
    public void persist(Object object) {
        em.persist(object);
    }
}
