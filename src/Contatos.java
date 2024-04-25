import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Contatos extends JFrame {
    private List<Contact> contacts;
    private JList<String> contactList;
    private DefaultListModel<String> listModel;

    public Contatos() {
        setTitle("Agenda Telefônica");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contacts = new ArrayList<>();
        listModel = new DefaultListModel<>();
        contactList = new JList<>(listModel);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(contactList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        JTextField nameField = new JTextField();
        JTextField numberField = new JTextField();
        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Número:"));
        inputPanel.add(numberField);

        JButton addButton = new JButton("Adicionar Contato");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String number = numberField.getText();
                if (!name.isEmpty() && !number.isEmpty()) {
                    Contact contact = new Contact(name, number);
                    contacts.add(contact);
                    listModel.addElement(contact.toString());
                    nameField.setText("");
                    numberField.setText("");
                } else {
                    JOptionPane.showMessageDialog(Contatos.this, "Por favor, preencha nome e número.");
                }
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(addButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Contatos();
    }

    private static class Contact {
        private String name;
        private String number;

        public Contact(String name, String number) {
            this.name = name;
            this.number = number;
        }

        @Override
        public String toString() {
            return name + " - " + number;
        }
    }
}
