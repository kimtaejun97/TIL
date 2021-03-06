# π λͺ©μ°¨
- #### βοΈ[νμ λ±λ‘ API](#-νμ-λ±λ‘-api)
- #### βοΈ[νμ μμ  API](#-νμ-μμ -api)
- #### βοΈ[νμ μ‘°ν API](#-νμ-μ‘°-api)
- #### βοΈ[μ§μ° λ‘λ©κ³Ό μ‘°ν μ±λ₯ μ΅μ ν](#-κ°λ¨ν-μ£Όλ¬Έ-μ‘°ν-api---μ§μ°-λ‘λ©κ³Ό-μ‘°ν-μ±λ₯-μ΅μ ν)
- #### βοΈ[@OneToMany, μ»¬λ μ μ‘°ν](#-μ£Όλ¬Έ-μ‘°νorderitems-ν¬ν¨)
- #### βοΈ[OSIV : Open Session In View](#-open-session-in-viewosiv)

> μλ μμλ‘ κ΅¬νν΄λ³΄λ©° κ° λ°©λ²μ μ₯ λ¨μ μ κ³΅λΆν΄ λ³Έλ€.
> - λ¨μ μν°ν°λ‘ μ‘°ν
> - μ‘°ν ν DTOλ‘ λ°ν
> - μ‘°ν ν DTOλ‘ λ°ν μ±λ₯ μ΅μ ν
> - DTOλ‘ λ°λ‘ μ‘°ν
> - DTOλ‘ λ°λ‘ μ‘°ν μ±λ₯ μ΅μ ν


# π νμ λ±λ‘ API
****
## π§ V1 - μν°ν°λ₯Ό κ·Έλλ‘ μ¬μ©.
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

### π μμ²­ νλΌλ―Έν°λ₯Ό μν°ν°λ‘ λ°μμ λμ λ¬Έμ μ .
- μν°ν°κ° λ³κ²½λμμ λ APIμ μ€νλν λ³κ²½λμ΄μΌ νλ€.
- νλ μ  νμ΄μ κ³μΈ΅μ μν κ²μ¦ λ‘μ§μ΄ μν°ν°μ λ€μ΄κ°κ² λλ€.(@NotEmpty)
  - APIμ λ°λΌ @NotEmptyκ° νμνμ§ μμ μλ μλ€.
  
## π§ V2 - DTO μ¬μ©
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
- μν°ν° μ€νμ΄ λ³κ²½λμ΄λ APIμ€νμ λ³κ²½νμ§ μμλ λλ€.
- μΈλΆμ μν°ν°κ° λΈμΆλμ§ μλλ€.
- μ΄λ€ λ°μ΄ν°λ₯Ό μκ΅¬νλμ§ λͺννλ€.


# π νμ μμ  API
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
- PUT λ©μλ μ¬μ©.
- memberService μμ idλ‘ λ©€λ²λ₯Ό μ°Ύμ ν ν΄λΉ κ°μ λ³κ²½,     
  Transaction μ΄ λλλ μμ μ Commit μ΄ μ΄λ£¨μ΄μ§λ©° λ³κ²½ κ°μ§λ₯Ό ν΅ν΄ λ°μ΄ν°κ° λ³κ²½λλ€.
  
### π memberService.update()μμ memberλ₯Ό λ°ννμ§ μλ μ΄μ .
- μν°ν° μ‘°νμμ λΆλ¦¬. memberμν°ν°λ₯Ό λ°ννκ² λλ©΄ μ‘°νμ μ±κ²©μ κ°μ§κ² λκΈ° λλ¬Έμ λΆλ¦¬νλ€.
- μ μ§λ³΄μμ± μ¦λ.

# π νμ μ‘°ν API

## π§ V1 - List\<Member\>
```java
@GetMapping("/api/v1/members")
public List<Member> membersV1(){
    return memberService.findMembers();
}
```

### π μν νΈμΆ -> λ¬΄ν λ£¨ν
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
- ordersμ ```@JsonIgnore```λ₯Ό μ¬μ©νμ¬ ordersλ₯Ό λ³΄λ΄μ§ μλλ‘ ν  μ μλ€.

- μν°ν°μ λͺ¨λ  κ°μ΄ λΈμΆ.
- μν°ν° λ³κ²½μ API μ€νμ΄ λ³κ²½λμ΄μΌ νλ€.
- νλ μ  νμ΄μ κ³μΈ΅μ λ‘μ§μ΄ μΆκ°λλ€.
- API μλ΅ μ€νμ λ§μΆκΈ° μν΄ μΆκ°μ μΈ λ‘μ§(JsonIgnore) νμ.
- ν μν°ν°λ‘ λ€μν APIλ₯Ό μν μλ΅ λ‘μ§μ λ΄κΈ°λ μ΄λ ΅λ€.
- κ°μ₯ λ°κΉ₯μͺ½μ΄ Arrayλ‘ λμ΄μκΈ° λλ¬Έμ API μ€νμ λ³κ²½νκΈ° μ΄λ ΅λ€ 
  - countλ₯Ό λ£μ΄λ¬λΌ -> μΆκ°ν  μ μμ.
  - λ°κΉ₯μͺ½μ΄ Arrayκ° μλ ObjectλΌλ©΄ κ°λ₯νλ€
  
## π§ V2 - Result, MemberDTO
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
- Resultλ‘ νλ² κ°μΈμ£ΌμκΈ° λλ¬Έμ κ°μ₯ λ°κΉ₯μͺ½μ΄ Arrayκ° μλ Object. νμ₯ κ°λ₯.



