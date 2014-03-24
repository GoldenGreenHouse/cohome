/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

import ejb.AnnuncioCasa;
import ejb.GestoreAnnunci;
import ejb.Opzione;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
/**
*
* @author marco
*/

//annotation per fare upload file
@MultipartConfig

public class CreaAnnuncioCasa extends HttpServlet {
    @EJB
    private GestoreAnnunci gestoreAnnunci;


    
    private static String getFilename(Part part) {
       for (String cd : part.getHeader("content-disposition").split(";")) {
          if (cd.trim().startsWith("filename")) {
             String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
             return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
          }
       }
       return null;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Calendar dataGCI = new GregorianCalendar();
        Calendar dataGCF= new GregorianCalendar();
        String[] data = null;
        AnnuncioCasa annuncioCasa = new AnnuncioCasa();
        
        String informazione = request.getParameter("titolo");
        annuncioCasa.setTitolo(informazione);
        informazione = request.getParameter("indirizzo");
        annuncioCasa.setIndirizzo(informazione);
        informazione = request.getParameter("numeroPosti");
        annuncioCasa.setNumeroPosti(Integer.parseInt(informazione));
        informazione = request.getParameter("dataInizio");
        data = informazione.split("/");
        dataGCI.set(Integer.parseInt(data[2]),(Integer.parseInt(data[1]) -1),Integer.parseInt(data[0]));
        annuncioCasa.setDataInizio(dataGCI);
        informazione = request.getParameter("dataFine");
        data = informazione.split("/");
        dataGCF.set(Integer.parseInt(data[2]),(Integer.parseInt(data[1]) - 1),Integer.parseInt(data[0]));
        annuncioCasa.setDataFine(dataGCF);
        informazione = request.getParameter("descrizione");
        annuncioCasa.setDescrizione(informazione);
        informazione = request.getParameter("localita");
        annuncioCasa.setLocalita(informazione);
        annuncioCasa.setAttivo(true);
        
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
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
        //recupera il numero di opzioni
        String numerOpzioni = request.getParameter("numerOpzioni");
        int numOp = 0;
        if(!numerOpzioni.equals("")) numOp = Integer.parseInt(numerOpzioni) ;
        String risultato="";
        for(int j=1; j<=numOp;j++){
            informazione = request.getParameter("opzione"+j);
            data = informazione.split("/");
            Opzione opzione = new Opzione();
            opzione.setNome(data[1]);
            opzione.setValore(data[0]);
            opzioni.add(opzione);
            risultato = risultato + informazione;
        }
        annuncioCasa.setOpzioni(opzioni);
        gestoreAnnunci.addAnnuncioCasa(annuncioCasa);
        
        //salvataggio foto
        Long id = annuncioCasa.getId();
        System.out.println("Id dell'annnuncio: " + id);
        String nomeFile ="";
        Part filePart = request.getPart("nomeFile");
        if(filePart != null){
            nomeFile = getFilename(filePart);
            data = nomeFile.split("\\.");
            if((!nomeFile.equals(""))&&(data[1].equals("zip"))){
                InputStream inputStream = null;
                FileOutputStream outputStream = null;
                try{
                    inputStream = filePart.getInputStream();
                    outputStream = new FileOutputStream("C:/immagini/" + nomeFile);
                    int c;
                    while ((c = inputStream.read()) != -1) {
                        outputStream.write(c);
                    }
                }finally{
                    if(inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
                String zipName = "C:/immagini/"+ nomeFile;
                try {
                    FileInputStream fis = new FileInputStream(zipName);
                    ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
                    ZipEntry entry;
                    String Dir = "C:/immagini/" + id;
                    boolean success = (new File(Dir)).mkdirs();
                    if (success){
                        //
                        // Read each entry from the ZipInputStream until no
                        // more entry found indicated by a null return value
                        // of the getNextEntry() method.
                        //
                        while ((entry = zis.getNextEntry()) != null) {
                            System.out.println("Unzipping: " + entry.getName());
                            int size;
                            byte[] buffer = new byte[2048];
                            FileOutputStream fos = new FileOutputStream("C:/immagini/" + id + "/" + entry.getName());
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
                    File fileZip = new File("C:/immagini/" + nomeFile);
                    fileZip.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}