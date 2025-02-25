package top.anyel.atlas.repositories;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 25/02/2025
 */
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.anyel.atlas.document.SensorData;

@Repository
public interface SensorDataRepository extends MongoRepository<SensorData, String> {
}