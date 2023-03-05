package manuel.springframework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTest {
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
        Dollar dollar = Money.dollar(20);
        Expression sum = dollar.plus(dollar);
        Bank bank = new Bank();
        Money money = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(40), money);

    }

    @Test
    void testPlusReturnSum(){
        Money dollar = Money.dollar(10);
        Expression result = dollar.plus(dollar);
        Sum sum = (Sum) result;
        assertEquals(dollar, sum.to);
        assertEquals(dollar, sum.from);
    }

    @Test
    void testReducedMoney(){

    }

    @Test
    void testReduceMoneyDifference(){
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
       Money result = bank.reduce(Money.dollar(6), "USD");
        assertEquals(Money.dollar(6), result);

    }

    @Test
    void testIdentityRate(){
        assertEquals(1, new Bank().rate("USD", "USD"));
        assertEquals(1, new Bank().rate("NGN", "NGN"));
    }
    @Test
    void testMixedAddition(){
      Money tenUSD = Money.dollar(10);
      Money hundredNaira = Money.naira(100);
      Bank bank = new Bank();
      bank.addRate("USD", "NGN", 2);
      Money result = bank.reduce(tenUSD.plus(hundredNaira), "USD");
      assertEquals(new Money(60, "USD"), result);
    }

    @Test
    void testSumPlusMoney(){
        Expression fiveDollars = Money.dollar(10);
        Expression fiftyNaira = Money.naira(40);
        Bank bank = new Bank();
        bank.addRate("NGN", "USD", 2);
        Expression sum = new Sum(fiveDollars, fiftyNaira.plus(fiveDollars)); // sum{ {10}  sum{ {10}, {40}} }
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(40), result);
    }

    @Test
    void testSumTimes(){
        Expression tenNaira = Money.naira(10);
        Expression twentyDollar = Money.dollar(20);
        Bank bank = new Bank();
        bank.addRate("USD", "NGN", 2);
        Expression sum = new Sum(tenNaira, twentyDollar.times(2));
    }
}
