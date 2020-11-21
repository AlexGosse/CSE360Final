import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuExample implements ActionListener, MenuListener
{
    JMenu fmenu, amenu;
    JMenuItem loadButton, addButton, saveButton, plotButton;
    MenuExample(){

        MenuListener listener;


        JFrame frame = new JFrame("CSE360 Final Project");
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
        frame.setJMenuBar(mb);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==loadButton)
        {
            //load process
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
        new MenuExample();
    }
}