package alexandria.alexandria.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepo locationRepo;

    @Autowired
    public LocationService(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    public List<Location> getLocations() {
        return locationRepo.findAll();
    }

    public Location getLocationById(long id) {
        return locationRepo.findById(id).orElse(null);
    }

    public Location saveLocation(Location location) {
        return locationRepo.save(location);
    }

    public List<Location> deleteLocation(long id) {
        locationRepo.deleteById(id);
        return locationRepo.findAll();
    }
}
