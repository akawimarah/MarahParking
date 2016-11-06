package com.akawi.marah.marahparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class mapActivity extends AppCompatActivity {
    private Button btnMoney;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        btnMoney =(Button)findViewById(R.id.btnMoney);
        bt=(Button)findViewById(R.id.bt);
        eventHandler();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itmLogOut:
                Toast.makeText(getBaseContext(),"logout",Toast.LENGTH_LONG).show();
                break;
            case R.id.itmSettings:
                Toast.makeText(getBaseContext(),"settings",Toast.LENGTH_LONG).show();
                break;


        }
        return true;

    }
    private void eventHandler(){
        btnMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mapActivity.this,payActivity.class);
                startActivity(i);
            }
        });

    bt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(mapActivity.this,MapsActivity1.class);
            startActivity(i);
        }
    });
    }

}
