package gq.gouq;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Mega
 * Intellij IDEA
 */
public class Main {

    public static int keyCode = KeyEvent.VK_C;
    public static double cps;
    public static ClickItAuto clickItAuto;
    public static boolean isKeyRequested = false;
    public static boolean keyPress = false;
    public static boolean keyActive = false;
    public static long lastTimeClicked = System.currentTimeMillis();

    static Robot bot = null;

    public static void mmain(String[] margs){
        try {
            GlobalScreen.registerNativeHook();
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            bot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);


        clickItAuto = new ClickItAuto();

        while (true) {

            cps = (double) clickItAuto.cpsSpinner.getValue();

            if(cps <= 0)
                cps = 1;

            clickItAuto.waitBetweenFor.setText("Wait " + Math.round(((1000.0 / cps) * (100 - (double)clickItAuto.clickQualitySlider.getValue())) * 10.0) / 1000.0 + " ms between every click");
            clickItAuto.buttonDownFor.setText("Hold mouse button down for " + Math.round(((1000.0 / cps) * (double)clickItAuto.clickQualitySlider.getValue()) * 10.0) / 1000.0 + " ms");

            if(!keyPress && !keyActive)
                lastTimeClicked = System.currentTimeMillis();

            if(System.currentTimeMillis() - lastTimeClicked > ((1000 / cps) * clickItAuto.clickQualitySlider.getValue()) / 100) {
                if (clickItAuto.mouseButtonComboBox.getSelectedItem().equals("Left click")) {
                    bot.mouseRelease(InputEvent.BUTTON1_MASK);
                } else if (clickItAuto.mouseButtonComboBox.getSelectedItem().equals("Middle click")) {
                    bot.mouseRelease(InputEvent.BUTTON2_MASK);
                } else if (clickItAuto.mouseButtonComboBox.getSelectedItem().equals("Right click")) {
                    bot.mouseRelease(InputEvent.BUTTON3_MASK);
                }
            }

            if(
                    (keyPress && clickItAuto.holdRadioButton.isSelected() && clickItAuto.isActiveCheckBox.isSelected())
                    ||
                    (keyActive && !clickItAuto.holdRadioButton.isSelected() && clickItAuto.isActiveCheckBox.isSelected())) {

                if(System.currentTimeMillis() - lastTimeClicked > 1000 / cps) {
                    if (clickItAuto.mouseButtonComboBox.getSelectedItem().equals("Left click")) {
                        bot.mousePress(InputEvent.BUTTON1_MASK);
                    } else if (clickItAuto.mouseButtonComboBox.getSelectedItem().equals("Middle click")) {
                        bot.mousePress(InputEvent.BUTTON2_MASK);
                    } else if (clickItAuto.mouseButtonComboBox.getSelectedItem().equals("Right click")) {
                        bot.mousePress(InputEvent.BUTTON3_MASK);
                    }
                    lastTimeClicked = System.currentTimeMillis();
                }
            }
        }
    }
}
