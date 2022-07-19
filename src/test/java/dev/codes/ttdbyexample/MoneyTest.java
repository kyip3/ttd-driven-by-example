package dev.codes.ttdbyexample;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTest {

    @Test
    void testMultiplication(){
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15),five.times(3));

        Money fivef = Money.franc(5);
        assertEquals(Money.franc(10),fivef.times(2));
    }

    @Test
    void testEquality()
    {
        assertEquals(Money.dollar(5),Money.dollar(5));
        assertEquals(Money.franc(5),Money.franc(5));

        assertNotEquals(Money.dollar(8),Money.dollar(5));
        assertNotEquals(Money.franc(8),Money.dollar(8));
    }

    @Test
    void testCurrency(){
        assertEquals("USD",Money.dollar(1).currency());
        assertEquals("CHF",Money.franc(1).currency());
    }

    @Test
    void testSimpleAddition(){
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum,"USD");
        assertEquals(Money.dollar(10),reduced);
    }

    @Test
    void testPlusReturnSum(){
        Money five = Money.dollar(5);

        //Money object to store augend and addend, and return the expression.
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five,sum.augend);
        assertEquals(five,sum.addend);
    }


    @Test
    void testReducedSum(){
        //Sum to take in 2 money objects, and return a new single money object. (2 reduced to 1)
        Expression sum = new Sum(Money.dollar(3),Money.dollar(4));

        Bank bank = new Bank();

        //bank to take in Expression sum, and return a money object
        Money result = bank.reduce(sum,"USD");
        assertEquals(Money.dollar(7),result);
    }


    @Test
    void testReduceMoney(){
        Bank bank = new Bank();

        //bank to take in a money object(type expression) and return a money object
        Money result = bank.reduce(Money.dollar(1),"USD");
        assertEquals(Money.dollar(1),result);
    }

    @Test
    void testReduceMoneyDifferentCurrency(){
        Bank bank = new Bank();
        bank.addRate("CHF","USD",2);
        Money result = bank.reduce(Money.franc(2),"USD");
        assertEquals(Money.dollar(1),result);
    }

    @Test
    void testIdentityRate(){
        assertEquals(1,new Bank().rate("USD","USD"));
        assertEquals(1,new Bank().rate("CHF","CHF"));
    }


}
