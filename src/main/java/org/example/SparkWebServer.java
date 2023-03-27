package org.example;



import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static spark.Spark.*;

public class SparkWebServer {

    public static int count = 0;
    static String[] ports = {"http://100.24.240.33:9001/mensajitos", "http://100.26.175.18:9001/mensajitos", "http://3.90.62.177:9001/mensajitos"};

    public static void main(String... args) throws IOException {
        staticFileLocation("/public");
        port(9000);






        get("hello", (req,res) -> "Hellooooooo!");
        post("guenas", (req,res) -> {
            if (count> 2) {count = 0;}

            String url = ports[count];
            count += 1;
           return  HTTPConnection.sendInfo(url) + url;
        } );

    }
}