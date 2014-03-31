/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;


/**
*
* @author marco
*/
public class AnnuncioCasaBean {
    String titolo ="";
    String indirizzo ="";
    String numeroPosti ="";
    String descrizione ="";
    String localita ="";
    String dataInizio ="";
    String dataFine ="";
    String lat ="";
    String lng ="";
    String[] opzioniStr = null;
    String pathFile ="";
    String pathDir = "";

    public String getPathDir() {
        return pathDir;
    }

    public void setPathDir(String pathDir) {
        this.pathDir = pathDir;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String[] getOpzioniStr() {
        return opzioniStr;
    }

    public void setOpzioniStr(String[] opzioniStr) {
        this.opzioniStr = opzioniStr;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNumeroPosti() {
        return numeroPosti;
    }

    public void setNumeroPosti(String numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getLocalita() {
        return localita;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }
}