# π κ°λ¨ν μ£Όλ¬Έ μ‘°ν API - μ§μ° λ‘λ©κ³Ό μ‘°ν μ±λ₯ μ΅μ ν
***

## π§ V1 - μν°ν° μ§μ  λΈμΆ
```java
@GetMapping("/api/v1/simple-orders")
public List<Order> ordersV1(){
      return orderService.findOrders(new OrderSearch());
}
```
- Orderμ μλ°©ν₯ μ°κ΄κ΄κ³λ₯Ό λ§Ίκ³  μλ μν°ν°λ€μ΄ μ‘΄μ¬νκΈ° λλ¬Έμ JSONκ°μ²΄λ₯Ό μμ±ν  λ μνμΌλ‘ κ³μ κ°μ²΄λ₯Ό μμ±νκ²λλ λ¬΄νλ£¨νμ λΉ μ§κ² λλ€.
- μλ°©ν₯ μ°κ΄κ΄κ³μ λ°λμͺ½μμ Orderλ₯Ό μ°Έμ‘°νλ λΆλΆμ ```@JsonIgnore```λ₯Ό μ¬μ©νμ¬ μμ±νμ§ μλλ‘ ν΄μΌνλ€.

### π Jsonκ°μ²΄ μμ±μ νλ‘μ κ°μ²΄.
```json
Type definition error: 
[simple type, class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor]
```
- Lazyλ‘λ©μ μ¬μ©ν  λ ν΄λΉ κ°μ²΄λ₯Ό λΉμλ μ μκΈ° λλ¬Έμ Hibernate μμ νλ‘μ κ°μ²΄λ₯Ό λ£μ΄λλ€.(ByteBuddy μ¬μ©)
- κ·Έλ¬λ Jackson λΌμ΄λΈλ¬λ¦¬λ νλ‘μ κ°μ²΄λ₯Ό λ€λ£¨μ§ λͺ»ν΄ λ°μν μλ¬.
```json
implementation 'com.fasterxml.jackson.datatype:jackson-datatype-hibernate5'
```
```java
@Bean
Hibernate5Module hibernate5Module(){
    return new Hibernate5Module();
}
```
- Hibernate5Moduleλ₯Ό μ¬μ© ν΄κ²° κ°λ₯. -> κΈ°λ³ΈμΌλ‘ Lazy Loadingμ nullλ‘ μ±μμ€λ€.
```java
@Bean
Hibernate5Module hibernate5Module(){
    Hibernate5Module hibernate5Module = new Hibernate5Module();
    hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
    return hibernate5Module;
}
```
- Force Lazy Loading μ΅μμ μ¬μ©νλ©΄ Jsonμ μμ±νλ μμ μ λ‘λ©ν΄ κ°μ μ±μμ€λ€.
- μ¬μ©νμ§ μλ κ²λ€λ λͺ¨λ κ°μ Έμλ²λ¦¬κΈ° λλ¬Έμ μ±λ₯ μ΄μκ° λ°μνλ€.
- Force Lazy Loadingμ μ¬μ©νμ§ μκ³  νλ‘μλ₯Ό κ°μ  μ΄κΈ°νμμΌ ν΄κ²°ν  μλ μλ€.(memberμ nameμ νΈμΆ ->νλ‘μ κ°μ²΄κ° μ΄κΈ°ν λλ€.)


## π§ V2 - μν°ν°λ₯Ό DTOλ‘ λ³ν
```java
@GetMapping("/api/v2/simple-orders")
public Result ordersV2(){
    List<SimpleOrderDto> orderDtos = orderService.findOrders(new OrderSearch()).stream()
            .map(o -> new SimpleOrderDto(o))
            .collect(Collectors.toList());

    return new Result(orderDtos);
}
@Data
@AllArgsConstructor
static class Result<T>{
    private T orders;
}

@Data
static class SimpleOrderDto{
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order){
        this.orderId = order.getId();
        this.name = order.getMember().getName(); // Lazy μ΄κΈ°ν
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.address = order.getDelivery().getAddress(); // Lazy μ΄κΈ°ν
    }

}
```
- orderService.findOrders μμ Order λ₯Ό μ‘°ν.
- DTO κ°μ μ€μ ν  λ Member μ Delivery λ₯Ό Lazy λ‘λ©μΌλ‘ κ°μ Έμ΄. -> N+1 μΏΌλ¦¬ λ¬Έμ  λ°μ.(μμμ± μ»¨νμ€νΈμ μλ λ°μ΄ν°λΉ 1κ±΄)

## π§ V3 - DTO + Fetch Join

```java
public List<Order> findOrdersWithMemberAndDelivery(OrderSearch orderSearch) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QDelivery delivery = QDelivery.delivery;

        return query.selectFrom(order)
                      .where(eqStatus(orderSearch.getOrderStatus(), order),
                            likeName(orderSearch.getMemberName(), order))
                      .join(order.member, member).fetchJoin()
                      .join(order.delivery, delivery).fetchJoin()
                      .fetch();
}
```
- Fetch Joinμ μ¬μ©νμ¬ N+1 μΏΌλ¦¬λ¬Έμ  ν΄κ²°.
- Order, Member, Delivery λ₯Ό νλ²μ Select μ μ λ£μ΄ μ‘°νν΄ μ¨λ€.
- μ΄λ―Έ μ‘°νν΄ μμμ± μ»¨νμ€νΈμ μ‘΄μ¬νκΈ° λλ¬Έμ Lazy Loading μ΄ λ°μνμ§ μκ³  νλ²μ μΏΌλ¦¬λ§ λ°μνκ² λλ€.

