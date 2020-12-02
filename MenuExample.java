
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.*;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;


class MenuExample implements ActionListener {
    JMenu fmenu, amenu;
    JMenuItem loadButton, addButton, saveButton, plotButton, aboutButton;
    JFrame frame;
    Load fileLoaded;
    JTable globaltable;
    DefaultTableModel globaltablemodel;
    JScrollPane oldscroll;
    int addedcols = 6;
    int fileselected;

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
        aboutButton=new JMenuItem("Team Information");

        //add listeners to menu buttons
        loadButton.addActionListener(this);
        addButton.addActionListener(this);
        saveButton.addActionListener(this);
        plotButton.addActionListener(this);
        aboutButton.addActionListener(this);

        //add buttons to their respective menu
        fmenu.add(loadButton);
        fmenu.add(addButton);
        fmenu.add(saveButton);
        fmenu.add(plotButton);
        amenu.add(aboutButton);

        //add menu to bar
        mb.add(fmenu);
        mb.add(amenu);

        //create frame
        this.frame.setJMenuBar(mb);
        //this.frame.setResizable(false);
        this.frame.setSize(800,800);
        this.frame.setVisible(true);
        fileselected = 0;
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

            try
            {
                FileInputStream fileinput = new FileInputStream(selectedFile);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            Scanner scan1 = null;
            try
            {
                scan1 = new Scanner(selectedFile);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            String s = scan1.nextLine();

            while(scan1.hasNextLine())
            {
                int commas = 0;
                s =scan1.nextLine();
                for(int i=0;i<s.length();i++) {
                    if (s.charAt(i) == ',') {
                        commas++;
                    }
                }
                if(commas!=5)
                {
                    JOptionPane.showMessageDialog(frame, "CSV file has wrong format.");
                    return;
                }
            }

            if(fileselected==1)
            {
                frame.remove(oldscroll);
                this.frame.setSize(701,400);
            }

            if (selectedFile.exists()) {


                String[] columns = new String[]{"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
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
                table.setRowHeight(20);
                table.getTableHeader().setReorderingAllowed(false);
                table.getTableHeader().setResizingAllowed(false);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table.getTableHeader().setMinimumSize(new Dimension(30,20));
                table.getTableHeader().setMaximumSize(new Dimension(30,20));
                table.getColumnModel().getColumn(0).setPreferredWidth(120);
                table.getColumnModel().getColumn(1).setPreferredWidth(120);
                table.getColumnModel().getColumn(2).setPreferredWidth(120);
                table.getColumnModel().getColumn(3).setPreferredWidth(120);
                table.getColumnModel().getColumn(4).setPreferredWidth(120);
                table.getColumnModel().getColumn(5).setPreferredWidth(120);


                JScrollPane tablescroll = new JScrollPane(table);
                tablescroll.setSize(2000,800);
                frame.getContentPane().add(tablescroll, BorderLayout.CENTER);
                this.frame.setSize(800,801);
                this.globaltable = table;
                this.fileselected = 1;
                globaltablemodel = model;
                oldscroll = tablescroll;
            }
        }

        if(e.getSource()==addButton)
        {
            if(fileselected == 0)
            {
                JOptionPane.showMessageDialog(frame, "No chart selected. Load one using Load a Roster.");
                return;
            }
            else
            {

                //same code as load a roster
                JFileChooser filechoose = new JFileChooser(FileSystemView.getFileSystemView());
                FileNameExtensionFilter csvfilter = new FileNameExtensionFilter("CSV only", "CSV");
                filechoose.setFileFilter(csvfilter);
                filechoose.setAcceptAllFileFilterUsed(false);
                int r = filechoose.showOpenDialog(frame);

                //return if no roster loaded
                if (r == 1)
                {
                    JOptionPane.showMessageDialog(frame, "No file selected.");
                    return;
                }
                File selectedFile = filechoose.getSelectedFile();

                //check the csv format

                try
                {
                    FileInputStream fileinput = new FileInputStream(selectedFile);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                Scanner scan1 = null;
                try
                {
                    scan1 = new Scanner(selectedFile);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

                String s;
                //count commas in line if commas dont match close the add process
                while(scan1.hasNextLine())
                {
                    int commas = 0;
                    s =scan1.nextLine();
                    for(int i=0;i<s.length();i++) {
                        if (s.charAt(i) == ',') {
                            commas++;
                        }
                        if(i == s.length()-1 && s.equals(""))
                        {
                            commas=1;
                        }
                    }
                    if(commas!=1)
                    {
                        JOptionPane.showMessageDialog(frame, "CSV file has wrong format.");
                        return;
                    }
                }


                if(selectedFile.exists())
                {
                    //create the select a date panel
                    JPanel datepane = new JPanel();

                    String[] monthlist = {"January", "February", "March", "April", "May","June","July","August","September","October","November","December"};
                    JComboBox months = new JComboBox(monthlist);

                    String[] daylist = {"1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" , "10" , "11" , "12" , "13" , "14" , "15" , "16" , "17" , "18" , "19" , "20" , "21" , "22" , "23" , "24" , "25" , "26" , "27" , "28" , "29" , "30" , "31"};
                    JComboBox days = new JComboBox(daylist);

                    //add the combo boxes
                    datepane.add(months);
                    datepane.add(days);


                    int optionselected = JOptionPane.showConfirmDialog(frame,datepane, "Date", JOptionPane.OK_CANCEL_OPTION);

                    if(optionselected == 2)
                    {
                        return;
                    }
                    else
                    {
                        //get the combobox info
                        String month = String.valueOf(months.getSelectedItem());
                        String day = String.valueOf(days.getSelectedItem());
                        String newcolumn = month +" "+ day;
                        TableColumn newtc = new TableColumn();
                        //DefaultTableModel attendanceEntry = globaltablemodel;
                        //attendanceEntry.addColumn(newcolumn);

                        //create new scanner to traverse file again
                        try
                        {
                            FileInputStream fileinput = new FileInputStream(selectedFile);
                        } catch (FileNotFoundException fileNotFoundException)
                        {
                            fileNotFoundException.printStackTrace();
                        }
                        Scanner scan = null;
                        try
                        {
                            scan = new Scanner(selectedFile);
                        } catch (FileNotFoundException fileNotFoundException)
                        {
                            fileNotFoundException.printStackTrace();
                        }

                        //create vectors used to store asurite id and minutes, indexs correspond to each other
                        String[] unsortedarray;
                        Vector<String> infoname = new Vector<String>();
                        Vector<Integer> infoint = new Vector<Integer>();

                        //opens attendance sheet, adds multiple asurite id minutes.
                        while (scan.hasNextLine())
                        {
                            String line = scan.nextLine();
                            if (line.indexOf(",") > -1)
                            {
                                unsortedarray = line.split(",");
                            }
                            else
                            {
                                unsortedarray = line.split("\t");
                            }

                            int newinsertToggle = 0;
                            if(infoname.isEmpty())
                            {
                                infoname.add(unsortedarray[0]);
                                int insert = Integer.parseInt(unsortedarray[1]);
                                infoint.add(insert);
                                newinsertToggle =1;

                            }

                            if(newinsertToggle == 0)
                            {
                                for(int i = 0; i <infoname.size();i++)
                                {
                                    //if asurite already has minutes just add to that index
                                    if(unsortedarray[0].compareTo(infoname.get(i))==0 )
                                    {
                                        int insert = Integer.parseInt(unsortedarray[1]);
                                        //System.out.println("Adding "+ insert+ " and "+ infoint.get(i));
                                        insert = insert + infoint.get(i);
                                        infoint.set(i,insert);
                                        newinsertToggle =1;
                                    }
                                }
                                //if asurite doesnt exist already create a new entry in both vectors
                                if(newinsertToggle==0)
                                {
                                    infoname.add(unsortedarray[0]);
                                    infoint.add(Integer.parseInt(unsortedarray[1]));
                                }
                            }


                        }

                        Vector<String> missingstudents = new Vector<String>();
                        Vector<Integer> missingint = new Vector<>();

                        //insert info into column
                        int shouldreturn = 1;
                        if(addedcols>5)
                        {
                            for(int i =0 ;i < addedcols;i++)
                            {
                                if(newcolumn.compareTo(globaltablemodel.getColumnName(i))==0)
                                {
                                    shouldreturn = 0;
                                    JOptionPane.showMessageDialog(frame, "Column for date already exists.");
                                }
                            }
                        }

                        if(shouldreturn == 1)
                        {
                            globaltablemodel.addColumn(newcolumn);
                            //System.out.println(shouldreturn);
                            for(int j = 0; j< infoname.size();j++)
                            {
                                int found =0;
                                for(int i = 0; i< globaltablemodel.getRowCount();i++)
                                {

                                    String colString = (String) globaltablemodel.getValueAt(i,5);

                                    if(colString!=null)
                                    {
                                        if(colString.compareTo(infoname.get(j))==0)
                                        {
                                            globaltablemodel.setValueAt(infoint.get(j),i,addedcols);
                                            found = 1;
                                        }

                                    }

                                }
                                if(found == 0)
                                {
                                    missingstudents.add(infoname.get(j));
                                    missingint.add(infoint.get(j));
                                }

                            }
                            addedcols++;

                            for(int i = 0; i< globaltablemodel.getRowCount();i++)
                            {
                                for(int j =0; j< globaltablemodel.getColumnCount();j++)
                                {
                                    //System.out.println(i + " "+ j);
                                    if(globaltablemodel.getValueAt(i,j)==null)
                                    {
                                        globaltablemodel.setValueAt(0,i,j);
                                    }
                                }

                            }


                            globaltable = new JTable();
                            globaltable.setModel(globaltablemodel);
                            globaltable.setRowHeight(20);
                            globaltable.getTableHeader().setReorderingAllowed(false);
                            //globaltable.getTableHeader().setResizingAllowed(false);
                            globaltable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                            //table.getTableHeader().setMinimumSize(new Dimension(30,20));
                           //table.getTableHeader().setMaximumSize(new Dimension(30,20));
                            globaltable.getColumnModel().getColumn(0).setPreferredWidth(120);
                            globaltable.getColumnModel().getColumn(1).setPreferredWidth(120);
                            globaltable.getColumnModel().getColumn(2).setPreferredWidth(120);
                            globaltable.getColumnModel().getColumn(3).setPreferredWidth(120);
                            globaltable.getColumnModel().getColumn(4).setPreferredWidth(120);
                            globaltable. getColumnModel().getColumn(5).setPreferredWidth(120);
                            globaltable.setSize(800,800);

                            JScrollPane tablescroll = new JScrollPane(globaltable);
                            tablescroll.setSize(799,799);
                            frame.getContentPane().removeAll();
                            frame.getContentPane().add(tablescroll);
                            //this.frame = newframe;
                            this.frame.setSize(801,801);


                            //create dialog
                            if(missingstudents.isEmpty())
                            {
                                JOptionPane.showMessageDialog(frame,
                                        "Data loaded for "+ infoname.size()+" users in the roster.");
                            }
                            else
                            {

                                String printdiag ="Data loaded for "+ (infoname.size() - missingstudents.size())+" users in the roster.\n\n" + missingstudents.size() +" additional students found:\n";
                                for(int i = 0 ; i<missingstudents.size();i++)
                                {
                                    printdiag += missingstudents.get(i)+ ", connected for " + missingint.get(i) +" minute(s).\n";

                                }
                                JOptionPane.showMessageDialog(frame, printdiag);
                            }

                        }


                    }

                }

            }

        }

        if(e.getSource()==saveButton)
        {
            if(fileselected == 0)
            {
                JOptionPane.showMessageDialog(frame, "No roster selected. Load one using Load a Roster.");
                return;
            }
            //save process
            JFileChooser filechoose = new JFileChooser(FileSystemView.getFileSystemView());
            filechoose.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter csvfilter = new FileNameExtensionFilter("CSV only", "CSV");
            filechoose.setFileFilter(csvfilter);
            int r = filechoose.showOpenDialog(frame);

            if (r == 1)
            {
                JOptionPane.showMessageDialog(frame, "No path selected.");
                return;
            }
            File fileselected = filechoose.getSelectedFile();
            try {

                String filepath = fileselected.getAbsolutePath();
                if(!filepath.contains(".csv"))
                {
                    filepath += ".csv";
                }
                FileWriter fw = new FileWriter(filepath);

                for (int i = 0; i < globaltablemodel.getColumnCount(); i++) {
                    fw.write(globaltablemodel.getColumnName(i) + ",");
                }

                fw.write("\n");

                for (int i = 0; i < globaltablemodel.getRowCount(); i++) {
                    for (int j = 0; j < globaltablemodel.getColumnCount(); j++) {
                        fw.write(globaltablemodel.getValueAt(i, j).toString());
                        if(j< globaltablemodel.getColumnCount()-1)
                        {
                            fw.write(",");
                        }
                    }
                    if(i<globaltablemodel.getRowCount()-1)
                    {
                        fw.write("\n");
                    }
                }

                fw.close();
            } catch (IOException er) {
                er.printStackTrace();
            }


        }

        if(e.getSource()==plotButton)
        {
            if(fileselected == 0)
            {
                JOptionPane.showMessageDialog(frame, "No roster selected. Load one using Load a Roster.");
                return;
            }

            int extracols = addedcols-6;
            if(extracols == 0)
            {
                JOptionPane.showMessageDialog(frame, "No attendance data. Add one using Add Attendance.");
                return;
            }
            //System.out.println(extracols);
            XYSeriesCollection dataset = new XYSeriesCollection();

            for(int i = 0;i<extracols;i++)
            {
                XYSeries seriesEntry = new XYSeries(globaltablemodel.getColumnName(6+i));
                double grade0,grade10,grade20,grade30,grade40,grade50,grade60,grade70,grade80,grade90,grade100;
                grade0 = grade10 =grade20 =grade30 =grade40 =grade50= grade60 = grade70 = grade80 = grade90 =grade100 = 0;
                for(int j = 0; j< globaltablemodel.getRowCount();j++)
                {

                    int entryint = (int) globaltablemodel.getValueAt(j,6+i);
                    double entrydub = entryint;
                    double percent = entrydub/75;
                    if(percent >1)
                    {
                        percent = 1;
                    }
                    else
                    {
                        percent = Math.round(percent * 10.0)/10.0;
                    }

                    //System.out.println(percent);
                    if(percent ==100)
                    {
                        grade100++;

                    }
                    else if(percent==0.9)
                    {
                        grade90++;

                    }
                    else if(percent==0.8)
                    {
                        grade80++;

                    }
                    else if(percent==0.7)
                    {
                        grade70++;

                    }
                    else if(percent==0.6)
                    {
                        grade60++;

                    }
                    else if(percent==0.5)
                    {
                        grade50++;

                    }
                    else if(percent==0.4)
                    {
                        grade40++;

                    }
                    else if(percent==0.3)
                    {
                        grade30++;

                    }
                    else if(percent==0.2)
                    {
                        grade20++;

                    }
                    else if(percent==0.1)
                    {
                        grade10++;

                    }
                    else if(percent==0.0)
                    {
                        grade0++;

                    }

                }
                seriesEntry.add(0,grade0);
                seriesEntry.add(0.1,grade10);
                seriesEntry.add(0.2,grade20);
                seriesEntry.add(0.3,grade30);
                seriesEntry.add(0.4,grade40);
                seriesEntry.add(0.5,grade50);
                seriesEntry.add(0.6,grade60);
                seriesEntry.add(0.7,grade70);
                seriesEntry.add(0.8,grade80);
                seriesEntry.add(0.9,grade90);
                seriesEntry.add(1.0,grade100);
                dataset.addSeries(seriesEntry);
            }
            JFreeChart chart = ChartFactory.createScatterPlot("Attendance vs each day", "% of Attendance", "Count", dataset);

            ChartPanel chartdia = new ChartPanel(chart);
            chartdia.setSize(1000,800);

            JPanel chartpanel = new JPanel();
            chartpanel.setSize(1100,800);

            chartpanel.add(chartdia,BorderLayout.CENTER);
            JDialog diag = new JDialog();
            diag.setSize(1100,850);
            diag.getContentPane().add(chartpanel);
            diag.setVisible(true);
        }

        if(e.getSource()==aboutButton)
        {
            JOptionPane.showMessageDialog(frame, "Class: CSE360 Thursday\n\n" +
                    "Team Members:\n" +
                    "Abraham Cervantes\n" +
                    "entername\n" +
                    "entername\n" +
                    "entername\n");
        }

    }

    public static void main(String args[])
    {
        JFrame frame1 = new JFrame("CSE360 Final Project");
        new MenuExample(frame1);
    }
}