import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//GUI and its various components and buttons
public class CPUsimGUI extends JFrame {
    private JPanel mainPanel;
    private JScrollPane memScroll;
    private JPanel memPanel;
    private JTable mem;
    private JButton lineButton;
    private JButton multiLineButton;
    private JButton loadButton;
    private JButton haltButton;
    private JButton restartButton;
    private JButton exitButton;
    private JPanel buttonPanel;
    private JPanel registerValue;
    private JTextPane Input;
    private JScrollPane inputScroll;
    private JPanel inputPanel;
    private JPanel show;
    private JLabel PC;
    private JLabel MAR;
    private JLabel MBR;
    private JLabel IR;
    private JTextField pcVal;
    private JTextField textField2;
    private JLabel gpr0;
    private JLabel gpr1;
    private JLabel gpr2;
    private JLabel gpr3;
    private JTextField marVal;
    private JTextField mbrVal;
    private JTextField irVal;
    private JTextField gpr1Val;
    private JTextField gpr2Val;
    private JTextField gpr3Val;
    private JTextField ixr1Val;
    private JTextField ixr2Val;
    private JTextField ixr3Val;
    private JLabel ixr1;
    private JLabel ixr2;
    private JLabel ixr3;
    private JLabel mfr;
    private JTextField mfrVal;
    private JTextArea Guide;

    //GUI buttons and their actions
    public CPUsimGUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        exitButton.addActionListener(new ActionListener() { //Exit button
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reset(e);
            }
        });
        //load from file button action
    }

    //Main
    public static void main(String[] args) {
        JFrame frame = new CPUsimGUI("CPU Simulator");
        frame.setVisible(true);
    }

    // Code for components for GUI button text area
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    private void addToInput(JTextPane textPane, String message, Color color){
        message = message+"\n";
        int length = textPane.getDocument().getLength();
        textPane.setCaretPosition(length);
        textPane.replaceSelection(message);
    }
            /*
        Set text in JTextArea line by line.
         */
            private void appendToPane(JTextPane tp, String msg, Color c)
            {
                msg = msg+"\n";
                StyleContext sc = StyleContext.getDefaultStyleContext();
                AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
                aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Andale Mono");
                aset = sc.addAttribute(aset,StyleConstants.FontSize,12);
                int len = tp.getDocument().getLength();
                tp.setCaretPosition(len);
                tp.setCharacterAttributes(aset, false);
                tp.replaceSelection(msg);
            }

}
