package com.example.lukaszhomik.mathgamechapter2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lukaszhomik.mathgamechapter2.Service.Calculator;
import com.example.lukaszhomik.mathgamechapter2.Service.GenerateOperation;

import java.util.Random;

public class GameActivity extends Activity implements View.OnClickListener{

    Button btnAnswer1;
    Button btnAnswer2;
    Button btnAnswer3;
    TextView txtOperator1;
    TextView txtOperator2;
    TextView txtOperation;
    TextView txtScore;
    TextView txtLevel;
    int answer;
    int currentScore = 0;
    int currentLevel = 1;
    int currentError = 0;
    int levelMultiplier = 5;
    Random random = new Random();;

    private GenerateOperation generateOperation = new GenerateOperation();
    private int operator1, operator2;
    private String sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setQuestion();

        btnAnswer1.setOnClickListener(this);
        btnAnswer2.setOnClickListener(this);
        btnAnswer3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int answerGiven = 0;
        switch (view.getId()) {
            case R.id.btnAnswer1:
                answerGiven = Integer.parseInt("" + btnAnswer1.getText());
                break;
            case R.id.btnAnswer2:
                answerGiven = Integer.parseInt("" + btnAnswer2.getText());
//button 2 stuff goes here
                break;
            case R.id.btnAnswer3:
                answerGiven = Integer.parseInt("" + btnAnswer3.getText());
                break;
        }
        updateScoreAndLevel(answerGiven);
        setQuestion();

    }
    void setQuestion(){
//Initialize operation and operators
        operator1 = generateOperation.generateOperator(currentLevel * levelMultiplier);
        operator2 = generateOperation.generateOperator(currentLevel * levelMultiplier);
        sign = generateOperation.generateSign();

//Initialize sign
        switch (sign){
            case "+":
                answer = Calculator.add(operator1,operator2);
                break;
            case "*":
                answer = Calculator.multiply(operator1,operator2);
                break;
            case "-":
                answer = Calculator.substract(operator1,operator2);
                break;
        }
//Initialize Text and button variables
        txtOperator1 = (TextView)findViewById(R.id.txtOperator1);
        txtOperator2 = (TextView)findViewById(R.id.txtOperator2);
        txtOperation = (TextView)findViewById(R.id.txtOperation);

        btnAnswer1 = (Button)findViewById(R.id.btnAnswer1);
        btnAnswer2 = (Button)findViewById(R.id.btnAnswer2);
        btnAnswer3 = (Button)findViewById(R.id.btnAnswer3);

        txtLevel = (TextView)findViewById(R.id.txtLevelResult);
        txtScore = (TextView)findViewById(R.id.txtScoreResult);

//Set TextView and buttons text
        txtOperator1.setText("" + operator1);
        txtOperator2.setText("" + operator2);
        txtOperation.setText(sign);

//Button random positioning
        int buttonLayout = random.nextInt(3);
        switch (buttonLayout){
            case 0:
                btnAnswer1.setText("" + answer);
                btnAnswer2.setText("" + (answer + 2));
                btnAnswer3.setText("" + (answer - 2));
                break;
            case 1:
                btnAnswer1.setText("" + (answer + 2));
                btnAnswer2.setText("" + answer);
                btnAnswer3.setText("" + (answer - 2));
                break;
            case 2:
                btnAnswer1.setText("" + (answer - 2));
                btnAnswer2.setText("" + (answer + 2));
                btnAnswer3.setText("" + answer);
                break;
        }
    }
    void updateScoreAndLevel(int answerGiven) {
        if(isCorrect(answerGiven)){
                currentScore++;
            if(currentScore % 5 == 0) {
                currentLevel++;
            }
        }
        else{
            currentScore = 0;
            currentLevel = 1;
            currentError = currentError + 1;
        }

        txtScore.setText("" + currentScore);
        txtLevel.setText("" + currentLevel);
    }
    boolean isCorrect(int answerGiven){

        boolean correctTrueOrFalse = false;
        if(answerGiven == answer){//YAY!
            Toast.makeText(getApplicationContext(), "Well done!",
                    Toast.LENGTH_LONG).show();
            correctTrueOrFalse=true;
        }else{//Uh-oh!
            Toast.makeText(getApplicationContext(), "Sorry",
                    Toast.LENGTH_LONG).show();
            correctTrueOrFalse=false;
        }

        return correctTrueOrFalse;
    }


}
