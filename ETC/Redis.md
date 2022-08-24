## ? Redis��?
Key-Value�� �����͸� �����ϰ� �����ϱ� ���� ������� �����ͺ��̽� ���� �ý�����, ���Ӽ��� �����ϴ� �θ޸� ������ ������̴�.    
�޸𸮿� �����͸� �����ϰ� ��ȸ�ϱ� ������ �ӵ��� ������. �׷��� ��ũ�� �����Ͽ� �������� ���Ӽ��� ������ ���� �ִ�.

<br>

### ? Redis�� �����ϴ� �پ��� ������Ÿ��
![img_1.png](img/img_3.png)

<br>

### ? Client: Lettuce vs Jedis
�������� jedis�� ���� ��������� ��Ƽ ������ �Ҿ�����, Pool�� �Ѱ� ������ Lettuce�� �Ѿ�� �߼�,
Jedis�� ��Ƽ ������ ȯ�濡�� �ν��Ͻ��� �����Կ� �־� thread-safe ���� �ʾ� �߰����� ����� �ʿ��ϴ�.(thread-pool)
Lettuce�� thread-safe �� �Ӵ���, Netty ����̱� ������ �񵿱⵵ �����Ѵ�.

���ɸ鿡���� Lettuce �� CPU �����̳�, Ŀ�ؼ� ��, ����ӵ��鿡�� �е����� �������̸� ���δ�.(�ּ� �� ��)

Spring Boot 2.x ���ʹ� Lettuce�� �⺻ ���������� �߰��Ѵ�. (Jedis�� ����ϰ� �ʹٸ� �̸� �����ϰ�, Jedis�� ����ؾ��Ѵ�.)

```java
@RequiredArgsConstructor
@EnableRedisRepositories
@Configuration
public class RedisConfig {
 
    private final RedisProperties redisProperties;
 
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
    }
 
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
 
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
 
        return redisTemplate;
    }
}
```

<br>

### ? RedisRepository, RedisTemplate
Repository�� ���� �� �߻�ȭ�� ����, ����ϱ�� ��������, �������̽��� �����ؾ� �ϰ�, ���� ��ü ���� Ŭ������ �����ؾ��Ѵ�.
������ �����͸� �����ϴ� ���� �ƴ� �ܼ� key-value ���� �����ϱ� ���ؼ� Ŭ������ �����ϴ� �� ���ٴ� Template�� ����ϴ� ���� ���� �� ����.

Template�� ����Ѵٸ�,    
opsForValue, opsForList, opsForSet, opsForZSet, opsForHahs ���� ����ȭ/������ȭ �޼��带 �̿��Ͽ� set, get�� �����ϴ�. (3��° ���ڷ� ����ð��� �� �� �ִ�.)

<br>

### ? �׽�Ʈ�� ���� EmbeddedRedis
> - com.github.kstyrc ? embedded-redisApache
> - it.ozimov ? embedded-redis

�������� kstryc �� ���� ����Ͽ����� ������Ʈ�� ������ �����Ǿ� it.ozimov���� �̸� ��ũ�Ͽ� �������.

```groovy
testImplementation 'it.ozimov:embedded-redis:0.7.3'
```
```java
@RequiredArgsConstructor
@Profile("test")
@Configuration
public class RedisTestConfig {
 
    private final RedisProperties redisProperties;
    private RedisServer redisServer;
 
    @PostConstruct
    public void redisServer() throws IOException {
        redisServer = new RedisServer(redisProperties.getPort());
        redisServer.start();
    }
 
    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
```
���� �� application-test.yml �� ����� ������ ��� redis�� �ٸ� ��Ʈ�� ���� ���� redis�� host�� port�� 127.0.0.1, 6378�� �������־���. ( redis�� ����Ʈ port�� 6379)

<br><br>

### ? ����
> - https://redis.io/docs/
> - https://devlog-wjdrbs96.tistory.com/374
> - https://jojoldu.tistory.com/418
