public interface UsersList {
    public void addUser(User user);
    public User getUserById(int id) throws UserNotFoundException;
    public User getUserByIndex(int index) throws UserNotFoundException;
    public int getUsersCount();
}