```sql
 select
    order0_.order_id as order_id1_6_0_,
    member1_.member_id as member_i1_4_1_,
    delivery2_.delivery_id as delivery1_2_2_,
    order0_.delivery_id as delivery4_6_0_,
    order0_.member_id as member_i5_6_0_,
    order0_.order_date as order_da2_6_0_,
    order0_.status as status3_6_0_,
    member1_.city as city2_4_1_,
    member1_.street as street3_4_1_,
    member1_.zipcode as zipcode4_4_1_,
    member1_.name as name5_4_1_,
    delivery2_.city as city2_2_2_,
    delivery2_.street as street3_2_2_,
    delivery2_.zipcode as zipcode4_2_2_,
    delivery2_.status as status5_2_2_ 
from orders order0_ 
inner join member member1_ 
        on order0_.member_id=member1_.member_id 
inner join delivery delivery2_ 
        on order0_.delivery_id=delivery2_.delivery_id
```


## π§ V4 - JPAμμ DTOλ‘ λ°λ‘ μ‘°ν

```java
@GetMapping("/api/v4/simple-orders")
public Result ordersV4(){
    List<SimpleOrderQueryDto> orderDtos = orderService.findOrderDto(new OrderSearch());
    return new Result(orderDtos);
}
```
- ### βοΈ JPQL μ¬μ©
```java
return em.createQuery(
                "select new jpabook.module.order.SimpleOrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                        "from Order o " +
                        "join o.member m " +
                        "join o.delivery d " +
                        "where o.status = :status and o.member.name = :name", SimpleOrderQueryDto.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .getResultList();
```

- ### βοΈ Querydsl μ¬μ©
```java
JPAQueryFactory query = new JPAQueryFactory(em);
QOrder order = QOrder.order;
QMember member = QMember.member;
QDelivery delivery = QDelivery.delivery;


return query.select(Projections.constructor(SimpleOrderQueryDto.class,
                order.id,
                member.name,
                order.orderDate,
                order.status,
                delivery.address))
            .from(order)
            .join(order.member, member) // λ³μΉ­ ν΄λμ€.
            .join(order.delivery, delivery)
            .where(eqStatus(orderSearch.getOrderStatus(), order),
                likeName(orderSearch.getMemberName(), order))
            .fetch();
```
- Projection.constructor : μμ±μλ₯Ό μ΄μ©. νλΌλ―Έν°μ μμ, νμμ΄ λ§μμΌ νλ€.
- Projection.bean : κΈ°λ³Έ μμ±μλ₯Ό μ΄μ©νμ¬ κ°μ²΄λ₯Ό μμ±ν ν Setter λ₯Ό μ΄μ©νμ¬ κ°μ μν.
- Projection.fields : λ¦¬νλ μ APIλ₯Ό μ¬μ©νμ¬ νλμ μ§μ  κ° μ£Όμ(κΈ°λ³Έ μμ±μ νμ)
- order.id.as("orderId")μ κ°μ΄ λ³μΉ­ κ°λ₯.
```sql
select
    order0_.order_id as col_0_0_,
    member1_.name as col_1_0_,
    order0_.order_date as col_2_0_,
    order0_.status as col_3_0_,
    delivery2_.city as col_4_0_,
    delivery2_.street as col_4_1_,
    delivery2_.zipcode as col_4_2_ 
from orders order0_ 
inner join member member1_ 
        on order0_.member_id=member1_.member_id 
inner join delivery delivery2_ 
        on order0_.delivery_id=delivery2_.delivery_id 
```

- fetch joinμ μ΄μ©νμλλ νμ΄λΈ μ μ²΄λ₯Ό Select νκΈ° λλ¬Έμ νμμλ λ°μ΄ν° λν μ‘°ννκ² λλ€.
- DTO λ₯Ό μ΄μ©νλ©΄ νμν λ°μ΄ν°λ§μ μ‘°ννμ¬ κ°μ Έμ¬ μ μλ€. μ΄μ μ λΉν΄ Select μ μμ μ‘°ννλ λ°μ΄ν°κ° μ€μ΄λ  κ²μ νμΈ ν  μ μλ€.
- DTO λ₯Ό μ¬μ©νμ¬ λ°νλ°μΌλ©΄ μ±λ₯μ μ‘°κΈ λ μ΅μ νλ  μ μμ§λ§, μ¬μ¬μ©μ±μ΄ κ±°μ μλ€.(member μ μ΄λ¦μ΄ μλλΌ μ νλ²νΈκ° νμνλ€λ©΄?)
- νμμ λ°λΌ μ΄λ€κ²μ μ¬μ©ν μ§ κ²°μ .
- Repository λ κ°μ²΄ κ·Έλνλ₯Ό νμνλ μ©λ(μν°ν° μ‘°ν)λ‘λ§ μ¬μ©λλ κ²μ΄ μ’λ€. λλ¬Έμ DTOλ₯Ό λ°ννλ μΏΌλ¦¬λ λ°λ‘ μ΄λ¬ν μΏΌλ¦¬λ₯Ό λͺ¨μλλ Repository λ₯Ό λ°λ‘ λλ κ²μ΄ μ’λ€.

