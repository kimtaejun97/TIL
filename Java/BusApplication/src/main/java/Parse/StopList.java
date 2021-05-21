package Parse;

import org.json.simple.JSONObject;

public class StopList {
    private int curStopId;
    private String curStopName;
    private String nextStopName;

    public StopList(JSONObject jsonObject){
        this.curStopId = Integer.parseInt(jsonObject.get("BUSSTOP_ID").toString());

        if(jsonObject.get("NEXT_BUSSTOP") != null)
            this.nextStopName = jsonObject.get("NEXT_BUSSTOP").toString();
        else this.nextStopName = "다음 정류장 없음";

        this.curStopName =jsonObject.get("BUSSTOP_NAME").toString();
    }

    public int getCurStopId() { return curStopId; }
    public String getCurStopName() { return curStopName; }
    public String getNextStopName() {



        return nextStopName; }

    //"RESULT_CODE":"SUCCSS"},"STATION_LIST":[{"STATION_NUM":1,
    // "BUSSTOP_NAME":"동원촌",
    // "ARS_ID":"5396",
    // "NEXT_BUSSTOP":"비아동주민센터(앞)",
}

