package com.example;

import java.util.ArrayList;
import java.util.List;

public class JokeTeller {

    private List<String> jokes = new ArrayList<>();

    JokeTeller(){
        jokes.add("Mother: \"Did you enjoy your first day at school?\" \n" +
                "Girl: \"First day? Do you mean I have to go back tomorrow? ");
        jokes.add("Teacher: Did your father help your with your homework? \n" +
                "Student: No, he did it all by himself.");
        jokes.add("Teacher: Do you have trouble making decisions? \n" +
                "Student: Well...yes and no.");
    }

    public String getJoke(){
        return "Mother: \"Did you enjoy your first day at school?\" \n" +
                "Girl: \"First day? Do you mean I have to go back tomorrow? ";
    }

    public String getRandom(){
        int randNum = (int)Math.random()*3;
        return jokes.get(randNum);
    }
}