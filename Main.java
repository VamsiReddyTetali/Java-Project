import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.*;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            String url = "jdbc:mysql://localhost:3306/javadb";
            String user = "root";
            String pass = "vamsi2reddy";
            Connection con = DriverManager.getConnection(url, user, pass);
            if(con != null)System.out.println("Successfully connnected!");
            Statement st = con.createStatement();

            JFrame mainFrame = new JFrame("Home Page");
            mainFrame.setResizable(false);
            mainFrame.setBounds(400, 200, 300, 250);
            mainFrame.setLayout(null);

            JButton searchButton = new JButton("Search");
            searchButton.setBounds(90, 50, 100, 40);
            searchButton.setFont(new Font("Arial", Font.BOLD, 14));
            searchButton.setBackground(Color.BLUE);
            searchButton.setForeground(Color.WHITE);
            searchButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    SearchPage s = new SearchPage();
                    s.showSearchPage(st);
                }
            });

            JButton createButton = new JButton("Create");
            createButton.setBounds(90, 120, 100, 40);
            createButton.setFont(new Font("Arial", Font.BOLD, 14));
            createButton.setBackground(Color.BLUE);
            createButton.setForeground(Color.WHITE);
            createButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    CreatePage c = new CreatePage();
                    c.showCreatePage(st);
                }
            });

            mainFrame.add(searchButton);
            mainFrame.add(createButton);
            mainFrame.setVisible(true);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
}