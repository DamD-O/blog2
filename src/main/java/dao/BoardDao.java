package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import vo.Category;

public class BoardDao {
    //1.카테고리 목록 출력
    public ArrayList<HashMap<String, Object>> categoryList() {
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>(); //카테고리 담을 리스트 객체 생성
        
        Connection conn = null; //디비 연결
        PreparedStatement stmt = null; //쿼리 실행
        ResultSet rs = null; //실행한 쿼리의 결과물을 저장 -> select
        //DB 연동 
        try {
            Class.forName("org.mariadb.jdbc.Driver"); //1. 드라이버 로딩

            //2. DB 접속 - 마리아디비
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/blog2", "root", "java1234");
            //디버깅
            System.out.println("conn => " + conn);
            
            //3.SQL 쿼리 작성 - 카테고리 목록 출력
            String sql ="SELECT category_name categoryName, create_date createDate, update_date updateDate FROM category";
            stmt = conn.prepareStatement(sql); //쿼리 실행
            System.out.println("stmt => " + stmt);
            rs = stmt.executeQuery(); //실행할 결과를 저장
            System.out.println("rs => " + rs);
            
            while(rs.next()) { //true이면 한행씩 실행(저장)
                HashMap<String, Object> map = new HashMap<>();
                map.put("categoryName", rs.getString("categoryName"));
                System.out.println("map => " + map);
                list.add(map);
            }
            System.out.println("list => " + list);

        } catch (Exception e) {
            e.printStackTrace();
                
        }finally {
             try {
                 //DB자원 종료(반환)
                rs.close();
                stmt.close();
                conn.close();
             } catch (SQLException e) {
                e.printStackTrace();
             }
        }
        return list; //list 값 반환
    }
}
