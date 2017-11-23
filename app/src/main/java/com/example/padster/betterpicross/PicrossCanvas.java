package com.example.padster.betterpicross;/*
* Copyright (c) 2016 Michael Salter
* Email: salterm@pdx.edu
* This program is available under the "MIT license". Please see the LICENSE file for the full text.
* This file may not be copied, modified, or distributed except according to the terms of said license.
*/

import java.util.*;

class PicrossCanvas {
    enum Colors {VOID, WHITE, BLACK}

    private final int height;
    private final int width;

    private Colors[][] puzzle;

    PicrossCanvas(final int height, final int width) {
        if (height <= 0) {
            throw new IllegalArgumentException("Invalid value for puzzle height.");
        } else {
            this.height = height;
        }
        if (width <= 0) {
            throw new IllegalArgumentException("Invalid value for puzzle width.");
        } else {
            this.width = width;
        }

        puzzle = new Colors[height][width];

        for (Colors[] ca : puzzle) {
            Arrays.fill(ca, Colors.VOID);
        }
    }

    PicrossCanvas(PicrossCanvas other) {
        this.height = other.height;
        this.width = other.width;
        puzzle = new Colors[height][width];
        for (int row = 0; row < this.height; row++) {
            System.arraycopy(other.puzzle[row], 0, this.puzzle[row], 0, this.width);
        }
    }

    void fillSquare(final int row, final int column, final Colors c) {
        if (row < 0 || row >= height || column < 0 || column >= width) {
            throw new IllegalArgumentException("Invalid row/column dimension!");
        } else {
            puzzle[row][column] = c;
        }
    }

    void fillRow(final int row, final ArrayList<Colors> ca) {
        if (row < 0 || row >= height || ca.size() < 1 || ca.size() > width) {
            throw new IllegalArgumentException("Invalid row/column dimension!");
        } else {
            for (int column = 0; column < width; column++) {
                puzzle[row][column] = ca.get(column);
            }
        }
    }

    private void clearRow(final int row) {
        if (row < 0 || row >= height) {
            throw new IllegalArgumentException("Invalid row dimension!");
        } else {
            for (int column = 0; column < width; column++) {
                puzzle[row][column] = Colors.VOID;
            }
        }
    }

    void clearCanvas() {
        for (int row = 0; row < height; row++) {
            clearRow(row);
        }
    }

    Colors getSquare(final int row, final int column) {
        return puzzle[row][column];
    }

    void print() {
        StringBuilder sb = new StringBuilder();
        for (Colors[] ca : puzzle) {
            for (Colors c : ca) {
                switch (c) {
                    case VOID:
                        sb.append("?");
                        break;
                    case WHITE:
                        sb.append("░");
                        break;
                    case BLACK:
                        sb.append("▓");
                        break;
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}