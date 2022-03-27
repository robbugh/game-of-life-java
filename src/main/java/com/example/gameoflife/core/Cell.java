package com.example.gameoflife.core;

import static com.example.gameoflife.core.Cell.CellState.*;

public class Cell {
    private CellState state = OFF;
    private CellState newState;

    public enum CellState {
        OFF(0), LEVEL_1(1), LEVEL_2(2), LEVEL_3(3);

        private final int levelCode;

        CellState(int levelCode) {
            this.levelCode = levelCode;
        }

        public CellState toCellState(int levelCode) {
            if (levelCode < 0 || levelCode > 3) {
                throw new RuntimeException("Invalid level code for CellState: " + levelCode);
            }

            return switch (levelCode) {
                case 1 -> LEVEL_1;
                case 2 -> LEVEL_2;
                case 3 -> LEVEL_3;
                default -> OFF;
            };
        }

        public int getLevelCode() {
            return levelCode;
        }

        public CellState prevState() {
            int nextLevelCode = levelCode - 1;
            if (nextLevelCode < 0) {
                nextLevelCode = 0;
            }

            return toCellState(nextLevelCode);
        }

        public CellState nextState() {
            int nextLevelCode = levelCode + 1;
            if (nextLevelCode > 3) {
                nextLevelCode = 3;
            }

            return toCellState(nextLevelCode);
        }
    }


    public Cell() {
    }

    public Cell(Cell orig) {
        state = orig.state;
        newState = orig.newState;
    }

    public Cell(CellState state) {
        this.state = state;
    }

    public void setNewState(CellState state) {
        newState = state;
    }

    public void updateState() {
        state = newState;
    }

    public CellState getState() {
        return state;
    }
}
