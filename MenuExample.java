import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


class MenuExample implements ActionListener, MenuListener
{
    JMenu fmenu, amenu;
    JMenuItem loadButton, addButton, saveButton, plotButton;
    JFrame frame;
    Load fileLoaded;

    MenuExample(JFrame frame){
        this.frame = frame;
        //initialize frames and menubars
        JMenuBar mb = new JMenuBar();
        JMenuBar aboutbar = new JMenuBar();

        //fmenu = file menu  amenu = about menu
        fmenu = new JMenu("File");
        amenu = new JMenu("About");

        //create and label buttons
        loadButton=new JMenuItem("Load a Roster");
        addButton=new JMenuItem("Add Attendance");
        saveButton=new JMenuItem("Save");
        plotButton=new JMenuItem("Plot Data");

        //add listeners to menu buttons
        loadButton.addActionListener(this);
        addButton.addActionListener(this);
        saveButton.addActionListener(this);
        plotButton.addActionListener(this);
        amenu.addMenuListener(this);

        //add buttons to their respective menu
        fmenu.add(loadButton);
        fmenu.add(addButton);
        fmenu.add(saveButton);
        fmenu.add(plotButton);

        //add menu to bar
        mb.add(fmenu);
        mb.add(amenu);

        //create frame
        this.frame.setJMenuBar(mb);
        this.frame.setSize(400,400);
        //this.frame.setLayout(null);
        this.frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==loadButton)
        {
            JFileChooser filechoose = new JFileChooser(FileSystemView.getFileSystemView());
            FileNameExtensionFilter csvfilter = new FileNameExtensionFilter("CSV only", "CSV");
            filechoose.setFileFilter(csvfilter);
            filechoose.setAcceptAllFileFilterUsed(false);
            int r = filechoose.showOpenDialog(frame);

            if (r == 1) {
                JOptionPane.showMessageDialog(frame, "No file selected.");
                return;
            }
            File selectedFile = filechoose.getSelectedFile();
            //read and create table

            if (selectedFile.exists()) {
                String[] columns = new String[]{"ID", "First Name", "Last Name", "Program and Plan", "Academic Level", "ASURITE"};
                //need help on Jtable

                JTable table = new JTable();

                try {
                    FileInputStream fileinput = new FileInputStream(selectedFile);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                Scanner scan = null;
                try {
                    scan = new Scanner(selectedFile);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                String[] array;
                DefaultTableModel model = new DefaultTableModel(columns, 0);
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    System.out.println(line);
                    if (line.indexOf(",") > -1)
                        array = line.split(",");
                    else
                        array = line.split("\t");
                    Object[] data = new Object[array.length];
                    for (int i = 0; i < array.length; i++)
                        data[i] = array[i];

                    model.addRow(data);
                }
                table.setModel(model);
                System.out.println(table.getRowCount());
                JPanel tableholder = new JPanel();
                JScrollPane tablescroll = new JScrollPane(table);
                frame.getContentPane().add(tablescroll, BorderLayout.CENTER);
                this.frame.setSize(700,400);


            }
        }

        if(e.getSource()==addButton)
        {
            //add process
        }

        if(e.getSource()==saveButton)
        {
            //save process
        }

        if(e.getSource()==plotButton)
        {
            //plot process
        }

    }

    public void menuSelected(MenuEvent a)
    {
        //about process
    }
    public void menuDeselected(MenuEvent e) { }
    public void menuCanceled(MenuEvent e) { }

    public static void main(String args[])
    {
        JFrame frame1 = new JFrame("CSE360 Final Project");
        new MenuExample(frame1);
    }
}