package kata4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Kata4 {

    public static void main(String[] args) throws SQLException{
        Connection connection = createConnetion("people.db") ;
        PersonLoader personLoader = new DatabasePersonLoader(connection);
        HistogramBuilder<Person> builder = new HistogramBuilder<>(personLoader.load());
        new ConsoleHistogramViewer<String>().show(builder.build(new AttributeExtractor<Person, String>() {

            @Override
            public String extract(Person person) {
                return person.getMail().getDomain();
            }
        }));
    }

    private static Connection createConnetion(String dbPath) throws SQLException {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }
    
}
