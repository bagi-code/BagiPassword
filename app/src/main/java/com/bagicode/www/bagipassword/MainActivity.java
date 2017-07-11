package com.bagicode.www.bagipassword;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * create by bagicode
 * free coding
 * good coding*/

public class MainActivity extends AppCompatActivity {

    private Timer timer = new Timer();
    private final long DELAY = 500;

    private EditText editTextPass;
    private TextView statusPassword;
    private ProgressBar progressBarPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPass = (EditText) findViewById(R.id.inputPassword);
        statusPassword = (TextView) findViewById(R.id.textPass);
        progressBarPass = (ProgressBar) findViewById(R.id.progressBarPass);

        editTextPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                if(timer != null)
                    timer.cancel();
            }
            @Override
            public void afterTextChanged(final Editable s) {
                //avoid triggering event when text is too short
                if (s.length() >= 0) {

                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // do what you need here (refresh list)
                            final String search = String.valueOf(editTextPass.getText());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    checkPassword(search);

                                }
                            });

                        }

                    }, DELAY);


                }
            }
        });
    }

    public void checkPassword(String parameters) {
        if(parameters.equals("")) {
            editTextPass.setError("Input Password");
            progressBarPass.setProgress(0);
            progressBarPass.setProgressTintList(ColorStateList.valueOf(Color.RED));
        } else if(parameters.length() > 6) {
            statusPassword.setText("Password Sedang");
            progressBarPass.setProgress(60);
            progressBarPass.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        } else {
            statusPassword.setText("Password Lemah");
            progressBarPass.setProgress(40);
            progressBarPass.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }
    }

}
