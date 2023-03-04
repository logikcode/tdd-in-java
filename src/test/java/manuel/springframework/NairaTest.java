package manuel.springframework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NairaTest {
    @Test
    void testMultiplication(){
        Naira money = new Naira(100, "NGN");
        Money n1 =  money.times(10);
        Money n2 = money.times(40);

        assertEquals(1000, n1.amount);
        assertEquals(4000, n2.amount);

    }

    @Test
    void testNairaEquality(){
        assertEquals(Money.naira(50), new Naira(50, "NGN"));
        assertNotEquals(new Naira(70, "NGN"), new Naira(50, "NGN"));
    }

    @Test
    void testDollarEquality(){
        Dollar dollar = new Dollar(10, "USD");
        Money newDollarValue = dollar.times(50);
        assertEquals(500, newDollarValue.amount);

        assertEquals(Money.dollar(5), Money.dollar(5));

        assertNotEquals(new Dollar(10, "USD"), new Dollar(1, "USD"));
    }

    @Test
    void testMoneyAddition(){
        Money naira = new Money(30, "NGN");


    }
}
