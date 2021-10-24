
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