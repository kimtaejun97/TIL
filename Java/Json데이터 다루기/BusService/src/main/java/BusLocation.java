import java.util.ArrayList;

public class BusLocation {
    private String busNumber;
    private String curStopName;
    private String isLowBus;

    public BusLocation(String busNumber, String curStopName, String isLowBus){
        this.busNumber = busNumber;
        this.curStopName = curStopName;
        this.isLowBus = isLowBus;
    }

    public String getCurStopName(){
        return curStopName;
    }
    public String getBusNumber(){
        return busNumber;
    }
    public String getIsLowBus(){
        return isLowBus;
    }


}
