package com.example.demo.service.busline;

import com.example.demo.domain.busline.BusLine;
import com.example.demo.domain.busline.BusLineRepository;
import com.example.demo.dto.BusLineSaveRequestDto;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusLineRepositoryTest {
    @Autowired
    private BusLineRepository busLineRepository;

    @After
    public void cleanUp(){
        busLineRepository.deleteAll();
    }


    @Test
    public void save(){
        long id = 2314;
        String name = "전남대학교";

        BusLineSaveRequestDto busLineSaveRequestDto =  BusLineSaveRequestDto.builder()
                .id(id)
                .name(name)
                .build();
//        busLineService.save(busLineSaveRequestDto);
        busLineRepository.save(BusLine.builder()
                .id(id)
                .name(name)
                .build());

        List<BusLine> all = busLineRepository.findAll();
        assertThat(all.get(0).getId()).isEqualTo(id);
        assertThat(all.get(0).getName()).isEqualTo(name);
    }



}
