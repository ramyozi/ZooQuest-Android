package fr.ldnr.ramyozi.ZooQuest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalActivity extends Activity implements View.OnClickListener {

    private Handler uiHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal);
        Button btEnvoyer = findViewById(R.id.bt_envoyer);
        btEnvoyer.setOnClickListener(this);
        uiHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onClick(View view) {
        // pas d'appel direct, trop long/interdit    chercher();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                chercher();
            }
        });
    }

    public void chercher() {
        try {
            EditText etAnimal = findViewById(R.id.et_animal);
            String espece = etAnimal.getText().toString();
            String url = "https://fr.wikipedia.org/w/api.php?action=query&prop=extracts&" +
                    "exsentences=3&format=json&titles=" + URLEncoder.encode(espece, "UTF-8");
            Log.i("AnimalActivity", "Appel de "+url);


            String contenuHtml = "????";
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    TextView tvWiki = findViewById(R.id.tv_wiki);
                    tvWiki.setText(contenuHtml);
                }
            });
        } catch(Exception ex) {
            Log.e("AnimalActivity", "Erreur vers WP", ex);
        }
    }
}


