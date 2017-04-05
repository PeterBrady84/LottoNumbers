package com.peter.lottonumbers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText howManyNum, maxNum;
    private TextView howManyNumValidation, maxNumValidation, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateTitleTextView();

        howManyNum = ((EditText) findViewById(R.id.howManyEditText));
        maxNum = ((EditText) findViewById(R.id.maxEditText));
        Button pick = (Button) findViewById(R.id.pickButton);
        result = (TextView) findViewById(R.id.picksTextView);
        howManyNumValidation = (TextView) findViewById(R.id.input_how_many_validation);
        maxNumValidation = (TextView) findViewById(R.id.input_max_validation);
        maxNumValidation.setVisibility(View.GONE);
        howManyNumValidation.setVisibility(View.GONE);

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (howManyNum.getText().toString().equals("") || maxNum.getText()
                        .toString().equals("")) {
                    if (howManyNum.getText().toString().equals("")) {
                        howManyNumValidation.setVisibility(View.VISIBLE);
                        howManyNumValidation.setText(R.string.empty_value);
                        result.setText("");
                    }
                    if (maxNum.getText().toString().matches("")) {
                        maxNumValidation.setVisibility(View.VISIBLE);
                        maxNumValidation.setText(R.string.empty_value);
                        result.setText("");
                    }
                } else {
                    // read and parse how many e.g. 6
                    int howMany = Integer.parseInt(howManyNum.getText().toString());
                    // read and parse max e.g. 45
                    int max = Integer.parseInt(maxNum.getText().toString());

                    if (max <= howMany) {
                        maxNumValidation.setVisibility(View.VISIBLE);
                        maxNumValidation.setText(R.string.invalid_value);
                    } else {
                        // pick the numbers
                        int[] picks = new int[0];
                        try {
                            picks = Lottery.pickNumbers(howMany, max);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // display the numbers
                        String data = "";
                        for (int number : picks) {
                            data = data + number + "  ";
                        }
                        maxNumValidation.setVisibility(View.GONE);
                        howManyNumValidation.setVisibility(View.GONE);
                        result.setText(data);
                        updateTitleTextView();
                    }
                }
            }
        });
    }

    // update title textview to display lottery info i.e. how many numbers to pick
    // and max number that can be picked
    protected void updateTitleTextView() {
        TextView numbersLabel = ((TextView) findViewById(R.id.numbersLabel));
        numbersLabel.setText(getString(R.string.number_label, ((EditText) findViewById(R.id
                .howManyEditText)).getText(), ((EditText) findViewById(R.id.maxEditText)).getText
                ()));
    }
}
