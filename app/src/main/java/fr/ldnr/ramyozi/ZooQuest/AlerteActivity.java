package fr.ldnr.ramyozi.ZooQuest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AlerteActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alerte);
    }

    public void envoyer(View view) {
        //TODO Afficher un ! au bout si l'alerte est urgente
        EditText etIntitule = findViewById(R.id.et_intitule);
        String intitule = etIntitule.getText().toString();
        CheckBox cbUrgent = findViewById(R.id.cb_urgent);
        boolean urgent = cbUrgent.isChecked();
        String message = "Alerte \""+intitule+"\" envoyée";
        if(urgent)
            message += " !";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
