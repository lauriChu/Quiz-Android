package com.lasalle.mdpa.lgarci.quiz;

import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionActivity extends AppCompatActivity {

    private int questionCount = 1;
    private int answerCorrect = 0;
    private int answerIncorrect = 0;

    private TextView questionTextView;
    private Button nextButton;
    private RadioButton radio1, radio2, radio3, radio4;

    private HashMap<Integer, ArrayList<Pair<String, Boolean>>> answers;
    private ArrayList<String> questions;

    private int currentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionTextView = (TextView)findViewById(R.id.textView);
        nextButton = (Button) findViewById(R.id.button);
        radio1 = (RadioButton) findViewById(R.id.radioButton);
        radio2 = (RadioButton) findViewById(R.id.radioButton2);
        radio3 = (RadioButton) findViewById(R.id.radioButton3);
        radio4 = (RadioButton) findViewById(R.id.radioButton4);
        populateQuestions();
        setupQuestion(questionCount);
        setupRadioButtons();
        setNextButton();
    }

    private void setNextButton(){
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionCount < 3){
                    correctAnswer();
                    questionCount++;
                    reloadQuestion(questionCount);
                    setupQuestion(questionCount);
                } else {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("Correct", answerCorrect);
                    intent.putExtra("Incorrect", answerIncorrect);
                    startActivity(intent);
                }
            }
        });
    }

    private void setupQuestion(int questionNumber){
        questionTextView.setText(questions.get(questionNumber));
        radio1.setText(answers.get(questionNumber).get(0).first);
        radio2.setText(answers.get(questionNumber).get(1).first);
        radio3.setText(answers.get(questionNumber).get(2).first);
        radio4.setText(answers.get(questionNumber).get(3).first);
    }

    private void reloadQuestion(int questionNumber){
        radio1.setChecked(false);
        radio2.setChecked(false);
        radio3.setChecked(false);
        radio4.setChecked(false);
        setupQuestion(questionNumber);
    }

    private void populateQuestions(){
        answers = new HashMap<>();
        ArrayList<Pair<String,Boolean>> answers1 = new ArrayList<>();
        answers1.add(new Pair<String, Boolean>("Argentina", false));
        answers1.add(new Pair<String, Boolean>("Brazil", false));
        answers1.add(new Pair<String, Boolean>("Spain", true));
        answers1.add(new Pair<String, Boolean>("Germany", false));

        ArrayList<Pair<String,Boolean>> answers2 = new ArrayList<>();
        answers1.add(new Pair<String, Boolean>("SKT", false));
        answers1.add(new Pair<String, Boolean>("Fnatic", true));
        answers1.add(new Pair<String, Boolean>("EDG", false));
        answers1.add(new Pair<String, Boolean>("Origen", false));

        answers.put(1, answers1);
        answers.put(2, answers1);
        answers.put(3, answers1);
        answers.put(4, answers2);

        questions = new ArrayList<>();
        questions.add("Who won the 2014 World Cup?");
        questions.add("Who won the 2014 World Cup?");
        questions.add("Who won the 2014 World Cup?");
        questions.add("Who won the 2016 LOL Worlds?");
    }

    private void setupRadioButtons(){
        radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                currentResult = 0;
            }
        });

        radio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                currentResult = 1;
            }
        });

        radio3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                currentResult = 2;
            }
        });

        radio4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                currentResult = 3;
            }
        });
    }

    private void correctAnswer(){
        if (answers.get(questionCount).get(currentResult).second){
            answerCorrect++;
        }else{
            answerIncorrect++;
        }
    }
}
