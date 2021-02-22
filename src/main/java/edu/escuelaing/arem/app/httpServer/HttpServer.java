package edu.escuelaing.arem.app.httpServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.*;
import edu.escuelaing.arem.app.httpServer.
/**
 * @author Johan
 * Clase HttpServer
 */
public class HttpServer {
    private static boolean running;
    private edu.escuelaing.arep.ASE.app.httpServer.dataBase connect= null;
    private int port=36000;
    private Map<String,String> request = new HashMap<>();
    URIProcessor uriProcessor;


    public HttpServer(URIProcessor up){
        this.up=up;
    }

    public HttpServer(int port){
        this.port=port;
    }
    /**
     * Metodo que inicia el HttpServer
     */
    public  void startServer() {
            try {
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(getPort());
                } catch (IOException e) {
                    System.err.println("Could not listen on port: " + getPort());
                    System.exit(1);
                }

                running = true;
                while (running) {
                    try {
                        Socket clientSocket = null;
                        try {
                            System.out.println("Listo para recibir en puerto " + getPort() + "...");
                            clientSocket = serverSocket.accept();
                        } catch (IOException e) {
                            System.err.println("Accept failed.");
                            System.exit(1);
                        }
                        processRequest(clientSocket);
                        clientSocket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                serverSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    /**
     * Hace el proceso de las diferentes peticiones que hace el servidor
     * @param clientSocket socket
     * @throws IOException error
     */
    private void processRequest(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        Map<String, String> request = new HashMap<>();
        boolean requestLineReady = false;
        while ((inputLine = in.readLine()) != null) {
            if (!requestLineReady) {
                request.put("requestLine", inputLine);
                requestLineReady = true;
            } else {
                String[] entry = createEntry (inputLine);
                if (entry.length > 1) {
                    request.put(entry[0], entry[1]);
                }
            }
            if (!in.ready()) {
                break;
            }
        }
        Request req = new Request(request.get("requestLine"));

        System.out.println("RequestLine: " + req);

        createResponse(req, new PrintWriter (clientSocket.getOutputStream(), true));
        in.close();
    }

    private String[] createEntry(String rawEntry) {
        return rawEntry.split(":");
    }

    private void createResponse(Request req, PrintWriter out) {
        String outputLine = testResponse();
        URI theuri = req.getTheuri();
        if (theuri.getPath().startsWith("/Apps")) {
            getAppResponse (theuri.getPath().substring(5), out);
        } else {
            getStaticResource(theuri.getPath(), out);
        }
        out.close();
    }




    private String testResponse() {
        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Title of the document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Mi propio mensaje</h1>\n"
                + "</body>\n"
                + "</html>\n";
        return outputLine;
    }

    /**
     * Obtiene los recuersos dependiendo de la peticion
     * @param out URI
     * @param path socket
     * @throws IOException error
     */
    private void getStaticResource(String path, PrintWriter out) {
        Path file = Paths.get("target/classes/public_html" + path);
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String header = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n";
            out.println(header);
            String line = null;
            while ((line = reader.readLine()) != null) {
                out.println(line);
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene el recurso de las imagenes dependiendo de su extension
     * @param requestURI URI
     * @param outputStream OUT
     */
    private void getImage(String requestURI, OutputStream outputStream) {
        File file = new File("src/main/resources/imagenes/" + requestURI);

        try {
            BufferedImage pic = ImageIO.read(file);
            ByteArrayOutputStream picShow = new ByteArrayOutputStream();
            DataOutputStream picDraw = new DataOutputStream(outputStream);
            ImageIO.write (pic, "JPG", picShow);
            picDraw.writeBytes("HTTP/1.1 200 OK\r\n" + "Content-Type: image/jpg \r\n\r\n");
            picDraw.write(picShow.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Obtiene los datos de la base de datos
     * @return int
     */
    private String getDataBase() {
        edu.escuelaing.arep.ASE.app.httpServer.dataBase db = new dataBase();
        ArrayList<String []> data = db.getTable();
        String list = "";
        for (String [] datos : data) {
            list += datos [0] + ". Nombre : " + datos [1] + "\n";
        }
        return list;
    }

    /**
     * Retorna el puerto
     * @return int
     */
    private int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000;
    }
    }