package com.example.demo.service.busstop;

import com.example.demo.domain.busstop.BusStopRepository;
import com.example.demo.dto.BusStopSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class BusStopService {
    @Autowired
    private final BusStopRepository busStopRepository;

    public BusStopService(BusStopRepository busStopRepository) {
        this.busStopRepository = busStopRepository;
    }

    @Transactional
    public Long save(BusStopSaveRequestDto requestDto){
        return busStopRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void delete(Long id){
        busStopRepository.findById(id).orElseThrow(()->
            new IllegalArgumentException("즐겨찾기 목록에 해당 정류장이 없습니다."));
        busStopRepository.deleteById(id);
    }
}
