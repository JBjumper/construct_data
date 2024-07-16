package com.ljb.generator;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class SelectFromDatabaseGenerator extends SelectGenerator {

    String databaseUrl;

    String databaseUser;

    String databasePassword;

    String referenceTableName;

    String referenceColumnName;

    public SelectFromDatabaseGenerator(String databaseUrl, String databaseUser, String databasePassword,
                                       String referenceTableName, String referenceColumnName) {
        this.databaseUrl = databaseUrl;
        this.databaseUser = databaseUser;
        this.databasePassword = databasePassword;
        this.referenceTableName = referenceTableName;
        this.referenceColumnName = referenceColumnName;
    }

    abstract String constructQuerySqlStatement();

    Connection getConnection() {
        try {
            Class.forName("com.vertica.jdbc.Driver");
            return DriverManager.getConnection(this.databaseUrl, this.databaseUser, this.databasePassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
