package fr.ldnr.ramyozi.ZooQuest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class AccueilActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);
        lireNouvelles();
        preparerBoutons();
    }

    private void lireNouvelles() {
        try {
            InputStream is = getAssets().open("news.txt");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is, Charset.forName("UTF-8")) );
            String texteComplet = "";
            String line;
            while( (line = br.readLine()) != null) // renvoie chaine ou null
                texteComplet += line + "\n";
            Log.i("AccueilActivity", "Nouvelles : "+texteComplet);

            TextView tvNouvelles = findViewById(R.id.tv_nouvelles);
            tvNouvelles.setText(texteComplet);

            is.close();
        } catch(Exception ex) {
            Log.e("AccueilActivity", "Erreur lecture news", ex);
        }
    }

    private void preparerBoutons() {
        Button btCarte = findViewById(R.id.bt_carte);
        btCarte.setOnClickListener(this); // et non add

        Button btAlerte = findViewById(R.id.bt_alerte);
        btAlerte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccueilActivity.this, AlerteActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View view) {
        // pas nécessaire : if(view.getId()==R.id.bt_carte) {
            Intent i = new Intent(this, CarteActivity.class);
            startActivity(i);
        // }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.accueil, menu);
        return true;
    }
}
