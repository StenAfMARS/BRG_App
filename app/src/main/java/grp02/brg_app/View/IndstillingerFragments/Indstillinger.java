package grp02.brg_app.View.IndstillingerFragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.content.ContextWrapper;
import android.widget.Toast;


import com.airbnb.lottie.animation.content.Content;

import java.util.UUID;

import grp02.brg_app.Control.BLE.Blessed.BluetoothCentral;
import grp02.brg_app.Control.BLE.Blessed.BluetoothCentralCallback;
import grp02.brg_app.Control.BLE.Blessed.BluetoothPeripheral;
import grp02.brg_app.Control.BLE.Blessed.WriteType;
import grp02.brg_app.Control.BLE.BluetoothHandler;
import grp02.brg_app.R;
import grp02.brg_app.View.IndstillingerActivity1;


public class Indstillinger extends Fragment implements View.OnClickListener {

    Button bt_BTN, blueetoothConnect, info;
    BluetoothPeripheral peripheral = null;
    BluetoothCentralCallback callback;
    Handler handler;
    BluetoothHandler BLEhandler = BluetoothHandler.getInstance(IndstillingerActivity1.context);

    UUID[] uuids = {UUID.fromString("4fafc201-1fb5-459e-8fcc-c5c9c331914b")};


    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothPeripheral peripheral = getPeripheral(intent.getStringExtra(BluetoothHandler.ESP32_EXTRA));

        }
    };

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_bluetooth_i_activity, container, false);

        blueetoothConnect = (Button) rod.findViewById(R.id.bluetoothConnect);
        bt_BTN = (Button) rod.findViewById(R.id.BTN_Bluetooth);
        bt_BTN.setOnClickListener(this);
        blueetoothConnect.setOnClickListener(this);
        TextView wificonnectionTV = rod.findViewById(R.id.Tv_wificonnection);
        TextView wifiTV = rod.findViewById(R.id.TV_wifi);
        IndstillingerActivity1 indstillingerActivity1 = IndstillingerActivity1.getInstance();

        if(!indstillingerActivity1.getWifiSSID(IndstillingerActivity1.context).equals("not_connected")) {
            String output = indstillingerActivity1.getWifiSSID(IndstillingerActivity1.context);
            output.replace("\"", "");
            wificonnectionTV.setText(output);
        } else {
            wificonnectionTV.setVisibility(View.GONE);
            wifiTV.setVisibility(View.GONE);
        }

        blueetoothConnect.setText("Enheder");

        info = (Button) rod.findViewById(R.id.info);
        info.setText("App info");
        info.setOnClickListener(this);

        return rod;
    }


    @Override
    public void onClick(View v) {
        if (v == bt_BTN){
            if(!(bt_BTN.getBackgroundTintList() == ColorStateList.valueOf(Color.BLUE))) {

                // State Bluetooth Connected!

                BLEhandler.scanAndConnect();

                bt_BTN.setBackgroundResource(R.drawable.ic_baseline_bluetooth_audio_24);
                bt_BTN.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
            } else {

                // State Bluetooth NOT Connected!
                BLEhandler.disconnect();

                bt_BTN.setBackgroundResource(R.drawable.ic_baseline_bluetooth_24);
                bt_BTN.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(139, 90, 57)));

            }
        } else if (v==blueetoothConnect){
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.bluetoothFragment, new Bluetooth())
                    .addToBackStack(null)
                    .commit();
        } else if (v==info){
            AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
            build.setMessage(Html.fromHtml("<b>Applikationen er skabt af:</b><br><br>Adrian Roed Schøning<br>Mathias Sejrup Bærentzen<br>Peter Martin Skaarup<br>Simon Søborg<br>Jacob Chua Ziska<br><br>Softwareteknologi studerende ved <b>Danmarks Teknikske Universitet</b>", 1));
            build.setPositiveButton("OK", (dialog, which) -> Toast.makeText(getContext(), "Enjoy", Toast.LENGTH_LONG).show());
            build.show();
        }
    }

    private BluetoothPeripheral getPeripheral(String peripheralAddress) {
        BluetoothCentral central = BluetoothHandler.getInstance(getContext().getApplicationContext()).central;
        return central.getPeripheral(peripheralAddress);
    }


}
