package JDAbot.Logic;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreDB {

    public static void main(String[] args) throws URISyntaxException, SQLException {
        createTable();
        System.out.println("Vse norm");
    }

    private static void createTable() throws URISyntaxException, SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        String sql = "CREATE TABLE IF NOT EXIST QUIZZ " +
                "(QUESTION     TEXT     NOT NULL," +
                 "ANSWER       TEXT     NOT NULL)";

        statement.executeUpdate(sql);
        statement.close();
        connection.close();
        System.out.println("Vse norm sozdal");
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
            URI dbUri = new URI(
                    "postgres://jtgevdutqqgotz:9854e64f061bb111b1d921bdbe1c7be697044aec80f4ce601686cbdc22a1f98d@ec2-54-246-117-62.eu-west-1.compute.amazonaws.com:5432/dc3osvnaf382hf");

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

            System.out.println("Vse norm");
            return DriverManager.getConnection(dbUrl, username, password);
    }
}
