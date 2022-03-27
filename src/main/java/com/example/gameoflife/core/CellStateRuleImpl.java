package com.example.gameoflife.core;

import com.example.gameoflife.core.Cell.CellState;

public class CellStateRuleImpl implements  CellStateRule {

    /**
     * Walks through the array of cells calculating the vitality level for each cell. The vitality level of a
     * given cell is the sum of all surrounding cell levels. For instance, given a cell at position x,y in the array,
     * referred to as 'C' in the diagram below, the vitality level of cell 'C' is the sum of the levels of the
     * cells 1 though 9, excluding 'C' itself.
     * <p>
     * <p>
     * +-----------+
     * | 1 | 2 | 3 |
     * +-----------+
     * | 4 | C | 6 |
     * +-----------+
     * | 7 | 8 | 9 |
     * +-----------+
     *
     * @param row row index
     * @param col column index
     * @return vitality level
     */
    public int getVitalityLevel(Board board, int row, int col) {
        int sum = 0;

        for (int x = col - 1; x <= col + 1; x++) {
            for (int y = row - 1; y <= row + 1; y++) {
                if (x < 0 || x >= board.getWidth() || y < 0 || y >= board.getHeight()) {
                    continue;
                }
                // Skip self
                if (x == col && y == row) {
                    continue;
                }

                sum += getCellLevel(board, y, x);
            }
        }

        return sum;
    }



    private int getCellLevel(Board board, int row, int col) {
        return board.getCell(row, col).getState().getLevelCode();
    }


    public CellState calculateCellState(Board board, CellState previousCellState, int vitalityLevel) {
        if (vitalityLevel < 3 || vitalityLevel > 9) {
            return CellState.OFF;
        }

        CellState newCellState;
        int totalVitality = previousCellState.getLevelCode() + vitalityLevel;
        if (totalVitality < 6) {
            newCellState = previousCellState.nextState();
        } else if (totalVitality > 9) {
            newCellState = previousCellState.prevState();
        } else {
            newCellState = previousCellState;
        }

        return newCellState;
    }
}
