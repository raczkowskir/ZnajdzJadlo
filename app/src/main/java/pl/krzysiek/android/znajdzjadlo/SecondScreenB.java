package pl.krzysiek.android.znajdzjadlo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SecondScreenB extends Activity {

    private ImageButton ibtnBack; //definicja przycisku graficznego

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen_a);

        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);

        //metoda cofaj�ca do ekranu glownego
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondScreenB.this, EkranGlowny.class));
                finish();
            }
        });


    }



}
