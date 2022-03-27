package com.example.gameoflife.core;

import com.example.gameoflife.core.Cell.CellState;

public class Board {
    private final Cell[][] grid;
    private final int height;
    private final int width;

    private final CellStateRule cellStateRule = CellStateRuleFactory.getCellStateRule(this);

    public Board(Cell[][] grid) {
        this.grid = grid;
        height = width = grid.length;
    }

    /**
     * @param height Number of rows in the board
     * @param width  Number of columns in the board
     * @param p      probability that Cell is alive at start
     */
    public Board(int height, int width, double p) {
        this.height = height;
        this.width = width;
        grid = new Cell[height][width];

        for (int h = 0; h < grid.length; h++) {
            for (int w = 0; w < grid[h].length; w++) {
                grid[h][w] = new Cell();
                if (Math.random() <= p) {
                    grid[h][w].setNewState(CellState.LEVEL_3);
                    grid[h][w].updateState();
                }
            }
        }
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row > width || col < 0 || col > height) {
            throw new RuntimeException(String.format("Illegal board cell access. Row:[%d], Col:[%d]", row, col));
        }

        return new Cell(grid[row][col]);
    }

    public int getSize() {
        return width;
    }

    public void update() {
        prepare();
        commit();
    }

    private void prepare() {
        for (int h = 0; h < grid.length; h++) {
            for (int w = 0; w < grid[h].length; w++) {
                int vitalityLevel = cellStateRule.getVitalityLevel(this, h, w);
                Cell cell = grid[h][w];
                cell.setNewState(cellStateRule.calculateCellState(this, cell.getState(), vitalityLevel));
            }
        }
    }

    /**
     * Updates Cell state based on newState
     */
    private void commit() {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                cell.updateState();
            }
        }
    }

    public CellStateRule getCellStateRule() {
        return cellStateRule;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
