import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    @Test
    void task1() {
        assertEquals(0, Calculator.add(""));
        assertEquals(1, Calculator.add("1"));
        assertEquals(3, Calculator.add("1,2"));
    }

    @Test
    void task2() {
        assertEquals(137, Calculator.add("1,2,3,4,34,43,43,3,4"));
    }

    @Test
    void task3() {
        assertEquals(6, Calculator.add("1\n2,3"));
    }

    @Test
    void task4() {
        assertEquals(3, Calculator.add("//;\n1;2"));
    }

    @Test
    void task5() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> Calculator.add("1,-2,3,-3"));
        assertEquals("-2, -3", e.getMessage());
    }

    @Test
    void task6() {
        assertEquals(6, Calculator.add("1,1002,2000,2,3000,3,1111"));
        assertThrows(IllegalArgumentException.class, () -> Calculator.add("1,-1,1002,2,3"));
    }

    @Test
    void task7() {
        assertEquals(6, Calculator.add("//[***]\n1***2***3"));
    }

    @Test
    void task8() {
        assertEquals(6, Calculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    void task9() {
        assertEquals(7, Calculator.add("//[***][%+][$*]\n1***2%+3$*1"));
    }
}