package pl.krzysiek.android.znajdzjadlo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SecondScreen extends Activity {

    private ImageButton ibtnBack; //definicja przycisku graficznego
    private WebView wvResults; //definicja pokazania wynikow

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);

        //metoda cofaj�ca do ekranu glownego
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondScreen.this, EkranGlowny.class));
                finish();
            }
        });

        Intent intent = getIntent();
        String place = intent.getStringExtra("etPlace"); //odebranie danych z edit text
        String dish = intent.getStringExtra("etDish"); //j.w.
        String query = place + "  " + dish; //utworzenie zapytania
        String url = "https://www.google.com/search?q="; //strona googla

        wvResults = (WebView) findViewById(R.id.wvResults);
        wvResults.getSettings().setJavaScriptEnabled(true);
        wvResults.loadUrl(url + query); //zaladowanie url (pokazanie wyniku)
        wvResults.setWebViewClient(new SecondWebViewClient());

    }

    //klasa pomocnicza do pokazania wynikow wyrzuconych przez google
    private class SecondWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    //klikniecie na linku z wynikami
    public void openResult() {
        Intent thirdScreen = new Intent(getApplicationContext(), ThirdScreen.class);
        thirdScreen.putExtra("wvResults", wvResults.getUrl().toString());
        startActivity(thirdScreen);
        finish();
        Log.d("status", "dziala");
    }

}
