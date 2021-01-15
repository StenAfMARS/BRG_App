package grp02.brg_app.Control.BLE;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
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

    public BluetoothGatt getConnection()
    {
        return gatt;
    }
}
