package com.google.code.caffeine.sudoku;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.ArrayTable.create;
import static com.google.common.collect.DiscreteDomains.integers;
import static com.google.common.collect.Iterables.*;
import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Ranges.closed;
import static com.google.common.collect.Sets.difference;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.union;
import static java.util.Collections.nCopies;

public class Sudoku2 {

    public Sudoku2(
            final Table<Integer, Integer, Integer> board) {
        this.board = create(newArrayList(closed(1, 9).asSet(integers())), newArrayList(closed(1, 9).asSet(integers())));
        for (Table.Cell<Integer, Integer, Integer> cell : board.cellSet()) {
            final Point point = new Point(this.board, cell.getColumnKey(), cell.getRowKey(), cell.getValue());
            this.board.put(cell.getRowKey(), cell.getColumnKey(), point);
        }
    }

    public Table<Integer, Integer, Point> getBoard() {
        return this.board;
    }

    public void solve() {
        for (Table.Cell<Integer, Integer, Point> cell : board.cellSet()) {
             cell.getValue().solve();
        }
    }

    public String possiblesToString() {
        String s = "";

        for (final Integer rowKey : board.rowKeySet()) {
            s += Joiner.on(", ").useForNull(" ").join(transform(board.row(rowKey).values(), new Function<Point, String>() {
                @Override
                public String apply(final Point input) {
                    final Iterable<Integer> allValues = concat(input.possibles(), nCopies(9 - input.possibles().size(), (Integer) null));
                    final String s = "[" + Joiner.on(":").useForNull(" ").join(allValues) + "]";

                    return s;
                }
            })) + "\n";
        }

        return s;
    }

    public String rowRemainersToString() {
        String s = "";

        for (final Integer rowKey : board.rowKeySet()) {
            s += Joiner.on(", ").useForNull(" ").join(transform(board.row(rowKey).values(), new Function<Point, String>() {
                @Override
                public String apply(final Point input) {
                    final Iterable<Integer> allValues = concat(input.rowRemainers(), nCopies(9 - input.rowRemainers().size(), (Integer) null));
                    final String s = "[" + Joiner.on(":").useForNull(" ").join(allValues) + "]";

                    return s;
                }
            })) + "\n";
        }

        return s;
    }

    public String columnRemainersToString() {
        String s = "";

        for (final Integer rowKey : board.rowKeySet()) {
            s += Joiner.on(", ").useForNull(" ").join(transform(board.row(rowKey).values(), new Function<Point, String>() {
                @Override
                public String apply(final Point input) {
                    final Iterable<Integer> allValues = concat(input.columnRemainers(), nCopies(9 - input.columnRemainers().size(), (Integer) null));
                    final String s = "[" + Joiner.on(":").useForNull(" ").join(allValues) + "]";

                    return s;
                }
            })) + "\n";
        }

        return s;
    }

    public String squareRemainersToString() {
        String s = "";

        for (final Integer rowKey : board.rowKeySet()) {
            s += Joiner.on(", ").useForNull(" ").join(transform(board.row(rowKey).values(), new Function<Point, String>() {
                @Override
                public String apply(final Point input) {
                    final Iterable<Integer> allValues = concat(input.squareRemainers(), nCopies(9 - input.squareRemainers().size(), (Integer) null));
                    final String s = "[" + Joiner.on(":").useForNull(" ").join(allValues) + "]";

                    return s;
                }
            })) + "\n";
        }

        return s;
    }

    public String remainersToString() {
        String s = "";

        for (final Integer rowKey : board.rowKeySet()) {
            s += Joiner.on(", ").useForNull(" ").join(transform(board.row(rowKey).values(), new Function<Point, String>() {
                @Override
                public String apply(final Point input) {
                    final Iterable<Integer> allValues = concat(input.remainers(), nCopies(9 - input.remainers().size(), (Integer) null));
                    final String s = "[" + Joiner.on(":").useForNull(" ").join(allValues) + "]";

                    return s;
                }
            })) + "\n";
        }

        return s;
    }

    public String toString() {
        String s = "";

        for (final Integer rowKey : board.rowKeySet()) {
            s += Joiner.on(", ").useForNull(" ").join(board.row(rowKey).values()) + "\n";
        }

        return s;
    }

    private final Table<Integer, Integer, Point> board;

    public static class Point {

        public Point(
                final Table<Integer, Integer, Point> board,
                final int x,
                final int y,
                final Integer value) {
            this.board = board;
            this.x = x;
            this.y = y;

            this.value = value;
        }

        public void solve() {
            if (this.isEmpty()) {
                solvePass++;
            }

            if (possibles().size() == 1) {
                this.solveLogic = SolveLogic.POSSIBLES;
                this.value = Iterables.getOnlyElement(possibles());
            }

            if (remainers().size() == 1) {
                this.solveLogic = SolveLogic.REMAINERS;
                this.value = Iterables.getOnlyElement(remainers());
            }
        }

        public Integer getValue() {
            return this.value;
        }

        public boolean isEmpty() {
            return (this.value == null);
        }

