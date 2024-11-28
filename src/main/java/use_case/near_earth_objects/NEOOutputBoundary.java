package use_case.near_earth_objects;

import entity.NEOEntity;
import java.util.List;

public interface NEOOutputBoundary {
    void presentNEOData(List<NEOEntity> neoData);
}
