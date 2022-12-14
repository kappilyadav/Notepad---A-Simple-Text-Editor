import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

class editor extends JFrame implements ActionListener{

    JFrame f;

    JTextArea t;

    editor(){

        //Initializing the frame
        f = new JFrame("Notepad");


        //setting the overall theme of app
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }

        catch(Exception e){

        }


        //creates the text area
        t = new JTextArea();

        //creating MenuBar
        JMenuBar menu = new JMenuBar();

        //creates menu
        JMenu file = new JMenu("File");

        //creates menuItem
        JMenuItem m1 = new JMenuItem("New");
        JMenuItem m2 = new JMenuItem("Open");
        JMenuItem m3 = new JMenuItem("Save");
        JMenuItem m4 = new JMenuItem("Print");

        //adding actionListener to menuItems
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);

        //Adding menuItem to menu
        file.add(m1);
        file.add(m2);
        file.add(m3);
        file.add(m4);


        //creates menu
        JMenu edit = new JMenu("Edit");

        //creates menuItem
        JMenuItem m5 = new JMenuItem("Cut");
        JMenuItem m6 = new JMenuItem("Copy");
        JMenuItem m7 = new JMenuItem("Paste");

        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);

        edit.add(m5);
        edit.add(m6);
        edit.add(m7);



        //creates menuItem
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(this);

        
        //adding menu
        menu.add(file);
        menu.add(edit);
        menu.add(close);

        //setting menu to the frame
        //adding textArea to frame
        f.setJMenuBar(menu);
        f.add(t);

        //sets TextArea font
        Font font = new Font("Time New Roman", Font.PLAIN, 30);
        t.setFont(font);
        t.setForeground(Color.BLACK);


        //setting default frame size
        f.setSize(720, 480);

        //displays the Frame
        f.setVisible(true);


    }


    //whenever a button pressed, action listener calls this actionPerformed function
    public void actionPerformed(ActionEvent e){

        //extracting the button user has pressed
        String s = e.getActionCommand();

        //if New button pressed
        if(s.equals("New")){
            t.setText("");
        }

        else if(s.equals("Open")){

            //initializing the JFileChooser with desired directory
            JFileChooser j = new JFileChooser("C:/Users/DELL/Desktop");

            //invoking the openDialogBox with an integer
            int r = j.showOpenDialog(f);

                 
            if(r == JFileChooser.APPROVE_OPTION){

                //getting the absolute path of file
                File fi = new File(j.getSelectedFile().getAbsolutePath());


                try {
                    String s1 = "", s2 = "";

                    //FileReader
                    FileReader fr = new FileReader(fi);

                    //Reads the file line by line
                    BufferedReader br = new BufferedReader(fr);

                    //storing the first line in string s2 and second line in s1
                    s2 = br.readLine();
                    s1 = br.readLine();

                    while(s1 != null){
                        s2 = s2 + "\n" + s1;
                        s1 = br.readLine();
                    }

                    //replaces the textArea text with string s2
                    t.setText(s2);

                } catch (Exception e1) {
                    //shows the exception as message
                    JOptionPane.showMessageDialog(f, e1.getMessage());
                }

            }

        }

        else if(s.equals("Save")){
            JFileChooser j = new JFileChooser("C:/Users/DELL/Desktop/");

            int r = j.showSaveDialog(f);

            if(r == JFileChooser.APPROVE_OPTION){
                
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    //FileWriter writes the file
                    FileWriter fw = new FileWriter(fi, false);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(t.getText());

                    //flush the stream of characters and closing the BufferedWriter
                    bw.flush();
                    bw.close();
                    
                         
                } catch (Exception e1) {
                    //shows message
                    JOptionPane.showMessageDialog(f, e1.getMessage());
                }


            }
        }

        else if(s.equals("Print")){
            
            //prints the file
            try {
                t.print();
            } 
            catch (Exception e1) {
                JOptionPane.showMessageDialog(f, e1.getMessage());
            }
        }

        else if(s.equals("Cut")){
            t.cut();
        }

        else if(s.equals("Copy")){
            t.copy();
        }

        else if(s.equals("Paste")){
            t.paste();
        }

        else if(s.equals("Close")){
            f.setVisible(false);
        }
    }


    public static void main(String args[]){
        editor e = new editor();
    }
}
