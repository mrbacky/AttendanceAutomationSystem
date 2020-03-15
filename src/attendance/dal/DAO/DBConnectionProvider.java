package attendance.dal.DAO;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * The {@code DbConnectionProvider} class is responsible for establishing
 * connection with a database.
 *
 * @author Martin
 */
public class DBConnectionProvider {

    private static final String PROP_FILE = "data/DBProperties.properties";
    private SQLServerDataSource ds;

    /**
     * Creates a connection with the database.
     */
    public DBConnectionProvider() {
        try {
            Properties databaseProperties = new Properties();
            databaseProperties.load(new FileInputStream(PROP_FILE));
            ds = new SQLServerDataSource();
            ds.setServerName(databaseProperties.getProperty("Server"));
            ds.setDatabaseName(databaseProperties.getProperty("Database"));
            ds.setUser(databaseProperties.getProperty("User"));
            ds.setPassword(databaseProperties.getProperty("Password"));
        } catch (IOException e) {
            //To DO
        }
    }

    /**
     * Returns Connection object which is able to provide information about
     * database.
     *
     * @return The connection with database.
     * @throws SQLServerException if connection with database cannot be
     * established.
     */
    public Connection getConnection() throws SQLServerException {
        return ds.getConnection();
    }
}
