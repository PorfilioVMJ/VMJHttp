import java.io.*;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Headers;

import javax.swing.text.html.HTMLDocument;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
        server.createContext("/", new IndexHandler());
        server.createContext("/home", new IndexHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("VMJHttp Server Has Started!");
    }

    static class IndexHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
           /*
           C:/VMJHttp/code/index.html
           */
            String html;
            StringBuilder contentBuilder = new StringBuilder();
            try {
                BufferedReader in = new BufferedReader(new FileReader("C:/VMJHttp/code/index.html"));
                while((html = in.readLine()) != null){
                    contentBuilder.append(html);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String content = contentBuilder.toString();
            t.sendResponseHeaders(200, content.length());
            OutputStream os = t.getResponseBody();
            os.write(content.getBytes());
            os.close();
        }
    }


}
