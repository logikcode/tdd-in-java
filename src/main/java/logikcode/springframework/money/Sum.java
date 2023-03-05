package logikcode.springframework.money;

public class Sum implements Expression {
    public Expression from;
    public Expression to;
    public Sum(Expression augmend, Expression addmend){
        this.from = augmend;
        this.to = addmend;
    }
    @Override
    public Money reduce(Bank bank, String toCurrency){
        int amount = to.reduce(bank, toCurrency).amount + from.reduce(bank, toCurrency).amount;
        System.out.println("AMOUNT IN SUM REDUCE "+ from.reduce(bank, toCurrency).amount);
        return new Money(amount, toCurrency);
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(from.times(multiplier), to.times(multiplier));
    }

    @Override
    public Expression plus(Expression  money) {
        return new Sum(this, to);
    }
}
