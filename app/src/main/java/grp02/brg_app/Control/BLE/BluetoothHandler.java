package grp02.brg_app.Control.BLE;

import java.util.UUID;

public class BluetoothHandler {

    private static volatile BluetoothHandler single_instance = null;

    //UUIDs for ESP32
    private static final UUID ESP32_SERVICE_UUID = UUID.fromString("4fafc201-1fb5-459e-8fcc-c5c9c331914b");
    private static final UUID ESP32_CHARACTERISTIC_UUID = UUID.fromString("beb5483e-36e1-4688-b7f5-ea07361b26a8");





    private BluetoothHandler(){

    }

    public static BluetoothHandler getInstance(){
        if (single_instance == null){
            synchronized (BluetoothHandler.class){
                if (single_instance == null){
                    single_instance = new BluetoothHandler();
                }
            }
        }

        return single_instance;
    }
}
