package com.example.gameoflife.console;
import com.example.gameoflife.core.Board;
import com.example.gameoflife.core.DisplayDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;


public class Display {
    public static DisplayDriver getDriver() {
        DisplayDriver driver;
        
        try {
            driver = new EclipseDriver();
        } catch (AWTException e) {
            // TODO: implement a better fall-back
            driver = new ConsoleDriver();
        }
        
        return driver;
    }
    
    private Display() {}

    private static class EclipseDriver extends ConsoleDriver {
        private final Robot eclipse;
        
        public EclipseDriver() throws AWTException {
            eclipse = new Robot();
        }
        
        public void displayBoard(Board board) {
            cleanConsole();
            super.displayBoard(board);
        }
        
        private void cleanConsole() {
            eclipse.keyPress(KeyEvent.VK_SHIFT);
            eclipse.keyPress(KeyEvent.VK_F10);
            eclipse.keyRelease(KeyEvent.VK_SHIFT);
            eclipse.keyRelease(KeyEvent.VK_F10);
            eclipse.keyPress(KeyEvent.VK_R);
            eclipse.keyRelease(KeyEvent.VK_R);
        }
    }
}