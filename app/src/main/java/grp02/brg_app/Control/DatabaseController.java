package grp02.brg_app.Control;

import android.content.Context;

public class DatabaseController {

    private static DatabaseController Instance;

    private DatabaseController() {}

    public static DatabaseController getInstance() {
        if (Instance == null) {
            Instance = new DatabaseController();
        }

        return Instance;
    }

    public IDatabaseConnector db;

    public void UseSQL(Context context){
        new Thread(() -> {
            db = new StorageController(context);
        }).start();
    }

    public void UseJson(Context context){
        db = new JsonDBController(context);
    }

    public IDatabaseConnector getDB(){
        return db;
    }

}
