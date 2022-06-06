import java.sql.*;
import java.util.Scanner;
public class cinema {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int check;
        int id;
        String name;
        String premiere;
        String director;
        int times;

        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/cinema", "root", "");
                PreparedStatement pstmtInsertU = conn.prepareStatement(
                        "insert into movie (id, name, premiere, director, times) values (?, ?, ?, ?, ?)");
                PreparedStatement pstmtUpdate = conn.prepareStatement(
                        "update movie set times = ? where name = ?");
                PreparedStatement pstmtDelete = conn.prepareStatement(
                        "delete from movie where id = ?");
                PreparedStatement pstmtSelect = conn.prepareStatement(
                        "select * from movie where id = ?");
                ) {
            conn.setAutoCommit(false);
            try {
                do {
                    System.out.println("Please choose the answers: ");
                    System.out.println("1.Insert \t\t 2.Update  \t\t 3.Delete  \t\t 4.Search");
                    check = sc.nextInt();
                    if (check == 1 ){
                        System.out.println(" Insert "); // ban chon THEM
                        sc.nextLine();
                        System.out.println("Enter ID (number)");
                        id = sc.nextInt();
                        System.out.println("Enter Name (write a-z)");
                        name = sc.next();
                        System.out.println("Enter Premiere (year-month-day)");
                        premiere = sc.next();
                        System.out.println("Enter Director (write)");
                        director = sc.next();
                        System.out.println("Enter Times (number)");
                        times = sc.nextInt();

                        pstmtInsertU.setInt(1, id);
                        pstmtInsertU.setString(2, name);
                        pstmtInsertU.setString(3, premiere);
                        pstmtInsertU.setString(4, director);
                        pstmtInsertU.setInt(5, times);
                        pstmtInsertU.executeUpdate();
                        System.out.println("Successful Insert !");
                    }
                    else if (check == 2){
                        System.out.println(" Update "); // ban Thay doi
                        sc.nextLine();
                        System.out.println("Enter name  : ");
                        name = sc.nextLine();
                        System.out.println("Enter change times: ");// nhap ngay thay doi
                        times = sc.nextInt();

                        pstmtUpdate.setInt(1, times);
                        pstmtUpdate.setString(2, name);
                        pstmtUpdate.executeUpdate();
                        System.out.println("Successful Update ! ");
                   }
                    else if (check == 3){
                        System.out.println(" Delete  "); // ban XOA
                        sc.nextLine();
                        System.out.println("Enter Delete ID : "); // nhap ID can xoa
                        id = sc.nextInt();
                        pstmtDelete.setInt(1, id);
                        pstmtDelete.executeUpdate();
                        System.out.println("Successful Delete ! ");
                    }
                    if (check == 4){
                        System.out.println(" Search "); // Tim Kiem
                        sc.nextLine();
                        System.out.println("Enter Search ID : "); // Nhap ID can tim
                        id = sc.nextInt();
                        pstmtSelect.setInt(1, id);
                        ResultSet rset = pstmtSelect.executeQuery();
                        while (rset.next()){
                            System.out.println(rset.getInt("id") +" : "
                                    + rset.getString("name") + "; "
                                    + rset.getString("premiere") + "; "
                                    + rset.getString("director") + "; "
                                    + rset.getInt("times") + ".");
                        }
                        System.out.println("Successful Search ! ");
                    }
                    conn.commit();
                    System.out.println("Do you want to continue? ");
                    System.out.println("5. YES \t\t 6. NO");
                    check = sc.nextInt();
                    if (check == 6 ){
                        break;
                    }
                }while (check != 1 || check != 2 || check != 3 || check != 4 || check != 5 );
            }catch (SQLException ex) {
                System.out.println(" Failing ! ");
                conn.rollback();
                ex.printStackTrace();
            }
        }
    }
}
