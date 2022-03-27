package com.example.gameoflife.core;

import com.example.gameoflife.core.Cell.CellState;

public interface CellStateRule {

    int getVitalityLevel(Board board, int row, int col);
    CellState calculateCellState(Board board, CellState previousCellState, int vitalityLevel);
}
