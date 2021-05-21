package Parse;

import org.json.simple.JSONObject;

public class BusList {
    private int lineId;
    private String lineName;
    private String dirDown;
    private String dirUp;
    private String lineKind;
    private String interval;
    private String firstTime;
    private String lastTime;


    public BusList(JSONObject jsonObject) {
        lineId = Integer.parseInt(jsonObject.get("LINE_ID").toString());
        lineName = jsonObject.get("LINE_NAME").toString();
        dirDown = jsonObject.get("DIR_DOWN_NAME").toString();
        dirUp = jsonObject.get("DIR_UP_NAME").toString();
        lineKind = lineKindData(jsonObject.get("LINE_KIND").toString());

        if(jsonObject.get("RUN_INTERVAL")!=null)
            interval = jsonObject.get("RUN_INTERVAL").toString();
        if(jsonObject.get("FIRST_RUN_TIME")!=null)
        firstTime = jsonObject.get("FIRST_RUN_TIME").toString();
        if(jsonObject.get("LAST_RUN_TIME")!=null)
        lastTime = jsonObject.get("LAST_RUN_TIME").toString();
    }


    public String lineKindData(String flag) {
        if(flag.equals("1"))
            return "급행간선";
        else if(flag.equals("2"))
            return "간선";
        else if(flag.equals("3"))
            return "지선";
        else if(flag.equals("4"))
            return "마을버스";
        else if(flag.equals("5"))
            return "공항버스";
        else
            return "광역버스";
    }

    public int getLineId() { return lineId; }
    public String getLineName() { return lineName; }
    public String getDirDown() { return dirDown; }
    public String getDirUp() { return dirUp; }
    public String getlineKind() { return lineKind; }
    public String getinterval() {
        if(interval!=null) return interval+"분";
        else return "정보 없음";
    }
    public String getfirstTime() {
        if(firstTime!=null) return firstTime;
        else return "정보 없음";
    }
    public String getlastTime() {
        if(lastTime!=null) return lastTime;
        else return "정보 없음";
    }

}
