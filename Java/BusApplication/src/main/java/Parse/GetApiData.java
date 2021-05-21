package Parse;

import GUI.BusGUI;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class GetApiData {
    private String type;
    private int Id=0;

    private HashMap<String, String> typeMap =new HashMap<String, String>(){{
            put("LINE", "lineInfo");
            put("STATION", "stationInfo");
            put("LINE_STATION", "lineStationInfo");
            put("ARRIVE", "arriveInfo");
            put("BUS_LOCATION", "busLocationInfo");

    }};
    private HashMap<String, String> idType= new HashMap<String,String>() {{
        put("lineStationInfo", "LINE_ID");
        put("busLocationInfo", "LINE_ID");
        put("arriveInfo", "BUSSTOP_ID");
    }};

    public GetApiData(String type){
        this.type = typeMap.get(type);
    }
    public GetApiData(String type, int Id){
        this.type = typeMap.get(type);
        this.Id = Id;

    }

    public String makeUrl(){
        //api url
        StringBuilder urlBuilder = new StringBuilder("http://api.gwangju.go.kr/json/"+type); /*URL*/
        urlBuilder.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode("6NJ+9v8lLytvSPezq+1BfBxvNrXCxjoJBuGKqv0HCIC2JCguk1J7zsghyyfWnEZdXUsaVLsQFBMF6GPsYW4Wig==", StandardCharsets.UTF_8)); /**/

        if(Id!=0){
            urlBuilder.append("&").append(URLEncoder.encode(idType.get(type), StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(String.valueOf(Id), StandardCharsets.UTF_8)); /*노선 ID*/
        }

        return urlBuilder.toString();

        //http://api.gwangju.go.kr/json/lineStationInfo?serviceKey=6NJ+9v8lLytvSPezq+1BfBxvNrXCxjoJBuGKqv0HCIC2JCguk1J7zsghyyfWnEZdXUsaVLsQFBMF6GPsYW4Wig==&LINE_ID=9
    }

    public JSONObject getData() {

        try {

            URL url = new URL(makeUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            JSONParser parser = new JSONParser();

            return (JSONObject) parser.parse(sb.toString());

        }catch (Exception e){
            BusGUI.alertPopup("에러", "인터넷 연결을 확인해 주세요.", Color.red, 20).start();
            return new JSONObject();
        }

    }

//    public static void main(String[] args) throws IOException, ParseException {
//        JSONObject jsonObject =  new GetApiData("STATION").getData();
//        System.out.println("전체 JsonObject: "+jsonObject.toJSONString());
//
//        //key 값으로 value 얻기.
//        String rowCount = jsonObject.get("ROW_COUNT").toString();
//        System.out.println("rowCount : "+ rowCount);
//
//        //key 값으로 리스트 가져오
//        JSONArray jsonArray = (JSONArray) jsonObject.get("BUSLOCATION_LIST");
//        System.out.println("BUS LIST: "+ jsonArray.toJSONString());
//
//        //인덱싱으로 버스 정보 하나 가져오
//        JSONObject busLocation = (JSONObject) jsonArray.get(0);
//        System.out.println("BUS"+busLocation);
//
//        //버스정보에서 값 하나 추출하기.
//        System.out.println("CARNO : "+busLocation.get("CARNO"));
//    }
}
