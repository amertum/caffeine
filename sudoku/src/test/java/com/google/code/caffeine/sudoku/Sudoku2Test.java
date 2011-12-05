package com.google.code.caffeine.sudoku;

import com.google.common.collect.ArrayTable;
import org.junit.Test;

import static com.google.common.collect.ArrayTable.create;
import static com.google.common.collect.DiscreteDomains.integers;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Ranges.closed;
import static org.fest.assertions.Assertions.assertThat;

public class Sudoku2Test {

    @Test
    public void testSolveHard() throws Exception {
        final Sudoku2 sudoku = new Sudoku2(makeTable(HARD));
        System.out.println(sudoku.toString());

        System.out.println(sudoku.getBoard().get(4, 5).rowValues());
        assertThat(sudoku.getBoard().get(4, 5).rowValues()).containsOnly(2, 3, 4, 5);
        System.out.println(sudoku.getBoard().get(4, 5).columnValues());
        assertThat(sudoku.getBoard().get(4, 5).columnValues()).containsOnly(1, 2, 6, 9);
        System.out.println(sudoku.getBoard().get(4, 5).squareValues());
        assertThat(sudoku.getBoard().get(4, 5).squareValues()).containsOnly(2, 3);

        System.out.println("\n" + sudoku.possiblesToString());

        System.out.println("solving...");
        sudoku.solve();

        System.out.println("\n" + sudoku.toString());
    }

    @Test
    public void testSolveHard2() throws Exception {
        final Sudoku2 sudoku = new Sudoku2(makeTable(HARD2));
        System.out.println(sudoku.toString());

        System.out.println("possiblesToString\n" + sudoku.possiblesToString());
        System.out.println("remainersToString\n" + sudoku.remainersToString());

        final int MAX = 10;
        int i = 1;
        boolean solved = false;
        do {
            System.out.println("solving..." + i++);
            solved = sudoku.solve();
            System.out.println("\n" + sudoku.toString());
        } while (!solved && i <= MAX);

        System.out.println("possiblesToString\n" + sudoku.possiblesToString());
        System.out.println("remainersToString\n" + sudoku.remainersToString());
    }

    private static ArrayTable<Integer, Integer, Integer> makeTable(int[][] array) {
        final ArrayTable<Integer, Integer, Integer> board = create(newArrayList(closed(1, 9).asSet(integers())), newArrayList(closed(1, 9).asSet(integers())));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final Integer value = (array[i][j] == 0) ? null : array[i][j];
                board.set(i, j, value);
            }
        }

        return board;
    }

    private static final int[][] HARD2 =
            {
                    {0, 0, 4, 0, 9, 0, 0, 0, 0},
                    {1, 0, 5, 0, 0, 0, 0, 0, 0},
                    {0, 2, 8, 0, 0, 0, 5, 3, 0},

                    {0, 0, 0, 9, 0, 7, 2, 0, 0},
                    {2, 9, 0, 0, 3, 0, 0, 8, 0},
                    {0, 1, 0, 0, 0, 0, 0, 4, 0},

                    {5, 4, 0, 0, 0, 9, 0, 2, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 2, 8, 0, 6},
            };

    private static final int[][] HARD =
            {
                    {4, 3, 8, 7, 6, 0, 1, 0, 2},
                    {2, 0, 0, 0, 9, 0, 5, 3, 0},
                    {0, 0, 0, 0, 0, 2, 6, 0, 8},

                    {0, 0, 4, 0, 2, 3, 0, 5, 0},
                    {3, 0, 0, 0, 0, 0, 8, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0},

                    {0, 0, 5, 0, 1, 0, 3, 0, 9},
                    {0, 1, 0, 0, 0, 0, 0, 8, 0},
                    {9, 0, 0, 6, 0, 0, 0, 7, 0},
            };

    private static final int[][] EASY =
            {
                    {0, 0, 3, 0, 0, 4, 7, 0, 0},
                    {0, 0, 0, 9, 2, 0, 0, 0, 0},
                    {8, 0, 0, 0, 0, 5, 9, 0, 2},

                    {6, 7, 0, 0, 0, 0, 0, 8, 3},
                    {4, 3, 0, 0, 8, 1, 5, 0, 0},
                    {0, 8, 0, 0, 0, 3, 6, 2, 0},

                    {0, 6, 4, 0, 0, 0, 1, 0, 7},
                    {1, 0, 0, 0, 4, 0, 2, 0, 0},
                    {2, 9, 0, 0, 0, 0, 0, 0, 5},
            };

}
