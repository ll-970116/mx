package com.example.p6qz;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageview_main;
    private TextView textview_main;
    private Button button_main;
    private int path = 5;
    private TimerTask timerTask;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAnimatro();
    }

    private void initAnimatro() {
		//我是23岁的罗传波
        //四种属性动画
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageview_main, View.ALPHA, 1, 0, 1, 0, 1);
        ObjectAnimator translation_x = ObjectAnimator.ofFloat(imageview_main, View.TRANSLATION_X, 1, 20, 30, 60, -60, -30, -20, 1);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(imageview_main, View.ROTATION, 1, 20, 30, 60, -60, -30, -20, 1);
        ObjectAnimator scale_x = ObjectAnimator.ofFloat(imageview_main, View.SCALE_X, 1, 20, 30, 60, -60, -30, -20, 1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha, translation_x, rotation, scale_x);
        animatorSet.setDuration(5000);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                button_main.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        //倒计时
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                path--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textview_main.setText("" + path);
                        if (path == 0) {
                            timer.cancel();
                            timerTask.cancel();
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    private void initView() {
        imageview_main = (ImageView) findViewById(R.id.imageview_main);
        textview_main = (TextView) findViewById(R.id.textview_main);
        button_main = (Button) findViewById(R.id.button_main);

        button_main.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_main:
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                break;
        }
    }
}