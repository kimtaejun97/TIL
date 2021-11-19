import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class RestAPI {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("https://jsonmock.hackerrank.com/api/football_matches?")
                .append("year=").append("2011")
                .append("&team1=").append("Barcelona");

        URL url = new URL(sb.toString());

        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Set-Cookie", "");
        con.setConnectTimeout(5000);

        con.setReadTimeout(5000);
//        con.setDoOutput(true);
//        DataOutputStream dos = new DataOutputStream(con.getOutputStream());
//        dos.flush();
//        dos.close();

        CookieManager cookieManager = new CookieManager();
        String cookiesHeader = con.getHeaderField("Set-Cookie");
        if(cookiesHeader != null){
            List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);
            // 쿠키 저장소에 요청 쿠키 저장.
            cookies.forEach(cookie -> cookieManager.getCookieStore().add(null, cookie));
        }
        // 쿠키 저장소에 쿠키 추가
        cookieManager.getCookieStore().add(null, new HttpCookie("username", "kim"));

        // 요청에 쿠키 추가
        con.disconnect();
        con = (HttpURLConnection) url.openConnection();

        List<HttpCookie> httpCookies = cookieManager.getCookieStore().getCookies();
        String[] cookies = new String[httpCookies.size()];
        httpCookies.forEach(httpCookie ->{
            int p = 0;
            cookies[p++] = httpCookie.toString();
        });
        con.setRequestProperty("Cookie", String.join(";",cookies));


        // 리다이렉션
        int status = con.getResponseCode();
        if (status == HttpURLConnection.HTTP_MOVED_TEMP
                || status == HttpURLConnection.HTTP_MOVED_PERM) {
            String location = con.getHeaderField("Location");
            URL newUrl = new URL(location);
            con = (HttpURLConnection) newUrl.openConnection();
        }

        status = con.getResponseCode();
        InputStreamReader isr;
        if(status > 299){
            isr = new InputStreamReader(con.getErrorStream());
        }
        else isr = new InputStreamReader(con.getInputStream());

        // 결과 받기.
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuffer res = new StringBuffer();
        while((line = br.readLine()) !=null){
            res.append(line);
        }
        System.out.println("Response: "+ con.getResponseCode() +" "+ con.getResponseMessage());
        System.out.println(res.toString());


    }
}
