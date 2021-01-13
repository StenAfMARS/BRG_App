package grp02.brg_app.Control;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;
import java.util.Set;

public class bluetoothDevisis {
    public ArrayList getBluetoothDevice(){
    BluetoothAdapter bAdapter =BluetoothAdapter.getDefaultAdapter();
    Set<BluetoothDevice> bt = bAdapter.getBondedDevices();
    String[] bluetoothDevices = new String[bt.size()];
    ArrayList list = new ArrayList();
    int i = 0;
    if( bt.size() > 0){
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