        public Set<Point> rowPoints() {
            return Sets.newHashSet(transform(filter(this.board.cellSet(), new Predicate<Table.Cell<Integer, Integer, Point>>() {
                @Override
                public boolean apply(Table.Cell<Integer, Integer, Point> input) {
                    return input.getRowKey().equals(Point.this.y);
                }
            }), new Function<Table.Cell<Integer, Integer, Point>, Point>() {
                @Override
                public Point apply(Table.Cell<Integer, Integer, Point> input) {
                    return input.getValue();
                }
            }));
        }

        public Set<Point> columnPoints() {
            return Sets.newHashSet(transform(filter(this.board.cellSet(), new Predicate<Table.Cell<Integer, Integer, Point>>() {
                @Override
                public boolean apply(Table.Cell<Integer, Integer, Point> input) {
                    return input.getColumnKey().equals(Point.this.x);
                }
            }), new Function<Table.Cell<Integer, Integer, Point>, Point>() {
                @Override
                public Point apply(Table.Cell<Integer, Integer, Point> input) {
                    return input.getValue();
                }
            }));
        }

        public Set<Point> squarePoints() {
            return Sets.newHashSet(transform(filter(this.board.cellSet(), new Predicate<Table.Cell<Integer, Integer, Point>>() {
                @Override
                public boolean apply(Table.Cell<Integer, Integer, Point> input) {
                    return getSquareIndex(input.getColumnKey(), input.getRowKey()) == getSquareIndex(x, y);
                }
            }), new Function<Table.Cell<Integer, Integer, Point>, Point>() {
                @Override
                public Point apply(Table.Cell<Integer, Integer, Point> input) {
                    return input.getValue();
                }
            }));
        }

        public Set<Integer> rowValues() {
            return newHashSet(filter(transform(rowPoints(), new Function<Point, Integer>() {
                @Override
                public Integer apply(Point input) {
                    return input.getValue();
                }
            }), notNull()));
        }

        public Set<Integer> columnValues() {
            return newHashSet(filter(transform(columnPoints(), new Function<Point, Integer>() {
                @Override
                public Integer apply(Point input) {
                    return input.getValue();
                }
            }), notNull()));
        }

        public Set<Integer> squareValues() {
            return newHashSet(filter(transform(squarePoints(), new Function<Point, Integer>() {
                @Override
                public Integer apply(Point input) {
                    return input.getValue();
                }
            }), notNull()));
        }

        public Set<Integer> possibles() {
            if (!this.isEmpty()) {
                return ImmutableSet.of();
            }

            final Set<Integer> union = union(union(rowValues(), columnValues()), squareValues());
            final Set<Integer> possibles = difference(closed(1, 9).asSet(integers()), union);

            return possibles;
        }

        public Set<Integer> rowRemainers() {
            if (!this.isEmpty()) {
                return ImmutableSet.of();
            }

            // possibles not in union(squares.eachWithoutCurrent.possibles)
            Set<Integer> values = Sets.newHashSet();

            for (final Point point : squarePoints()) {
                final boolean current = point.equals(this);
                if (!current) {
                    values = union(point.possibles(), values);
                }
            }

            return difference(possibles(), values);
        }

        public Set<Integer> columnRemainers() {
            if (!this.isEmpty()) {
                return ImmutableSet.of();
            }

            Set<Integer> values = Sets.newHashSet();

            for (final Point point : columnPoints()) {
                final boolean current = point.equals(this);
                if (!current) {
                    values = union(point.possibles(), values);
                }
            }

            return difference(possibles(), values);
        }

        public Set<Integer> squareRemainers() {
            if (!this.isEmpty()) {
                return ImmutableSet.of();
            }

            Set<Integer> values = Sets.newHashSet();

            for (final Point point : squarePoints()) {
                final boolean current = point.equals(this);
                if (!current) {
                    values = union(point.possibles(), values);
                }
            }

            return difference(possibles(), values);
        }

        public Set<Integer> remainers() {
            if (!this.isEmpty()) {
                return ImmutableSet.of();
            }

            return union(union(rowRemainers(), columnRemainers()), squareRemainers());
        }

        public int getSquareIndex(
                int x,
                int y) {
            return ((y - 1) / 3) * 3 + ((x - 1) / 3) + 1;
        }

        @Override
        public String toString() {
            String s = "";

            if (this.isEmpty()) {
                s += "     ";
            }
            else {
                if (!solveLogic.equals(SolveLogic.UNSOLVED)) {
                    s += "[" + this.solvePass + "]";
                }
                else {
                    s += "   ";
                }

                s += this.solveLogic.toString() + this.value;
            }

            return s;
        }

        private final Table<Integer, Integer, Point> board;
        private final int x;
        private final int y;

        private Integer value;
        private SolveLogic solveLogic = SolveLogic.UNSOLVED;
        private int solvePass;

        private enum SolveLogic {

            UNSOLVED(" "),
            POSSIBLES("p"),
            REMAINERS("r");

            private SolveLogic(
                    final String symbol) {
                this.symbol = symbol;
            }

            public String toString() {
                return this.symbol;
            }

            private final String symbol;

        }

    }

}
