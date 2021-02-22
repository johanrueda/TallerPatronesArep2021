package edu.escuelaing.arem.app.component;

import edu.escuelaing.arem.app.httpServer.HttpServer;

/**
 * Clase que inicia el servicio de HttpServer
 */
public class SparkDServer {

    /**
     * Clase principal que inicializa el servicio
     * @param args
     */
    public static void main(String[] args){
        HttpServer server = new HttpServer();
        server.start();
    }

}