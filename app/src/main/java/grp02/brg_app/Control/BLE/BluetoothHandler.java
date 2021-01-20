package grp02.brg_app.Control.BLE;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import grp02.brg_app.Control.BLE.Blessed.BluetoothBytesParser;
import grp02.brg_app.Control.BLE.Blessed.BluetoothCentral;
import grp02.brg_app.Control.BLE.Blessed.BluetoothCentralCallback;
import grp02.brg_app.Control.BLE.Blessed.BluetoothPeripheral;
import grp02.brg_app.Control.BLE.Blessed.BluetoothPeripheralCallback;
import grp02.brg_app.Control.BLE.Blessed.GattStatus;
import grp02.brg_app.Control.BLE.Blessed.HciStatus;
import grp02.brg_app.Control.BLE.Blessed.WriteType;
import grp02.brg_app.Timber.Timber;

import static android.bluetooth.BluetoothGatt.CONNECTION_PRIORITY_HIGH;
import static grp02.brg_app.Control.BLE.Blessed.BluetoothBytesParser.bytes2String;


public class BluetoothHandler {

    private static volatile BluetoothHandler single_instance = null;
    private final Context context;
    public BluetoothCentral central;
    private BluetoothBytesParser parser = new BluetoothBytesParser();
    private final Handler handler = new Handler();
    private boolean killConnection = false;

    // Intent constants
    public static final String ESP32_EXTRA = "ESP32.EXTRA";

    //UUIDs for Bryg Coffee maker
    public static final UUID ESP32_SERVICE_UUID = UUID.fromString("4fafc201-1fb5-459e-8fcc-c5c9c331914b");
    private static final UUID ESP32_CHARACTERISTIC_UUID = UUID.fromString("beb5483e-36e1-4688-b7f5-ea07361b26a8");

    // UUIDs for the Device Information service (DIS)
    private static final UUID DIS_SERVICE_UUID = UUID.fromString("0000180A-0000-1000-8000-00805f9b34fb");
    private static final UUID MANUFACTURER_NAME_CHARACTERISTIC_UUID = UUID.fromString("00002A29-0000-1000-8000-00805f9b34fb");
    private static final UUID MODEL_NUMBER_CHARACTERISTIC_UUID = UUID.fromString("00002A24-0000-1000-8000-00805f9b34fb");

    private static final UUID[] uuids = {ESP32_SERVICE_UUID};

