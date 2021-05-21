package Parse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;


public class BusLineMap {
    public BusLine[] BusLines;
    public int size;
    public int lineId;
    public String lineName;


    public BusLineMap(int id)throws IOException, org.json.simple.parser.ParseException{

        JSONObject jsonObject = new GetApiData("LINE_STATION", id).getData();
        JSONArray jsonArray = (JSONArray) jsonObject.get("BUSSTOP_LIST");
        size =Integer.parseInt(jsonObject.get("ROW_COUNT").toString());
        lineId =id;
        lineName = ((JSONObject)jsonArray.get(0)).get("LINE_NAME").toString();

        /*
        {"RESULT":
        {"RESULT_MSG":"정상적으로 처리되었습니다.",
        "RESULT_CODE":"SUCCESS"},
        "BUSSTOP_LIST" :[
        {"BUSSTOP_NAME":"장등동",
        "ARS_ID":"4311",
        "RETURN_FLAG":2,
        "BUSSTOP_NUM":1,
        "BUSSTOP_ID":1165,
        "LONGITUDE":126.933688,
        "LATITUDE":35.200203,
        "LINE_ID":9,
        "LINE_NAME":"문흥18"}
        */

        BusLines = new BusLine[size];
        for(int i=0;i<size;i++)
            BusLines[i] = new BusLine((JSONObject) jsonArray.get(i));
    }

    public void BusLinePrint(){
        System.out.println("정류장 개수: "+size);
        for(int i=0;i<size;i++)
            System.out.println(" 정류장 번호: " +BusLines[i].StopId +" | 정류장 이름 : "+ BusLines[i].StopName+"  | 정류장 종류 : "+ BusLines[i].flag);
    }

    public String getLineName(){ return lineName; }
    public BusLine[] getBusLines() { return BusLines; }
    public int getLineId() { return lineId; }
}
