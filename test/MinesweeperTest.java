import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import Minesweeper.Minesweeper;

public class MinesweeperTest {
    Minesweeper minesweeper = new Minesweeper();

    @Test
    public void testForTenBombs() {
        Set<Integer> results = Stream.generate(() -> minesweeper.countMines())
                                    .limit(100)
                                    .collect(Collectors.toSet());
        assertEquals(Set.of(10), results);
    }
}
