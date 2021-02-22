package edu.escuelaing.arem.app.httpServer;

public interface URIProcessor {

    public abstract void mapService(String command) throws Exception;
    public String executeService (String uri);
}