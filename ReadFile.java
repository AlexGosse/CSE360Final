import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class ReadFile {
    public DefaultTableModel Read(JFrame frame, int fileselected, JScrollPane oldscroll) {
        JFileChooser filechoose = new JFileChooser(FileSystemView.getFileSystemView());
        FileNameExtensionFilter csvfilter = new FileNameExtensionFilter("CSV only", "CSV");
        filechoose.setFileFilter(csvfilter);
        filechoose.setAcceptAllFileFilterUsed(false);
        int r = filechoose.showOpenDialog(frame);

        String[] columns = new String[]{"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (r == 1) {
            JOptionPane.showMessageDialog(frame, "No file selected.");
            return null; //No file
        }
        File selectedFile = filechoose.getSelectedFile();

        //read and create table
        try {
            FileInputStream fileinput = new FileInputStream(selectedFile);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        Scanner scan1 = null;
        try {
            scan1 = new Scanner(selectedFile);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        String s = scan1.nextLine();

        while (scan1.hasNextLine()) {
            int commas = 0;
            s = scan1.nextLine();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ',') {
                    commas++;
                }
            }
            if (commas != 5) {
                JOptionPane.showMessageDialog(frame, "CSV file has wrong format.");
                return null;
            }
        }

        if (fileselected == 1) {
            frame.remove(oldscroll);
            frame.setSize(701, 400);
        }

        if (selectedFile.exists()) {
            Scanner scan = null;
            try {
                scan = new Scanner(selectedFile);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            String[] array; //Array of Comma separated user info
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.indexOf(",") > -1)
                    array = line.split(",");
                else
                    array = line.split("\t");
                model.addRow(array);
            }
        }
        return model;
    }
}
