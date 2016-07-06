abstract class Money {
    protected int amount;

    abstract Money times(int multiplier);
    protected String currency;

    public boolean equals(Object object){
        Money money = (Money) object;
        return amount == money.amount
            && getClass().equals(money.getClass());
    }

    String currency(){
        return currency;
    }

    static Money dollar(int amount){
        return new Dollar(amount,null);
    }

    static Money franc(int amount){
        return new Franc(amount, null);
    }
}

class Dollar extends Money{

    private String currency;

    Dollar(int amount, String currency){
        this.amount = amount;
        this.currency = "USD";
    }

    Money times(int multiplier){
        return new Dollar(amount * multiplier, null);
    }
}

class Franc extends Money{

    private String currency;

    Franc(int amount, String currency){
        this.amount = amount;
        this.currency = "CHF";
    }

    Money times(int multiplier){
        return new Franc(amount * multiplier, null);
    }
}