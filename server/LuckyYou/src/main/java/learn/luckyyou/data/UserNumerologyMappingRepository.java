package learn.luckyyou.data;

import learn.luckyyou.models.UserNumerologyMapping;

import java.util.List;

public interface UserNumerologyMappingRepository {

    List<UserNumerologyMapping> findAll();
    UserNumerologyMapping findById(int userNumerologyMappingId);
    List<UserNumerologyMapping> findByUserId(int userId);
    List<UserNumerologyMapping> findByNumerologyType(String numerologyType);
    List<UserNumerologyMapping> findByNumerologyDescriptionId(int numerologyDescriptionId);
    UserNumerologyMapping findByUserIdAndNumerologyType(int userId, String numerologyType);
    boolean saveNumerologyMapping(UserNumerologyMapping mapping);
    boolean deleteNumerologyMappingsByUserId(int userId);
}
