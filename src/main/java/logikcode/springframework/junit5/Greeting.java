package logikcode.springframework.junit5;

public class Greeting {
    public String name = "";
    public final String greeting = "Hello";

    public String greets(){
        return greeting + "World";
    }
    public String greets(String name){
        this.name = name;
        return greeting + " "+ name;
    }
}
