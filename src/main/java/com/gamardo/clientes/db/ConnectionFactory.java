package com.gamardo.clientes.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String PROPS_FILE = "/application.properties";
    private static String url;
    private static String user;
    private static String pass;

    static {
        try (InputStream in = ConnectionFactory.class.getResourceAsStream(PROPS_FILE)) {
            if (in == null) {
                throw new RuntimeException("Arquivo application.properties não encontrado no classpath");
            }
            Properties props = new Properties();
            props.load(in);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            pass = props.getProperty("db.password");
        } catch (IOException e) {
            throw new RuntimeException("Erro carregando configuração do banco: " + e.getMessage(), e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
