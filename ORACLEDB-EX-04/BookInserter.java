import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class BookInserter {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver bulunamadı!");
            e.printStackTrace();
            return;
        }

        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "user123"; // Kendi kullanıcı adınızı girin
        String password = "123456"; // Kendi şifrenizi girin

        String insertSQL = "INSERT INTO BOOK (ID, NAME, ISBN) VALUES (?, ?, ?)";
        Random random = new Random();

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            
            System.out.println("Veritabanına başarıyla bağlanıldı!");
            
            for (int i = 1; i <= 100; i++) {
                pstmt.setInt(1, i);
                pstmt.setString(2, "Book_" + getRandomString(random, 6));
                pstmt.setString(3, getRandomISBN(random));
                pstmt.executeUpdate();
                
                if (i % 10 == 0) {
                    System.out.println(i + " kayıt eklendi...");
                }
            }

            System.out.println("100 kayıt başarıyla eklendi.");
        } catch (SQLException e) {
            System.out.println("Veritabanı hatası:");
            e.printStackTrace();
        }
    }

    private static String getRandomString(Random random, int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    private static String getRandomISBN(Random random) {
        StringBuilder isbn = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            isbn.append(random.nextInt(10));
        }
        return isbn.toString();
    }
}