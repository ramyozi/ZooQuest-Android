package com.ramyozi.zooquest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MainEventActivity extends Activity {
    private long start , end;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        start = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        setContentView(new MainEventView(this));
        Log.i("MainEventActivity","onCreate() is done");

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
            Intent intent = new Intent(this, PopcornActivity.class);
            end = System.currentTimeMillis();
            long time = end - start;
            intent.putExtra("TimePassed", time);
            startActivity(intent);
        }
        return super.onTouchEvent(event);
    }



    class MainEventView extends View {
        public MainEventView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.takamura);
            canvas.drawBitmap(b,0,0,null);
            Log.i("MainEventActivity","Aquarium : " + b.getWidth()+"/"+b.getHeight());
        }
    }
}
