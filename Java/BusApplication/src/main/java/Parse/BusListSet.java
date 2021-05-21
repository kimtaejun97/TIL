package Parse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;


public class BusListSet {
    public static HashMap<Integer, BusList> buslist;
    public static int size;

    public static HashMap<Integer, BusList> getBusLists(){

        if(buslist!=null) return buslist;

        JSONObject jsonObject = new GetApiData("LINE").getData();
        JSONArray jsonArray=(JSONArray) jsonObject.get("LINE_LIST");
        size = Integer.parseInt(jsonObject.get("ROW_COUNT").toString());
        buslist = new HashMap<>();
        for(int i=0; i<size; i++) {
            BusList b = new BusList((JSONObject)jsonArray.get(i));
            buslist.put(b.getLineId(), b);
        }
        return buslist;
    }

    public static void BusListPrint(HashMap<Integer, BusList> buslist) {
        if (buslist != null) {
            for(int i=0; i< 1000; i++) {
                if (buslist.get(i) != null) {
                    System.out.println("노선번호: "+buslist.get(i).getLineId()+"노선이름: "+buslist.get(i).getLineName()+"종점(상행): "+buslist.get(i).getDirUp()+"종점(하행): "+buslist.get(i).getDirDown()+"노선종류: "+buslist.get(i).getlineKind()+"배차간격: "+buslist.get(i).getinterval()+"첫차출발시각: "+buslist.get(i).getfirstTime()+"막차출발시각: "+buslist.get(i).getlastTime());
                }
            }
        }

    }
}