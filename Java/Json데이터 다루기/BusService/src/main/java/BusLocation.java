import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class BusLocation {

    private BusLine busLine;
    private StopList stopList;
    private GetApiData getApiData;
    private String id;
    private String busName;
    private String curStopId;
    private int busCount;
    private ArrayList<String> busNumberList = new ArrayList<>();
    private ArrayList<String> curStopNameList = new ArrayList<>();
    private ArrayList<String> isLowBusList = new ArrayList<>();

    public BusLocation(String id) throws IOException, ParseException {
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
             busNumberList.add(bus.get("CARNO").toString());
             curStopNameList.add(getCurStopName(bus.get("CURR_STOP_ID").toString()));
             isLowBusList.add(isLowBus(bus.get("LOW_BUS").toString()));
        }

    }
    private String isLowBus(String flag){
        if(flag.equals("0"))
            return "일반";
        else
            return "저상";
    }
    private String getCurStopName(String curStopId){
        return stopList.getStop(curStopId);
    }

    public int getBusCount(){
        return busCount;
    }
    public String getBusName(){
        return busName;
    }
    public ArrayList<String> getBusNumberList(){
        return busNumberList;
    }
    public ArrayList<String> getCurStopNameList(){
        return curStopNameList;
    }
    public ArrayList<String> isLowBusList(){
        return isLowBusList;
    }






}
