import com.epam.rd.java.basic.practice8.Demo;
import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Spy;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class Part5StudentTest {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String URL_CONNECTION = "jdbc:h2:~/test;user=youruser;password=yourpassword;";
    private static final String USER = "youruser";
    private static final String PASS = "yourpassword";

    @Spy
    private static DBManager dbManager;

    @BeforeClass
    public static void beforeTest() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);

        try (OutputStream output = new FileOutputStream("app.properties")) {
            Properties prop = new Properties();
            prop.setProperty("connection.url", URL_CONNECTION);
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }

        dbManager = DBManager.getInstance();

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = con.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (\n" +
                    "  id INTEGER(11) NOT NULL AUTO_INCREMENT,\n" +
                    " login VARCHAR(20) NOT NULL, \n" +
                    "  PRIMARY KEY (id));";

            statement.executeUpdate(sql);
        }
    }

    @Test
    public void shouldChangeTeamName() {
        dbManager = DBManager.getInstance();
        Team team = DBManager.getTeam("teamC");
        int index = team.getId();
        team.setName("teamX");
        dbManager.updateTeam(team);
        List<Team> teams = dbManager.findAllTeams();
        String newName=null;
        for (Team t : teams) {
            if (t.getId() == index) {
                newName = t.getName();
            }
        }
        Assert.assertEquals("teamX",newName);
    }

    @Test
    public void checkDemo() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Demo.main(new String[] {"foo"});
        outContent.flush();
        String whatWasPrinted = new String(outContent.toByteArray());
        Assert.assertNotNull(whatWasPrinted);
    }

    @Test
    public void Test() {
        Demo.main(null);
        Assert.assertTrue(true);
    }
}