package GUI;

import com.example.demo.dto.BusLineSaveRequestDto;
import com.example.demo.service.busline.BusLineService;
import org.springframework.beans.factory.annotation.Autowired;

public class test {
    @Autowired
    public static BusLineService busLineService;

    public static void main(String[] args) {
        Long id = 1234l;
        String name = "전남대";
        BusLineSaveRequestDto busLineSaveRequestDto = BusLineSaveRequestDto.builder()
                .id(id)
                .name(name)
                .build();
        busLineService.save(busLineSaveRequestDto);
    }
}
