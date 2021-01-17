package grp02.brg_app.Control.BLE;

public class BluetoothHandler {
    private static BluetoothHandler single_instance = null;


    private BluetoothHandler(){}

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
