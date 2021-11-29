import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import Minesweeper.Minesweeper;

public class MinesweeperTest {
    Minesweeper minesweeper = new Minesweeper();

    @Test
    public void testForTenBombs() {
        assertEquals(true, Stream.generate(() -> new Minesweeper())
                                    .limit(100)
                                    .mapToInt(game -> game.countMines())
                                    .allMatch(num -> num == 10));
    }
}
