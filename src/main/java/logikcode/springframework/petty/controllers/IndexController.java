package logikcode.springframework.petty.controllers;

public class IndexController {

    public String index(){

        return "index";
    }

    public String oupsHandler(){
        return "something";
    }

    public String exThrower(){
        throw new ValueNotFoundException();

    }
}
