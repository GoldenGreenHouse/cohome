/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

import java.util.Vector;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Andr3A
 */
@WebService(serviceName = "CoHomeWebService")
@Stateless()
public class CoHomeWebService {

    /**
     * This is a sample web service operation
     * @return le località maggionrmente cercate nella piattaforma CoHome.
     */
    @WebMethod(operationName = "getlocation")
    public Vector<String> getLocation() {
        Vector<String> v = new Vector();
        v.add("Torino");
        v.add("Roma");
        v.add("Parigi");
        return v;
    }
}
