
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class GetApiData {
    private String type;
    private String Id="";

    private HashMap<String, String> typeMap =new HashMap<>(){{
            put("LINE", "lineInfo");
            put("STATION", "stationInfo");
            put("LINE_STATION", "lineStationInfo");
            put("ARRIVE", "arriveInfo");
            put("BUS_LOCATION", "busLocationInfo");

    }};
    private HashMap<String, String> idType= new HashMap<>(){{
        put("lineStationInfo", "LINE_ID");
        put("busLocationInfo", "LINE_ID");
        put("arriveInfo","BUSSTOP_ID");
    }};

    public GetApiData(String type){
        this.type = typeMap.get(type);
    }
    public GetApiData(String type, String Id){
        this.type = typeMap.get(type);
        this.Id = Id;

    }

    public String makeUrl(){
        //api url
        StringBuilder urlBuilder = new StringBuilder("http://api.gwangju.go.kr/json/"+type); /*URL*/
        urlBuilder.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode("6NJ+9v8lLytvSPezq+1BfBxvNrXCxjoJBuGKqv0HCIC2JCguk1J7zsghyyfWnEZdXUsaVLsQFBMF6GPsYW4Wig==", StandardCharsets.UTF_8)); /**/

        if(Id!=""){
            urlBuilder.append("&").append(URLEncoder.encode(idType.get(type), StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(Id, StandardCharsets.UTF_8)); /*노선 ID*/
        }

        return urlBuilder.toString();
    }

    public JSONObject callApi() throws IOException, ParseException {
        URL url = new URL(makeUrl());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
        JSONObject string2Json = (JSONObject) parser.parse(sb.toString());

        return string2Json;

    }

    public JSONObject getData() throws IOException, ParseException {
        return callApi();
    }

    public static void main(String[] args) throws IOException, ParseException {
        JSONObject jsonObject =  new GetApiData("BUS_LOCATION", "9").getData();
        System.out.println("전체 JsonObject: "+jsonObject.toJSONString());

        //key 값으로 value 얻기.
        String rowCount = jsonObject.get("ROW_COUNT").toString();
        System.out.println("rowCount : "+ rowCount);

        //key 값으로 리스트 가져오
        JSONArray jsonArray = (JSONArray) jsonObject.get("BUSLOCATION_LIST");
        System.out.println("BUS LIST: "+ jsonArray.toJSONString());

        //인덱싱으로 버스 정보 하나 가져오
        JSONObject busLocataion = (JSONObject) jsonArray.get(0);
        System.out.println("BUS"+busLocataion);

        //버스정보에서 값 하나 추출하기.
        System.out.println("CARNO : "+busLocataion.get("CARNO"));
    }
}