### π μ±λ₯ μ΅μ ν μμ.
    1. μν°ν°λ₯Ό DTO λ‘ λ³ννμ¬ μ¬μ©. (Lazy Loading μ¬μ©)
    2. N + 1λ± μ±λ₯ μ΄μκ° λ°μνλ©΄ Fetch Join μ¬μ©.
    3. νλκ° λ§€μ° λ§μ κ·Έλλ ν΄κ²°λμ§ μλλ€λ©΄ DTOλ‘ μ§μ  μ‘°ννμ¬ ννκ² κ°μ Έμ€λ λ°©λ² μ¬μ©.
    4. JPA κ° μ κ³΅νλ λ€μ΄ν°λΈ SQL μ΄λ μ€νλ§ JDBC Template λ₯Ό μ¬μ©νμ¬ μ§μ  SQL μ¬μ©.

# π μ£Όλ¬Έ μ‘°ν(orderItems ν¬ν¨)
***
## π§ V1 - μν°ν° μ§μ  λΈμΆ
```java
@GetMapping("/api/v1/orders")
public List<Order> ordersV1(){
    List<Order> all = orderService.findOrders(new OrderSearch());

    // νλ‘μ κ°μ  μ΄κΈ°ν.
   for (Order order : all) {
        order.getMember().getName();
        order.getDelivery().getAddress();
        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.stream()
                .forEach(o->o.getItem().getName());
    }
    return all;
}
```
- μλ°©ν₯ μ°κ΄κ΄κ³μλ @JsonIgnore νμ.
- Hibernate5Moduleμ κΈ°λ³Έ μ΅μμΌλ‘ μμ±νλ€λ©΄ νλ‘μλ₯Ό κ°μ λ‘ μ΄κΈ°ννλ κ²μ΄ νμνλ€.

## π§ V2 - Dto μ¬μ©
```java
@GetMapping("/api/v2/orders")
public Result<OrderDto> ordersV2(){
    List<Order> orders = orderService.findOrders(new OrderSearch());
    List<OrderDto> collect = orders.stream()
            .map(o -> new OrderDto(o))
            .collect(Collectors.toList());

    return new Result(collect);
}
@Data
static class OrderDto{
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order){
        this.orderId = order.getId();
        this.name = order.getMember().getName();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.address = order.getDelivery().getAddress();
        this.orderItems = order.getOrderItems().stream()
                .map(oi -> new OrderItemDto(oi))
                .collect(Collectors.toList());;
    }
}
@Data
static class OrderItemDto{
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemDto(OrderItem orderItem){
        this.itemName = orderItem.getItem().getName();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
    }
}
```
- Order μν°ν°λ§μ Dtoλ‘ λ³ννλλ° κ·ΈμΉμ§ μκ³ , Order μν°ν° λ΄λΆμ μλ μν°ν°λ€ λν Dtoλ‘ λ³κ²½ν΄μ£Όμ΄μΌ νλ€.
- λ΄λΆμ μν°ν°κ° μμΌλ©΄ λκ°μ΄ νλ‘μκ° λ€μ΄κ° nullμ΄ λ€μ΄κ°κ³ , κ°μ  μ΄κΈ°νλ₯Ό μμΌμ£Όμ΄μΌ νλ€.
- Orderλ₯Ό λΆλ¬μ¬λ N+1(delivery,member), OrderItemsλ₯Ό λΆλ¬μ¬λ N+1(item) μ΄ λ°μνμ¬ λ¬΄μν λ§μ μΏΌλ¦¬κ° μ€νλκΈ° λλ¬Έμ μ£Όμνμ.

## π§ V3 - FetchJoin
```java
public List<Order> findOrdersWithMemberAndDeliveryAndItem(OrderSearch orderSearch) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QDelivery delivery = QDelivery.delivery;
        QOrderItem orderItem = QOrderItem.orderItem;
        QItem item = QItem.item;

        return query.selectFrom(order)
                .where(eqStatus(orderSearch.getOrderStatus(), order),
                        likeName(orderSearch.getMemberName(), order))
                .join(order.member, member).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .join(order.orderItems, orderItem).fetchJoin()
                .join(orderItem.item, item).fetchJoin()
                .distinct()
                .fetch();
    }
```
- orderItems μ Itemμ fetchJoin.
- μ»¬λ μμ μ‘°μΈ(OneToMany)νκ² λλ©΄ λ°μ΄ν°κ° μ¦κ°νλ€.(order id=1μΈ orderItemμ΄ 2κ°λΌλ©΄ order id=1μΈ λ°μ΄ν°κ° 2λ² μ‘°νλλ€.)
- distinct()λ₯Ό μΆκ°νμ¬ μ€λ³΅λ λ°μ΄ν°λ₯Ό μ κ±°ν΄μ€λ€.
  - μ€μ  rowλ joinλ itemμ΄ λ€λ₯΄κΈ° λλ¬Έμ DBμμΌλ‘λ λ€λ₯΄μ§λ§ java μν°ν° κ°μ²΄μμΌλ‘λ κ°κΈ° λλ¬Έμ(μλ³μκ° λμΌ) μ κ±°λλ€.
    (μλμ distinct κΈ°λ₯ μΈμ JPA μμλ μ νλ¦¬μΌμ΄μ λ¨μμ μ€λ³΅μ μ κ±°νλ κΈ°λ₯μ΄ μΆκ°λμ΄ μλ€.)
  
