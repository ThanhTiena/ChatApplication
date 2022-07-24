package Data;

import java.util.ArrayList;
public class DataStorage {
    private static DataStorage instance = new DataStorage();
    private DataStorage() {
    }

    public static DataStorage getInstance(){
        return instance;
    }
}

