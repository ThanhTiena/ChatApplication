package Data;

public class DataStorage {
    private static DataStorage instance = new DataStorage();

    private DataStorage() {
    }

    public static DataStorage getInstance(){
        return instance;
    }
}

