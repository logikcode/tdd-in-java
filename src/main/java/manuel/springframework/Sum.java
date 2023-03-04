package manuel.springframework;

public class Sum implements Expression {
    Money from;
    Money to;
    public Sum(Money augmend, Money addmend){
        this.from = augmend;
        this.to = addmend;
    }
    @Override
    public Money reduce(Bank bank, String toCurrency){
        int amount = to.amount + from.amount;
        return new Money(amount, toCurrency);
    }
    @Override
    public Expression plus(Money money) {
        return null;
    }
}
