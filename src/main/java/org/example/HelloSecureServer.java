package org.example;
import static spark.Spark.*;

public class HelloSecureServer {

    public static void main(String[] args) {
        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath,truststorePassword);
        secure(getKeyStore(), getPwdKeyStore(), null, null);

        port(getPort());
        get("/server", (req, res) -> "Hello World, from SERVER 1 :D");
        get("/secondServer", (req, res) -> URLReader.Reader("target/certificados/ecikeystore2.p12", "https://ec2-184-73-127-250.compute-1.amazonaws.com:5001/server2"));

    }

    static String getPwdKeyStore() {
        if (System.getenv("KEYSTOREPWD") != null) {
            return System.getenv("KEYSTOREPWD");
        }
        return "123456";

    }

    static String getKeyStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "target/certificados/ecikeystore.p12";
    }


    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
