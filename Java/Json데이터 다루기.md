### 라이브러리 
******************
1. json-simple-1.1.1.jar 라이브러리를 다운받아
    프로젝트 속성 > java build path > Livraries > Add jars를 통해 라이브러리 추가.

2. Maven 프로젝트에서의 의존성 추가.
````xml
  <dependency>
    <groupId>com.googlecode.json-simple</groupId>
    <artifactId>json-simple</artifactId>
    <version>1.1</version>
  </dependency>
````

### JsonObject
***************
JsonObject는 객체를 Json객체로 바꿔주거나 새로운 Json 객채를 생성한다.
- Json객체의 생성과 요소 추가, 추출
````java
  JsonObject jsonObject = new JsonObject();
  jsonObject.put("key","value");
  jsonObject.get("key")
````

- String을 Json으로
````java
  JsonObject String2Json = new JsonObject(str);
````

### JsonArray
json이 들어있는 Array
````java
  JsonArray jsonArray = new JsonArray();
  jsonArray.put(jsonObject1);
  jsonArray.put(jsonObject2);
````
jsonArray의 형태는.    
jsonArray = [
  {"key1" : "value1"},
  {"key1" : "value1"}
 ]
 
 index로 접근하는 것이 가능. jsonArray.get(0)
 
 
 ### JsonParser
 json 내부에 json 객체가 또 있을 경우 파싱.
 
 - 예시 데이터 객체 명은 jsonData로 한다.
 ````json
{"RESULT":
	{"RESULT_MSG":"정상적으로 처리되었습니다.",
	"RESULT_CODE":"SUCCESS"},
"BUSSTOP_LIST":
[
	{"BUSSTOP_NAME":"상무지구”,
	“ARS_ID":"2227",
	"RETURN_FLAG":2,
	"BUSSTOP_NUM":1,
	"BUSSTOP_ID":722,
	"LONGITUDE":126.83866389,
	"LATITUDE":35.15676944,
	"LINE_ID":1,
	"LINE_NAME":"순환01"},

	{"BUSSTOP_NAME":"5.18자유공원",
	"ARS_ID":"2209",
	"RETURN_FLAG":1,
	"BUSSTOP_NUM":2,
	"BUSSTOP_ID":189,
	"LONGITUDE":126.84041944,
	"LATITUDE":35.147825,
	"LINE_ID":1,
	"LINE_NAME":"순환01"}
],
”ROW_COUNT":93}  
 ````
 
 - 파싱 예시
 ````java
  JsonParser jsonParser = new JsonParser();
  
  // 전체 파싱
  JsonObject parseAll = (JsonObject)Parser.parse(jsonData);
  String rowCount = parseAll.get("ROW_COUNT");
  
  //내부 객체 파싱
  JsonObject parseInner = (JsonObject)Parser.parse("RESULT);
  String msg = parseInner.get("RESULT_MSG");
 ````
