# 💻 HTTP 기본 지식
****
> 앱과 서버의 통신, 서버와 서버의 통신 등 모두 HTTP를 이용하여 데이터를 주고 받는다. 때문에 HTTP는 앱,웹 개발에 필수로 요하게 되는 기술이다.
> 
```이미지 출처 : 모든 개발자를 위한 HTTP 웹 기본지식 - 김영한님.```

# 📌 인터넷 네트워
***
## 🧐 IP(Internet Protocol)
- 지정한 IP주소에 데이터 전달.
- 패킷(packet)이라는 통신 단위로 데이터 전달.
> - 패킷 정보 : 출발지 IP, 목적지 IP, 데이터 ...
> - 주소간 패킷을 전달. (노드들을 통해)
> 
> ![img.png](img.png)

- ### 👎 IP 프로토콜의 한계
> - 비연결성 : 패킷을 수신할 대상이 없거나 서비스 불능 상태여도 패킷이 전송됨.
> - 비신뢰성 : 패킷의 소실, 패킷의 순서가 바뀌는 등의 문제.
> - 프로그램 구분 : 같은 IP를 사용하는 서버에서 통신하는 애플리케이션이 둘 이상일 때의 구분.
> - 해결 : TCP


## 🧐 TCP
![img_1.png](img_1.png)
- 전송 제어 프로토콜 (Transmission Control Protocol)
- 데이터 전달 보증
- 순서 보장
- 신뢰성 있는 프로토콜

![img_2.png](img_2.png)
  > - IP 패킷 : IP 주소 
  > - TCP 세그먼트 :포트, 전송 제어, 순서, 검증 정보 등 ...

- ### TCP 3 Way Handshake
1. 클라이언트 -> 서버로 SYN
2. 서버 -> 클라이언트로 SYN + ACK
3. 클라이언트 -> 서버 ACK + 데이터

- ### 데이터 전달 보장 (TCP 세그먼트의 전송제어 정보)
1. 클라이언트 서버 -> 데이터 전송
2. 서버 -> 클라이언트 : 데이버 수신 응답.

- ### 데이터 순서 보장 (TCP 세그먼트의 순서정보.)
1. 패킷 1,2,3 을 순서대로 전송
2. 서버에 1,3,2 순서로 도청
3. 패킷 2부터 재전송 요청


## 🧐 UDP
- 순서보장, 데이터 전달 보증이 되지 않는다.
- 단순하고 빠름. (handshake, 응답 등이 없음.)
- IP와 거의 같지만  + 포트정보 + 체크섬
- 애플리케이션에서 추가 작업 필요.
- ☝️ HTTP3 에서 UDP를 사용하며 최근 대두되고 있다.

## 🧐 PORT 
> 한 IP에서 둘 이상의 애플리케이션을 연결해야 한다면?

- 같은 IP 내에서 프로세스 구분.
- 비유 : 아파트(IP) 내의 호수(PORT)

> - 할당 가능한 포트 번호 : 0~65535
> - 잘 알려진 포트 : 0~1023 (사용하지 않는 것이 좋음)
> > - FTP : 20, 21
> > - Telnet : 23
> > - HTTP : 80
> > - HTTPS : 443

## 🧐 DNS (Domain name System)
> IP의 문제 : 기억하기 어렵고 변경 가능성이 있다.

- 도메인 명을 IP주소로 변환해준다.
ex> google.com - 200.200.200.2
  ![img_3.png](img_3.png)
  

# 📌 URI와 웹 브라우저 요청 흐름
****

## 🧐 URI
> URI : Uniform Resource Identifier
> - Uniforn : 리소스를 식별하ㅣ는 통일된 방식.
> - Resource : 자원, URI로 식별할 수 있는 모든 것.
> - Identifier : 다른 항목과 구분하는데 사용되 정보.
> > - URL(Uniform Resource Locator) : 리소스의 위치
> > - URN(Uniform Resource Name) : 리소스의 이름
> - URN만으로 실제 리소스를 찾을 수 있는 방법이 보편화되지 않아 대부분 URL을 사용.
> 
![img_4.png](img_4.png)

🧐 URL
- ### 전체 문법
> - Scheme
> > - 주로 프로토콜 사용(자원 접근 약속 규칙 http, https, ftp 등)
> > - 생략시 http로 동작.
> - userinfo
> > - URL에 사용자 정보를 포함해서 인증, 거의 사용하지 않는다.
> - Host
> > - 호스트명, 도메인명 또는 IP주소를 직접 입력
> - PORT
> > - 접속 포트, 일반적으로 생략, 생략시 http는 80, http는 443
> - Path
> > - 리소스 경로, 계층적 구조
> > - ex) /home/.../.../ file.png
> - Query
> > - key = value 형태
> > - ?로 시작, &로 추가 가능
> > - ex) keyA=valueA&keyB=valueB
> > - query parameter, query string 등으로 불린다. 웹 서버에서 제공하는 파라미터, 문자열.
> - fragment
> > - 다른곳으로 이동하는 html 내부 북마크 등에서 사용, 서버에 전송하는 정보가 아니다.


## 🧐 웹 브라우저 요청 흐름
![img_5.png](img_5.png)

1. 요청 : https://www.google.com:443/search?q=heloo&hl=ko
2. DNS 조회, PORT 조회 : IP, PORT를 얻어온다.
3. HTTP 요청 메시지 생성.
```
GET /search?q=hello&hl=ko HTTP/1.1
HOST: www.google.com
```
4. Socket 라이브러리를 통해 전송 계층(TCP)으로 전달.
5. TCP에서 IP,PORT 정보를 추가.
6. 노드들을 통해 서버에 전달.
7. 서버에서는 TCP/IP 패킷을 버리고, HTTP 메시지를 해석 및 처리.
8. 서버에서 HTTP 응답 메시지 생성.
```
HTTP/1.1 200 OK
Content-Type: text/html;charset=URF-8
Content-Length: 3423

<html>
    ...
<html>
```
9. (4~6)의 과정을 거치고 도착한 패킷에서 HTML을 렌더링해서 결과를 보여준다.