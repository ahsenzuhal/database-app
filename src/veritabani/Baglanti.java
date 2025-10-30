package veritabani;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Baglanti {

    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/KutuphaneSistemi";
    private static final String USER = "root"; 
    private static final String PASS = "2Ehsen.615";


    public static Connection baglan() {
        try {
            // Sınıfı (JDBC sürücüsünü) yüklüyoruz
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            // Bağlantıyı kuruyoruz
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Veritabanı bağlantı hatası: " + e.getMessage());
            return null;
        }
    }
}
