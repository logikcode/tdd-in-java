package manuel.springframework;

public interface Expression {
    Expression plus(Expression money);
    Money reduce(Bank bank, String toCurrency);
    Expression times(int multiplier);
}
