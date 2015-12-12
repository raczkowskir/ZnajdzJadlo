package pl.krzysiek.android.znajdzjadlo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EkranGlowny extends Activity {

    private Button btnSearch; //definicja buttona
    private EditText etPlace; //definicja edit text miejscowosc
    private EditText etDish; //definicja edit text danie

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_glowny);

        etPlace = (EditText) findViewById(R.id.etPlace);
        etDish = (EditText) findViewById(R.id.etDish);

        btnSearch = (Button) findViewById(R.id.btnSearch);

        //metoda pobierajaca dane z edit textow i wysylajaca dane do ekranu drugiego
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondScreen = new Intent(getApplicationContext(), SecondScreen.class);
                secondScreen.putExtra("etPlace", etPlace.getText().toString());
                secondScreen.putExtra("etDish", etDish.getText().toString());
                startActivity(secondScreen);
                finish();
            }
        });


    }

}
