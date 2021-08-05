package com.studyweb.studyweb.modules.zone;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ZoneService {

    private final ZoneRepository zoneRepository;


    @PostConstruct
    public void initZoneData() throws IOException {
        if (zoneRepository.count() == 0){
            Resource resource = new ClassPathResource("zones_kr.csv");
            List<Zone> zones = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8).stream()
                    .map(line -> {
                        String[] split = line.split(",");
                        return Zone.builder()
                                .city(split[0])
                                .localNameOfCity(split[1])
                                .province(split[2])
                                .build();
                    }).collect(Collectors.toList());

            zoneRepository.saveAll(zones);
        }
    }

    public List<String> getAllZones() {
        return zoneRepository.findAll().stream()
                .map(Zone::toString)
                .collect(Collectors.toList());
    }
}
