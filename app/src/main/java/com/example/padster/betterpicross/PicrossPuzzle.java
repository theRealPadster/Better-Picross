package com.example.padster.betterpicross;/*
/*
* Copyright (c) 2016 Michael Salter
* Email: salterm@pdx.edu
* This program is available under the "MIT license". Please see the LICENSE file for the full text.
* This file may not be copied, modified, or distributed except according to the terms of said license.
*/

import java.util.*;

class PicrossPuzzle {
    ArrayList<Vector<Integer>> rows;
    ArrayList<Vector<Integer>> columns;
    final int height;
    final int width;

    PicrossPuzzle(final int height, final int width) {
        this.height = height;
        this.width = width;
        rows = new ArrayList<>(height);
        columns = new ArrayList<>(width);
    }

    private boolean isRowSatisfied(final PicrossCanvas puzzle, final int row) {
        //List of blocks existing in the puzzle
        Vector<Integer> puzzleRow = new Vector<>();

        int currentBlockLength = 0;
        //Iterate through the row
        for (int column = 0; column < width; column++) {
            switch (puzzle.getSquare(row, column)) {
                case VOID:
                    return false;
                case WHITE:
                    if (currentBlockLength > 0) {
                        puzzleRow.add(currentBlockLength);
                        currentBlockLength = 0;
                    }
                    break;
                case BLACK:
                    currentBlockLength++;
                    break;
            }
        }
        //If there was a block that ended at the last column, add it to the list
        if (currentBlockLength > 0) {
            puzzleRow.add(currentBlockLength);
        }

        //If the lengths, count, and order of blocks in the puzzle match that of the clues for the row
        return rows.get(row).equals(puzzleRow);
    }

    boolean isRowSatisfied(final ArrayList<PicrossCanvas.Colors> submittedRow, final int row) {
        //List of blocks existing in the puzzle
        Vector<Integer> puzzleRow = new Vector<>();

        int currentBlockLength = 0;
        //Iterate through the row
        for (PicrossCanvas.Colors aSubmittedRow : submittedRow) {
            switch (aSubmittedRow) {
                case VOID:
                    return false;
                case WHITE:
                    if (currentBlockLength > 0) {
                        puzzleRow.add(currentBlockLength);
                        currentBlockLength = 0;
                    }
                    break;
                case BLACK:
                    currentBlockLength++;
                    break;
            }
        }
        //If there was a block that ended at the last column, add it to the list
        if (currentBlockLength > 0) {
            puzzleRow.add(currentBlockLength);
        }

        //If the lengths, count, and order of blocks in the puzzle match that of the clues for the row
        return rows.get(row).equals(puzzleRow);
    }

    private boolean isColumnSatisfied(final PicrossCanvas puzzle, final int column) {
        //List of blocks existing in the puzzle
        Vector<Integer> puzzleColumn = new Vector<>();

        int currentBlockLength = 0;
        //Iterate through the row
        for (int row = 0; row < height; row++) {
            switch (puzzle.getSquare(row, column)) {
                case VOID:
                    return false;
                case WHITE:
                    if (currentBlockLength > 0) {
                        puzzleColumn.add(currentBlockLength);
                        currentBlockLength = 0;
                    }
                    break;
                case BLACK:
                    currentBlockLength++;
                    break;
            }
        }
        //If there was a block that ended at the last row, add it to the list
        if (currentBlockLength > 0) {
            puzzleColumn.add(currentBlockLength);
        }

        //If the lengths, count, and order of blocks in the puzzle match that of the clues for the column
        return columns.get(column).equals(puzzleColumn);
    }

    boolean isPuzzleSatisfied(final PicrossCanvas puzzle) {
        //If the clues are satisfied in every row...
        for (int row = 0; row < height; row++) {
            if (!isRowSatisfied(puzzle, row)) {
                return false;
            }
        }
        //...and in every column...
        for (int column = 0; column < width; column++) {
            if (!isColumnSatisfied(puzzle, column)) {
                return false;
            }
        }
        //...then the puzzle is solved!
        return true;
    }
}