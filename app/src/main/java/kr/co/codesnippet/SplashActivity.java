package kr.co.codesnippet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends Activity {
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
     ctx = this;

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent i = new Intent(ctx, MainActivity.class);
                startActivity(i);
                finish();

            }
        }, 3000);
    }
}
