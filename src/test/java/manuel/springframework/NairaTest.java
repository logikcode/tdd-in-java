package manuel.springframework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NairaTest {
    @Test
    void testMultiplication(){
        Naira money = new Naira(100);
        Naira n1 =  money.times(10);
        Naira n2 = money.times(40);

        assertEquals(1000, n1.amount);
        assertEquals(4000, n2.amount);

    }

    @Test
    void testNairaEquality(){
        assertEquals(new Naira(50), new Naira(50));
        assertNotEquals(new Naira(70), new Naira(50));
    }
}
