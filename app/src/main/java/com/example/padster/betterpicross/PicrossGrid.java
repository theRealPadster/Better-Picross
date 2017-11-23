package com.example.padster.betterpicross;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by padster on 05/10/17.
 */

public class PicrossGrid {
    public enum Colours {WHITE, BLACK};

    private int numRows = 0;
    private int numCols = 0;

    private Colours[][] grid;
    private ArrayList<Vector<Integer>> clues;

    public PicrossGrid(int rows, int cols) {
        if (rows > 0) {
            this.numRows= rows;
        }

        if (cols > 0) {
            this.numCols = cols;
        }

        grid = new Colours[numRows][numCols];

        for (Colours[] ca : grid) {
            Arrays.fill(ca, Colours.WHITE);
        }

        //TODO - is this populated now?

        for (int r = 0; r < numRows; r++) {

            for (int c = 0; c < numCols; c++) {
                if (randomWithRange(0,4) == 0) {
                    grid[r][c] = Colours.BLACK;
                }
            }
        }

        //TODO - now ~1/4 of them are filled?
    }

    private int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public Colours getTile(int row, int col) {
        return grid[row][col];
    }

    public void setTile(int row, int col, Colours colour) {
        grid[row][col] = colour;
    }



}
