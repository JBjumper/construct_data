package com.ljb.implemet;

import com.ljb.generator.RandomStringGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdsHisGzServerMetOpsApply {
    public static void main(String[] args) throws Exception{
        //ZY010000887885,ZY020000657401 ODS_HIS_GZSERVER_MET_OPS_APPLY
        String url = "jdbc:vertica://1.181.141.98:5433/H3C_Database";
        String user = "dbadmin";
        String password = "passwd";

        // 加载Vertica JDBC驱动（可选，DriverManager会自动加载）
        Class.forName("com.vertica.jdbc.Driver");

        String sql = "SELECT CLINIC_CODE FROM public.ODS_HIS_GZSERVER_MET_OPS_APPLY WHERE CLINIC_CODE NOT IN ('ZY010000887885','ZY020000657401')";

        String updateSql = "UPDATE public.ODS_HIS_GZSERVER_MET_OPS_APPLY SET OPERATIONNO = ? WHERE CLINIC_CODE=?";

        List<String> inPatientNos = new ArrayList<>();

        try(// 获取数据库连接
            Connection connection = DriverManager.getConnection(url, user, password);
            ) {
            try(PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    String inPatientNo = rs.getString(1);
                    inPatientNos.add(inPatientNo);
                }
                ps.clearParameters();
            }
            System.out.println(inPatientNos);

            try(PreparedStatement ps = connection.prepareStatement(updateSql);){
                for(String inPatientNo:inPatientNos){
                    RandomStringGenerator generator = new RandomStringGenerator(RandomStringGenerator.ContentTypeEnum.NUMBER, "125", 7);
                    generator.setResultSize(1);
                    ps.setString(1,generator.generate().get(0));
                    ps.setString(2,inPatientNo);
                    ps.addBatch();
                }

                ps.executeBatch();

            }


        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