### π λ¨μ : νμ΄μ§μ΄ λΆκ°λ₯ν΄μ§λ€.
  - firstResult, maxResultλ₯Ό μ€μ ν΄λ, λ©λͺ¨λ¦¬μμ μ€νν  κ²μ΄λΌλ κ²½κ³ λ¬Έκ΅¬κ° λ°μνκ³ , **λ©λͺ¨λ¦¬μμ νμ΄μ§ μ²λ¦¬λ₯Ό νλ€.**
    - κ·Έλ¬λ λ°μ΄ν°μ μκ° λ§μμ§λ©΄ OOMκ° λ°μνκ³ , λ©λͺ¨λ¦¬λ‘ μ΄λ¬ν λ°μ΄ν°λ₯Ό μ¬λ¦°λ€λ κ² μμ²΄κ° μννλ€.
  - distinct λν μ λλ‘ μ μ©λμ§ μκΈ° λλ¬Έμ λ΄κ° μνλ λ°μ΄ν°λ₯Ό κ°μ§κ³  νμ΄μ§ν  μ μλ€.(rowμ λ°μ΄ν°κ° λ€λ¦.)

  - μ»¬λ μ fetch joinμ 1κ°λ§ μ¬μ©ν  μ μλ€.(1 : N : N λ± λΆκ°λ₯.)

## π§ V3.1 μ»¬λ μ μ‘°ν νμ΄μ§ νκ³ ν΄κ²°.
1. -ToOne κ΄κ³λ λ°μ΄ν°κ° μ¦κ°νμ§ μμΌλ―λ‘, fetchJoinμ κ±Έμ΄μ€λ€.
2. μ»¬λ μμ μ§μ° λ‘λ©μΌλ‘ μ‘°ν.
3. μ§μ°λ‘λ© μ±λ₯ μ΅μ νλ₯Ό μν΄ ```spring.jpa.properties.hibernate.default_batch_fetch_size``` λλ ```@BatchSize(size = n)``` λ₯Ό μ μ©νλ€.(batchSizeλ§νΌ νλ²μ κ°μ Έμ¨λ€.)
   - in (?, ?, ... ?) μΏΌλ¦¬κ° λ°μνλ©° μ€μ ν μ λ§νΌ λ―Έλ¦¬ κ°μ Έμ¨λ€.
    ```sql
    where
    orderitems0_.order_id in (
        ?, ?
    )  
   /*params : 1,2, .. */
    ```
    #### βοΈ κ²°λ‘ 
        -οΈ μ»¬λ μ ν¨μΉμ‘°μΈμ μ¬μ©νμ§ μμμΌλ―λ‘ λΉμ°ν νμ΄μ§μ΄ κ°λ₯νλ€.
        - λν μ»¬λ μ ν¨μΉμ‘°μΈμμ distinct λ₯Ό νλλΌλ DB μΏΌλ¦¬μμμλ λ λμ΄λ λ°μ΄ν°λ₯Ό μ‘°νν νμ JPAμμ μ΄λ₯Ό μ κ±°νκ² λλλ°,
        μ΄λ¬ν κ³Όμ μ΄ λΉ μ Έ μ±λ₯μ΅μ νκ° μ΄λ£¨μ΄μ§λ€. 
        - sizeλ 100~1000 μ¬μ΄λ₯Ό μ ννλ κ²μ κΆμ₯. λ°μ΄ν°λ² μ΄μ€μ λ°λΌ 1000μΌλ‘ μ ννκΈ°λ νλ€.
        λμ²΄μ μΌλ‘ 1000κ°λ‘ μ€μ νλ κ²μ΄ λ μ’μ§λ§ μκ° λΆνλ₯Ό κ²¬λμ μλ€λ©΄ λ λ?μ μλ₯Ό κΆμ₯νλ€.


## π§ V4 - DTOλ‘ λ°λ‘ μ‘°ννκΈ°.
```java
public List<OrderQueryDto> findOrderDto(){
        List<OrderQueryDto> orders = findOrder();

        orders.stream()
                .forEach(o-> o.setOrderItems(findOrderItems(o)));
        return orders;
}
```
Orderμ μ‘΄μ¬νλ OrderItem λν Dtoλ‘ λ³νν΄ μ£Όμ΄μΌ νλ€.
Orderλ₯Ό DTOμ λ΄μ λ°λ‘ μ‘°ννκ³ , Orderλ₯Ό μννλ©΄μ order idλ₯Ό μ΄μ©νμ¬ OrderItemμ μ‘°ννμ¬ λ£μ΄μ€λ€.
```java
public List<OrderQueryDto> findOrder() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QDelivery delivery = QDelivery.delivery;

        return query
                .select(Projections.constructor(OrderQueryDto.class,
                        order.id, member.name, order.orderDate, order.status, delivery.address))
                .from(order)
                .join(order.member, member)
                .join(order.delivery, delivery)
                .fetch();
}
```
Orderλ₯Ό DTOμ λ΄μ λ°λ‘ μ‘°ννλ€.
```java
private List<OrderItemQueryDto> findOrderItems(OrderQueryDto o) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrderItem orderItem = QOrderItem.orderItem;
        QItem item = QItem.item;

        return query
                .select(Projections.constructor(OrderItemQueryDto.class,
                       orderItem.order.id, item.name, orderItem.orderPrice, orderItem.count))
                .from(orderItem)
                .where(orderItem.order.id.eq(o.getOrderId()))
                .join(orderItem.item, item)
                .fetch();
}
```
OrderItem λν DTOμ λ΄μμ μ‘°ννλ€.

