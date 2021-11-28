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
        double results = Stream.generate(() -> new Minesweeper())
                                    .limit(100)
                                    .mapToInt(game -> game.countMines())
                                    .summaryStatistics()
                                    .getAverage();
        assertEquals(10.0, results);
    }
}
