package com.lasalle.mdpa.lgarci.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button tryButton = (Button) findViewById(R.id.button);
        TextView textTotal = (TextView) findViewById(R.id.textView_total);
        TextView textCorrect = (TextView) findViewById(R.id.textView_correct);
        TextView textIncorrect = (TextView) findViewById(R.id.textView_incorrect);
        Bundle bundle = (Bundle) getIntent().getExtras();
        Integer correct = bundle.getInt("Correct");
        Integer incorrcet = bundle.getInt("Incorrect");
        textTotal.setText(Integer.toString(correct + incorrcet));
        textCorrect.setText(correct.toString());
        textIncorrect.setText(incorrcet.toString());

        tryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