#### π N + 1 : foreachλ₯Ό λλ¦¬λ©° κ°κ° μ‘°ννκΈ° λλ¬Έμ N + 1 μΏΌλ¦¬ λ¬Έμ κ° λ°μνλ€.

## π§ V5 - DTOλ‘ λ°λ‘ μ‘°ννκΈ° - μ΅μ ν.
```java
public List<OrderQueryDto> findOrderDtoOptimization() {
        List<OrderQueryDto> orders = findOrder();

        Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderItemMap(getOrderIds(orders));
        orders.forEach(o->o.setOrderItems(orderItemMap.get(o.getOrderId())));

        return orders;
    }
```
```java
private List<Long> getOrderIds(List<OrderQueryDto> orders) {
    List<Long> orderIds = orders.stream()
            .map(o -> o.getOrderId())
            .collect(Collectors.toList());
    return orderIds;
}

private Map<Long, List<OrderItemQueryDto>> findOrderItemMap(List<Long> orderIds) {
    JPAQueryFactory query = new JPAQueryFactory(em);
    QOrderItem orderItem = QOrderItem.orderItem;
    QItem item = QItem.item;
    
    List<OrderItemQueryDto> orderItems = query
                    .select(Projections.constructor(OrderItemQueryDto.class,
                            orderItem.order.id, item.name, orderItem.orderPrice, orderItem.count))
                    .from(orderItem)
                    .where(orderItem.order.id.in(orderIds))
                    .join(orderItem.item, item)
                    .fetch();

    return DtoToMap(orderItems);
}

private Map<Long, List<OrderItemQueryDto>> DtoToMap(List<OrderItemQueryDto> orderItems) {
    Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream()
            .collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId()));
    return orderItemMap;
    }
```
- orderλ₯Ό λ―Έλ¦¬ μ‘°νν ν OrderItemμ in μ μ μ΄μ©νμ¬ μ‘°ννλ€.
- in μ μ μ΄μ©νμ¬ λͺ¨λ  order idμ λν΄ νλ²μ μ‘°νκ° λκΈ° λλ¬Έμ N+1 λ¬Έμ κ° λ°μνμ§ μλλ€.
- κ·Έλ¬λ μ‘°νν OrderItem μ λͺ¨λ  orderμ λν OrderItemμ΄κΈ° λλ¬Έμ orderμ λ§κ² λΆλ°°ν΄μ£Όλ μ μ²λ¦¬κ° νμνλ€.
    - Map<OrderId, OrderItem> μ ννλ‘ λ΄κ³ , Orderμ μννλ©΄μ orderIdλ‘ Mapμμ OrderItemμ κΊΌλ΄ λ£μ΄μ€λ€.
    


## π§ V6 - DTOλ‘ νλ²μ λͺ¨λ μ‘°ννκΈ°, Flat λ°μ΄ν° μ΅μ ν
```java
public List<OrderFlatDto> findOrderDtoFlatOptimization() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrder order = QOrder.order;
        QOrderItem oi = QOrderItem.orderItem;
        QMember m = QMember.member;
        QDelivery d = QDelivery.delivery;
        QItem i = QItem.item;

        return query
                .select(Projections.constructor(OrderFlatDto.class, order.id, m.name, order.orderDate,
                        order.status, d.address, i.name, oi.orderPrice, oi.count))
                .from(order)
                .join(order.member, m)
                .join(order.delivery, d)
                .join(order.orderItems, oi)
                .join(oi.item, i)
                .fetch();
    }
```
- orderμ orderItem μ λͺ¨λ λ΄μ μμλ DTO λ₯Ό μμ±νμ¬ νλ²μ μ‘°ν.
#### π μ»¬λ μ μ‘°μΈμ μ¬μ©νκΈ° λλ¬Έμ μ€λ³΅ λ°μ΄ν°κ° λ°μνλ€.
- μ νλ¦¬μΌμ΄μ λ¨μμ λ°μ΄ν° μ μ κ° νμ.
```java
@GetMapping("api/v6/orders")
public Result<OrderQueryDto> ordersV6(){
    List<OrderFlatDto> flats = orderService.findOrderDtoFlatOptimization();

    // orderμ OrderItem key, valueλ‘ λ¬ΆκΈ°.
    Map<OrderQueryDto, List<OrderItemQueryDto>> collect = flats.stream()
            .collect(Collectors.groupingBy(o -> new OrderQueryDto(o.getOrderId(), o.getUsername(), o.getOrderDate(), o.getStatus(), o.getAddress()),
                    Collectors.mapping(o -> new OrderItemQueryDto(o.getOrderId(), o.getItemName(), o.getOrderPrice(), o.getCount()), Collectors.toList())));

    // Order νλ κ°λ€κ³Ό List<orderItemQueryDto>μ κ°μ§κ³  μλ κ°μ²΄
    List<OrderQueryDto> orderDtos = collect.entrySet().stream()
            .map(e -> new OrderQueryDto(e.getKey().getOrderId(), e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getStatus(), e.getKey().getAddress(),
                    e.getValue()))
            .collect(Collectors.toList());


    return new Result(orderDtos);
}
```
- API μ€νμ λ§μΆ° λ°μ΄ν° κ°κ³΅.
- νλ«λ°μ΄ν°λ₯Ό κ·Έλλ‘ λ°ννλ©΄ orderItemμ λ§μΆ° λ°μ΄ν°κ° μμ±λκΈ° λλ¬Έμ member, address λ±μμ μ€λ³΅μ΄ λ°μνλ€.   
  
    ![img.png](img.png)

