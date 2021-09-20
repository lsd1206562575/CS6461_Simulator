import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static javax.swing.BorderFactory.createLineBorder;

//GUI and its various components and buttons
public class CPUsimGUI extends JFrame {
    private JPanel mainPanel;
    private JButton lineButton;
    private JButton multiLineButton;
    private JButton loadButton;
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
    private JTextPane output;
    private JPanel memPanel;
    private JScrollPane memPanScroll;
    private JTable usedMem;

    Instruction data = new Instruction();
    //GUI buttons and their actions
    public CPUsimGUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        //set the text fields uneditable
        gpr1Val.setEditable(false);
        gpr2Val.setEditable(false);
        gpr3Val.setEditable(false);
        ixr1Val.setEditable(false);
        ixr2Val.setEditable(false);
        ixr3Val.setEditable(false);
        marVal.setEditable(false);
        mbrVal.setEditable(false);
        irVal.setEditable(false);
        mfrVal.setEditable(false);
        pcVal.setEditable(false);
        textField2.setEditable(false);

        //background colors
        gpr1Val.setBackground(Color.decode("#D6FAFF"));
        gpr2Val.setBackground(Color.decode("#D6FAFF"));
        gpr3Val.setBackground(Color.decode("#D6FAFF"));
        ixr1Val.setBackground(Color.decode("#D6FAFF"));
        ixr2Val.setBackground(Color.decode("#D6FAFF"));
        ixr3Val.setBackground(Color.decode("#D6FAFF"));
        marVal.setBackground(Color.decode("#D6FAFF"));
        mbrVal.setBackground(Color.decode("#D6FAFF"));
        irVal.setBackground(Color.decode("#D6FAFF"));
        mfrVal.setBackground(Color.decode("#D6FAFF"));
        pcVal.setBackground(Color.decode("#D6FAFF"));
        textField2.setBackground(Color.decode("#D6FAFF"));

        //show.setBorder(createLoweredBevelBorder());
        show.setBorder(createLineBorder(new Color(255,255,255),20));
        registerValue.setAlignmentX(20);

        //set output uneditable by users
        output.setEditable(false);

        usedMem.setVisible(false);

        setMomoryPanel();



        exitButton.addActionListener(new ActionListener() { //Exit button
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //load from file button action
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loadButton) {
                    JFileChooser loadFile = new JFileChooser();
                    int resp = loadFile.showOpenDialog(null);
                    if (resp == JFileChooser.APPROVE_OPTION) {
                        //load hex data and turn it into binary then store it into memory
                        File file = loadFile.getSelectedFile();
                        BufferedReader readFile = null;
                        try {
                            readFile = new BufferedReader(new FileReader(file));
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        String inputHexData;
                        while (true) {
                            try {
                                while (((inputHexData = readFile.readLine()) != null)) {
                                    String[] binaryData = null;
                                    binaryData = inputHexData.split(" ");
                                    int address = Integer.valueOf(binaryData[0], 16);
                                    String address_mem = Integer.toBinaryString(address);
                                    String value = Integer.toBinaryString(Integer.parseInt(binaryData[1],16));
                                    data.simulator_memory.putMem(address_mem,value);
                                    setMomoryPanel();
                                }
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    //Main
    public static void main(String[] args) {

        JFrame frame = new CPUsimGUI("CPU Simulator");
        frame.setVisible(true);
        CPUsimGUI cpuSim = new CPUsimGUI("simulator");
        cpuSim.setMomoryPanel();
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

    //set the content of memory panel
    public void setMomoryPanel() {
        final String[] columnNames = {"Address", "Value"};
        TableModel dataModel = new DefaultTableModel(data.simulator_memory.memoryArray, columnNames);
        usedMem.setModel(dataModel);
        System.out.println(data.simulator_memory.toString());
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
