package gq.gouq;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;

/**
 * Created by Mega
 * Intellij IDEA
 */
public class ClickItAuto extends JFrame{

    public JButton changeKeyButton;
    public JPanel panel;
    public JLabel key;
    public JLabel buttonDownFor;
    public JSlider clickQualitySlider;
    public JButton donateButton;
    public JCheckBox isActiveCheckBox;
    public JLabel waitBetweenFor;
    public JComboBox mouseButtonComboBox;
    public JSpinner cpsSpinner;
    private JTabbedPane tabbedPane1;
    public JRadioButton holdRadioButton;
    private JTextArea thisFreeSoftwareIsTextArea;
    private JButton sourceforgeSiteButton;
    private JButton sourceCodeButton;
    public JComboBox clicksPerComboBox;
    public JSpinner clickAmountSpinner;

    public ClickItAuto(){
        super("Click It Auto");
        setContentPane(panel);
        pack();
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        changeKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.isKeyRequested = true;
                key.setText("...");
            }
        });

        donateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Desktop.isDesktopSupported())
                {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=G47UDT9NVW3EW"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        sourceforgeSiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Desktop.isDesktopSupported())
                {
                    try {
                        Desktop.getDesktop().browse(new URI("https://sourceforge.net/u/gouqgq/profile/"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        sourceCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Desktop.isDesktopSupported())
                {
                    try {
                        Desktop.getDesktop().browse(new URI("https://github.com/GouqGq/ClickItAuto/"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        thisFreeSoftwareIsTextArea.setText("This free software is brought to you by Gouq.gq, please consider donating to us by clicking the button down below. Alwas think of the license which was enclosed to this software (LICENSE.txt)");

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();


        setDefaultLookAndFeelDecorated(true);
        setResizable(true);

        setVisible(true);

        pack();
    }

    private void createUIComponents() {
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
        cpsSpinner = new JSpinner(model);
        SpinnerNumberModel model1 = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        clickAmountSpinner = new JSpinner(model1);
    }
}
