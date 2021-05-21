package Parse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Arrive {

    private final ArriveLine[] arriveLines;
    private final int size;
    private String stopName;     // from Stoplist
    private String stopTo;       // from Stoplist

    public Arrive(int id){
        JSONObject jsonObject = new GetApiData("ARRIVE", id).getData();
        size=Integer.parseInt(jsonObject.get("ROW_COUNT").toString());

        arriveLines = new ArriveLine[size];
        JSONArray jsonArray=(JSONArray) jsonObject.get("BUSSTOP_LIST");
        for(int i=0; i<size; i++)
           arriveLines[i]=new ArriveLine((JSONObject) jsonArray.get(i));
    }

    public void setStopName(String stopName) { this.stopName = stopName; }
    public void setStopTo(String stopTo) { this.stopTo = stopTo; }

    public ArriveLine[] getLines() { return arriveLines; }
    public int getSize() { return size; }
    public String getStopName() { return stopName; }
    public String getStopTo() {
        if(!stopTo.equals("다음 정류장 없음")) return stopTo+" 방향";
        else return "다음 정류장 없음";
    }
    public String getStopNameWithTo() {
        if(!stopTo.equals("다음 정류장 없음")) return stopName+"("+stopTo+" 방향)";
        return stopName+"(다음 정류장 없음)"; }

    /*
    * {"RESULT":{"RESULT_MSG":"정상적으로 처리되었습니다.","RESULT_CODE":"SUCCESS"},"BUSSTOP_LIST":[{"ARRIVE":"","REMAIN_STOP":4,"SHORT_LINE_NAME":"진월07","BUS_ID":"772547","METRO_FLAG":0,"BUSSTOP_NAME":"광주공고입구","CURR_STOP_ID":177,"LINE_ID":4,"REMAIN_MIN":8,"ENG_BUSSTOP_NAME":"Gwangju Technical High School Entrance","DIR_START":"살레시오고","DIR":1,"DIR_END":"송암공단","LOW_BUS":"1","ARRIVE_FLAG":0,"LINE_NAME":"진월07"},{"ARRIVE":"","REMAIN_STOP":4,"SHORT_LINE_NAME":"문흥18","BUS_ID":"772452","METRO_FLAG":0,"BUSSTOP_NAME":"문화중","CURR_STOP_ID":532,"LINE_ID":9,"REMAIN_MIN":7,"ENG_BUSSTOP_NAME":"Munhwa Middle School","DIR_START":"장등동","DIR":0,"DIR_END":"진곡산단","LOW_BUS":"1","ARRIVE_FLAG":0,"LINE_NAME":"문흥18"},{"ARRIVE":"","REMAIN_STOP":19,"SHORT_LINE_NAME":"용전184","BUS_ID":"771838","METRO_FLAG":0,"BUSSTOP_NAME":"삼소동입구","CURR_STOP_ID":1871,"LINE_ID":77,"REMAIN_MIN":21,"ENG_BUSSTOP_NAME":"Samso-dong Entrance","DIR_START":"수북","DIR":0,"DIR_END":"동부소방서","LOW_BUS":"0","ARRIVE_FLAG":0,"LINE_NAME":"용전184"}],"ROW_COUNT":3}
    *
    * */
    public void print(){

        System.out.println(stopName+" 도착 정보");
        System.out.println("노선\t현재 위치\t\t남은 시간\t남은 정거장");
        for (ArriveLine l : arriveLines)
            System.out.println(l.getLineName()+"\t"+l.getCurStopName()+"\t"+l.getRemainMin()+"\t\t"+l.getRemainStop());
    }




}

