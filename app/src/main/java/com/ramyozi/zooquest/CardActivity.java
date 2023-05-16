package com.ramyozi.zooquest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;

import androidx.annotation.Nullable;

public class CardActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CardView(this));
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
        }
    }
}
