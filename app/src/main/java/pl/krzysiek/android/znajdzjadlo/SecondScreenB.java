package pl.krzysiek.android.znajdzjadlo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class SecondScreenB extends Activity {

    private ImageButton ibtnBack; //definicja przycisku graficznego
    private TextView nazwa, adres, telefon; // text view na ktorych bedziemy wyswietlac dane wybranego lokalu
    PlacesObjects lokale = new PlacesObjects(); //obiekt ktory dostosuje sie do dania ktore wybralismy
    PlacesTemplate wybranyLokal; //obiekt szablon - który zostaje zapełniony za pomoca obiektu typu PlacesObject

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen_b);

        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);
        nazwa = (TextView) findViewById(R.id.nazwa);
        adres = (TextView) findViewById(R.id.adres);
        telefon = (TextView) findViewById(R.id.telefon);

        //metoda cofaj�ca do ekranu glownego
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondScreenB.this, EkranGlowny.class));
                finish();
            }
        });

        Intent intent = getIntent();
        String danie = intent.getStringExtra("etDish"); //odebranie danych z edit text

        //podanie naszych wspolrzednych do obiektu typu PlacesObject
        lokale.naszPunktDl(EkranGlowny.dlugosc);
        lokale.setNaszPunktSzer(EkranGlowny.szerokosc);

        //sprawdzenie jakie danie zostalo wybrane i odpowiednie zapelnienie danymi obiekty typu PlacesTemplate
        if (danie.equals("Burger")) {
            lokale.setBurger();
            wybranyLokal = lokale.getBurger();
            int nrKomorki = wybranyLokal.wskazLokal();

            //te trzy linie bedzie mozna wyciagnac poza "if-a" - ale dopiero po zaimplementowaniu wiekszej ilosci obiektow klasy PlacesTemplate
            //przypisanie danych lokalu do odpowiednich textView
            nazwa.setText(wybranyLokal.nazwy[nrKomorki]);
            adres.setText(wybranyLokal.adresy[nrKomorki]);
            telefon.setText(wybranyLokal.telefony[nrKomorki]);
        }
        //jezeli dla danego dania nie sa zaimplementowane dane lokali zostanie wyswietlony komunikat
        else {
            nazwa.setText("Jesteś na Marsie !");
            adres.setText("Jesteś na Marsie !");
            telefon.setText("Jesteś na Marsie !");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second_screen_b, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
