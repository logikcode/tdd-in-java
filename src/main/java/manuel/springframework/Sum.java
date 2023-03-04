package manuel.springframework;

public class Sum implements Expression {
    Expression from;
    Expression to;
    public Sum(Expression augmend, Expression addmend){
        this.from = augmend;
        this.to = addmend;
    }
    @Override
    public Money reduce(Bank bank, String toCurrency){
        int amount = to.reduce(bank, toCurrency).amount + from.reduce(bank, toCurrency).amount;
        return new Money(amount, toCurrency);
    }
    @Override
    public Expression plus(Money money) {
        return new Sum(from, to);
    }
}
