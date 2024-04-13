import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class CreatePage {
    public void showCreatePage(Statement st){
        JFrame createFrame = new JFrame("Create User");
        createFrame.setBounds(400, 200, 400, 300);
        createFrame.setLayout(new GridLayout(6, 2));

        String[] labels = {"Username:", "First Name:", "Last Name:", "Interests:", "Hobbies:"};
        JTextField[] textFields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            JLabel txt = new JLabel(labels[i]);
            txt.setForeground(Color.RED);
            txt.setFont(new Font("Arial", Font.BOLD, 14));
            createFrame.add(txt);
            textFields[i] = new JTextField();
            createFrame.add(textFields[i]);
        }

        JButton createButton = new JButton("Create");
        createButton.setFont(new Font("Arial", Font.BOLD, 14));
        createButton.setBackground(Color.BLUE);
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = textFields[0].getText();
                    String firstName = textFields[1].getText();
                    String lastName = textFields[2].getText();
                    String interests = textFields[3].getText();
                    String hobbies = textFields[4].getText();

                    ResultSet usr = st.executeQuery("select * from users where username = '" + username + "';");

                    //Throws error if any field is empty.
                    if(username.equals("")){
                        JOptionPane.showMessageDialog(null, "Must fill the username field.!");
                        throw new SQLException();
                    }
                    if(firstName.equals("")){
                        JOptionPane.showMessageDialog(null, "Must fill the first name field.!");
                        throw new SQLException();
                    }
                    if(lastName.equals("")){
                        JOptionPane.showMessageDialog(null, "Must fill the last name field.!");
                        throw new SQLException();
                    }
                    if(interests.equals("")){
                        JOptionPane.showMessageDialog(null, "Must fill the interests field.!");
                        throw new SQLException();
                    }
                    if(hobbies.equals("")){
                        JOptionPane.showMessageDialog(null, "Must fill the hobbies field.!");
                        throw new SQLException();
                    }

                    //Checks if username already exists.
                    if(usr.next()){
                        JOptionPane.showMessageDialog( null, "Username already Exists.!");
                    }
                    else {
                        st.executeUpdate("insert into users values ('" + username + "','" + firstName + "','" + lastName + "','" + interests + "','" + hobbies +"');");
                        JOptionPane.showMessageDialog(null, "User created successfully.!");
                        createFrame.dispose();
                    }
                }
                catch (SQLException a) {
                    JOptionPane.showMessageDialog(null, "Failed to create user.!");
                }
            }
        });

        createFrame.add(createButton);
        createFrame.setVisible(true);
        createFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}