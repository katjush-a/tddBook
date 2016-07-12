import java.util.Hashtable;

interface Expression{
    Money reduce(Bank bank, String to);
}

class Money implements Expression{
    protected int amount;
    protected String currency;

    Money times(int multiplier){
        return new Money(amount * multiplier, currency);
    }

    Expression plus(Money addend){
        return new Sum(this, addend);
    }

    Money(int amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }

    String currency(){
        return currency;
    }

    public String toString(){
        return amount + " " + currency;
    }

    public boolean equals(Object object){
        Money money = (Money) object;
        return amount == money.amount
                && currency.equals(money.currency());
    }

    static Money dollar(int amount){
        return new Money(amount, "USD");
    }

    static Money franc(int amount){
        return new Money(amount, "CHF");
    }
    public Money reduce(Bank bank, String to){
        int rate = bank.rate(currency, to);
        return new Money(amount/rate, to);
    }
}



class Bank{
    private class Pair{
        private String from;
        private String to;

        Pair(String from, String to){
            this.from = from;
            this.to = to;
        }

        public boolean equals(Object object){
            Pair pair = (Pair) object;
            return from.equals(pair.from) && to.equals(pair.to);
        }

        public int hashCode(){
            return 0;
        }
    }

    private Hashtable rates = new Hashtable();

    Money reduce(Expression source, String to){
        return source.reduce(this, to);
    }

    int rate(String from, String to){
        if (from.equals(to)) return 1;
        Integer rate = (Integer) rates.get(new Pair(from, to));
        return rate.intValue();
    }

    void addRate(String from, String to, int rate){
        rates.put(new Pair(from, to), new Integer(rate));
    }
}

class Sum implements Expression{
    Money addend;
    Money augend;

    Sum(Money augend, Money addend){
        this.augend = augend;
        this.addend = addend;
    }

    public Money reduce(Bank bank, String to){
        int amount = augend.amount + addend.amount;
        return new Money(amount, to);
    }
}

