package fr.ldnr.ramyozi.ZooQuest;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class PopcornActivity extends Activity {
    public static final String CLE_TEMPS_PASSE = "tempsPasse";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PopcornView(this));
        long temps = getIntent().getLongExtra(CLE_TEMPS_PASSE, 0);
        if(temps>=1000) {
            String[] especes = getResources().getStringArray(R.array.popcorn_especes);
            int indice = new Random().nextInt(especes.length);
            String seconde = getResources().getQuantityString(R.plurals.popcorn_secondes, (int)temps/1000);
            String message = getString(R.string.popcorn_avertissement, (int)temps/1000, especes[indice],
                    seconde);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            Intent resultat = new Intent();
            resultat.putExtra("messageAffiche", true);
            setResult(0, resultat);
        }
        Log.i("PopcornActivity", "onCreate() est terminé");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getActionMasked()==MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            int width = findViewById(android.R.id.content).getWidth();
            if(x < width/2) { // à gauche
                Intent i = new Intent(this, CarteActivity.class);
                // i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                // si la carte est deja dessous, la reprendre
                // i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                // reviens à la carte, en dépilant les 2 autres
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            } else { // à droite
                String page = "https://fr.wikipedia.org/wiki/Pop-corn";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(page));
                // Logguer un message d'erreur si ActivityNotFoundException
                try {
                    startActivity(i);
                } catch(ActivityNotFoundException ex) {
                    Log.e("PopcornActivity", "Pas de navigateur ?", ex);
                }
            }
        }
        return true;
    }

    class PopcornView extends View {

        public PopcornView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.popcorn);
            canvas.drawBitmap(b, 0, 0, null);
        }
    }
}
