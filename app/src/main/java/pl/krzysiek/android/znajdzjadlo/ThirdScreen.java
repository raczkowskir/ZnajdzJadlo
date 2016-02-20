package pl.krzysiek.android.znajdzjadlo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class ThirdScreen extends Activity {

    ImageButton ibtnReturn; //definicja przycisku graficznego
    WebView wvSelected; //definicja pokazania zaznaczonego wyniku

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);

        ibtnReturn = (ImageButton) findViewById(R.id.ibtnReturn);

        //metoda cofajaca do drugiej aktywnosci
        ibtnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdScreen.this, SecondScreenA.class);
                finish();
            }
        });

        //odebranie zaznaczonego wyniku
        Intent intent = getIntent();
        String result = intent.getStringExtra("wvResults");
        wvSelected = (WebView) findViewById(R.id.wvSelected);
        wvSelected.getSettings().setJavaScriptEnabled(true);
        wvSelected.loadUrl(result); //wyswietlenie zaznaczonego wyniku
    }

    //klasa pomocnicza do pokazania wynikow wyrzuconych przez google
    private class ThirdWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
