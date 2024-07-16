package com.ljb.generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SelectColumnRandomFromDatabaseGenerator extends SelectFromDatabaseGenerator{
    public SelectColumnRandomFromDatabaseGenerator(String databaseUrl, String databaseUser, String databasePassword,
                                                   String referenceTableName, String referenceColumnName) {
        super(databaseUrl, databaseUser, databasePassword, referenceTableName, referenceColumnName);
    }

    @Override
    public List<String> generate(Integer size) {
        this.resultSize = size;
        String sql = constructQuerySqlStatement();
        List<String> resultList = new ArrayList<>();
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

        for(int i = 1;i<=this.resultSize;i++){
            int randomIndex = ThreadLocalRandom.current().nextInt(this.selects.size());
            resultList.add(this.selects.get(randomIndex));
        }

        return resultList;
    }

    @Override
    public List<String> generate() {
        return generate(this.resultSize);
    }


    String constructQuerySqlStatement() {
        StringBuilder sqlst = new StringBuilder("SELECT ");
        sqlst.append(this.referenceColumnName).append(" FROM ")
                .append(this.referenceTableName).append(" LIMIT ")
                .append(this.resultSize);
        System.out.println(sqlst);
        return sqlst.toString();
    }


    public static void main(String[] args) {
        String url = "jdbc:vertica://1.181.141.98:5433/H3C_Database";
        String user = "dbadmin";
        String password = "passwd";
        String tableName = "ODS_HIS_GZSERVER_FIN_IPR_INMAININFO";
        String referenceColumnName = "INPATIENT_NO";
        SelectColumnRandomFromDatabaseGenerator generator =
                new SelectColumnRandomFromDatabaseGenerator(url, user, password, tableName,
                        referenceColumnName);

        List<String> generate = generator.generate();
        System.out.println(generate);
    }
}
