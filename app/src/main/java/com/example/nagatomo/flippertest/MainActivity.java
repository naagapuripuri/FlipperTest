package com.example.nagatomo.flippertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;
import android.util.Log;


public class MainActivity extends Activity implements OnTouchListener {

    ViewFlipper viewflipper;
    float lastTouchX;
    float currentX;
    Animation left_in;
    Animation right_in;
    Animation left_out;
    Animation right_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewFlipperを取得する
        viewflipper = (ViewFlipper)this.findViewById(R.id.viewflipper);

        // リスナーを設定する
        viewflipper.setOnTouchListener(this);

        // アニメーションを設定する
        left_in = AnimationUtils.loadAnimation(this, R.anim.left_in);
        right_in = AnimationUtils.loadAnimation(this, R.anim.right_in);
        left_out = AnimationUtils.loadAnimation(this, R.anim.left_out);
        right_out = AnimationUtils.loadAnimation(this, R.anim.right_out);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.v( "Y", ""+event.getY() );
        Log.v( "X", ""+event.getX() );

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // タッチ場所を取得
                lastTouchX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                currentX = event.getX();
                if(lastTouchX > currentX){
              // アニメーションを設定
                    viewflipper.setInAnimation(right_in);
                    viewflipper.setOutAnimation(left_out);
                    // 次ページへ移動
                    viewflipper.showNext();
                }else if(lastTouchX < currentX){
                // アニメーションを設定
                viewflipper.setInAnimation(left_in);
                viewflipper.setOutAnimation(right_out);
                // 前ページへ移動
                viewflipper.showPrevious();
                }
            default:
                break;
            }
        return true;
    }
}
