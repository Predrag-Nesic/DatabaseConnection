package databaseconnection;

import java.sql.*;

public class DatabaseConnection {

    public static void main(String[] args) {
        
        Connection con = null;
        int rows = 0;
        
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/testDatabase1", "username", "password");
           // A table can be created trough services tab, right click on the db address, and create table
           // using sql commands, or it can be created with Statement object and .execute() method
            Statement st = con.createStatement();
//            st.execute("create table friends2(\n" +
//                "id int primary key,\n" +
//                "name varchar (30),\n" +
//                "surname varchar (30)\n" +
//                ")");
            // ... when executed (created) this code needs to be commented because it cannot create any more tables
            // by the same name
            
            String s1 = "insert into friends1 values(1, 'Predrag', 'Nesic')";
            String s2 = "insert into friends1 values(2, 'Jelena', 'Nikolic')";
            String s3 = "insert into friends1 values(3, 'Predrag', 'Kovic')";
            String s4 = "insert into friends1 values(4, 'Bojana', 'Acimovac')";
            
//            st.execute(s1);
//            st.execute(s2);
//            st.execute(s3);
//            st.execute(s4);
            
            // ... the he same principle applies here, or it will print SQLIntegrityConstraintViolationException
            
            // The updates can be done trough Statement object or PreparedStatement object
            st.executeUpdate("update friends1 set surname='Mitrovic' where id=4");
            
            String s5 = "update friends1 set surname=? where id=?";
            PreparedStatement ps = con.prepareStatement(s5);
            ps.setString(1, "Acimovac");
            ps.setInt(2, 4);
            ps.executeUpdate();
            
            ResultSet rs = st.executeQuery("select * from friends1");
            while(rs.next()) {
                rows++;
                System.out.println(rs.getInt(1) + "\t" + rs.getString("name") + "\t" + rs.getString("surname"));
                //                 first column         second table column           third column
                // The columns can be accesed with the integer or the column name
            }
        } catch(SQLException e) {
            System.err.println("Error has occured: " + e);
        }
    }
}
