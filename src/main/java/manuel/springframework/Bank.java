package manuel.springframework;

public class Bank {
    public Money reduce(Expression source, String currencyType){
        return source.reduce(this, currencyType);
    }

    public int rate(String from, String to){
       return from.equals("USD") && to.equals("NGN") ? 2 : 1;
    }
    public void addRate(String from, String to, int rate){

    }
}
