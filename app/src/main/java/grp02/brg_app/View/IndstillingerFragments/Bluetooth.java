package grp02.brg_app.View.IndstillingerFragments;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Set;

import grp02.brg_app.R;

public class Bluetooth extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View bluetooth = inflater.inflate(R.layout.fragment_bluetooth, container, false);

        // Inflate the layout for this fragment
        return bluetooth;
    }
    public ArrayList getBluetoothDevice(){
        BluetoothAdapter bAdapter =BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> bt = bAdapter.getBondedDevices();
        String[] bluetoothDevices = new String[bt.size()];
        ArrayList list = new ArrayList();
        int i = 0;     if( bt.size() > 0){
            for(BluetoothDevice device : bt){
                String devicename = device.getName();
                String macAddress = device.getAddress();
                list.add("Name: " +devicename+ "MAC adress: " +macAddress);
                i++;
            }
        }
        return list;
    }

}