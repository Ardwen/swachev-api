package com.evliion.ev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evliion.ev.model.ChargePoint;
import com.evliion.ev.model.Station;

@Repository
public interface ChargePointRepository extends JpaRepository<ChargePoint, Long>{

	@Query("SELECT s FROM Station s where s.id = :stationId")
	Optional<Station> getStation(@Param("stationId") Long satationId);

	List<ChargePoint> findByStationId(Long stationId);

	@Query(value="SELECT * FROM charge_points as cp where cp.station_id in (" +
			"select st.id from (SELECT\n" + 
			"  s.id , (\n" + 
			"    6371 * acos (\n" + 
			"      cos ( radians(:latitude) )\n" + 
			"      * cos( radians( s.latitude ) )\n" + 
			"      * cos( radians( s.longitued ) - radians(:longitued) )\n" + 
			"      + sin ( radians(:latitude) )\n" + 
			"      * sin( radians( s.latitude ) )\n" + 
			"    )\n" + 
			"  ) AS distance\n" + 
			"FROM stations as s \n" + 
			"HAVING distance < :distance\n" + 
			"ORDER BY distance\n" + 
			"LIMIT 0 , 20) as st)", nativeQuery=true)
	List<ChargePoint> findByGeoLocation(@Param("latitude") double lat, @Param("longitued") double lng, @Param("distance") int radius);


}
