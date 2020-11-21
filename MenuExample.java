import javax.swing.*;
class MenuExample
{
    JMenu menu, amenu;
    JMenuItem loadButton, addButton, saveButton, plotButton;
    MenuExample(){
        JFrame f= new JFrame("Menu and MenuItem Example");
        JMenuBar mb = new JMenuBar();
        menu=new JMenu("File");

        loadButton=new JMenuItem("Load a Roster");
        addButton=new JMenuItem("Add Attendance");
        saveButton=new JMenuItem("Save");
        plotButton=new JMenuItem("Plot Data");

        JMenuBar aboutbar = new JMenuBar();
        amenu = new JMenu("About");


        menu.add(loadButton); menu.add(addButton); menu.add(saveButton); menu.add(plotButton);
        mb.add(menu);
        mb.add(amenu);
        f.setJMenuBar(mb);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }
    public static void main(String args[])
    {
        new MenuExample();
    }
}