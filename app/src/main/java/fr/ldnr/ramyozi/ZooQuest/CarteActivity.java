package fr.ldnr.ramyozi.ZooQuest;

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

public class CarteActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CarteView(this));
        Log.i("CarteActivity", "onCreate() est terminé");
        Toast.makeText(this, getString(R.string.carte_bienvenue), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getActionMasked()==MotionEvent.ACTION_DOWN) {
            Intent i = new Intent(this, AquariumActivity.class);
            startActivity(i);
        }
        return true;
    }

    class CarteView extends View {

        public CarteView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.carte);
            canvas.drawBitmap(b, 0, 0, null);
            Log.i("CarteActivity", "Carte : "+b.getWidth()+"/"+b.getHeight());
        }
    }
}
