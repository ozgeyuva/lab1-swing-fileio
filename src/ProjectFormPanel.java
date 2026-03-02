import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectFormPanel extends JPanel {

    public ProjectFormPanel() {

        setLayout(new GridLayout(0, 2, 10, 10));

        JLabel title = new JLabel("=== Project Entry ===");
        add(title);
        add(new JLabel(""));

        add(new JLabel("Project Name"));
        JTextField txtProjectName = new JTextField();
        add (txtProjectName);

        add(new JLabel("Team Leader"));
        JTextField txtTeamLeader = new JTextField();
        add (txtTeamLeader);

        add(new JLabel("Team Size:"));
        JComboBox<String> cmbTeamSize = new JComboBox<>(new String[]{
                "1-3", "4-6", "7-10", "10+"
        });
        add(cmbTeamSize);

        add(new JLabel("Project Type"));
        JComboBox<String> comboProjectType = new JComboBox<>(new String[]{
                "Web", "Mobile", "Desktop", "API"
        });
        add (comboProjectType);

        add(new JLabel("Start Date(DD/MM/YYYY):"));
        JTextField txtStartDate = new JTextField();
        add (txtStartDate);

        JButton btnSave = new JButton("Save");
        JButton btnClear = new JButton("Clear");
        add(btnSave);
        add(btnClear);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String projectName = txtProjectName.getText().trim();
                String teamLeader = txtTeamLeader.getText().trim();
                String teamSize = cmbTeamSize.getSelectedItem().toString();
                String projectType = comboProjectType.getSelectedItem().toString();
                String startDate = txtStartDate.getText().trim();

                if (projectName.isEmpty() || teamLeader.isEmpty() || startDate.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "All fields must be filled!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String recordTime =
                        LocalDateTime.now().format(formatter);

                try (BufferedWriter writer =
                             new BufferedWriter(new FileWriter("projects.txt", true))) {

                    writer.write("=== Project Entry ===");
                    writer.newLine();
                    writer.write("Project Name  : " + projectName);
                    writer.newLine();
                    writer.write("Team Leader   : " + teamLeader);
                    writer.newLine();
                    writer.write("Team Size     : " + teamSize);
                    writer.newLine();
                    writer.write("Project Type  : " + projectType);
                    writer.newLine();
                    writer.write("Start Date    : " + startDate);
                    writer.newLine();
                    writer.write("Record Time   : " + recordTime);
                    writer.newLine();
                    writer.write("====================");
                    writer.newLine();
                    writer.newLine();

                    JOptionPane.showMessageDialog(null,
                            "Project saved successfully!");

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error writing to file!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                txtProjectName.setText("");
                txtTeamLeader.setText("");
                txtStartDate.setText("");

                cmbTeamSize.setSelectedIndex(0);
                comboProjectType.setSelectedIndex(0);
            }
        });

    }
}
