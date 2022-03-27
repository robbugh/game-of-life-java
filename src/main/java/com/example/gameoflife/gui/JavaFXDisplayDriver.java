package com.example.gameoflife.gui;

import com.example.gameoflife.core.Board;
import com.example.gameoflife.core.Cell;
import com.example.gameoflife.core.DisplayDriver;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class JavaFXDisplayDriver implements DisplayDriver {
    private final int sz;
    private final TilePane tilePane = new TilePane(5,5);

    public JavaFXDisplayDriver(int boardSize, int cellSizePx, Board board) {
        sz = boardSize;
        tilePane.setPrefRows(boardSize);
        tilePane.setPrefColumns(boardSize);

        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                Cell cell = board.getCell(i,j);
                Color color = getCellColor(cell.getState());
                Rectangle r = new Rectangle(cellSizePx, cellSizePx, color);
                tilePane.getChildren().add(r);
                attachListeners(r, cell);
            }
        }
    }

    @Override
    public void displayBoard(Board board) {
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                Cell cell = board.getCell(i, j);
                Rectangle r = (Rectangle) tilePane.getChildren().get(boardToPaneCoords(i, j));
                r.setFill(getCellColor(cell.getState()));
            }
        }
    }

    public TilePane getPane() {
        return tilePane;
    }

    private int boardToPaneCoords(int i, int j) {
        return i * sz + j;
    }
    
    private void attachListeners(Rectangle r, Cell c) {
        r.setOnMousePressed(e -> r.setFill(Color.GRAY));

        r.setOnMouseClicked(e -> {
            Cell.CellState newCellState = rotateCellState(c.getState());
            r.setFill(getCellColor(newCellState));
            c.setNewState(newCellState);
            c.updateState();
        });
    }

    private Cell.CellState rotateCellState(Cell.CellState previousCellState) {
        int cellLevel = (previousCellState.getLevelCode() + 1) % (Cell.CellState.LEVEL_3.getLevelCode() + 1);
        return previousCellState.toCellState(cellLevel);
    }

    private Color getCellColor(Cell.CellState cellState) {
        return switch (cellState) {
            case LEVEL_1 -> Color.CORNFLOWERBLUE;
            case LEVEL_2 -> Color.DARKGOLDENROD;
            case LEVEL_3 -> Color.DARKMAGENTA;
            default -> Color.WHITE;
        };
    }
}
