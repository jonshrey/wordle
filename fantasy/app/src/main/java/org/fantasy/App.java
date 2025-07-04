/*
 * This source file was generated by the Gradle 'init' task
 */
package org.fantasy;

import java.sql.SQLException;

import org.fantasy.db.DBUtil;
import org.fantasy.db.HibernateUtil;
import org.fantasy.model.Player;
import org.fantasy.model.Team;

public class App {
    private static final String FIND_TEAM = "SELECT * FROM Teams WHERE name = ?";
    private static final String TRANSFER_PLAYER = "UPDATE Players SET team_id = ? WHERE last_name = ?";
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        // try(DBUtil db = new DBUtil()) {
        //     int hyderabadTeamId = db.getSingleId(FIND_TEAM, "Sunrisers Hyderabad").orElse(0);
        //     int numRowsAffected = db.runStatement(TRANSFER_PLAYER, hyderabadTeamId, "Dhoni");
        //     System.out.println(numRowsAffected);
        // } catch(SQLException e) {
        //     e.printStackTrace();
        // } //the close() method of db will be called right now

        var sessionFactory = HibernateUtil.getSessionFactory();
        // sessionFactory.inSession(session -> {
        //     Team foundTeam = session.find(Team.class, 1);
        //     System.out.println(foundTeam.getName());
        // });

        // var session = sessionFactory.openSession();
        // Team foundTeam = session.find(Team.class, 2);
        // System.out.println(foundTeam.getName());
        // session.close();
        // sessionFactory.close();

        sessionFactory.inSession(session -> {
            var player = session.find(Player.class, 1);
            System.out.println(player.getLastName() + " plays for " + player.getTeam().getName());
        });
    }
}
