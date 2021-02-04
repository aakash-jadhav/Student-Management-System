package com.company;

import java.sql.*;

import static java.sql.DriverManager.*;

public class StudentDao {

    Connection con = null;
    public void connect() throws Exception{
        String url = "jdbc:mysql://localhost:3306/student";
        String user = "root";
        String password = "123456";
        con = getConnection(url, user, password);
    }

    public void addStudent(Student student) throws SQLException {

        String query = "   INSERT INTO students (name,age,department,year)\n" +
                "VALUES (?, ?, ?, ?);";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,student.getName());
        pst.setInt(2,student.getAge());
        pst.setString(3,student.getDepartment());
        pst.setInt(4,student.getYear());
        int count = pst.executeUpdate();
        System.out.println(count + " number of rows affected");
        pst.close();
        con.close();
    }

    public ResultSet getStudents() throws SQLException {

        String query = "SELECT * FROM students";
        Statement statement = con.createStatement();

        return statement.executeQuery(query);


    }

    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE students\n SET\n name= ?,\n age = ?,\n department = ?,\n year = ?\n WHERE id = ?;";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,student.getName());
        pst.setInt(2,student.getAge());
        pst.setString(3,student.getDepartment());
        pst.setInt(4,student.getYear());
        pst.setInt(5,student.getId());
        int count = pst.executeUpdate();
        System.out.println(count + " number of rows affected");
        pst.close();
        con.close();
    }

    public void deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM students WHERE id = ?;";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1,id);
        int count = statement.executeUpdate();
        System.out.println(count + " number of rows affected");
        statement.close();
        con.close();

    }
}


