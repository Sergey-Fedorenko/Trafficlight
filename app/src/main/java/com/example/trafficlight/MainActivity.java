package com.example.trafficlight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout b_1, b_2, b_3;
    private Button button1;
    private boolean start_stop = false;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Присвоил переменным b_1, b_2, b_3 id Layout
        b_1 = findViewById(R.id.lamp_1);
        b_2 = findViewById(R.id.lamp_2);
        b_3 = findViewById(R.id.lamp_3);
        //Присвоил переменной button1 id Button
        button1 = findViewById(R.id.button1);
    }

    //Метод кнопки - Start
    public void onClickStart(View view) {
        if(!start_stop) {
            button1.setText("Stop");
            start_stop = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (start_stop) {
                        counter ++;
                        switch (counter) {
                            case 1:
                                b_1.setBackgroundColor(getResources().getColor(R.color.green));
                                b_2.setBackgroundColor(getResources().getColor(R.color.gray));
                                b_3.setBackgroundColor(getResources().getColor(R.color.gray));
                                break;
                            case 2:
                                b_1.setBackgroundColor(getResources().getColor(R.color.gray));
                                b_2.setBackgroundColor(getResources().getColor(R.color.yellow));
                                b_3.setBackgroundColor(getResources().getColor(R.color.gray));
                                break;
                            case 3:
                                b_1.setBackgroundColor(getResources().getColor(R.color.gray));
                                b_2.setBackgroundColor(getResources().getColor(R.color.gray));
                                b_3.setBackgroundColor(getResources().getColor(R.color.red));
                                counter = 0;
                                break;
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } else {
            start_stop = false;
            button1.setText("Start");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        start_stop = false;
        counter ++;

    }
}