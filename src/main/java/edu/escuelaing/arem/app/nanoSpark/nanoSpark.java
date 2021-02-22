package edu.escuelaing.arem.app.nanoSpark;

import edu.escuelaing.arem.app.httpServer.HttpServer;


/**
 * Clase principal que inicia el servicio de mini Spark
 */
public class nanoSpark {
    /**
     * Metodo prinicipal que inicia el el servidor
     * @param args principal
     */
    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.startServer();
    }
}
