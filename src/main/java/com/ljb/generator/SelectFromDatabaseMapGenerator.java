package com.ljb.generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class SelectFromDatabaseMapGenerator extends SelectFromDatabaseGenerator{

    private final Map<String,String> mapping = new HashMap<>();

    private final List<String> keyList;

    private final String columnNameKey;

    private final String columnNameValue;

    public SelectFromDatabaseMapGenerator(String databaseUrl, String databaseUser, String databasePassword,
                                          String referenceTableName, String columnNameKey, String columnNameValue,
                                         List<String> keyList) {
        super(databaseUrl, databaseUser, databasePassword, referenceTableName, null);
        this.keyList = keyList;
        this.columnNameValue = columnNameValue;
        this.columnNameKey = columnNameKey;
    }

    public void getMap(){
        String sql = constructQuerySqlStatement();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    this.mapping.put(rs.getString(1),rs.getString(2));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String constructQuerySqlStatement() {
        StringBuilder sqlst = new StringBuilder("SELECT ");
        sqlst.append(this.columnNameKey).append(",")
                .append(this.columnNameValue).append(" FROM ")
                .append(this.referenceTableName).append(" LIMIT ")
                .append(this.resultSize);
        System.out.println(sqlst);
        return sqlst.toString();
    }

    @Override
    public List<String> generate(Integer size) {
        List<String> result = new ArrayList<>();
        for(String key : keyList){
            if(result.size() == size){
                break;
            }
            result.add(mapping.get(key));

        }
        return result;
    }

    @Override
    public List<String> generate() {
        return generate(this.resultSize);
    }
}
