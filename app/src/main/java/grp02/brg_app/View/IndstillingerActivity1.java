package grp02.brg_app.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import grp02.brg_app.Control.BLE.BluetoothController;
import grp02.brg_app.R;

public class IndstillingerActivity1 extends AppCompatActivity implements View.OnClickListener {

    Button bt_BTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger1);
        bt_BTN = findViewById(R.id.BTN_Bluetooth);
        // Navigation
        // ##########################################################
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        // Set nav highlighted button
        bottomNav.setSelectedItemId(R.id.nav_IndstillingerBtn);
        // Perform ItemSelectedListener
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_HomeBtn:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_BroegBtn:
                        startActivity(new Intent(getApplicationContext(), BroegActivity1.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_HistorikBtn:
                        startActivity(new Intent(getApplicationContext(), HistorikActivity1.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_RensBtn:
                        startActivity(new Intent(getApplicationContext(), RensActivity1.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_IndstillingerBtn:
                        return true;
                }
                return false;
            }
        });
        // ##########################################################

        initBtn();

    }
    public void initBtn(){
        bt_BTN.setOnClickListener(this);
    }

    private static final int REQUEST_ENABLE_BT = 1;
    private static final int ACCESS_LOCATION_REQUEST = 2;
    private BluetoothGatt GATTConnection;

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        if (v == bt_BTN){
            if(!(bt_BTN.getBackgroundTintList() == ColorStateList.valueOf(Color.BLUE))) {

                // State Bluetooth Connected!
                BluetoothController controller = BluetoothController.getInstance();
                controller.scan();
                if(controller.scan() != null){
                    GATTConnection = controller.getConnection();
                    if(controller.connect(this.getApplicationContext()) != null)
                    {
                        
                        bt_BTN.setBackgroundResource(R.drawable.ic_baseline_bluetooth_audio_24);
                        bt_BTN.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                    }

                }else{

                }

            } else {

                // State Bluetooth NOT Connected!
                bt_BTN.setBackgroundResource(R.drawable.ic_baseline_bluetooth_24);
                bt_BTN.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(139, 90, 57)));

            }
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        if (!isBluetoothEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {

        }
    }

    private boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter == null) return false;

        return bluetoothAdapter.isEnabled();
    }




}