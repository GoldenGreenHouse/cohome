package web;

import bean.AnnuncioCasaBean;
import ejb.GestoreAnnunci;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utility.PostToGoogle;

/**
 *
 * @author Andrea
 */
public class CreaAnnuncioCasaServlet extends HttpServlet {
    @EJB
    private GestoreAnnunci gestoreAnnunci;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletConfig conf = getServletConfig();
        ServletContext ctx = conf.getServletContext();
        int numOp = 0;
        String[] data = null;
        String path =ctx.getInitParameter("pathImage");
        //String informazione;
        HttpSession session = request.getSession();
        long userID = (long)session.getAttribute("userID");
        
        AnnuncioCasaBean annuncioCasaBean = new AnnuncioCasaBean();
        annuncioCasaBean.setUserID(userID);
        annuncioCasaBean.setTitolo(request.getParameter("titolo"));
        annuncioCasaBean.setIndirizzo(request.getParameter("indirizzo"));
        annuncioCasaBean.setNumeroPosti(request.getParameter("numeroPosti"));
        annuncioCasaBean.setDescrizione(request.getParameter("descrizione"));
        annuncioCasaBean.setLocalita(request.getParameter("localita"));
        annuncioCasaBean.setDataInizio(request.getParameter("dataInizio"));
        annuncioCasaBean.setDataFine(request.getParameter("dataFine"));
        annuncioCasaBean.setLat(request.getParameter("lat"));
        annuncioCasaBean.setLng(request.getParameter("lng"));
        String numerOpzioni = request.getParameter("numerOpzioni");
        if(!numerOpzioni.equals("")) numOp = Integer.parseInt(numerOpzioni) ;
        String[] opzioniStr = new String[numOp];
        for(int j=0; j<numOp;j++){
            opzioniStr[j] = request.getParameter("opzione"+(j + 1));
        }
        annuncioCasaBean.setOpzioniStr(opzioniStr);
        //gestoreAnnunci.addAnnuncioCasa(annuncioCasaBean);

        //post su facebook
        String msg = "Titolo: " + request.getParameter("titolo");
        msg =  msg + " \n Luogo: " + request.getParameter("localita");
        msg =  msg + " \n Descrizione: " + request.getParameter("descrizione");
        msg = msg + " \n Numero posti: " + request.getParameter("numeroPosti");
        msg = msg + " \n Dal: " + request.getParameter("dataInizio");
        msg = msg + " al: " + request.getParameter("dataFine");
        PostToGoogle postToGoogle = new PostToGoogle();
        postToGoogle.postGoogle(msg);
        
        //memorizzazione file foto
        String nomeFile ="";
        String zipName = "";
        String visualizzaPath ="";
        Part filePart = request.getPart("nomeFile");
        if(filePart != null){
            nomeFile = getFilename(filePart);
            data = nomeFile.split("\\.");
            if((!nomeFile.equals(""))&&(data[1].equals("zip"))){
                InputStream inputStream = null;
                FileOutputStream outputStream = null;
                try{
                    inputStream = filePart.getInputStream();          
                    ServletContext context = request.getServletContext();
                    annuncioCasaBean.setPathFile(path);
                    outputStream = new FileOutputStream(path +"\\"+ nomeFile);       
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
                zipName = path +"\\"+ nomeFile;
            }
        }
        annuncioCasaBean.setPathFile(zipName);
        
        annuncioCasaBean.setPathDir(ctx.getInitParameter("pathImage"));
        gestoreAnnunci.addAnnuncioCasa(annuncioCasaBean);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }
    
    private static String getFilename(Part part) {
      for (String cd : part.getHeader("content-disposition").split(";")) {
         if (cd.trim().startsWith("filename")) {
            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
         }
      }
      return null;
   }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
