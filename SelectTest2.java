package com.wishneed.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*JDBC App to get Emp db table record based on the given three designation*/

public class SelectTest2 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try
		{
			//read input
			sc=new Scanner(System.in);
			String desg1=null,desg2=null,desg3=null;
			if(sc!=null)
			{
				System.out.println("Enter designation of 1st person:- ");
				desg1=sc.next().toUpperCase();
				System.out.println("Enter designation of 2nd person:- ");
				desg2=sc.next().toUpperCase();
				System.out.println("Enter designation of 3rd person:- ");
				desg3=sc.next().toUpperCase();
			}//if
			
			//convert the input values as required for the SQL Query.
			String cond="('"+desg1+"','"+desg2+"','"+desg3+"')";
			System.out.println(cond);
			
			//Load JDBC driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection with db s/w
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","sys");
			
			//Create statement obj
			if(con!=null)
				st=con.createStatement();
			
			//prepare SQL query
			//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN('CLERK','MANNAGER','SALESMAN')ORDER BY JOB;
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN"+cond+"ORDER BY JOB";
			System.out.println(query);
			
			//send and execute query in db s/w
			if(st!=null)
				rs=st.executeQuery(query);
			
			//process the ResultSet obj
			if(rs!=null) {
				boolean rsEmpty=true;
				while(rs.next()) {
					rsEmpty=false;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					}//while
				if(rsEmpty=true)
				{
					System.out.println("Opp'sss...! No Record Found For The Given Input");
				}
				else
				{
					System.out.println("Displaying Record According To The Input Provided");
				}
				}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			//close JDBC obj
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main

}//class
