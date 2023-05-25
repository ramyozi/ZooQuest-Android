package fr.ldnr.ramyozi.ZooQuest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
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


            URLConnection conn = new URL(url).openConnection();
            String tout = "";
            try(InputStream is = conn.getInputStream()) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is, Charset.forName("UTF-8")));
                String ligne;
                while((ligne=br.readLine())!=null)
                    tout += ligne + "\n";
                Log.i("AnimalActivity", "Lu : "+tout);
            }
            // (racine) > query > pages > (numero) > extract
            JSONObject racine = new JSONObject(tout);
            JSONObject query = racine.getJSONObject("query");
            JSONObject pages = query.getJSONObject("pages");
            String numeroPage = pages.keys().next();
            JSONObject page = pages.getJSONObject(numeroPage);
            String contenuHtml = page.getString("extract");

            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    TextView tvWiki = findViewById(R.id.tv_wiki);
                    tvWiki.setText(Html.fromHtml(contenuHtml));
                }
            });
        }
     catch(Exception ex) {
        Log.e("AnimalActivity", "Erreur vers Wikipedia", ex);
    }

}
}


