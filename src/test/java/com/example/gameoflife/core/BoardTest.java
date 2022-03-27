package com.example.gameoflife.core;

import org.junit.jupiter.api.Test;

import static com.example.gameoflife.core.Cell.CellState.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
   
    @Test
    public void testCellRule() {
        Cell[][] cells = {
            {new Cell(LEVEL_1), new Cell(LEVEL_1), new Cell(LEVEL_1)},
            {new Cell(LEVEL_1), new Cell(LEVEL_1), new Cell(LEVEL_1)},
            {new Cell(LEVEL_1), new Cell(LEVEL_1), new Cell(LEVEL_1)}
        };
        Board b = new Board(cells);

        assertEquals(3, b.getCellStateRule().getVitalityLevel(b,0,0));
        assertEquals(8, b.getCellStateRule().getVitalityLevel(b,1,1));
        assertEquals(5, b.getCellStateRule().getVitalityLevel(b,1,0));
    }

    @Test
    public void testAllCellsDie() {
        Cell[][] cells = {
            {new Cell(LEVEL_3), new Cell(OFF), new Cell(LEVEL_3)},
            {new Cell(OFF), new Cell(LEVEL_3), new Cell(OFF)},
            {new Cell(LEVEL_3), new Cell(), new Cell(LEVEL_3)}
        };

        Board b = new Board(cells);

        assertEquals(3, b.getCellStateRule().getVitalityLevel(b,0, 0));
        assertEquals(12, b.getCellStateRule().getVitalityLevel(b,1, 1));

        b.update();

        assertEquals(0, b.getCellStateRule().getVitalityLevel(b,0, 0));
        assertEquals(0, b.getCellStateRule().getVitalityLevel(b,2, 2));
        assertEquals(0, b.getCellStateRule().getVitalityLevel(b,2, 0));
        assertEquals(0, b.getCellStateRule().getVitalityLevel(b,0, 2));
        assertEquals(12, b.getCellStateRule().getVitalityLevel(b,1, 1));
    }

    @Test
    public void testGridStaysTheSame() {
        Cell[][] cells = {
            {new Cell(LEVEL_1), new Cell(LEVEL_1), new Cell()},
            {new Cell(LEVEL_1), new Cell(LEVEL_1), new Cell()},
            {new Cell(), new Cell(), new Cell()}
        };

        Board b = new Board(cells);

        b.update();

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                assertEquals(cells[i][j].getState(), b.getCell(i,j).getState());
            }
        }
    }

    @Test
    public void testOverpopulationAndIsBorn() {
        Cell[][] cells = {
            {new Cell(LEVEL_1), new Cell(LEVEL_1), new Cell(LEVEL_1)},
            {new Cell(LEVEL_1), new Cell(LEVEL_1), new Cell()},
            {new Cell(), new Cell(), new Cell()}
        };   
        
        Board b = new Board(cells);
        
        b.update();

        assertEquals(6, b.getCellStateRule().getVitalityLevel(b,0, 0));
        assertEquals(5, b.getCellStateRule().getVitalityLevel(b,0, 2));
        assertEquals(6, b.getCellStateRule().getVitalityLevel(b,1, 0));
        assertEquals(4, b.getCellStateRule().getVitalityLevel(b,1, 2));

        assertEquals(7, b.getCellStateRule().getVitalityLevel(b,0, 1));
        assertEquals(7, b.getCellStateRule().getVitalityLevel(b,1, 1));
    }
}
