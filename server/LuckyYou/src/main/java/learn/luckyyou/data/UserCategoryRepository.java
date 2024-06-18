package learn.luckyyou.data;

import learn.luckyyou.models.UserCategory;

import java.util.List;

public interface UserCategoryRepository {
    List<UserCategory> findAll();

    UserCategory findById(int userCategoryId);
    UserCategory findByCategoryId(int categoryId);
    UserCategory findByUserId(int userId);

    UserCategory findByNumerologyId(int numerologyId);

    UserCategory findByZodiacId(int zodiacId);

    UserCategory findByConcordGroupId(int concordGroupId);
//    UserCategory add(Users users);
}
