package web.dao;

import java.util.List;
import web.model.Role;
import web.model.User;

public interface UserDAO {

    List<User> getAllUsers();

    void save(User user);

    void removeUser(long id);

    User getById(long id);

    User getByUsername(String username);

    Role getRole(String role);

    List<Role> getAllRoles();
}
