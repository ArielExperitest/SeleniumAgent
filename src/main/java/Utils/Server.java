package Utils;

import java.io.*;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {
    static HttpServer server;

    public Server() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/colorTest", new RootHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    public static class RootHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            String response = "<html>\n" +
                    "<head>\n" +
                    "<script>\n" +
                    "    var colors = [\"red\", \"blue\", \"green\"];\n" +
                    "    var colorIndex = 0;\n" +
                    "    function changeColor() {\n" +
                    "        var col = document.getElementById(\"body\");\n" +
                    "        if( colorIndex >= colors.length ) {\n" +
                    "            colorIndex = 0;\n" +
                    "        }\n" +
                    "        col.style.backgroundColor = colors[colorIndex];\n" +
                    "        colorIndex++;\n" +
                    "    }\n" +
                    "</script>\n" +
                    "    <body id='body'>\n" +
                    "    <button onclick=\"changeColor();\">Change color</button>\n" +
                    "    </body>\n" +
                    "</html";

            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
            server.stop(0);
//send respond to
        }
    }

    public static void main(String[] args) throws Exception {
        new Server();
    }
}
