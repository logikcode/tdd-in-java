package manuel.springframework;

public interface Expression {
    Expression plus(Money money);
    Money reduce(Bank bank, String toCurrency);
}
