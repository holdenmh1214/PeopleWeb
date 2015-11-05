import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by holdenhughes on 11/4/15.
 */
public class PeopleTest {
    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./test");
        People.createTables(conn);
        return conn;
    }

    public void endConnection(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE people");
        conn.close();
    }

    @Test
    public void testPerson() throws SQLException {
        Connection conn = startConnection();
        People.insertPerson(conn, "firstname", "lastname", "email", "country", "ip" );
        Person person = People.selectPerson(conn, 0);
        endConnection(conn);
        assertTrue(person != null);
    }

    @Test
    public void testPeople() throws SQLException {
        Connection conn = startConnection();
        People.insertPerson(conn, "", "", "", "", "");
        ArrayList<Person> people = People.selectPeople(conn, People.SHOW_COUNT);
        endConnection(conn);

        assertTrue(people !=null);
    }

}