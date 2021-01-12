package grp02.brg_app.Control;

public class MainController {
    // Singleton class
    private static MainController Instance;

    private MainController() {}

    public static MainController getInstance() {
        if (Instance.equals(null)) {
            Instance = new MainController();
        }

        return Instance;
    }

}
