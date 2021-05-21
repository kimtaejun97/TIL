package service;

import Dto.BusLineRequestDto;
import Dto.BusStopRequestDto;
import domain.busline.BusLine;
import domain.busstop.BusStop;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FavoritesServiceTest {
    public FavoritesService favoritesService= new FavoritesService();

    @Before
    @After
    public void cleanup(){
        favoritesService.deleteAllLine();
        favoritesService.deleteAllStop();
    }

    @Test
    public void save(){
        Integer id = 1;
        String name = "광주역";

        // BusStop Test
        BusStopRequestDto busStopRequestDto = new BusStopRequestDto.Builder()
                .id(id)
                .name("광주역")
                .build();

        favoritesService.save(busStopRequestDto);

        List<BusStop> busStopList = favoritesService.findAllStops();
        assertThat(busStopList.get(0).getId()).isEqualTo(id);
        assertThat(busStopList.get(0).getName()).isEqualTo(name);


        // BusLine Test
        BusLineRequestDto busLineRequestDto = new BusLineRequestDto.Builder()
                .id(id)
                .name("광주역")
                .build();

        favoritesService.save(busLineRequestDto);

        List<BusLine> busLineList = favoritesService.findAllLines();
        assertThat(busLineList.get(0).getId()).isEqualTo(id);
        assertThat(busLineList.get(0).getName()).isEqualTo(name);

    }

    @Test
    public void deleteById(){
        Integer id = 1;
        String name = "광주역";

        //BusStop test
        BusStopRequestDto busStopRequestDto = new BusStopRequestDto.Builder()
                .id(id)
                .name("광주역")
                .build();

        favoritesService.save(busStopRequestDto);

        List<BusStop> busStopsList = favoritesService.findAllStops();
        assertThat(busStopsList.size()).isEqualTo(1);

        BusStopRequestDto busStopDelRequestDto =new BusStopRequestDto.Builder()
                .id(id)
                .build();

        favoritesService.delById(busStopDelRequestDto);

        List<BusStop> busStopsList2 = favoritesService.findAllStops();
        assertThat(busStopsList2.size()).isEqualTo(0);

        //BusLine Test
        BusLineRequestDto busLineRequestDto = new BusLineRequestDto.Builder()
                .id(id)
                .name("광주역")
                .build();

        favoritesService.save(busLineRequestDto);

        List<BusLine> busLineList = favoritesService.findAllLines();
        assertThat(busStopsList.size()).isEqualTo(1);

        BusLineRequestDto busLineDelRequestDto =new BusLineRequestDto.Builder()
                .id(id)
                .build();

        favoritesService.delById(busLineDelRequestDto);

        List<BusLine> busStopList2 = favoritesService.findAllLines();
        assertThat(busStopList2.size()).isEqualTo(0);


    }

    @Test
    public void findAll(){
        //BusStop Test
        favoritesService.save(new BusStopRequestDto.Builder()
                .id(1)
                .name("전남대")
                .build()
        );
        favoritesService.save(new BusStopRequestDto.Builder()
                .id(2)
                .name("전남대후문")
                .build()
        );

        List<BusStop> busStopList =favoritesService.findAllStops();
        assertThat(busStopList.size()).isEqualTo(2);
        assertThat(busStopList.get(0).getId()).isEqualTo(1);
        assertThat(busStopList.get(0).getName()).isEqualTo("전남대");
        assertThat(busStopList.get(1).getId()).isEqualTo(2);
        assertThat(busStopList.get(1).getName()).isEqualTo("전남대후문");

        //BusLine Test
        favoritesService.save(new BusLineRequestDto.Builder()
                .id(1)
                .name("일곡18")
                .build()
        );
        favoritesService.save(new BusLineRequestDto.Builder()
                .id(2)
                .name("진월07")
                .build()
        );

        List<BusLine> busLineList =favoritesService.findAllLines();
        assertThat(busLineList.size()).isEqualTo(2);
        assertThat(busLineList.get(0).getId()).isEqualTo(1);
        assertThat(busLineList.get(0).getName()).isEqualTo("일곡18");
        assertThat(busLineList.get(1).getId()).isEqualTo(2);
        assertThat(busLineList.get(1).getName()).isEqualTo("진월07");




    }
}
