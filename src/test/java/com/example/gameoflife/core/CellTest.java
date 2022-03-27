package com.example.gameoflife.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellTest {

    @Test
    public void testUpdateState() {
        Cell c = new Cell();

        c.setNewState(Cell.CellState.LEVEL_1);
        c.updateState();
        assertEquals(Cell.CellState.LEVEL_1, c.getState());
    }

    @Test
    public void testConstructor() {
        Cell c = new Cell(Cell.CellState.LEVEL_1);

        assertEquals(Cell.CellState.LEVEL_1, c.getState());
    }
}
