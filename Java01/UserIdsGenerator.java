
public class UserIdsGenerator {
    static UserIdsGenerator instance;
    int next_id = 0;
    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }
    public int generateId() {
        return next_id++;
    }
}

