package logikcode.springframework.money;

import java.util.HashMap;

public class Bank {
    private final HashMap<Pair, Integer> rateMap = new HashMap<>();
    public Money reduce(Expression source, String currencyType){

       Money money = source.reduce(this,  currencyType);
        System.out.println("AMOUNT "+ money.amount + " " );
        return money;
    }

    public int rate(String from, String to){
        if (from.equals(to)){
            return 1;
        }
       Integer rate = rateMap.get(new Pair(from, to));
       //return from.equals("USD") && to.equals("NGN") ? 2 : 1;
        return 2;
    }
    public void addRate(String from, String to, int rate){
    rateMap.put(new Pair(from, to), rate);
    }
}
