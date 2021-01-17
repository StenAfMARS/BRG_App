package grp02.brg_app.Control.BLE;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothProfile;
import android.content.Context;

public class BluetoothController {

    private static volatile BluetoothController single_instance = null;
    private BluetoothScanner scanner = BluetoothScanner.getInstance();

    private BluetoothDevice device;
    private BluetoothGatt gatt;


    private BluetoothController(){

    }

    public static BluetoothController getInstance(){
        if (single_instance == null){
            synchronized (BluetoothController.class){
                if (single_instance == null){
                    single_instance = new BluetoothController();
                }
            }
        }

        return single_instance;
    }

    public BluetoothDevice scan(){
        device = scanner.Scan();
        if (device != null)
            return device;
        return null;
    }

    public BluetoothGatt connect(Context context){
        gatt =  device.connectGatt(context, false, null, BluetoothDevice.TRANSPORT_LE);
            if(gatt != null){
                return gatt ;
            } else {
                return null;
            }
    }

    private final BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            String IntentAction;
            if(newState == BluetoothProfile.STATE_CONNECTED){


            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
        }
    };
}
