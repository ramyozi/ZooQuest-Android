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

public class PopcornActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PopcornView(this));
        long time = getIntent().getLongExtra("TimePassed", 0);
        if (time >= 1000){
            Toast.makeText(this,
                    "Don't eat too much Popcorn (" + (time/1000) + " secondes)",
                    Toast.LENGTH_LONG).show();
            Intent result = new Intent();
            result.putExtra("MessageDisplayed", true);
            setResult(0,result);
        }
        Log.i("PopcornActivity","onCreate() is done");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
            Intent intent = new Intent(this, CardActivity.class);
            startActivity(intent);
        }
        return true;
    }



    class PopcornView extends View {
        public PopcornView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.popcorn);
            canvas.drawBitmap(b,0,0,null);
            Log.i("PopcornActivity","popcorn : " + b.getWidth()+"/"+b.getHeight());
        }
    }
}
