package com.ramyozi.zooquest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CardActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CardView(this));
        Log.i("CardActivity","onCreate() is done");
        Toast.makeText(this,"Welcome", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
            Intent intent = new Intent(this, MainEventActivity.class);
            startActivity(intent);
        }
        return super.onTouchEvent(event);
    }

    class CardView extends View {
        public CardView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.ippo);
            canvas.drawBitmap(b,0,0,null);
            Log.i("CardActivity","Card : " + b.getWidth()+"/"+b.getHeight());
        }
    }
}
