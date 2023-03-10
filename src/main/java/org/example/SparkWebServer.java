package org.example;



import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static spark.Spark.*;

public class SparkWebServer {

    public static void main(String... args){
        staticFileLocation("/public");
        port(9000);

        before((request, response) -> {
            String path = request.pathInfo();
            if (path.endsWith(".html")) {
                try {
                    byte[] bytes = Files.readAllBytes(Paths.get("public/" + path));
                    String content = new String(bytes, StandardCharsets.UTF_8);
                    // Modificar el contenido del archivo
                    //content = content.replace("9001", "9002");
                    response.body(content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        get("hello", (req,res) -> "Hellooooooo!");
    }
}