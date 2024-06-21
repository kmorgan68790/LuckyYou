package learn.luckyyou.data;


import learn.luckyyou.models.NumerologyDescription;

import java.util.List;

public interface NumerologyDescriptionRepository {
    List<NumerologyDescription> findAll();

    NumerologyDescription findById(int numerologyDescriptionId);
    NumerologyDescription findByNumerologyType(String numerologyType);
    NumerologyDescription findByNumerologyDescription(String numerologyDescription);
    NumerologyDescription findByNumerologyNumber(int numerologyNumber);
}
