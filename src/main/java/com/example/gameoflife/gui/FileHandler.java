package com.example.gameoflife.gui;

import com.example.gameoflife.core.Board;
import com.example.gameoflife.core.Cell;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {

    public static Board openFromFile(int defaultSize) {
        File file = askForOpenFile();
        if (file == null) {
            return null;
        }
        
        return loadFromFile(file, defaultSize);
    }
    
    public static Board loadFromFile(File file, int defaultSize) {
        StringBuilder input = new StringBuilder();
        int sz = defaultSize;
        try (Scanner s = new Scanner(file)) {
            while (s.hasNextLine()) {
                String line = s.nextLine().replaceAll("\\s+","");
                input.append(line);
                
                sz = line.length();
            }
        } catch (FileNotFoundException e) {
            // should never happen since we return on null file
            // so if we end up here it's something really bad 
            // and so we let it blow up to runtime
            throw new RuntimeException(e);
        }

        Cell[][] g = new Cell[sz][sz];

        int pos = 0;
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                char state = input.charAt(pos);
                switch (state) {
                    case '1' -> g[i][j] = new Cell(Cell.CellState.LEVEL_1);
                    case '2' -> g[i][j] = new Cell(Cell.CellState.LEVEL_2);
                    case '3' -> g[i][j] = new Cell(Cell.CellState.LEVEL_3);
                    default -> g[i][j] = new Cell(Cell.CellState.OFF);
                }
                pos++;
            }
        }

        return new Board(g);        
    }
    
    public static void saveToFile(Board board) throws IOException {
        File file = askForSaveFile();
        if (file == null) {
            return;
        }
        
        StringBuilder output = new StringBuilder(); // string of numbers from board
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                output.append(board.getCell(i,j).getState().getLevelCode());
            }
            if (i != board.getWidth()-1) {
                output.append("\n");
            }
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(output.toString());
        }
    }

    private static File askForSaveFile() {
        return getFileChooser().showSaveDialog(new Stage());
    }
    
    private static File askForOpenFile() {
        return getFileChooser().showOpenDialog(new Stage());
    }
    
    private static FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Game of Life Board File");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("GOFB files (*.gofb)", "*.gofb"));
        
        return fileChooser;
    }
}
