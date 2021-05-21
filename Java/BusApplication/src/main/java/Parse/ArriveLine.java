package Parse;

import org.json.simple.JSONObject;

public class ArriveLine {

    private final int lineId;
    private final String lineName;
    private final int curStopId;
    private final String curStopName;
    private final int remainMin;
    private final int remainStop;

    public ArriveLine(JSONObject jsonObject) {
        lineId = Integer.parseInt(jsonObject.get("LINE_ID").toString());
        lineName = jsonObject.get("SHORT_LINE_NAME").toString();
        curStopId = Integer.parseInt(jsonObject.get("CURR_STOP_ID").toString());
        curStopName = jsonObject.get("BUSSTOP_NAME").toString();
        remainMin = Integer.parseInt(jsonObject.get("REMAIN_MIN").toString());
        remainStop = Integer.parseInt(jsonObject.get("REMAIN_STOP").toString());
    }

    public int getLineId() { return lineId; }
    public String getLineName() { return lineName; }
    public int getCurStopId() { return curStopId; }
    public String getCurStopName() { return curStopName; }
    public int getRemainMin() { return remainMin; }
    public int getRemainStop() { return remainStop; }
}

/*
 * {"RESULT":{"RESULT_MSG":"정상적으로 처리되었습니다.","RESULT_CODE":"SUCCESS"},
 * "BUSSTOP_LIST":[{"ARRIVE":"","REMAIN_STOP":6,
 * "SHORT_LINE_NAME":"문흥18",
 * "BUS_ID":"772025",
 * "METRO_FLAG":0,
 * "BUSSTOP_NAME":"문흥명지아파트",
 * "CURR_STOP_ID":1286,
 * "LINE_ID":9,
 * "REMAIN_MIN":10,
 * "ENG_BUSSTOP_NAME":"Munheung Myeongji Apt.",
 * "DIR_START":"장등동",
 * "DIR":0,
 * "DIR_END":"진곡산단",
 * "LOW_BUS":"1",
 * "ARRIVE_FLAG":0,
 * "LINE_NAME":"문흥18"}
 * ],
 * "ROW_COUNT":7}
 *
 * */