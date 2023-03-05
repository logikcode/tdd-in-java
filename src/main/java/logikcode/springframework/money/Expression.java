package logikcode.springframework.money;

public interface Expression {
    Expression plus(Expression money);
    Money reduce(Bank bank, String toCurrency);
    Expression times(int multiplier);
}
