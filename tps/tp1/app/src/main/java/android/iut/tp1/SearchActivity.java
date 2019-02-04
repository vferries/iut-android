package android.iut.tp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO 1 - inflate du fichier de layout dans l'activité
        setContentView(R.layout.activity_main);

        // TODO 16 - Récupérer une référence au bouton et au champ de texte
        Button search = findViewById(R.id.search);
        // On doit la mettre final pour y accéder dans le click listener
        final EditText name = findViewById(R.id.name);
        // TODO 17 - Ajouter un événement onClick sur le bouton
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 18 - Dedans, afficher un Toast avec un message contenant le contenu du champ saisi
                // Ici, this pointe sur le View.OnClickListener, on doit donc utiliser SearchActivity.this
                Toast.makeText(SearchActivity.this, name.getText(), Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
