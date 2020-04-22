package com.redevio.database;

import java.sql.*;

/**
 * Created by Muhammed Topgul.
 * Date: 17.04.2020
 * Time: 00:23
 */
public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() {

        try {
            String connectionUrl = "jdbc:mysql://localhost:3306/unity_hair_3d?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            connection = DriverManager.getConnection(connectionUrl, "root", "root");

            if (!connection.isClosed()) {
                System.out.println("Connected");
            } else {
                System.out.println("Not Connected");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return connection;
    }
}
