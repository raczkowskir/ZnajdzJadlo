package pl.krzysiek.android.znajdzjadlo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;

public class EkranGlowny extends Activity implements LocationListener {


    private Button btnSearch; //definicja buttona
    private EditText etPlace; //definicja edit text miejscowosc
    private Spinner etDish; //definicja edit text danie

    LocationManager lm; // obiekt opisujacy dostawce wspórzednych ( w naszym przypadku - network)
    Criteria kr; //obiekt odpowiadajacy za kryteria wyboru dostawcy, np. dokladność pomiaru, darmowość
    Location loc; // obiekt z ktorego pobierzemy wspólrzedne GPS
    String najlepszyDostawca;


    // metoda ustawijąca aktualne wspołrzedne
    private void odswiez() {
        najlepszyDostawca = lm.getBestProvider(kr, true);
        loc = lm.getLastKnownLocation(najlepszyDostawca);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_glowny);

        etPlace = (EditText) findViewById(R.id.etPlace);
        etDish = (Spinner) findViewById(R.id.etDish);

        ArrayAdapter<CharSequence> staticAdapter =
                ArrayAdapter.createFromResource(this, R.array.meal_array, android.R.layout.simple_spinner_item);

        // Określenie layoutu jaki ma byc uzyty kiedy pojawi sie rozwijana lista
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // ustawienie adaptera dla spinnera
        etDish.setAdapter(staticAdapter);

        btnSearch = (Button) findViewById(R.id.btnSearch);

        //metoda pobierajaca dane z edit textow i wysylajaca dane do ekranu drugiego
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondScreen = new Intent(getApplicationContext(), SecondScreen.class);
                secondScreen.putExtra("etPlace", etPlace.getText().toString());
                secondScreen.putExtra("etDish", etDish.getSelectedItem().toString());
                startActivity(secondScreen);
                finish();
            }

        });
// przy włączeniu aplikacji odrazu pobierane są współrzedne
        kr = new Criteria();
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        odswiez();
        lm.requestLocationUpdates(najlepszyDostawca, 1000, 1, this);


//tworzony jest obiekt klasy LocationAddress ktora odpowiada za zamiane współrzednych GPS na nazwe miejscowości
        LocationAddress locationAddress = new LocationAddress();
        locationAddress.getAddressFromLocation(loc.getLatitude(), loc.getLongitude(),
                getApplicationContext(), new GeocoderHandler());
    }

    // metody wymagane przez interface: LocationListener
    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    private class GeocoderHandler extends Handler {
        @Override

        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
// gdy urzadzenie nie jest podłączone do internetu sprawdzenie miejscowości nie jest możliwe
// wtedy poniższy warunek chroni przed wyświetleniem wspolrzednych GPS zamiast nazwy miejscowości
                    if (locationAddress.startsWith("Latitude")) {
                        locationAddress = "";
                    }
                    break;
                default:
                    locationAddress = null;
            }
// wyświetlenie nazwy miejscowości
            etPlace.setText(locationAddress);
        }
    }
}
