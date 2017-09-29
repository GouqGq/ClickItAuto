package gq.gouq;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Mega
 * Intellij IDEA
 */
public class Main {

    public static int keyCode = KeyEvent.VK_C;
    public static double clicks;
    public static int clicks_done;
    public static ClickItAuto clickItAuto;
    public static boolean isKeyRequested = false;
    public static boolean keyPress = false;
    public static boolean keyActive = false;
    public static long lastTimeClicked = System.currentTimeMillis();


    static Robot bot = null;


    public static void main(String[] args){
        try {
            GlobalScreen.registerNativeHook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            bot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);


        clickItAuto = new ClickItAuto();

        while (true) {

            clicks = (int) clickItAuto.cpsSpinner.getValue();

            if (clickItAuto.clicksPerComboBox.getSelectedItem().equals("second")) {

            } else if (clickItAuto.clicksPerComboBox.getSelectedItem().equals("minute")) {
                clicks = clicks * (1.0/60.0);
            } else if (clickItAuto.clicksPerComboBox.getSelectedItem().equals("hour")) {
                clicks = clicks * (1.0/60.0) * (1.0/60.0);
            } else if (clickItAuto.clicksPerComboBox.getSelectedItem().equals("day")) {
                clicks = clicks * (1.0/60.0) * (1.0/60.0) * (1.0/24.0);
            }

            if(clicks < 0)
                clicks = 1;
            
            double wait_ms = Math.round(((1000.0 / clicks) * (100 - (double)clickItAuto.clickQualitySlider.getValue())) * 10.0) / 1000.0;
            double down_ms = Math.round(((1000.0 / clicks) * (double)clickItAuto.clickQualitySlider.getValue()) * 10.0) / 1000.0;
            
            clickItAuto.waitBetweenFor.setText("Wait " + wait_ms + " ms between every click");
            clickItAuto.buttonDownFor.setText("Hold mouse button down for " + down_ms + " ms");

            if(!keyPress && !keyActive) {
                lastTimeClicked = System.currentTimeMillis();
                clicks_done = 0;
            } else if(keyPress && keyActive) {
                keyPress = clickItAuto.holdRadioButton.isSelected();
                keyActive = !clickItAuto.holdRadioButton.isSelected();
            }


            if(System.currentTimeMillis() - lastTimeClicked > ((1000 / clicks) * clickItAuto.clickQualitySlider.getValue()) / 100) {
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

                if(System.currentTimeMillis() - lastTimeClicked > 1000 / clicks && ((int)clickItAuto.clickAmountSpinner.getValue() == 0 || clicks_done < (int)clickItAuto.clickAmountSpinner.getValue())) {
                    if (clickItAuto.mouseButtonComboBox.getSelectedItem().equals("Left click")) {
                        bot.mousePress(InputEvent.BUTTON1_MASK);
                    } else if (clickItAuto.mouseButtonComboBox.getSelectedItem().equals("Middle click")) {
                        bot.mousePress(InputEvent.BUTTON2_MASK);
                    } else if (clickItAuto.mouseButtonComboBox.getSelectedItem().equals("Right click")) {
                        bot.mousePress(InputEvent.BUTTON3_MASK);
                    }
                    lastTimeClicked = System.currentTimeMillis();
                    clicks_done++;
                }
            }
            try{
                if(!keyPress && !keyActive) {
                    Thread.sleep(200);
                } else {
                    Thread.sleep((int)Math.max(Math.min(wait_ms, down_ms) - 2, 1.0);
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            if(!clickItAuto.isVisible()){
                bot.mouseRelease(InputEvent.BUTTON1_MASK);
                bot.mouseRelease(InputEvent.BUTTON2_MASK);
                bot.mouseRelease(InputEvent.BUTTON3_MASK);
                System.exit(0);
            }
        }
    }
}
