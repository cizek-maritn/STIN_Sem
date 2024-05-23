package bank.Stin;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import java.sql.*;

@SpringBootApplication
public class Application {
    public static Connection dbCon=null;

	public static void main(String[] args) {
            try {
                Class.forName("org.sqlite.JDBC");
                dbCon = DriverManager.getConnection("jdbc:sqlite:test.db");
                Statement st=dbCon.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS LOGIN" +
                        "(INFO TEXT PRIMARY KEY NOT NULL)";
                st.execute(sql);
                sql="CREATE TABLE IF NOT EXISTS TEMP" +
                        "(ID INT PRIMARY KEY NOT NULL,"+
                        "MSG TEXT NOT NULL)";
                st.execute(sql);
                sql="INSERT INTO TEMP (ID,MSG) VALUES (1,'LOADING');";
                st.execute(sql);
                st.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Opened database successfully");
            
            SpringApplication.run(Application.class, args);
	}
}