    public final BluetoothCentralCallback BluetoothCentralCallback = new BluetoothCentralCallback() {
        @Override
        public void onConnectedPeripheral(@NotNull BluetoothPeripheral peripheral) {
            Timber.i("connected to '%s'", peripheral.getName());
        }

        @Override
        public void onConnectionFailed(@NotNull BluetoothPeripheral peripheral, @NotNull HciStatus status) {
            Timber.e("connection '%s' failed with status %s", peripheral.getName(), status);
        }

        @Override
        public void onDisconnectedPeripheral(@NotNull BluetoothPeripheral peripheral, @NotNull HciStatus status) {
            Timber.i("disconnected '%s' with status %s", peripheral.getName(), status);
            if(!killConnection){
                // Reconnect to this device when it becomes available again
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        central.autoConnectPeripheral(peripheral, peripheralCallback);
                    }
                }, 5000);
            } else {
                killConnection = false;
            }


        }

        @Override
        public void onDiscoveredPeripheral(@NotNull BluetoothPeripheral peripheral, @NotNull ScanResult scanResult) {
            Timber.i("Found peripheral '%s'", peripheral.getName());
            central.stopScan();
            central.connectPeripheral(peripheral, peripheralCallback);
        }

        @Override
        public void onScanFailed(int errorCode) {
            Timber.i("scanning failed with error %d", errorCode);
        }

        @Override
        public void onBluetoothAdapterStateChanged(int state) {
            Timber.i("bluetooth adapter changed state to %d", state);
            if (state == BluetoothAdapter.STATE_ON) {
                // Bluetooth is on now, start scanning again
                // Scan for peripherals with a certain service UUIDs
                central.startPairingPopupHack();
                central.scanForPeripheralsWithServices(new UUID[]{ESP32_SERVICE_UUID});
            }
        }
    };

    public final BluetoothPeripheralCallback peripheralCallback = new BluetoothPeripheralCallback() {
        @Override
        public void onServicesDiscovered(@NotNull BluetoothPeripheral peripheral) {
            Timber.i("discovered services");


            // Request a higher MTU, iOS always asks for 185
            peripheral.requestMtu(185);

            // Request a new connection priority
            peripheral.requestConnectionPriority(CONNECTION_PRIORITY_HIGH);

            // Read manufacturer and model number from the Device Information Service
            //peripheral.readCharacteristic(DIS_SERVICE_UUID, MANUFACTURER_NAME_CHARACTERISTIC_UUID);
            //peripheral.readCharacteristic(DIS_SERVICE_UUID, MODEL_NUMBER_CHARACTERISTIC_UUID);

            // Try to turn on notifications characteristics
            peripheral.setNotify(ESP32_SERVICE_UUID, ESP32_CHARACTERISTIC_UUID, true);

        }

        @Override
        public void onNotificationStateUpdate(@NotNull BluetoothPeripheral peripheral, @NotNull BluetoothGattCharacteristic characteristic, @NotNull GattStatus status) {
            if(status == GattStatus.SUCCESS) {
                final boolean isNotifying = peripheral.isNotifying(characteristic);
                Timber.i("SUCCESS: Notify set to '%s' for %s", isNotifying, characteristic.getUuid());

            }
        }

        @Override
        public void onCharacteristicUpdate(@NotNull BluetoothPeripheral peripheral, @NotNull byte[] value, @NotNull BluetoothGattCharacteristic characteristic, @NotNull GattStatus status) {
            if (status != GattStatus.SUCCESS) return;

            UUID characteristicUUID = characteristic.getUuid();
            BluetoothBytesParser parser = new BluetoothBytesParser(value);

            if(characteristicUUID.equals(ESP32_CHARACTERISTIC_UUID)){
                //
            }


        }

        @Override
        public void onCharacteristicWrite(@NotNull BluetoothPeripheral peripheral, @NotNull byte[] value, @NotNull BluetoothGattCharacteristic characteristic, @NotNull GattStatus status) {
            if (status == GattStatus.SUCCESS) {
                Timber.i("SUCCESS: Writing <%s> to <%s>", bytes2String(value), characteristic.getUuid());
            } else {
                Timber.i("ERROR: Failed writing <%s> to <%s> (%s)", bytes2String(value), characteristic.getUuid(), status);
            }
        }

        @Override
        public void onMtuChanged(@NotNull BluetoothPeripheral peripheral, int mtu, @NotNull GattStatus status) {
            Timber.i("new MTU set: %d", mtu);
        }
    };

    private BluetoothHandler(Context context){
        this.context = context;

        Timber.plant(new Timber.DebugTree());

        central = new BluetoothCentral(context, BluetoothCentralCallback, new Handler());

    }

    public static BluetoothHandler getInstance(Context context){

        if (single_instance == null){
            synchronized (BluetoothHandler.class){
                if (single_instance == null){
                    single_instance = new BluetoothHandler(context.getApplicationContext());
                }
            }
        }

        return single_instance;
    }

    public boolean writeToCharacteristik(String value, UUID Service, UUID Characteristic, WriteType type){
        byte[] bValue = value.getBytes();
        List<BluetoothPeripheral> pList = central.getConnectedPeripherals();
        if(!pList.isEmpty()){
            if (pList.get(0).getService(Service).getUuid().equals(Service)){
                pList.get(0).writeCharacteristic(ESP32_SERVICE_UUID, ESP32_CHARACTERISTIC_UUID, bValue , WriteType.WITH_RESPONSE);
                pList.clear();
                return true;
            }
        }
        return false;
    }

    public boolean isConnected(){
        List<BluetoothPeripheral> peripheralList = central.getConnectedPeripherals();
        if(!peripheralList.isEmpty()){
            for (int i = 0; i < peripheralList.size() ; i++) {
                List<BluetoothGattService> serviceList = peripheralList.get(i).getServices();
                for (int j = 0; j < serviceList.size() ; j++) {
                    serviceList.get(j).getUuid().equals(ESP32_SERVICE_UUID);
                    return true;
                }
            }
        }

        return false;
    }

    public final void scanAndConnect(){

        central.scanForPeripheralsWithServices(uuids);

        //central.connectPeripheral(central.getPeripheral("40:F5:20:70:63:8E"),peripheralCallback);

    }

    public final void disconnect(){
        //central.cancelConnection(central.getConnectedPeripherals().get(0));
        killConnection = true;
        central.getConnectedPeripherals().get(0).cancelConnection();
    }
}
