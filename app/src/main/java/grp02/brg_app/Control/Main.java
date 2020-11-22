package grp02.brg_app.Control;

public class Main {
    // Singleton class
    private static Main Instance;

    private Main() {}

    public static Main getInstance() {
        if (Instance.equals(null)) {
            Instance = new Main();
        }

        return Instance;
    }

}
