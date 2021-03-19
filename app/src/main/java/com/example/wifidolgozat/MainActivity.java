package com.example.wifidolgozat;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView info;
    private BottomNavigationView nbar;

    private Boolean wifi=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        nbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.wifion:
                        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                        wifi.setWifiEnabled(true);
                        info.setText("Wifi bekapcsolva");
                        break;
                    case R.id.wifioff:
                        WifiManager wifi2 = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                        wifi2.setWifiEnabled(false);
                        info.setText("Wifi kikapcsolva");
                        break;
                    case R.id.wifiinfo:
                        csatlakoztatvaE();
                        break;
                }
                return true;
            }
        });
    }

    public void init(){
        info=findViewById(R.id.tvinfo);
        nbar=findViewById(R.id.navbar);
    }

    public void csatlakoztatvaE(){
        ConnectivityManager cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nwi=cm.getActiveNetworkInfo();

        if (nwi !=null && nwi.isConnected()){
            wifi=nwi.getType()==cm.TYPE_WIFI;
            if(wifi){
                WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
                info.setText("Ip cím: "+ip);
            }
        }
        else{
            info.setText("Nem csatlakozik wifi hálózathoz");
        }
    }

}