/*

When interviewer asks -->

Ques --> What did you learn by making this project?
Ans  --> OOPs(Obect Oriented Programming)
         inheritence is happening here (extends IFrame)
         we're using contructor here in classes

Whenever you are exlaining this project
--> first of all cover all the functionality

--> Tell them that -> 
    It is a Simple Text Editor Application
    It has certain functionalities like 'SAVE' option, 'OPEN', 'PRINT' and 'CUT', 'COPY', "PASTE" and 'CLOSE'
    these options we have added

--> Tell them the motive behind why're you making this project
    Tell them that so this is a introductory project to Java
    like I was started to learn Java and that's why I made this project
    so, that I can have some idea about File Handling and basic Java Swing Libraries
    you can also tell you've learned Exception Handling(using try/catch block) in Java

--> Explain inteviewer the features 
    Tell them that you've used OOPs here, you've used constructors, you've used inheritence also

    --> We have created a Frame
    --> We have given the title 'Notepad' to the Frame
    --> We've added the TextArea
    --> We created the Menu Bar
    --> we created the Menu's - 'FILE', 'EDIT' and 'CLOSE'
    --> And created the Menu Items like 'SAVE', 'PRINT', 'CUT' and 'COPY'
    --> Added Action Listener to the Menu Items
    --> Added Menu Item to the Menu and
    --> Added Menu's to the Menu Bar
    --> Added Menu Bar to the Frame
    --> Added TextArea to the Frame
    --> Then we've added all the functionalities that are 'CUT', 'COPY', 'PASTE', 'PRINT'
    --> We've used Java Swing in-built functions for adding functionalities to 'OPEN' and 'SAVE'
 */



import java.awt.*; //Java Awt Library
import javax.swing.*; //Java Swing Library
import java.io.*; //Used for showing in-built windows which are Open File Window, Save File Window
import java.awt.event.*; //To implement Action Listener(Listens to mouse inputs)
import javax.swing.plaf.metal.*; //Theme for the App
import javax.swing.text.*; //Providing TextArea Functionality

class editor extends JFrame implements ActionListener{

    JFrame f;

    JTextArea t;

    editor(){

        //Initializes the frame with the title
        f = new JFrame("Notepad");


        //setting the overall theme of app
        //using try & catch because if it fails to get the theme file
        //then code will not be interrupted
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }

        catch(Exception e){

        }


        //creates the text component
        t = new JTextArea();

        //creates menu
        JMenuBar menu = new JMenuBar();

        //creates menu
        JMenu file = new JMenu("File");

        //creates menuItem
        JMenuItem m1 = new JMenuItem("New");
        JMenuItem m2 = new JMenuItem("Open");
        JMenuItem m3 = new JMenuItem("Save");
        JMenuItem m4 = new JMenuItem("Print");

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



        //creates menu
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(this);

        
        //adding menu
        menu.add(file);
        menu.add(edit);
        menu.add(close);

        //setting menu to the frame
        f.setJMenuBar(menu);
        //adding textArea
        f.add(t);

        //sets JTextArea font and color.
        Font font = new Font("Time New Roman", Font.PLAIN, 30);
        t.setFont(font);
        t.setForeground(Color.BLACK);


        //setting default frame size
        f.setSize(720, 480);

        //display the Frame
        f.setVisible(true);


    }


    //whenever a button pressed, action listener calls this actionPerformed function
    public void actionPerformed(ActionEvent e){

        //extracting the button user has pressed
        String s = e.getActionCommand();


        if(s.equals("New")){
            t.setText("");
        }

        else if(s.equals("Open")){

            //initializing the JFileChooser with desired directory
            //j contains the path of the choosen file
            JFileChooser j = new JFileChooser("C:/Users/DELL/Desktop");

            //invoking the openDialogBox with an integer
            //initializing the integer with state of DialogBox
            //showOpenDialog(f) --> Here OpenDialogBox opens in centre on the frame f
            //showOpenDialog(null) --> Here OpenDialogBox opens in centre of the screen, bcoz null is given
            int r = j.showOpenDialog(f);

            //when user selected the file on fileChooser
            if(r == JFileChooser.APPROVE_OPTION){

                //gets the absolute path of file like C:/Users/Desktop/file.txt
                File fi = new File(j.getSelectedFile().getAbsolutePath());


                try {
                    //string to copy data from the chosen file
                    String s1 = "", s2 = "";

                    //FileReader
                    FileReader fr = new FileReader(fi);
                    
                    //BufferedReader is used because FileReader reads file character by character
                    //which is very slow so BufferedReader is used to read the file line by line

                    //Reads the file line by line
                    BufferedReader br = new BufferedReader(fr);

                    //taking file text input in string s1,s2 line by line using BufferedReader
                    //storing the first line in string s2 and second line in s1
                    s2 = br.readLine();
                    s1 = br.readLine();

                    while(s1 != null){
                        s2 = s2 + "\n" + s1;
                        s1 = br.readLine();
                    }

                    //Replaces the textArea text with string s2
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
                    //FileWriter takes text from BufferedWriter and writes the file
                    //append: false --> bcoz we don't want to append new text to old text
                    //we just want to replace the old text with new
                    FileWriter fw = new FileWriter(fi, false);

                    //gives text to the FileWriter
                    BufferedWriter bw = new BufferedWriter(fw);

                    //takes text from TextArea character by character
                    bw.write(t.getText());

                    //after writing is finished flush/clear the stream of characters
                    bw.flush();

                    //after flushing close the BufferWriter
                    //so, that next time the new BufferWriter will be created
                    bw.close();

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(f, e1.getMessage());
                }


            }
        }

        else if(s.equals("Print")){
            //try catch is used to catch the error if printer
            //doesn't available
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