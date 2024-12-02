package use_case.near_earth_objects;

import entity.NearEarthObjectEntity;
import java.util.List;

public interface NEOOutputBoundary {
    void presentNEOData(List<NearEarthObjectEntity> neoData);
}
