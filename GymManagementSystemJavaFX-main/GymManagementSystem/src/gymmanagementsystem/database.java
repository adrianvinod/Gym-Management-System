/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author MarcoMan
 * Subscribe our YouTube Channel: https://www.youtube.com/@marcomanchannel
 */
public class database {

    public static Connection connectDb() {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            // SSL configuration:
            // - Secure (default): useSSL=true, requireSSL=true, verifyServerCertificate=true
            //   Provide trust store via environment variables:
            //     JAVA_TRUSTSTORE_PATH, JAVA_TRUSTSTORE_PASSWORD
            // - Temporary insecure option: set env DB_USE_SSL=false to force useSSL=false

            String host = System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : "localhost";
            String databaseName = System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : "gym";
            String username = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
            String password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "root";

            boolean disableSsl = "false".equalsIgnoreCase(System.getenv("DB_USE_SSL"))
                    || "0".equals(System.getenv("DB_USE_SSL"));

            String url;
            if (disableSsl) {
                // Less secure/temporary fix
                url = "jdbc:mysql://" + host + "/" + databaseName + "?useSSL=false";
            } else {
                // Secure with SSL and certificate verification
                String trustStorePath = System.getenv("JAVA_TRUSTSTORE_PATH");
                String trustStorePassword = System.getenv("JAVA_TRUSTSTORE_PASSWORD");
                if (trustStorePath != null && trustStorePassword != null) {
                    System.setProperty("javax.net.ssl.trustStore", trustStorePath);
                    System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
                }
                url = "jdbc:mysql://" + host + "/" + databaseName
                        + "?useSSL=true&requireSSL=true&verifyServerCertificate=true";
            }

            Connection connect = DriverManager.getConnection(url, username, password); // credentials can be overridden via env
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

        // NOW LETS OPEN OUR XAMPP : ) 
    }

}
