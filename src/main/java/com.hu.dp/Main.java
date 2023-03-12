package com.hu.dp;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String jdbcurl = /* LET OP! */ "jdbc:postgresql://<naam van de host>:<naam van de port>/<naam van de DB>";
        String username = /* LET OP! */ "<Voer de naam van de username in om in de PostgreSQL database in te loggen>";
        String password = /* LET OP! */ "<Voer de wachtwoord in om in de PostgreSQL Ddatebase in te loggen>";

        try {
            Connection connection = DriverManager.getConnection(jdbcurl, username, password);

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Reiziger");

            System.out.println("Connected to the database");
            while(rs.next()) {
                String t1 = rs.getString("reiziger_id");
                String t2 = rs.getString("voorletters");
                String t3 = rs.getString("tussenvoegsel");
                String t4 = rs.getString("achternaam");
                String t5 = rs.getString("geboortedatum");

                System.out.printf("#%s: %s.%s %s (%s)%n", t1, t2, t3 == null ? "" : " " + t3, t4, t5);
            }

            rs.close();
            st.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Failed to connect with database");
            e.printStackTrace();
        }
    }
}
