package com.example.gameoflife.console;


import com.example.gameoflife.core.Board;
import com.example.gameoflife.core.Cell;
import com.example.gameoflife.core.DisplayDriver;

import static java.lang.System.*;

public class ConsoleDriver implements DisplayDriver {
    public void displayBoard(Board board) {
        String border = String.format("+%0" + 2*board.getWidth() + "d+", 0).replace("0","-");
        
        out.println(border);

        for (int i=0; i<board.getWidth(); i++) {
            StringBuilder r = new StringBuilder("|");
            for (int j=0; j<board.getHeight(); j++) {
                Cell cell = board.getCell(i, j);
                r.append(cell.getState().getLevelCode()).append(" ");
            }
            r.append("|");
            out.println(r);
        }
        
        out.println(border);
    }
}
