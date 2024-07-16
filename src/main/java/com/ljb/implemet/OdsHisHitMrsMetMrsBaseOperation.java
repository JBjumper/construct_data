package com.ljb.implemet;

import com.ljb.constructor.DataConstructor;
import com.ljb.formatter.SQLFormatter;
import com.ljb.generator.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class OdsHisHitMrsMetMrsBaseOperation {
    private static String URL = "jdbc:vertica://1.181.141.98:5433/H3C_Database";
    private static String USER = "dbadmin";
    private static String PASSWORD = "passwd";
    String TABLENAME = "";


    //ZY010000887859,ZY010000888014
    public static void main(String[] args) {
        modifyOperationCode();
    }

    public static void modifyOperationCode(){
        String querySQL1 = "SELECT INPATIENT_NO FROM public.ODS_HIS_HIT_MRS_MET_MRS_BASE_OPERATION";
        String updateSQL = "UPDATE public.ODS_HIS_HIT_MRS_MET_MRS_BASE_OPERATION SET OPERATION_CODE=? ,RJSS='1' WHERE INPATIENT_NO=?";
        String querySQL2 = "select ICD9_CM_3_CODE  from public.ODS_HIS_GZSERVER_DAY_SURGERY_INFO";
        List<String> inpatientNoList = query(querySQL1);
        List<String> daySurgeryCodeList = query(querySQL2);
        Random random = new Random();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(updateSQL)){
            for(int i = 0;i<50;i++){
                int randomInpatientNoIndex = random.nextInt(inpatientNoList.size());
                String randomInpatientNo = inpatientNoList.get(randomInpatientNoIndex);
                int randomSurgeryCodeIndex = random.nextInt(daySurgeryCodeList.size());
                System.out.println(randomInpatientNo);
                String randomDaySurgeryCode = daySurgeryCodeList.get(randomSurgeryCodeIndex);
                System.out.println(randomDaySurgeryCode);
                ps.setString(1,randomDaySurgeryCode);
                ps.setString(2,randomInpatientNo);
                ps.execute();
                ps.clearParameters();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public static List<String> query(String sql){
        List<String> result = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){
            while (rs.next()){
                result.add(rs.getString(1));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }
    public static void update(String sql){

    }

    public static Connection getConnection() {
        try {
            Class.forName("com.vertica.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void constructSql(){
        LinkedHashMap<String, String> columnInfos = new LinkedHashMap<>();
        columnInfos.put("INPATIENT_NO","varchar(42)");
        columnInfos.put("SEQUENCE_NO","int");
        columnInfos.put("OPERATION_CODE","varchar(36)");
        columnInfos.put("OPERATION_LEVEL","varchar(3)");
        columnInfos.put("OPERATION_DOC_CODE","varchar(24)");
        columnInfos.put("FIRST_AID_CODE","varchar(24)");
        columnInfos.put("SECOND_AID_CODE","varchar(24)");
        columnInfos.put("INCISION_LEVEL","varchar(3)");
        columnInfos.put("HEAL_KIND","varchar(12)");
        columnInfos.put("NARCOSIS_KIND_CODE","varchar(30)");
        columnInfos.put("NARCOSIS_DOC_CODE","varchar(24)");
        columnInfos.put("SELECT_OPS_FLAG","varchar(3)");
        columnInfos.put("EMERGENCY_OPS_FLAG","varchar(3)");
        columnInfos.put("STATISTICS_OPS_FLAG","varchar(3)");
        columnInfos.put("ASA","varchar(60)");
        columnInfos.put("OPSCONTINUEDTIME","varchar(60)");

        SQLFormatter sqlFormatter = new SQLFormatter("public.ODS_HIS_HIT_MRS_MET_MRS_BASE_OPERATION", columnInfos);


        String url = "jdbc:vertica://1.181.141.98:5433/H3C_Database";
        String user = "dbadmin";
        String password = "passwd";
        String tableName = "ODS_HIS_GZSERVER_FIN_IPR_INMAININFO";
        String referenceColumnName = "INPATIENT_NO";
        String sortColumnName = "INPATIENT_NO";
        String tableName2 = "ODS_HIS_GZSERVER_MET_COM_ICDOPERATION";
        String referenceColumnName2 = "ICD_CODE";

        DataConstructor dataConstructor = new DataConstructor();
        List<String> resultSqls = dataConstructor.setResultSize(200)
                .build(new SelectColumnAllFromDatabaseGenerator(url, user, password, tableName,
                        referenceColumnName, sortColumnName))
                .build(new RandomNumberGenerator(1L,10L, RandomNumberGenerator.RandomNumberTypeEnum.LONG))
                .build(new SelectColumnRandomFromDatabaseGenerator(url, user, password, tableName2,
                        referenceColumnName2))
                .build(new RandomNumberGenerator(1L,4L, RandomNumberGenerator.RandomNumberTypeEnum.LONG))//OPERATION_LEVEL
                .build(new RandomStringGenerator(RandomStringGenerator.ContentTypeEnum.NUMBER,"00",6))
                .build(new RandomStringGenerator(RandomStringGenerator.ContentTypeEnum.NUMBER,"00",6))
                .build(new RandomStringGenerator(RandomStringGenerator.ContentTypeEnum.NUMBER,"00",6))
                .build(new RandomNumberGenerator(0L,3L, RandomNumberGenerator.RandomNumberTypeEnum.LONG))
                .build(new SelectFromListGenerator("1","2","3","4","")) //HEAL_KIND
                .build(new ConstantGenerator("1"))
                .build(new RandomStringGenerator(RandomStringGenerator.ContentTypeEnum.NUMBER,"00",6))
                .build(new RandomNumberGenerator(1L,2L, RandomNumberGenerator.RandomNumberTypeEnum.LONG))
                .build(new RandomNumberGenerator(1L,2L, RandomNumberGenerator.RandomNumberTypeEnum.LONG))
                .build(new RandomNumberGenerator(1L,2L, RandomNumberGenerator.RandomNumberTypeEnum.LONG))
                .build(new ConstantGenerator("1"))
                .build(new RandomNumberGenerator(2L,8L, RandomNumberGenerator.RandomNumberTypeEnum.LONG))
                .make(sqlFormatter);

        for(String sql:resultSqls){
            System.out.println(sql);
        }

    }
}
