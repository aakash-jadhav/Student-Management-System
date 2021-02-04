package com.company;

//Student Management System

import java.sql.ResultSet;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        int choice;
        Scanner sc = new Scanner(System.in);
        StudentDao dao = new StudentDao();
        do{
            System.out.print(new String(new char[50]).replace("\0", "-"));
            System.out.println("\nStudent Management System");
            System.out.println("1.Add 2.Read 3.Update 4.Delete 5.Exit");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.println("Add new Student: ");
                    Student student = new Student();
                    System.out.println("Enter Student name: ");
                    String name = sc.nextLine();
                    student.setName(name);

                    System.out.println("Enter student age: ");
                    int age = sc.nextInt();
                    student.setAge(age);
                    sc.nextLine();
                    System.out.print("Enter student department: ");
                    student.setDepartment(sc.nextLine());

                    System.out.print("Enter student year: ");
                    student.setYear(sc.nextInt());

                    dao.connect();
                    dao.addStudent(student);
                }
                case 2 -> {
                    System.out.println("Fetching Data...");
                    dao.connect();
                    ResultSet rs = dao.getStudents();
                    System.out.format("%3s%10s%10s%15s%10s\n","ID", "NAME", "AGE", "DEPARTMENT", "YEAR");

                    while(rs.next()) {
                        //Retrieve by column name
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        String department = rs.getString("department");
                        int year = rs.getInt("year");

                        //Display values
                        System.out.println(new String(new char[50]).replace("\0", "-"));
                        System.out.format("%3d%10s%10d%15s%10d\n",id, name, age, department, year);
                        System.out.println();
                    }
                    System.out.println(new String(new char[50]).replace("\0", "_"));

                }
                case 3 -> {
                    System.out.println("Update");
                    Student student = new Student();

                    System.out.println("Enter Id of the student to update:");
                    int id = sc.nextInt();
                    student.setId(id);
                    sc.nextLine();

                    System.out.println("Enter Student name: ");
                    String name = sc.nextLine();
                    student.setName(name);

                    System.out.println("Enter student age: ");
                    int age = sc.nextInt();
                    student.setAge(age);
                    sc.nextLine();

                    System.out.print("Enter student department: ");
                    student.setDepartment(sc.nextLine());

                    System.out.print("Enter student year: ");
                    student.setYear(sc.nextInt());

                    dao.connect();
                    dao.updateStudent(student);

                }
                case 4 -> {
                    System.out.println("Delete");
                    System.out.println("Enter Id of the student to delete:");
                    int id = sc.nextInt();
                    dao.connect();
                    dao.deleteStudent(id);
                }
                case 5 ->System.exit(0);
                default -> System.out.println("Enter valid choice");
            }
        }while(true);
    }
}
