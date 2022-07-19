package dev.codes.ttdbyexample;

public class Money implements Expression {

    protected int amount;
    protected String currency;
    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    protected String currency() {
        return currency;
    }

    public static Money dollar(int amount){
        return new Money(amount,"USD");
    }
    public static Money franc(int amount){
        return new Money(amount,"CHF");
    }
    @Override
    public boolean equals(Object object){
        Money money = (Money)object;
        return amount == money.amount
                && currency == money.currency;
    }


    public Money times(int multiplier){
        return new Money(multiplier*amount,this.currency);
    }

    //Augend - first value of addition operation. the '3' in 3 + 6
    //Addend - 2nd value of addition operation. the '6' in 3 + 6
    Expression plus(Money addend){
        return new Sum(this,addend);
    }

    @Override
    public Money reduce(Bank bank, String to){

        //int rate = (currency.equals("CHF") && to.equals("USD")) ? 2:1;
        int rate = bank.rate(this.currency,to);
        return new Money(amount/rate,to);
    }


}
