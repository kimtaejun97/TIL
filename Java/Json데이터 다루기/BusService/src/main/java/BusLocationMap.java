import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public class BusLocationMap {

    private BusLine busLine;
    private StopList stopList;
    private GetApiData getApiData;
    private String id;
    private String busName;
    private String curStopId;
    private int busCount;
    private String busNumber;
    private String curStopName;
    private String isLowBus;

    private HashMap<String, BusLocation> busLocationMap = new HashMap<>();
    BusLocation busLocation;


    public BusLocationMap(String id) throws IOException, ParseException {
        this.id = id;
        this.busLine = new BusLine(id);
        this.stopList = new StopList();
        this.getApiData = new GetApiData("BUS_LOCATION", id);
        this.busName = busLine.getBusName();
        setData();
    }


    private void setData() throws IOException, ParseException {
        JSONObject data= getApiData.getData();
        JSONObject bus;
        busCount = Integer.parseInt(data.get("ROW_COUNT").toString());

        JSONArray busArray = (JSONArray) data.get("BUSLOCATION_LIST");

        for(int i=0; i<busCount; i++){
             bus = (JSONObject)busArray.get(i);
             curStopId = bus.get("CURR_STOP_ID").toString();
             busNumber = (bus.get("CARNO").toString());
             curStopName = (getCurStopName(bus.get("CURR_STOP_ID").toString()));
             isLowBus =(isLowBus(bus.get("LOW_BUS").toString()));

             busLocation = new BusLocation(busNumber, curStopName, isLowBus);
             busLocationMap.put(curStopId, busLocation);
        }

    }
    private String isLowBus(String flag){
        if(flag.equals("0") || flag.equals(null))
            return "일반";
        else
            return "저상";
    }
    private String getCurStopName(String curStopId){
        return stopList.getStop(curStopId);
    }

    public BusLocation getBusLocation(String stopId){
        return busLocationMap.get(stopId);
    }

    public int getBusCount(){
        return busCount;
    }
    public String getBusName(){
        return busName;
    }






}
