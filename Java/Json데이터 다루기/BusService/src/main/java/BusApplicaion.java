import org.json.simple.parser.ParseException;

import java.io.IOException;

public class BusApplicaion {
    public static void main(String[] args) throws IOException, ParseException {
        BusLocation busLocation= new BusLocation("9");
        System.out.println("버스 이름 : "+ busLocation.getBusName());
        System.out.println("버스 수: "+ busLocation.getBusCount());
        for(int i=0; i<busLocation.getBusCount(); i++){
            System.out.println("\t 버스 번호 : " +busLocation.getBusNumberList().get(i));
            System.out.println("\t 정류장 : "+busLocation.getCurStopNameList().get(i));
            System.out.println("\t 버스 종류 : "+ busLocation.isLowBusList().get(i));
        }

    }
}
