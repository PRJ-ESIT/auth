package com.esit;

import java.sql.ResultSet;

import javax.ws.rs.core.MultivaluedMap;

public class AuthManager {
    ConnectionManager conn;
    private String email;
    private String password;

    // constructor
    public AuthManager(MultivaluedMap<String, String> formParams) {
        this.setEmail(formParams.get("email").get(0));
        this.setPassword(formParams.get("password").get(0));
    }

    public String validate() {
        //token is empty by default
        String token = "";
        try {
            // create a query string
            String _query = "SELECT Employee.employeeId, "
                    + "Employee.firstName, "
                    + "Employee.lastName, "
                    + "Employee.role "
                    + "FROM Employee "
                    + "WHERE password = '" + this.getPassword() + "' "
                    + "AND email = '" + this.getEmail() + "'";

            // create a new Query object
            conn = new ConnectionManager();
            //execute the query statement and get the ResultSet
            ResultSet resultSet = conn.executeQuery(_query);

            // Iterating through the Results and filling the jsonObject
            while (resultSet.next()) {
                token += resultSet.getString("employeeId");
                token += "_" + resultSet.getString("firstName");
                token += "_" + resultSet.getString("lastName");
                token += "_" + resultSet.getString("role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close the connection to the database
            conn.closeConnection();
        }
        return token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