### π μ λ¦¬
- μ₯μ  : λ¨ νλ²μ μΏΌλ¦¬λ‘ λ°μ΄ν°λ₯Ό κ°μ Έμ¨λ€.
- μ€λ³΅ λ°μ΄ν°κ° μΆκ°λλ―λ‘ μν©μ λ°λΌ V5λ³΄λ€ λλ¦΄ μ μλ€.
- μ νλ¦¬μΌμ΄μμμ μΆκ° μμμ΄ ν¬λ€.
- μ€λ³΅ λ°μ΄ν°κ° λ°μνκΈ° λλ¬Έμ orderλ₯Ό κΈ°μ€μΌλ‘ νμ΄μ§ ν  μ μλ€.


## π API κ°λ° κ³ κΈ μ λ¦¬
- ### μν°ν° μ‘°ν
    - μν°ν°λ₯Ό μ‘°νν΄μ κ·Έλλ‘ λ°ν
    - μν°ν°λ‘ μ‘°νν DTOλ‘ λ°ν
    - ν¨μΉ μ‘°μΈμΌλ‘ μΏΌλ¦¬ μ μ΅μ ν(xToOne)
    - μ»¬λ μ(xToMany)κ³Ό νμ΄μ§
        - μ»¬λ μμ ν¨μΉ μ‘°μΈμ νμ΄μ§μ΄ λΆκ°λ₯.(λ©λͺ¨λ¦¬μμ νμ΄μ§, λ°μ΄ν° μ¦κ°.)
        - ToOne κ΄κ³λ ν¨μΉ μ‘°μΈμΌλ‘ μΏΌλ¦¬ μλ₯Ό μ΅μ ννκ³ , μ»¬λ μμ μ§μ° λ‘λ©μΌλ‘ μ€μ  ν batch size λ₯Ό μ΄μ©νμ¬ μ΅μ ν.
            - ```spring.jpa.properties.hibernate.default_batch_fetch_size: ```
            - ```@BatchSize(size = n)``` 
    
- ### DTO λ‘ λ°λ‘ μ‘°ν
    - JPAμμ DTOλ₯Ό μ§μ  μ‘°ν.(jpqlμ new λλ querydslμ Projections)
    - μ»¬λ μ μ‘°ν μ΅μ ν : IN μ μ νμ©νμ¬ λ©λͺ¨λ¦¬μμ λ―Έλ¦¬ μ‘°νν΄μ μ΅μ ν.(INμ λ‘ μ‘°ν ν μλ§κ² λ§€ν.)
    - νλ« λ°μ΄ν° μ΅μ ν : λͺ¨λ μ‘°μΈνμ¬ μ‘°ν ν μ νλ¦¬μΌμ΄μ λ¨μμ μνλ API μ€νμ λ§κ² λ³ν.

### βοΈ κΆμ₯ μμ
> 1. μν°ν° μ‘°νν DTOλ‘ λ°ννλ λ°©μμΌλ‘ μ°μ  μ κ·Ό.(μ½λλ₯Ό κ±°μ μμ νμ§ μκ³  λ€μν μ±λ₯ μ΅μ ν μλ κ°λ₯.)
>    - ν¨μΉ μ‘°μΈμΌλ‘ μΏΌλ¦¬ μλ₯Ό μ΅μ ν.
>    - batch sizeλ‘ μ»¬λ μ μ‘°ν μ΅μ ν.(νμ΄μ§μ΄ νμμλ€λ©΄ fetch join + distinct μ¬μ©.)
> 2. μν°ν° λ°©μμΌλ‘ μ±λ₯ λ¬Έμ λ₯Ό ν΄κ²°ν  μ μλ€λ©΄ DTOλ‘ λ°λ‘ μ‘°ννλ λ°©μμ μ¬μ©.(λ§μ μ½λ λ³κ²½μ΄ νμ.) 
> 3. DTO μ‘°ν λ°©μμΌλ‘λ ν΄κ²°μ΄ μλλ€λ©΄ NativeSQL λλ Spring JdbcTemplate μ¬μ©.

### π DTO μ‘°ν λ°©μμ μ νμ§
    - V4λ μ½λκ° λ¨μνλ€. νΉμ  μ£Όλ¬Έμ νκ±΄λ§ μ‘°νν  κ²μ΄λΌλ©΄ V4λ₯Ό μ¬μ©νμ¬λ λλ€.
    - κ·Έλ¬λ μ¬λ¬ μ£Όλ¬Έμ νλ²μ μ‘°ννλ€λ©΄ V4μμλ N+1 μΏΌλ¦¬ λ¬Έμ κ° λ°μνκΈ° λλ¬Έμ μ΄λ₯Ό μ΅μ ν ν V5κ° ν¨μ¬ μ±λ₯μ΄ μ λμ¨λ€.
    - V6λ μΏΌλ¦¬κ° λ¨ νλ² λκ°κΈ° λλ¬Έμ μ±λ₯μ΄ μ’μλ³΄μ΄μ§λ§ Orderλ₯Ό κΈ°μ€μΌλ‘ νμ΄μ§μ΄ λΆκ°λ₯ νλ€λ λ¨μ μ΄ μλ€. λν λ°μ΄ν°κ° λ§μμ§λ€λ©΄,
    κ·Έλ§νΌ μ€λ³΅ λ°μ΄ν°λ μ¦κ°νκΈ° λλ¬Έμ μ±λ₯ μ¦κ° λν λ―ΈλΉνλ€.


