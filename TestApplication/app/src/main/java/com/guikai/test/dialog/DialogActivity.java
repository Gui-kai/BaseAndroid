package com.guikai.test.dialog;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.guikai.test.R;
import com.guikai.test.dialog.lemonsoft.LemoDialogActivity;
import com.guikai.test.dialog.mydialog.MyDialogActivity;
import com.zjun.progressbar.CircleDotProgressBar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Description:$
 * Crete by Anding on 2020-03-11
 */
public class DialogActivity extends AppCompatActivity {

    private boolean isProgressGoing;
    private int max;
    private int progress;

    private Timer mTimer;
    private TimerTask mTimerTask;

    private CircleDotProgressBar bar_percent;
    private TextView textView;


    private void initData() {
        max = 100;
        readyProgress();
    }

    private void initView() {
        bar_percent = findViewById(R.id.bar_percent);
        bar_percent.setProgressMax(100);
        bar_percent.setProgress(0);
        findViewById(R.id.progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isProgressGoing) {
                    if (progress == max) {
                        progress = 0;
                        bar_percent.setProgress(progress);
                    }
                    startProgress();
                } else {
                    stopProgress();
                }
            }
        });
    }

    private void stopTimerTask() {
        if (mTimerTask != null) {
            mTimerTask.cancel();
        }
        if (mTimer != null) {
            mTimer.cancel();
        }
        mTimerTask = null;
        mTimer = null;
    }

    private void stopProgress() {
        isProgressGoing = false;

        stopTimerTask();
    }

    private void startProgress() {
        isProgressGoing = true;

        stopTimerTask();
        readyProgress();
        mTimer.schedule(mTimerTask, 100, 10);
    }

    private void readyProgress() {
        if (mTimer == null) {
            mTimer = new Timer();
        }
        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    if (!isProgressGoing) {
                        return;
                    }
                    if (++progress >= max) {
                        progress = max;
                        bar_percent.setProgress(progress);
                        stopProgress();
                        return;
                    }
                    bar_percent.setProgress(progress);
                }
            };
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initData();
        initView();
        textView = findViewById(R.id.tv_animmm);
        findViewById(R.id.lemon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DialogActivity.this, LemoDialogActivity.class));
            }
        });

        findViewById(R.id.my_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DialogActivity.this, MyDialogActivity.class));
            }
        });

        textAnimator();
    }

    private void textAnimator() {
        ValueAnimator animator = ValueAnimator.ofInt(70,100);
        animator.setDuration(2000);//时长5s
        animator.setInterpolator(new LinearInterpolator());//线性插值器
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(animation.getAnimatedValue().toString());
            }
        });//监听
        animator.start();
    }


}
