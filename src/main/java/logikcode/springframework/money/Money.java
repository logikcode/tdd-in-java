package logikcode.springframework.money;

public class Money implements Expression {
    public int amount;
    protected  String currency;
    public Money(int amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }
    public boolean equals(Object o){
        Money money = (Money) o;
        return money.amount == this.amount && this.currency == money.currency;
    }
    public static Dollar dollar(int amount){
      return   new Dollar(amount, "USD");
    }

    public static Naira naira(int amount){
        return new Naira(amount, "NGN");
    }

    protected String currency() {
        return currency;
    }
    @Override
    public Money times(int multiplier){
        return new Money(amount * multiplier, this.currency);
    }

    @Override
    public Expression plus(Expression money) {
        return new Sum( this, money);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        System.out.println("REDUCE IN MONEY CLASS CALLED "+ " amount => "+ amount);
        return new Money(amount / bank.rate(this.currency, to), to);

    }
}
