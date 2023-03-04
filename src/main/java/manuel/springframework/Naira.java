package manuel.springframework;

public class Naira {
    public int amount;
    public Naira(int amount){
        this.amount = amount;
    }

    public Naira times(int multiplier){
        return new Naira(amount * multiplier);
    }

    public boolean equals(Object o){
        Naira naira = (Naira) o;
        return naira.amount == this.amount;
    }
}
