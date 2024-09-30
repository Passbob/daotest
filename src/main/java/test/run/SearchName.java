package test.run;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static test.common.JDBCTemplate.getConnection;

public class SearchName {

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rset1 = null;
        ResultSet rset2 = null;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/test/mapper/test.xml"));
            String query1 = prop.getProperty("searchName");
            String query2 = prop.getProperty("searchCode");


            pstmt1 = con.prepareStatement(query1);
            Scanner sc = new Scanner(System.in);
            System.out.println("조회할 이름을 적어주세요 : ");
            String answer = sc.nextLine();

            pstmt1.setString(1, answer);

            rset1 = pstmt1.executeQuery();

            if(rset1.next()){
                System.out.println(rset1.getString("USER_NO")+" , " +rset1.getString("USER_NAME") + " , " +rset1.getString("HEIGHT"));
            }

            pstmt2 = con.prepareStatement(query2);
            System.out.println("조회할 이름을 적어주세요 : ");
            String answer2 = sc.nextLine();

            pstmt2.setString(1, answer2);

            rset2 = pstmt2.executeQuery();

            if(rset2.next()){
                System.out.println(rset2.getString("USER_NAME")+ " , " +rset2.getString("USER_CODE"));
            }




        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
