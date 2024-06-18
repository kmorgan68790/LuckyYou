package learn.luckyyou.data;

import learn.luckyyou.models.Users;

import java.util.List;

public interface UserRepository {
    List<Users> findAll();

    Users findById(int userId);

    Users add(Users users);

    boolean update(Users users);

    boolean deleteById(int userId);
}
