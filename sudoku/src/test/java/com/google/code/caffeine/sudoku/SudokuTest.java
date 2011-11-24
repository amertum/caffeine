package com.google.code.caffeine.sudoku;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.ArrayTable.create;
import static com.google.common.collect.DiscreteDomains.integers;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Ranges.closed;
import static org.fest.assertions.Assertions.assertThat;

public class SudokuTest {

    @Test
    public void testSolve() throws Exception {
        final int[][] array =
                {
                        {4, 3, 8,   7, 6, 0,   1, 0, 2},
                        {2, 0, 0,   0, 9, 0,   5, 3, 0},
                        {0, 0, 0,   0, 0, 2,   6, 0, 8},

                        {0, 0, 4,   0, 2, 3,   0, 5, 0},
                        {3, 0, 0,   0, 0, 0,   8, 0, 0},
                        {6, 0, 0,   0, 0, 0,   0, 0, 0},

                        {0, 0, 5,   0, 1, 0,   3, 0, 9},
                        {0, 1, 0,   0, 0, 0,   0, 8, 0},
                        {9, 0, 0,   6, 0, 0,   0, 7, 0},
                };

        final ArrayTable<Integer, Integer, Integer> board = create(newArrayList(closed(1, 9).asSet(integers())), newArrayList(closed(1, 9).asSet(integers())));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final Integer value = (array[i][j] == 0) ? null : array[i][j];
                board.set(i, j, value);
            }
        }

        final Sudoku sudoku = new Sudoku(board);
        assertThat(sudoku.getRow(3)).containsExactly(null, null, null, null, null, 2, 6, null, 8);
        assertThat(sudoku.getColumn(4)).containsExactly(7, null, null, null, null, null, null, null, 6);
        assertThat(sudoku.getSquare(3)).containsExactly(1, null, 2, 5, 3, null, 6, null, 8);
        assertThat(sudoku.getSquare(5)).containsExactly(null, 2, 3, null, null, null, null, null, null);
        assertThat(sudoku.getSquare(7)).containsExactly(null, null, 5, null, 1, null, 9, null, null);

        assertThat(sudoku.getSquareIndex(1, 1)).isEqualTo(1);
        assertThat(sudoku.getSquareIndex(3, 3)).isEqualTo(1);
        assertThat(sudoku.getSquareIndex(4, 4)).isEqualTo(5);
        assertThat(sudoku.getSquareIndex(7, 2)).isEqualTo(3);
        assertThat(sudoku.getSquareIndex(2, 7)).isEqualTo(7);
        assertThat(sudoku.getSquareIndex(7, 7)).isEqualTo(9);

        assertThat(sudoku.getPossibles(1, 1)).isEmpty();
        assertThat(sudoku.getPossibles(2, 2)).containsOnly(6, 7);
        assertThat(sudoku.getImpossibles(2, 2)).containsOnly(1, 2, 3, 4, 5, 8, 9);

        System.out.println(sudoku.toString());

        System.out.println("solving...");
        sudoku.solve();

        System.out.println("\n" + sudoku.toString());
    }

}
