package Parse;

import org.json.simple.JSONObject;

public class BusLine {
    public String StopName;
    public int StopId;
    public String flag;

    public BusLine(JSONObject jsonObject){
        StopName = jsonObject.get("BUSSTOP_NAME").toString();
        StopId = Integer.parseInt(jsonObject.get("BUSSTOP_ID").toString());
        flag = flagData(Integer.parseInt(jsonObject.get("RETURN_FLAG").toString()));
    }

    public String getFlag() { return flag; }
    public String getStopName() { return StopName; }
    public int getStopId() { return StopId; }

    public  String flagData(int index){
        if ( index== 1)
            return "일반";
        else if (index==2 || index == 4)
            return "<기점>";
        else
            return "[종점]";
    }

}