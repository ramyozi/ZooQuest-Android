package fr.ldnr.ramyozi.ZooQuest;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;


public class AccueilActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);
        lireNouvelles();
        preparerBoutons();
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            enregistrerOuverture();
        } else {
            requestPermissions(new String[] {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode==0 &&
                permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) ) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enregistrerOuverture();
            } else {
                // rien ou requestPermission ou Toast.... ou finish() ...
                Log.i("AccueilActivity", "L'utilisateur a refusé l'écriture sur SDCard");
            }
        }
    }

    private void enregistrerOuverture() {
        Log.i("AccueilActivity", "Enregistrement de l'ouverture");
        //TODO enregistrer dans la SDCard
        // import android.text.format.DateFormat; et java.util.Date
        DateFormat df = new DateFormat();
        // 2023-05-25 08:54 Ouverture de l'application
        String line = df.format("yyyy-MM-dd hh:mm", new Date())+" Ouverture\n";
        if(Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                File repertoire = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS);
                File fichier = new File(repertoire, "zoo.log.txt");
                try(FileWriter fw = new FileWriter(fichier, true)) {
                    fw.write(line);
                }
                Log.i("AccueilActivity", "Enregistrement terminé");
            } catch(IOException ex) {
                Log.e("AccueilActivity", "Erreur ecriture log", ex);
            }
        }
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
        SharedPreferences sp = getSharedPreferences("zoo", MODE_PRIVATE);
        MenuItem mi = menu.findItem(R.id.menu_envoi);
        // true : par defaut, si on est pas encore passé par onMenuItemSelected()
        mi.setChecked(sp.getBoolean("envoi", true));
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, @NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_carte) {
            startActivity(new Intent(this, CarteActivity.class));
        }
        if(item.getItemId()==R.id.menu_envoi) {
            item.setChecked( ! item.isChecked());

            SharedPreferences sp = getSharedPreferences("zoo", MODE_PRIVATE);
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("envoi", item.isChecked());
            e.commit();
        }
        if(item.getItemId()==R.id.menu_animal) {
            startActivity(new Intent(this, AnimalActivity.class));
        }

        return true;
    }
}

