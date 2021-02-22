package edu.escuelaing.arem.app.component;

import org.springframework.web.bind.annotation.RequestMapping;

public class helloController {

    @RequestMapping("/hello")
    public static String greetings(){
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/pagina")
    public static String pageWeb (){
        String outputLine =
                "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "<meta charset=\"UTF-8\">\n"
                        + "<title>Taller Arep</title>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<h1>Johan</h1> \n"
                        + "</body>\n"
                        + "</html>\n";
        return outputLine;
    }
}
