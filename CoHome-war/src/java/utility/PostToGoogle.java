package utility;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author marco
 */
public class PostToGoogle {
    
    public void postGoogle(String msg){
        try{
            Configurazione conf = Configurazione.getConfigurazione();
            URL url = new URL(conf.getUrlGoogleApp());
            String access_token = conf.getAccessToken();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String data = access_token + "&chiave1=" + msg + "&chiave2= \n Per ulteriori informazioni visita il sito\r\n";//corpo della richiesta
            connection.setDoOutput(true);//abilita la scrittura
            connection.setRequestMethod("POST");//settaggio del metodo
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(data);//scrittura del content
            wr.flush();
            wr.close();   
            int responseCode = connection.getResponseCode(); 
        }catch (MalformedURLException e) {}   
        catch (IOException e) {} 
    }
    
}
