
# 📌 회원 등록 API
****
## 🧐 V1 - 엔티티를 그대로 사용.
```java
@PostMapping("/api/v1/members")
public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
    Long newAccountId = memberService.join(member);
    return new CreateMemberResponse(newAccountId);
}

@Data
static class CreateMemberResponse{
    private Long id;

    public CreateMemberResponse(Long id) {
        this.id = id;
    }
}
```

### 🖍 요청 파라미터를 엔티티로 받았을 때의 문제점.
- 엔티티가 변경되었을 때 API의 스펙또한 변경되어야 한다.
- 프레젠테이션 계층을 위한 검증 로직이 엔티티에 들어가게 된다.(@NotEmpty)
  - API에 따라 @NotEmpty가 필요하지 않을 수도 있다.
  
## 🧐 V2 - DTO 사용
```java
@PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Long newAccountId = memberService.join(request.toEntity());
        return new CreateMemberResponse(newAccountId);
    }
@Data
static class CreateMemberRequest{
  @NotEmpty
  private String name;

  public Member toEntity(){
    Member member = new Member();
    member.setName(name);
    return member;
  }
}
```
- 엔티티 스펙이 변경되어도 API스펙을 변경하지 않아도 된다.
- 외부에 엔티티가 노출되지 않는다.
- 어떤 데이터를 요구하는지 명확하다.


# 📌 회원 수정 API
***
```java
@PutMapping("/api/v2/members/{id}")
public UpdateMemberResponse UpdateMemberV2(@PathVariable("id") Long id,
                                           @RequestBody @Valid UpdateMemberRequest request){
    Long memberId = memberService.update(id, request.getName());
    Member member = memberService.findById(memberId);
    return new UpdateMemberResponse(member.getId(), member.getName());
}

@Data
@AllArgsConstructor
static class UpdateMemberResponse{
    private Long id;
    private String name;
}
@Data
static class UpdateMemberRequest{
    private String name;
}
```
- PUT 메서드 사용.
- memberService 에서 id로 멤버를 찾은 후 해당 값을 변경,     
  Transaction 이 끝나는 시점에 Commit 이 이루어지며 변경 감지를 통해 데이터가 변경된다.
  
### 🖍 memberService.update()에서 member를 반환하지 않는 이유.
- 엔티티 조회와의 분리. member엔티티를 반환하게 되면 조회의 성격을 가지게 되기 때문에 분리한다.
- 유지보수성 증대.

# 📌 회원 조회 API

## 🧐 V1 - List\<Member\>
```java
@GetMapping("/api/v1/members")
public List<Member> membersV1(){
    return memberService.findMembers();
}
```

### 🖍 순환 호출 -> 무한 루프
```json
[
  {
    "id": 1,
    "name": "kim",
    "address": {
      "city": "city1",
      "street": "street1",
      "zipcode": "1"
    },
    "orders": [
      {
        "id": 4,
        "orderDate": "2021-10-24T22:08:48.39548",
        "status": "ORDER",
        "member": {
          "id": 1,
          "name": "kim",
          "address": {
            "city": "city1",
            "street": "street1",
            "zipcode": "1"
          },
          "orders": [
            {
              "id": 4,
              "orderDate": "2021-10-24T22:08:48.39548",
              "status": "ORDER",
              "member": {
                "id": 1,
                "name": "kim",
                "address": {
                  "city": "city1",
                  "street": "street1",
                  "zipcode": "1"
                },
                "orders": [
                  ...  
]

```
- orders에 ```@JsonIgnore```를 사용하여 orders를 보내지 않도록 할 수 있다.

- 엔티티의 모든 값이 노출.
- 엔티티 변경시 API 스펙이 변경되어야 한다.
- 프레젠테이션 계층의 로직이 추가된다.
- API 응답 스펙에 맞추기 위해 추가적인 로직(JsonIgnore) 필요.
- 한 엔티티로 다양한 API를 위한 응답 로직을 담기는 어렵다.
- 가장 바깥쪽이 Array로 되어있기 때문에 API 스펙을 변경하기 어렵다 
  - count를 넣어달라 -> 추가할 수 없음.
  - 바깥쪽이 Array가 아닌 Object라면 가능하다
  
## 🧐 V2 - Result, MemberDTO
```java
    @GetMapping("/api/v2/members")
    public Result memberV2(){
        List<MemberDto> memberDtos = memberService.findMembers().stream()
                .map(m -> new MemberDto(m.getName(), m.getAddress()))
                .collect(Collectors.toList());

        return new Result(memberDtos.size(),memberDtos);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
        private Address address;
    }
```
  ```json
  {
    "count": 2,
    "data": [
      {
        "name": "kim",
        "address": {
          "city": "city1",
          "street": "street1",
          "zipcode": "1"
        }
      },
      {
        "name": "kim2",
        "address": {
          "city": "city2",
          "street": "street2",
          "zipcode": "2"
        }
      }
    ]
  } 
  ```
- Result로 한번 감싸주었기 때문에 가장 바깥쪽이 Array가 아닌 Object. 확장 가능.



# 📌 고급 주문 조회 API
***

## 🧐 V1 - 엔티티 직접 노출
```java
@GetMapping("/api/v1/simple-orders")
public List<Order> ordersV1(){
      return orderService.findOrders(new OrderSearch());
}
```
- Order와 양방향 연관관계를 맺고 있는 엔티티들이 존재하기 때문에 JSON객체를 생성할 때 순환으로 계속 객체를 생성하게되는 무한루프에 빠지게 된다.
- 양방향 연관관계의 반대쪽에서 Order를 참조하는 부분에 ```@JsonIgnore```를 사용하여 생성하지 않도록 해야한다.

### 🖍 Json객체 생성시 프록시 객체.
```json
Type definition error: 
[simple type, class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor]
```
- Lazy로딩을 사용할 때 해당 객체를 비워둘 수 없기 때문에 Hibernate 에서 프록시 객체를 넣어둔다.(ByteBuddy 사용)
- 그러나 Jackson 라이브러리는 프록시 객체를 다루지 못해 발생한 에러.
```json
implementation 'com.fasterxml.jackson.datatype:jackson-datatype-hibernate5'
```
```java
@Bean
Hibernate5Module hibernate5Module(){
    return new Hibernate5Module();
}
```
- Hibernate5Module를 사용 해결 가능. -> 기본으로 Lazy Loading은 null로 채워준다.
```java
@Bean
Hibernate5Module hibernate5Module(){
    Hibernate5Module hibernate5Module = new Hibernate5Module();
    hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
    return hibernate5Module;
}
```
- Force Lazy Loading 옵션을 사용하면 Json을 생성하는 시점에 로딩해 값을 채워준다.
- 사용하지 않는 것들도 모두 가져와버리기 때문에 성능 이슈가 발생한다.
- Force Lazy Loading을 사용하지 않고 프록시를 강제 초기화시켜 해결할 수도 있다.(member의 name을 호출 ->프록시 객체가 초기화 된다.)


## 🧐 V2 - 엔티티를 DTO로 변환