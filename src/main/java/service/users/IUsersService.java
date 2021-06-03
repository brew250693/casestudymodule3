package service.users;

import model.Users;

public interface IUsersService {
    void signUp(Users users);

    boolean login(Users users);

    boolean logout();

    boolean updateUser(Users users);

    boolean changePassword(Users users);
}
