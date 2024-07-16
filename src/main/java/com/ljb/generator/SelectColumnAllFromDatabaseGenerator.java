package com.ljb.generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class SelectColumnAllFromDatabaseGenerator extends SelectFromDatabaseGenerator {

    private String sortColumnName;

    public SelectColumnAllFromDatabaseGenerator(String databaseUrl, String databaseUser, String databasePassword,
                                                String referenceTableName, String referenceColumnName, String sortColumnName) {
        super(databaseUrl, databaseUser, databasePassword, referenceTableName, referenceColumnName);
        this.sortColumnName = sortColumnName;
    }

    @Override
    public List<String> generate(Integer size) {
        this.resultSize = size;
        String sql = constructQuerySqlStatement();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    this.selects.add(rs.getString(1));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return this.selects;
    }

    @Override
    public List<String> generate() {
        return generate(this.resultSize);
    }

    String constructQuerySqlStatement() {
        StringBuilder sqlst = new StringBuilder("SELECT ");
        sqlst.append(this.referenceColumnName).append(" FROM ")
                .append(this.referenceTableName).append(" ORDER BY ")
                .append(sortColumnName).append(" ASC ").append(" LIMIT ").append(this.resultSize);
        //System.out.println(sqlst);
        return sqlst.toString();
    }

    public static void main(String[] args) {
        String url = "jdbc:vertica://1.181.141.98:5433/H3C_Database";
        String user = "dbadmin";
        String password = "passwd";
        String tableName = "ODS_HIS_GZSERVER_FIN_IPR_INMAININFO";
        String referenceColumnName = "INPATIENT_NO";
        String sortColumnName = "INPATIENT_NO";
        SelectColumnAllFromDatabaseGenerator generator =
                new SelectColumnAllFromDatabaseGenerator(url, user, password, tableName,
                        referenceColumnName, sortColumnName);

        List<String> generate = generator.generate();
        System.out.println(generate);
    }
}