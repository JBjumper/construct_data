package com.ljb.generator;

import java.sql.Connection;

public interface IDataFromDatabaseGenerator extends IDataGenerator{
    String constructQuerySqlStatement();

    Connection getConnection();
}
