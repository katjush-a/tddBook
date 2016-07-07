abstract class Money {
    protected int amount;

    abstract Money times(int multiplier);
    protected String currency;

    Money(int amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }

    String currency(){
        return currency;
    }

    public boolean equals(Object object){
        Money money = (Money) object;
        return amount == money.amount
                && getClass().equals(money.getClass());
    }

    static Money dollar(int amount){
        return new Dollar(amount, "USD");
    }

    static Money franc(int amount){
        return new Franc(amount, "CHF");
    }
}

class Dollar extends Money{

    private String currency;

    Dollar(int amount, String currency){
        super(amount, currency);
    }

    Money times(int multiplier){
        return Money.dollar(amount * multiplier);
    }
}

class Franc extends Money{

    private String currency;

    Franc(int amount, String currency){
        super(amount, currency);
    }

    Money times(int multiplier){
        return Money.franc(amount * multiplier);
    }
}