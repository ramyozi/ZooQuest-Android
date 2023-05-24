package fr.ldnr.ramyozi.ZooQuest;



import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AlerteActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alerte);
        AutoCompleteTextView etLieu = findViewById(R.id.et_lieu);
        String[] lieux = getResources().getStringArray(R.array.alerte_lieux);
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, lieux);
        etLieu.setAdapter(aa);
    }

        public void envoyer(View view) {
            SharedPreferences sp = getSharedPreferences("zoo", MODE_PRIVATE);
            if(! sp.getBoolean("envoi", true) )
                return;

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.alerte_titre);
            builder.setMessage(R.string.alerte_confirmer);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton(android.R.string.yes,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            confirmer();
                        }
                    });
            builder.setNegativeButton(android.R.string.no, null);
            builder.show();
        }

    public void confirmer() {
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
