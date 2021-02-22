
package edu.escuelaing.arem.app.httpServer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Johan
 * Clase principal de respuesta de las peticiones tipo URI
 */

public class Request {
    private String metodo;
    private String requestURI;
    private String HTTPVersion;
    private URI theuri;
    private Map<String, String> query;

    /**
     * clase hace realiza solicitudes de tipo URI
     *
     * @param inputLine input
     */
    public Request(String inputLine) {
        parseQuery(inputLine);
    }

    /**
     * Clase que mapea la peticion URI
     *
     * @param requestLine query
     * @return Mapeo
     */
    public void parseRequestLine(String requestLine) {
        try {
            String[] components = requestLine.split("\\s");
            metodo = components[0];
            requestURI = components[1];
            HTTPVersion = components[2];
            setTheuri(new URI(requestURI));
            query = parseQuery(theuri.getQuery());
        } catch (URISyntaxException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getMethod() {
        return metodo;
    }

    /**
     * Retorna URI
     *
     * @return URI
     */
    public String getRequestURI() {
        return requestURI;
    }

    /**
     * metodo que convierte a string
     *
     * @return URI
     */
    public String toString() {
        return metodo + " " + requestURI + " " + HTTPVersion + "\n\r" + getTheuri() + "\n\r" + "Query: " + query;
    }

    /**
     * obtiene el uri
     *
     * @return URI
     */
    private URI getTheuri() {
        return theuri;
    }

    /**
     * Cambia el uri
     *
     * @param theuri cambio  URI
     */
    public void setTheuri(URI theuri) {
        this.theuri = theuri;
    }

    public String getHTTPVersion() {
        return HTTPVersion;
    }

    private Map<String, String> parseQuery(String query) {
        if (query == null) return null;
        Map<String, String> theQuery = new HashMap();
        String[] nameValuePairs = query.split("&");
        for (String nameValuePair : nameValuePairs) {
            int index = nameValuePair.indexOf("=");
            if (index != -1) {
                theQuery.put(nameValuePair.substring(0, index), nameValuePair.substring(index + 1));
            }
        }
        return theQuery;
    }

    public String getValFromQuery(String varname) {
        return query.get(varname);
    }



    
}
