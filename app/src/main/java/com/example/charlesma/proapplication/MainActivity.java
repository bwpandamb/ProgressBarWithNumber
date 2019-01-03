package com.example.charlesma.proapplication;

import android.animation.ValueAnimator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private MyProgress pro;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        pro = (MyProgress) findViewById(R.id.pro);
        et = (EditText) findViewById(R.id.et);

//        pro.setProgressDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.pro_bg_blue));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et.getText().toString().trim())) {
                    setProgressAnimation(pro, Integer.parseInt(et.getText().toString()));
                }
            }
        });
    }

    private void setProgressAnimation(final ProgressBar view, final int progress) {
        ValueAnimator animator = ValueAnimator.ofInt(0, progress).setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                if (animatedValue <= 100 && animatedValue > 80) {
                    view.setProgressDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.pro_bg_common));
                } else if (animatedValue <= 80 && animatedValue > 60) {
                    view.setProgressDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.pro_bg_blue));
                } else if (animatedValue <= 60 && animatedValue > 0) {
                    view.setProgressDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.pro_bg_green));
                }
                view.setProgress((int) animatedValue);
            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
