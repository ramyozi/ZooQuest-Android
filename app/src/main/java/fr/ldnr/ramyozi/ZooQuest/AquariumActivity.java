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

import androidx.annotation.Nullable;

public class AquariumActivity extends Activity {
    private long debut, fin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new AquariumView(this));
        debut = System.currentTimeMillis();
        Log.i("AquariumActivity", "onCreate() est terminé");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getActionMasked()==MotionEvent.ACTION_DOWN) {
            Intent i = new Intent(this, PopcornActivity.class);
            fin = System.currentTimeMillis();
            long temps = fin - debut;
            i.putExtra(PopcornActivity.CLE_TEMPS_PASSE, temps);
            startActivityForResult(i, 0);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null && data.getBooleanExtra("messageAffiche", false)) {
            Log.i("AquariumActivity", "Popcorn a affiché le message d'avertissement");
        } else {
            Log.i("AquariumActivity", "Popcorn n'a PAS affiché le message d'avertissement");
        }
    }

    class AquariumView extends View {

        public AquariumView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.aquarium);
            canvas.drawBitmap(b, 0, 0, null);

        }
    }
}
