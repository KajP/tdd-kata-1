import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void task1() {
        assertEquals(0, Calculator.add(""));
        assertEquals(1, Calculator.add("1"));
        assertEquals(3, Calculator.add("1,2"));
    }
}