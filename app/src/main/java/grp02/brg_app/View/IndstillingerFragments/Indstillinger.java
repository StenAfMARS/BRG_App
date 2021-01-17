package grp02.brg_app.View.IndstillingerFragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import grp02.brg_app.Control.BLE.BluetoothController;
import grp02.brg_app.Control.BLE.BluetoothScanner;
import grp02.brg_app.R;


public class Indstillinger extends Fragment implements View.OnClickListener {

    TextView textView;
    Button bt_BTN, blueetoothConnect;
    BluetoothScanner scanner = BluetoothScanner.getInstance();
    BluetoothController BLEController = BluetoothController.getInstance();

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_bluetooth_i_activity, container, false);

        blueetoothConnect = rod.findViewById(R.id.bluetoothConnect);
        bt_BTN = rod.findViewById(R.id.BTN_Bluetooth);
        bt_BTN.setOnClickListener(this);
        blueetoothConnect.setOnClickListener(this);

        textView = rod.findViewById(R.id.textView5);
        blueetoothConnect.setText("Enheder");
        return rod;
    }


    @Override
    public void onClick(View v) {
        if (v == bt_BTN){
            if(!(bt_BTN.getBackgroundTintList() == ColorStateList.valueOf(Color.BLUE))) {

                // State Bluetooth Connected!

                bt_BTN.setBackgroundResource(R.drawable.ic_baseline_bluetooth_audio_24);
                bt_BTN.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));

            } else {

                // State Bluetooth NOT Connected!
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
        }
    }
}
