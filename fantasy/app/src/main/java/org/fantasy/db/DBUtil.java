package org.fantasy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class DBUtil implements AutoCloseable{
    private Connection connection;

    public DBUtil() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:fantasy.db");
    }

    //I'm going to be calling this to transfer Dhoni from Chennai to Hyderabad
    // "UPDATE Players set TEAM = " + newTeam + "where name = " + playerName <---vulnerable to SQL Injection
    // "UPDATE Players set TEAM  = ? where name = ?", newTeam, playerName <----jdbc does things to prevent SQL Injection
    /**
     * 
     * @param sql the SQL to be run, with parameters annotated with ?s
     * @param params the list of parameters to insert within the SQL in a safe way
     * @return the list of ids that were affected by the query
     * @throws SQLException
     * 
     */
    public int runStatement(String sql, Object ...params) throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(sql)){
            addObjects(statement, params);
            return statement.executeUpdate();
        }
    }
    
    /**
     * 
     * @param query the SQL to be run, with parameters annotated with ?s
     * @param params the list of parameters to insert within the SQL in a safe way
     * @return either a single row ID assu
     * @throws SQLException when, among other things, there is no column called "id"
     * assumes that the table being queried has a column called "id"
     */
    public Optional<Integer> getSingleId(String query, Object ...params) throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            addObjects(statement, params);
            ResultSet queryResults = statement.executeQuery(); //instead of this, start a virtual thread that runsthe query in the bg and either return a CompletableFuture or call the callback provided by the user.
            return queryResults.next() ? Optional.of(queryResults.getInt("id")) : Optional.empty();
        }
    }

    private void addObjects(PreparedStatement preparedStatement, Object ...params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}
