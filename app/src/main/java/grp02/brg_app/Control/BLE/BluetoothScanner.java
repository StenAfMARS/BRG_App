package grp02.brg_app.Control.BLE;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.ParcelUuid;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BluetoothScanner {

    private UUID serviceUUID = UUID.fromString("4fafc201-1fb5-459e-8fcc-c5c9c331914b");
    private UUID[] serviceUUIDs = new UUID[] {serviceUUID};
    private List<ScanFilter> filters = null;

    private BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothLeScanner scanner = adapter.getBluetoothLeScanner();
    private BluetoothDevice device;

    private static volatile BluetoothScanner single_instance = null;

    private BluetoothScanner(){ }

    public static BluetoothScanner getInstance(){
        if (single_instance == null){
            synchronized (BluetoothScanner.class){
                if (single_instance == null){
                    single_instance = new BluetoothScanner();
                }
            }
        }

        return single_instance;
    }

    private final ScanCallback scanCallback = new ScanCallback(){
        @Override
        public void onScanResult(int callBackType, ScanResult result){
            device = result.getDevice();

        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            // Ignore for now
        }

        @Override
        public void onScanFailed(int errorCode) {
            // Ignore for now
        }
    };



    public BluetoothDevice Scan(){
        if(serviceUUIDs != null) {
            filters = new ArrayList<>();
            for (UUID serviceUUID : serviceUUIDs) {
                ScanFilter filter = new ScanFilter.Builder()
                        .setServiceUuid(new ParcelUuid(serviceUUID))
                        .build();
                filters.add(filter);
            }
        }

        ScanSettings scanSettings = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
                .setCallbackType(ScanSettings.CALLBACK_TYPE_FIRST_MATCH)
                .setMatchMode(ScanSettings.MATCH_MODE_AGGRESSIVE)
                .setNumOfMatches(ScanSettings.MATCH_NUM_ONE_ADVERTISEMENT)
                .setReportDelay(0L)
                .build();

        if (scanner != null){
          scanner.startScan(filters, scanSettings, scanCallback);
        }

        return device;
    }
}
