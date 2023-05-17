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

public class AquariumActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new AquariumView(this));
        Log.i("AquariumActivity","onCreate() is done");
        Toast.makeText(this,"Welcome", Toast.LENGTH_LONG).show();

    }



    class AquariumView extends View {
        public AquariumView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.takamura);
            canvas.drawBitmap(b,0,0,null);
            Log.i("AquariumActivity","Aquarium : " + b.getWidth()+"/"+b.getHeight());
        }
    }
}
