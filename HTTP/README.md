# 💻 HTTP 기본 지식
****
> 앱과 서버의 통신, 서버와 서버의 통신 등 모두 HTTP를 이용하여 데이터를 주고 받는다. 때문에 HTTP는 앱,웹 개발에 필수로 요하게 되는 기술이다.
> 
```이미지 출처 : 모든 개발자를 위한 HTTP 웹 기본지식 - 김영한님.```

# 📌 인터넷 네트워크
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
> - U niforn : 리소스를 식별하ㅣ는 통일된 방식.
> - R esource : 자원, URI로 식별할 수 있는 모든 것.
> - I dentifier : 다른 항목과 구분하는데 사용되는 정보.
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

1. 요청 : ```https://www.google.com:443/search?q=heloo&hl=ko```
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


# 📌 HTTP 기본
****
> - HTML의 시작 : HyperText Transfer Protocol
> - 현재 : 모든 것을 담아 전송
> - HTML,Text, Image, 음성, 영상, 파일, JSON, XML 등..

## 🧐 HTTP 역사
- HTTP/0.9(1991) : GET만 지원, 헤더가 존재하지 않는다.
- HTTP/1.0(1996) : 다양한 메서드, 헤더 추가.
- ### HTTP/1.1(1997) : 가장 많이 사용.
  >  RFC2069(1997) -> RFC2616(1999) ->RFC7230~7235(2014)
    
- HTTP/2(2015) : 성능 개선
- HTTP/3(진행중) : TCP 대신 <strong>UDP</strong>를 사용, 성능의 개선.

## 🧐 HTTP 특징
> - 클라이언트 - 서버 구조
> > - Request - Response 구조
> > - 클라이언트는 서버에 요청을 보내고 응답을 대기, 서버가 요청에 대한 결과를 생성하고 응답.
> > - <strong>비즈니스 로직과 데이터는 서버에서 처리, 클라이언트는 UI에 집중.</strong>
> > - 각자의 역할에 집중, 독립적.
> - 무상태 프로토콜(Stateless)
> > - [Stateful] : 서버가 상태를 저장하고 있다고 생각하고 클라이언트가 진행상황 이후의 요청만을 보낸다. 서버가 바뀌면 클라이언트의 요청을 이해하지 못하게 된다.
> > - [Stateless] :서버가 클라이언트의 상태를 보존하지 않는다, 클라이언트가 그때 그때 필요한 요청을 모두 넘겨줌.
> > - 👍 <strong>장점 :</strong> 
> >     - 응답 서버를 쉽게 바꿀 수 있다.   
> >     - 요청이 증가해도 서버의 추가가 쉽다.(스케일 아웃- 수평 확장)
> > - 👎 <strong>단점 :</strong> 클라이언트가 요청을 추가 전송해야 한다.
> > - ☝️ <strong>실무 한계 :</strong>
> >     - 모든 것을 무상태로 설계 할 수는 없다.
> >     - ex) 상태를 서버에 유지해야 하는 로그, 일반적으로 브라우저 쿠키와 서버 세션등을 사용하여 상태를 유지한다.
> >     - 상태 유지는 최소한만 사용한다.    
> >
> > ☝️ Stateless !
> > - 같은 시간에 맞추어 발생하는 대용량 트래픽(선착순 이벤트 등 ..)
> > - 최대한 Stateless하게 설계해야 대응하기 용이.
> - 비연결성(Connectionless)
> > - 서버와 클라이언트의 연결을 유지하지 않아, 최소한의 자원으로 서버를 유지할 수 있다.
> > - 👎 한계 : 
> >     - TCP/IP 연결을 새로 맺어야 함(3 way handshake 시간 추가)
> >     - 사이트를 요청하면 JS,CSS, 이미지 등 수 많은 자원이 함께 다운로드 된다.
> > - 👍 해결 : HTTP 지속연결(Persistent Connections) 사용. HTTP/2,HTTP/3에서 더 많은 최적화가 이루어 졌다, 일정 시간동안 지속.
> >
> > ![img_8.png](img_8.png)
> > ![img_9.png](img_9.png)
> - 단순함, 확장 가능


## 🧐 HTTP 메시지
- ### 구조
![img_10.png](img_10.png)
-  Empty Line(CRLF)는 필수로 존재 (CR:Carriage return, LF:Line Feed)

> ### 시작 라인 (start-line) = request-line / status-line
> ```GET /search?q=hello HTTP/1.1```
> - request-line = method SP(공백) request-target SP HTTP-version CRLF
> > - method : GET, POST, PUT, DELETE ...
> > - request-target : absolute-path[?query]
> >     - ex) /search?q=hello
> > - HTTP-version : HTTP/1.1
> - status-line = HTTP-version SP status-code SP reason-phrase CRLF
> > - status-code : 요청 성공, 실패 등의 상태 코드. 200: 성공, 400: 클라이언트 오류, 500: 서버 오류 ...
> > - reason-phrase : 사람이 이해할 수 있는 짧은 상태코드 설명.
> > - 

> ### Header = field-name ":" OWS field-value OWS(띄어쓰기 허용)
> ```Host:www.google.com```   
> ```Content-Type:text/html;charset=UTF-8```
> 
> > - field-name은 대소문자를 구분하지 않는다. value는 구분.
> > - HTTP 전송에 필요한 모든 부가 정보가 담긴다.
> >  - 메시지 바디의 내용, 바디의 크기, 압축, 인증, 요청 클라이언트 정보, 서버 애플리케이션 정보, 캐시 관리 정보 ...
> > - 필요시 임의의 헤더 추가 가능 fieldName: value

> ### Message Body
> > - 실제 전송할 데이터
> > - 바이트로 표현할 수 있는 모든 데이터 전송 가능.

