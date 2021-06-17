
import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Spy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class Part1StudentTest {

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
    public void shouldReturnListOfUsers (){
        dbManager = DBManager.getInstance();
        DBManager.insertUser(User.createUser("petrov"));
        DBManager.insertUser(User.createUser("obama"));
        List<User> userList = dbManager.findAllUsers();
        Assert.assertEquals(2,userList.size());
    }

    @Test
    public void testGetConnection(){
        dbManager = DBManager.getInstance();
        Connection connection = null;
        try {
            connection = dbManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(connection);
    }

    @Test
    public void testGetInstance(){
        dbManager = DBManager.getInstance();
        Assert.assertNotNull(dbManager);
    }

    @Test
    public void testInsertUser(){
        dbManager = DBManager.getInstance();
        DBManager.insertUser(User.createUser("petrov"));
        User user = DBManager.getUser("petrov");
        Assert.assertEquals(user.getLogin(), "petrov");
    }
    @Test

    public void shouldAddIdAutomatically(){
        dbManager = DBManager.getInstance();
        DBManager.insertUser(User.createUser("petrov"));
        User userPetrov = DBManager.getUser("petrov");
        Assert.assertNotEquals(0,userPetrov.getId());
    }

}