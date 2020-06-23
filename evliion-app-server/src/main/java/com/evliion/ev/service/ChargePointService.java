package com.evliion.ev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evliion.ev.exception.ResourceNotFoundException;
import com.evliion.ev.model.ChargePoint;
import com.evliion.ev.model.ChargePointStatus;
import com.evliion.ev.model.ChargePointType;
import com.evliion.ev.model.Station;
import com.evliion.ev.payload.ChargePointRequest;
import com.evliion.ev.payload.ChargePointResponse;
import com.evliion.ev.repository.ChargePointRepository;
import com.evliion.ev.util.ModelMapper;

@Service
public class ChargePointService {

	@Autowired
	private ChargePointRepository chargePointRepository;

	public ChargePoint addChargePoint(ChargePointRequest chargePointRequest) {
		ChargePoint chargePoint = new ChargePoint();

		chargePoint.setChargePointIdentifier(chargePointRequest.getChargePointIdentifier());
		chargePoint.setCapacity(chargePointRequest.getCapacity());
		chargePoint.setType(ChargePointType.valueOf(chargePointRequest.getType().toUpperCase()));
		chargePoint.setStatus(ChargePointStatus.ACTIVE);

		Station station =  chargePointRepository.getStation(chargePointRequest.getStationId())
				.orElseThrow(() -> new ResourceNotFoundException("Station", "stationId", chargePointRequest.getStationId()));
		chargePoint.setStation(station);

		chargePointRepository.save(chargePoint);
		return chargePoint;
	}

	public ChargePointResponse getChargePointById(Long chargePointId) {
		ChargePoint chargePoint = chargePointRepository.findById(chargePointId)
				.orElseThrow(() -> new ResourceNotFoundException("ChargePoint", "chargePointId", chargePointId));
		return ModelMapper.mapChargePointToChargePointResponse(chargePoint);
	}

	public List<ChargePointResponse> getChargerPointByStationId(Long stationId) {
		List<ChargePoint> chargePointList = chargePointRepository.findByStationId(stationId);
		return chargePointList.stream()
				.map(chargePoint -> ModelMapper.mapChargePointToChargePointResponse(chargePoint))
				.collect(Collectors.toList());
	}


	public List<ChargePointResponse> getAllChargePoints(double geoLat, double geoLng, int radius ){
		List<ChargePoint> chargePointList = chargePointRepository.findByGeoLocation(geoLat, geoLng, radius);
		return chargePointList.stream()
				.map(chargePoint -> ModelMapper.mapChargePointToChargePointResponse(chargePoint))
				.collect(Collectors.toList());
	}



}
