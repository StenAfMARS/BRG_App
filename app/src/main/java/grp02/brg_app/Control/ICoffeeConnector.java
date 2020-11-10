package grp02.brg_app.Control;

import grp02.brg_app.Model.DTO_Coffee;

public interface ICoffeeConnector {
    void connect();
    void makeCoffee(DTO_Coffee Coffee);
}
