package com.evliion.ev.repository;

import com.evliion.ev.model.ChargePoint;
import com.evliion.ev.model.Poll;
import com.evliion.ev.model.Station;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 */
@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
	
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
	List<Station> findByGeoLocation(@Param("latitude") double lat, @Param("longitued") double lng, @Param("distance") int radius);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update Station s set s.active = "+ false + " where s.id = :stationId",nativeQuery=true)
	void deactivateStore(@Param("stationId") Long stationId);
	
	//Page<Station> findByActive(boolean active, Pageable pageable);
}