package pl.krzysiek.android.znajdzjadlo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.util.Log;

import android.widget.ArrayAdapter;
import android.widget.Spinner;



public class EkranGlowny extends Activity {

    private Button btnSearch; //definicja buttona
    private EditText etPlace; //definicja edit text miejscowosc
    private Spinner etDish; //definicja edit text danie

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_glowny);

        etPlace = (EditText) findViewById(R.id.etPlace);
        etDish = (Spinner) findViewById(R.id.etDish);

        ArrayAdapter<CharSequence> staticAdapter =
                ArrayAdapter.createFromResource(this, R.array.brew_array,android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        etDish.setAdapter(staticAdapter);

        btnSearch = (Button) findViewById(R.id.btnSearch);

        //metoda pobierajaca dane z edit textow i wysylajaca dane do ekranu drugiego
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondScreen = new Intent(getApplicationContext(), SecondScreen.class);
                secondScreen.putExtra("etPlace", etPlace.getText().toString());
                secondScreen.putExtra("etDish", etDish.getSelectedItem().toString());
                Log.d("getPrompt: ", etDish.getSelectedItem().toString());
                startActivity(secondScreen);
                finish();
            }

        });



    }

}
