import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchPage {
    public void showSearchPage(Statement st){
        JFrame searchFrame = new JFrame("Search User");
        searchFrame.setBounds(400, 200, 300, 250);
        searchFrame.setLayout(new GridLayout(4, 2));

        String[] labels = {"Username:", "Interests:", "Hobbies:"};
        JTextField[] textFields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            JLabel txt = new JLabel(labels[i]);
            txt.setFont(new Font("Arial", Font.BOLD, 14));
            txt.setForeground(Color.RED);
            searchFrame.add(txt);
            textFields[i] = new JTextField();
            searchFrame.add(textFields[i]);
        }

        JButton findButton = new JButton("Find");
        findButton.setFont(new Font("Arial", Font.BOLD, 14));
        findButton.setBackground(Color.BLUE);
        findButton.setForeground(Color.WHITE);
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try{
                    String username = textFields[0].getText();
                    String interests = textFields[1].getText();
                    String hobbies = textFields[2].getText();

                    if(interests.equals("") || hobbies.equals("")){
                        JOptionPane.showMessageDialog(null, "Must fill hobbies and interests field.!");
                    }
                    else {
                        ResultSet usr = st.executeQuery("select * from users where username = '" + username + "';");

                        if(usr.next()){
                            StringBuilder foundUsernames = new StringBuilder();
                            int cnt = 0;
                            ResultSet buddies = st.executeQuery("select * from users where interests like '%" + interests + "%' and hobbies like '%"+ hobbies + "%' and username not like '" + username + "';");
                            while(buddies.next()){
                                cnt++;
                                foundUsernames.append(cnt + ". ").append(buddies.getString("username")).append("\n");
                            }
                            if(cnt == 0){
                                JOptionPane.showMessageDialog(null, "No users with similar interests and hobbies.!");
                            }
                            else{ 
                                if(cnt == 1){
                                    JOptionPane.showMessageDialog(null, cnt + " user with similar hobbies and interests exists.!\n" + foundUsernames);
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, cnt + " users with similar hobbies and interests exists.!\n" + foundUsernames);
                                }
                            }
                            textFields[1].setText("");
                            textFields[2].setText("");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Username does not exist.!");
                        }
                    }
                }
                catch (SQLException a) {
                    System.out.println(a);
                }
            }
        });

        searchFrame.add(findButton);
        searchFrame.setVisible(true);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}