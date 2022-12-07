package com.novax.ex.demo.provider;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * RedissionTest
 *
 * @author zhenghao
 * @date 2022/8/10 13:56
 */
// 在类上增加注解 @TestMethodOrder ，然后在方法上使用 @Order 指定顺序，数字越小优先级越高，可以保证测试方法运行顺序。
@DisplayName("Junit5 测试demo")
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class Junit5Test {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 使用 @BeforeAll 可以在单元测试前初始化部分信息，@BeforeAll只能使用在静态方法上，
     * 被注解的方法会在测试开始前运行一次。
     *
     * @author mars
     * @date 2022-12-07 16:48
     */
    @BeforeAll
    public static void init() {
        log.info("初始化，准备测试信息");
    }

    /**
     * 使用 @BeforeEach 注解的方法，会在每一个 @Test 注解的方法运行前运行一次。
     *
     * @author mars
     * @date 2022-12-07 16:50
     */
    @BeforeEach
    public void start() {
        log.info("开始测试...");
    }

    /**
     * 重复测试 @RepeatedTest(10) 参数 10 可以让单元测试重复运行 10 次。
     *
     * @author mars
     * @date 2022-12-07 17:11
     */
    @RepeatedTest(2)
    @Order(3)
    public void test1() {
        String id = redissonClient.getId();
        log.info("redis id = {}", id);
//        Assertions.assertNull(id);
        Assertions.assertNotNull(id);
        Assertions.assertEquals(id.length(), 36);
        Assertions.assertNotEquals(id, "xxx");
        Assertions.assertTrue(id.length() == 36);
        Assertions.assertFalse(id.length() == 35);
    }

    /**
     * 使用 @DisplayName 注解可以自定义测试方法的显示名称，下面为两个测试方法自定义名称。
     *
     * @author mars
     * @date 2022-12-07 17:03
     */
    @Order(1)
    @DisplayName("test2方法")
    @Test
    public void test2() {
        Assertions.assertTrue(true);
        log.info("test2 hello ...");
    }

    /**
     * 被 @Disabled 注解的方法不在参与测试，下面对 test3 方法添加了 @Disabled 注解。
     * 使用 @DisplayName 注解可以自定义测试方法的显示名称，下面为两个测试方法自定义名称。
     *
     * @author mars
     * @date 2022-12-07 16:55
     */
    @Order(2)
    @Disabled("由于xx原因，关闭 test3 测试")
    @Test
    public void test3() {
        log.info("test3 hello ...");
    }

    /**
     * 使用注解 @ParameterizedTest 结合 @ValueSource ，可以对不用的入参进行测试。
     * 下面的示例使用 @ParameterizedTest 来开始参数化单元测试，
     * name 属性用来定义测试名称，@ValueSource 则定义了两个测试值。
     *
     * @param name name
     * @author mars
     * @date 2022-12-07 17:05
     */
    @Order(0)
    @DisplayName("是否是狗")
    @ValueSource(strings = {"dog", "cat"})
    @ParameterizedTest(name = "开始测试入参 {0} ")
    public void testIsDog(String name) {
        Assertions.assertEquals(name, "dog");
    }

    /**
     * 使用@AfterAll 注解只能使用在静态方法上，被注解的方法会在所有单元测试运行完毕后运行一次。
     *
     * @author mars
     * @date 2022-12-07 16:52
     */
    @AfterAll
    public static void close() {
        System.out.println("结束，准备退出测试");
    }

    /**
     * 使用 @AfterEach 注解的方法，会在每一个 @Test 注解的方法运行结束前运行一次。
     *
     * @author mars
     * @date 2022-12-07 16:53
     */
    @AfterEach
    public void end() {
        log.info("测试完毕...");
    }
}
