import org.json.simple.parser.ParseException;

import java.io.IOException;

public class BusApplicaion {
    public static void main(String[] args) throws IOException, ParseException {
        BusLocationMap busLocationMap= new BusLocationMap("9");
        System.out.println("버스 이름 : "+ busLocationMap.getBusName());
        System.out.println("버스 수: "+ busLocationMap.getBusCount());

        String stopNum = "524";
        System.out.println("버스 number : " + busLocationMap.getBusLocation(stopNum).getBusNumber());
        System.out.println("버스 stopName : " + busLocationMap.getBusLocation(stopNum).getCurStopName());
        System.out.println("버스 저상여부 :" + busLocationMap.getBusLocation(stopNum).getIsLowBus());
    }


}
