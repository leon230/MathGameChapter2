package com.example.lukaszhomik.mathgamechapter2.Service;

import java.util.Random;

/**
 * Created by lukasz.homik on 2017-02-15.
 */

public class GenerateOperation {
    private Random random = new Random();
    private String[] sign = {"+","-","*"};


    public int generateOperator(int level){
        return random.nextInt(level) + 1;
    }

    public String generateSign(){

        return sign[random.nextInt(3)];
    }


}
