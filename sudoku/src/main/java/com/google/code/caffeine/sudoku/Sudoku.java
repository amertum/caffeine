package com.google.code.caffeine.sudoku;

import com.google.common.base.Joiner;
import com.google.common.collect.Table;

import java.util.List;
import java.util.Set;

import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.DiscreteDomains.integers;
import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Ranges.closed;
import static com.google.common.collect.Sets.*;

public class Sudoku {

    public Sudoku(
            final Table<Integer, Integer, Integer> board
    ) {
        this.board = board;
    }

    public void solve() {

    }

    public Set<Integer> getPossibles(int x, int y) {
        final Integer value = this.board.get(x, y);
        if (value != null) {
            return newTreeSet();
            // ? return newTreeSet(Sets.newHashSet(value));
        }

        final Set<Integer> rowValues = newTreeSet(filter(this.getRow(x), notNull()));
        final Set<Integer> columnValues = newTreeSet(filter(this.getColumn(x), notNull()));
        final Set<Integer> squareValues = newTreeSet(filter(this.getSquare(this.getSquareIndex(x, y)), notNull()));

        final Set<Integer> impossibles = newTreeSet(union(union(rowValues, columnValues), squareValues));
        return difference(closed(1, 9).asSet(integers()), impossibles);
    }

    public int getSquareIndex(int x, int y) {
        return ((y - 1) / 3) * 3 + ((x - 1) / 3) + 1;
    }

    public Set<Integer> getImpossibles(int x, int y) {
        return difference(closed(1, 9).asSet(integers()), getPossibles(x, y));
    }

    public List<Integer> getRow(int index) {
        return newArrayList(this.board.row(index).values());
    }

    public List<Integer> getColumn(int index) {
        return newArrayList(this.board.column(index).values());
    }

    /**
     * <pre>
     * index     index-1    x       y
     * 1 2 3     0  1  2    0 0 0   0 1 2
     * 4 5 6     3  4  5    1 1 1   0 1 2
     * 7 8 9     6  7  8    2 2 2   0 1 2
     * </pre>
     */
    public List<Integer> getSquare(int index) {
        int x1 = ((index - 1) / 3) * 3 * 9 + ((index - 1) % 3) * 3;
        int y1 = x1 + 3;
        return newArrayList(concat(
                newArrayList(this.board.values()).subList(x1, y1),
                newArrayList(this.board.values()).subList(x1 + 9, y1 + 9),
                newArrayList(this.board.values()).subList(x1 + 2 * 9, y1 + 2 * 9)));
    }

    public String toString() {
        String s = "";

        for (final Integer rowKey : board.rowKeySet()) {
            s += Joiner.on(", ").useForNull(" ").join(board.row(rowKey).values()) + "\n";
        }

        return s;
    }

    private final Table<Integer, Integer, Integer> board;

}
