package org.example;

import static spark.Spark.port;
import static spark.Spark.*;

public class ClientHello {

    public static void main(String[] args) {
        port(5000);
        get("/hello", (req, res) -> "Hello Heroku");
    }

}
