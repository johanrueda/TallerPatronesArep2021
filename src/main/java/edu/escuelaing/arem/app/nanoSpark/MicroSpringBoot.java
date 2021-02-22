package edu.escuelaing.arem.app.nanoSpark;

import edu.escuelaing.arem.app.httpServer.HttpServer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MicroSpringBoot {


    public static void main(String[] args) {

        args=new String[1];
        args[0] = "edu.escuelaing.arem.app.nanoSpark.HelloController";
        try {
            MicroSpring iocServer = new MicroSpring();
            iocServer.start(args);
            HttpServer server = new HttpServer(iocServer);
            server.start();
        } catch (Exception ex) {
            Logger.getLogger(MicroSpringBoot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}