# π Open Session In View(OSIV)
```
WARN 20317 --- [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration :
spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering.
Explicitly configure spring.jpa.open-in-view to disable this warning
```
> JPA μμλ Open EntityManager In Viewμ΄μ§λ§ Hibernateμμ Open Session In ViewλΌκ³  νμκΈ° λλ¬Έμ κ΄λ‘μμΌλ‘ μ¬μ©.
> 
- Springμμλ κΈ°λ³Έμ μΌλ‘ Open Session In Viewλ₯Ό μ¬μ©νλ κ²μ κΈ°λ³ΈμΌλ‘ νλ€. λλ¬Έμ μμκ°μ κ²½κ³  λ©μμ§κ° λ°μνλ€.
    
### βοΈ OSIV ON (default)
- ```spring.jpa.open-in-view: true```
- open-in-viewκ° μΌμ Έ μμΌλ©΄ Transaction μμκ³Ό κ°μ μ΅μ΄ λ°μ΄ν°λ² μ΄μ€ μ»€λ₯μμ μμ μμ λΆν° APIμ μλ΅μ΄ λλ λ κΉμ§ μμμ± μ»¨νμ€νΈμ DB μ»€λ₯μμ μ μ§νλ€.
    > - π μ₯μ : λλ¬Έμ μμμ± μ»¨νμ€νΈλ₯Ό μ΄μ©νλ μ§μ° λ‘λ©μ΄ κ°λ₯νλ€.
    > - π λ¨μ : DB μ»€λ₯μμ μ€λμκ° κ°μ§κ³  μκΈ° λλ¬Έμ μ€μκ° νΈλν½μ΄ λ§μ μ νλ¦¬μΌμ΄μμμλ μ»€λ₯μμ΄ λͺ¨μλΌκ² λκ³ , μ₯μ λ‘ μ΄μ΄μ§λ€.

### βοΈ OSIV OFF
```spring.jpa.open-in-view: false```
- open0in-viewκ° κΊΌμ ΈμμΌλ©΄ Transactionμ μ’λ£ν  λ μμμ± μ»¨νμ€νΈλ₯Ό λ«κ³ , DB μ»€λ₯μμ λ°ννλ€.
    > - π μ₯μ : DB μ»€λ₯μμ λΉ λ₯΄κ² λ°ννκΈ° λλ¬Έμ μ»€λ₯μ λ¦¬μμ€λ₯Ό λ­λΉνμ§ μκ³ , μ₯μ λ₯Ό λ°©μ§ν  μ μλ€.
    > - π λ¨μ : μμμ± μ»¨νμ€νΈλ₯Ό λ«κΈ° λλ¬Έμ μ»¨νΈλ‘€λ¬ λ¨μ΄λ View Templateμμ μ§μ° λ‘λ©μ΄ λΆκ°λ₯ νλ€.
    > > - λλ¬Έμ Transaction μμμ λ―Έλ¦¬ κ°μ  μ΄κΈ°νλ₯Ό νκ±°λ ν¨μΉμ‘°μΈμ νμ¬ νμν λͺ¨λ  λ°μ΄ν°λ₯Ό λ―Έλ¦¬ κ°μ ΈμμΌ νλ€.     
       
## π§ OSIVλ₯Ό λ μνλ‘ λ³΅μ‘μ± κ΄λ¦¬.
- λ³΄ν΅ λΉμ¦λμ€ λ‘μ§μ νΉμ  μν°ν° λͺκ°λ₯Ό λ±λ‘νκ±°λ μμ νλ κ²μ΄λ―λ‘ μ±λ₯μ ν¬κ² μν₯μ μ£Όμ§ μλλ€.
- λ³΅μ‘ν νλ©΄μ μΆλ ₯νκΈ° μν μΏΌλ¦¬λ μ€νμ λ§μΆμ΄ μ±λ₯μ μ΅μ ν νλ κ²μ΄ νμνλ€. κ·Έλ¬λ μ΄λ¬ν μΏΌλ¦¬λ λΉμ¦λμ€ λ‘μ§μ ν¬κ² μν₯μ μ£Όμ§ μλλ€.
- μ΄λ₯Ό λΆλ¦¬νκ² λλ©΄ μ μ§ λ³΄μ μΈ‘λ©΄μμ μ΄μ μ μ»μ μ μλ€.

### βοΈ λΉμ¦λμ€ λ‘μ§κ³Ό μΏΌλ¦¬μ λΆλ¦¬.
- Service: ν΅μ¬ λΉμ¦λμ€ λ‘μ§
- QueryService: νλ©΄μ΄λ APIμ λ§μΆ μλΉμ€.
    - Service λ¨μμλ Transactionμ μ μ§νκΈ° λλ¬Έμ OSIVλ₯Ό falseλ‘ ν ν Transaction μμμ κ°μ  μ΄κΈ°νλ₯Ό νλ μ­ν μ QueryServiceμ μμ.
    
### π μ λ¦¬
- κ³ κ° μλΉμ€μ κ°μ νΈλν½μ΄ λ§μ μ€μκ° APIμμλ OSIVλ₯Ό λκ³ , μ»€λ₯μμ λ§μ΄ μ¬μ©νμ§ μλ κ³³μμλ OSIVλ₯Ό μΌκ³  νΈνκ² μ¬μ©.