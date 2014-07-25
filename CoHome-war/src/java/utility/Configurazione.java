package utility;

/**
 *
 * @author marco
 */
public class Configurazione {
    
    private static Configurazione configurazione = null;

    private String accessToken = "";
    private String urlGoogleApp = "";
    
    private Configurazione(){}
    
    public static synchronized Configurazione getConfigurazione() {
        if (configurazione == null) 
            configurazione = new Configurazione();
        return configurazione;
    }

    public void setAccessToken(String access) { 
        accessToken = access;
    }
        
    public String getAccessToken(){
        return accessToken;
    }

    public String getUrlGoogleApp() {
        return urlGoogleApp;
    }

    public void setUrlGoogleApp(String urlGoogleApp) {
        this.urlGoogleApp = urlGoogleApp;
    }
 
}
