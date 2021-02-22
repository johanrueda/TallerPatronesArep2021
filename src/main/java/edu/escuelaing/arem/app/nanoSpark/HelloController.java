package edu.escuelaing.arem.app.nanoSpark;

/**
 * Clase controladora de peticiones
 */
public class HelloController {
    @RequestMapping("/hello")
    static public String hola() {
        return "Greetings from Micro Spring Boot!";
    }

}
