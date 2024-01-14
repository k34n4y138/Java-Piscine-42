
public class UsersArrayList implements UsersList {
    private User[] users = new User[10];
    private int count = 0;

    public void addUser(User user) {
        if (count == users.length) {
            User[] new_users = new User[users.length  + users.length / 2];
            System.arraycopy(users, 0, new_users, 0, users.length);
            users = new_users;
        }
        users[count++] = user;
    }
    public User getUserById(int id) throws UserNotFoundException {
        for (int i = 0; i < count; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User with id: " + id + " not found");
    }
    public User getUserByIndex(int index) throws UserNotFoundException {
        if (index < count) {
            return users[index];
        }
        throw new UserNotFoundException("User with index: " + index + " not found");
    }
    public int getUsersCount() {
        return count;
    }
}
