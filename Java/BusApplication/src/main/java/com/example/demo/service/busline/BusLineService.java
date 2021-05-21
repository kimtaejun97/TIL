package com.example.demo.service.busline;

import com.example.demo.domain.busline.BusLineRepository;
import com.example.demo.dto.BusLineSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BusLineService {
    private final BusLineRepository busLineRepository;

    @Transactional
    public Long save(BusLineSaveRequestDto requestDto){
        return busLineRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void delete(Long id){
        busLineRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("즐겨찾기 목록에 해당 노선이 없습니다."));
        busLineRepository.deleteById(id);
    }

}
