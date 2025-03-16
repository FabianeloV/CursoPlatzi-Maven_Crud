package org.example.fabianelo.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnection {
    public static final String databaseURL = "jdbc:mysql://localhost:3306/project";
    public static final String user = "fabianelo";
    public static final String password = "123456";

    private static BasicDataSource pool;


    public static BasicDataSource getPool() {
        if (pool == null) {
            // Configurar propiedades de conexión
            pool = new BasicDataSource();
            pool.setUrl(databaseURL);
            pool.setUsername(user);
            pool.setPassword(password);


            // Configurar parámetros del pool
            pool.setInitialSize(3); // tamaño inicial del pool
            pool.setMaxTotal(10);   // máximo de conexiones simultáneas
            pool.setMaxIdle(3);     // máximo de conexiones inactivas
            pool.setMinIdle(2);     // mínimo de conexiones inactivas

        }
        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getPool().getConnection();
    }
}
