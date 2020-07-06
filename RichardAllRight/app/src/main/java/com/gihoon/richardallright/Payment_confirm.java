package com.gihoon.richardallright;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Payment_confirm extends AppCompatActivity {
    private static String TAG = "Dynamic_confirm";
    private String PG = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent appLinkIntent = getIntent();
        String appLinkData = appLinkIntent.getStringExtra("pg_token");
        System.out.println(appLinkData);
        if(appLinkData.equals("cancel") || appLinkData.equals("fail")){
            setContentView(R.layout.payfail);
            Button homebt = findViewById(R.id.homebutton);
            homebt.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getApplication(), Map.class);
                    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(it);
                    finish();
                }
            });
        }else{
            //실제 서비스에서는 카카오페이로 요청보내서 결제 승인해야함
            setContentView(R.layout.paysuccess);
            Button reserbt = findViewById(R.id.reserconfirm);
            reserbt.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getApplication(), Information.class);
                    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(it);
                    finish();
                }
            });
        }
    }
}