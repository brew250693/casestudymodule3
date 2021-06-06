package service.users;

import model.Users;

import java.util.List;

public interface IUsersService {
    void signUp(Users users);

    boolean login(String email, String password);

    boolean updateProfile(Users users);

    boolean changePassword(Users users);

    Users selectUserByEmail(String email);
}
