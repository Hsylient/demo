package com.novax.ex.common.core.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Description: Redis工具类
 * @author my.miao
 * @date 2018年2月24日上午9:21:27
 */
@Component
@Slf4j
public class RedisUtil {

	private static RedisTemplate redisTemplate;

	@Resource
	public void setRedisTemplate(RedisTemplate redisTemplate){
		RedisUtil.redisTemplate = redisTemplate;
	}

	/** -------------------key相关操作--------------------- */

	/**
	 * key存在时删除key
	 *
	 * @param key 指定key
	 */
	public static Boolean delete(String key) {
		return redisTemplate.delete(key);
	}

	/**
	 * 批量删除key
	 *
	 * @param keys 批量key
	 */
	public static Long delete(Collection<String> keys) {
		return redisTemplate.delete(keys);
	}

	/**
	 * 序列化key，返回被序列化的值
	 *
	 * @param key 要序列化的key
	 * @return 序列化后的值
	 */
	public static byte[] dump(String key) {
		return redisTemplate.dump(key);
	}

	/**
	 * 检查key是否存在
	 *
	 * @param key key
	 * @return 是否存在
	 */
	public static Boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 设置过期时间
	 *
	 * @param key key
	 * @param timeout 超时时间
	 * @param unit 超时单位，详见{@link TimeUnit}
	 * @return 是否设置成功
	 */
	public static Boolean expire(String key, long timeout, TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}

	/**
	 * 设置过期日期
	 *
	 * @param key key
	 * @param date 过期日期
	 * @return 是否设置成功
	 */
	public static Boolean expireAt(String key, Date date) {
		return redisTemplate.expireAt(key, date);
	}

	/**
	 * 查找所有符合给定模式(pattern)的key
	 *
	 * @param pattern 匹配模式，如"*"表示所有key
	 * @return 所有符合给定模式(pattern)的key
	 */
	public static Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	/**
	 * 将当前数据库的 key 移动到给定的数据库 db 当中
	 *
	 * @param key key
	 * @param dbIndex 要移动到的数据库
	 * @return 移动结果
	 */
	public static Boolean move(String key, int dbIndex) {
		return redisTemplate.move(key, dbIndex);
	}

	/**
	 * 移除 key 的过期时间，key 将持久保持
	 *
	 * @param key key
	 * @return 是否成功
	 */
	public static Boolean persist(String key) {
		return redisTemplate.persist(key);
	}

	/**
	 * 返回 key 的剩余的过期时间
	 *
	 * @param key key
	 * @param unit 剩余时间单位，详见{@link TimeUnit}
	 * @return 剩余时间
	 */
	public static Long getExpire(String key, TimeUnit unit) {
		return redisTemplate.getExpire(key, unit);
	}

	/**
	 * 返回 key 的剩余的过期时间
	 *
	 * @param key key
	 * @return 过期时间（单位秒）
	 */
	public static Long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}

	/**
	 * 从当前数据库中随机返回一个 key
	 *
	 * @return 随机key
	 */
	public static String randomKey() {
		return (String) redisTemplate.randomKey();
	}

	/**
	 * 修改 key 的名称
	 *
	 * @param oldKey 老的key
	 * @param newKey 新的key
	 */
	public static void rename(String oldKey, String newKey) {
		redisTemplate.rename(oldKey, newKey);
	}

	/**
	 * 仅当 newkey 不存在时，将 oldKey 改名为 newkey
	 *
	 * @param oldKey 老的key
	 * @param newKey 新的key
	 * @return
	 */
	public static Boolean renameIfAbsent(String oldKey, String newKey) {
		return redisTemplate.renameIfAbsent(oldKey, newKey);
	}

	/**
	 * 返回 key 所储存的值的类型
	 *
	 * @param key key
	 * @return key存储值的类型
	 */
	public static DataType type(String key) {
		return redisTemplate.type(key);
	}

	/** -------------------string相关操作--------------------- */

	/**
	 * 设置指定 key 的值
	 * @param key key
	 * @param value value
	 */
	public static void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 设置指定 key 的值
	 * @param key key
	 * @param value value
	 */
	public static void set(String key, Object value, long expire, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, value, expire, unit);
	}

	/**
	 * 获取指定 key 的值
	 * @param key key
	 * @return 值
	 */
	public static Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 返回 key 中字符串值的子字符
	 * @param key key
	 * @param start 字符串开始
	 * @param end 字符串结束
	 * @return 子字符串
	 */
	public static String getRange(String key, long start, long end) {
		return redisTemplate.opsForValue().get(key, start, end);
	}

	/**
	 * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
	 *
	 * @param key key
	 * @param value value
	 * @return 旧值
	 */
	public static Object getAndSet(String key, Object value) {
		return redisTemplate.opsForValue().getAndSet(key, value);
	}

	/**
	 * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)
	 *
	 * @param key key
	 * @param offset 偏移量
	 * @return 位
	 */
	public static Boolean getBit(String key, long offset) {
		return redisTemplate.opsForValue().getBit(key, offset);
	}

	/**
	 * 批量获取
	 *
	 * @param keys keys
	 * @return 批量value
	 */
	public static List<Object> multiGet(Collection<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}

	/**
	 * 设置ASCII码, 字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第offset位值变为value
	 *
	 * @param key
	 *            位置
	 * @param value
	 *            值,true为1, false为0
	 * @return 设置是否成功
	 */
	public static boolean setBit(String key, long offset, boolean value) {
		return redisTemplate.opsForValue().setBit(key, offset, value);
	}

	/**
	 * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
	 *
	 * @param key key
	 * @param value value
	 * @param timeout
	 *            过期时间
	 * @param unit
	 *            时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
	 *            秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
	 */
	public static void setEx(String key, Object value, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	/**
	 * 只有在 key 不存在时设置 key 的值
	 *
	 * @param key key
	 * @param value value
	 * @return 之前已经存在返回false,不存在返回true
	 */
	public static boolean setIfAbsent(String key, Object value, long second) {
		return redisTemplate.opsForValue().setIfAbsent(key, value, second, TimeUnit.SECONDS);
	}

	/**
	 * 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
	 *
	 * @param key key
	 * @param value value
	 * @param offset
	 *            从指定位置开始覆写
	 */
	public static void setRange(String key, Object value, long offset) {
		redisTemplate.opsForValue().set(key, value, offset);
	}

	/**
	 * 获取字符串的长度
	 *
	 * @param key key
	 * @return 长度
	 */
	public static Long size(String key) {
		return redisTemplate.opsForValue().size(key);
	}

	/**
	 * 批量添加
	 *
	 * @param maps 批量key-value
	 */
	public static void multiSet(Map<String, Object> maps) {
		redisTemplate.opsForValue().multiSet(maps);
	}

	/**
	 * 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在
	 *
	 * @param maps 批量key-value
	 * @return 之前已经存在返回false,不存在返回true
	 */
	public static boolean multiSetIfAbsent(Map<String, Object> maps) {
		return redisTemplate.opsForValue().multiSetIfAbsent(maps);
	}

	/**
	 * 增加(自增长), 负数则为自减
	 *
	 * @param key key
	 * @param increment 增加值
	 * @return 增加后的结果
	 */
	public static Long incrBy(String key, long increment) {
		return redisTemplate.opsForValue().increment(key, increment);
	}

	/**
	 * 增加double值
	 *
	 * @param key key
	 * @param increment 增加值
	 * @return 增加后的结果
	 */
	public static Double incrByFloat(String key, double increment) {
		return redisTemplate.opsForValue().increment(key, increment);
	}

	/**
	 * 追加到末尾
	 *
	 * @param key key
	 * @param value value
	 * @return 追加后的长度
	 */
	public static Integer append(String key, String value) {
		return redisTemplate.opsForValue().append(key, value);
	}

	/** -------------------hash相关操作------------------------- */

	/**
	 * 获取存储在哈希表中指定字段的值
	 *
	 * @param key key
	 * @param field 哈希表中的key
	 * @return 哈希表中key对应的值
	 */
	public static Object hGet(String key, String field) {
		return redisTemplate.opsForHash().get(key, field);
	}

	/**
	 * 获取所有给定字段的值
	 *
	 * @param key key
	 * @return 哈希表
	 */
	public static Map<Object, Object> hGetAll(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 获取所有给定字段的值
	 *
	 * @param key key
	 * @param fields 哈希表批量key
	 * @return 哈希表批量的值
	 */
	public static List<Object> hMultiGet(String key, Collection<Object> fields) {
		return redisTemplate.opsForHash().multiGet(key, fields);
	}

	/**
	 * 哈希表设置值
	 *
	 * @param key key
	 * @param hashKey hashKey
	 * @param value hashValue
	 */
	public static void hPut(String key, String hashKey, Object value) {
		redisTemplate.opsForHash().put(key, hashKey, value);
	}

	/**
	 * 哈希表批量设置值
	 *
	 * @param key key
	 * @param maps 哈希表批量 key value
	 */
	public static void hPutAll(String key, Map<Object, Object> maps) {
		redisTemplate.opsForHash().putAll(key, maps);
	}

	/**
	 * 仅当hashKey不存在时才设置
	 *
	 * @param key key
	 * @param hashKey hashKey
	 * @param value 值
	 * @return 是否设置成功
	 */
	public static Boolean hPutIfAbsent(String key, String hashKey, Object value) {
		return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
	}

	/**
	 * 删除一个或多个哈希表字段
	 *
	 * @param key key
	 * @param fields 要删除的哈希表的key
	 * @return 结果
	 */
	public static Long hDelete(String key, Object... fields) {
		return redisTemplate.opsForHash().delete(key, fields);
	}

	/**
	 * 查看哈希表 key 中，指定的字段是否存在
	 *
	 * @param key key
	 * @param field 哈希表的key
	 * @return 是否存在
	 */
	public static boolean hExists(String key, Object field) {
		return redisTemplate.opsForHash().hasKey(key, field);
	}

	/**
	 * 为哈希表 key 中的指定字段的整数值加上增量 increment
	 *
	 * @param key key
	 * @param field hashKey
	 * @param increment 要增加的值
	 * @return 增加后的值
	 */
	public static Long hIncrBy(String key, Object field, long increment) {
		return redisTemplate.opsForHash().increment(key, field, increment);
	}

	/**
	 * 为哈希表 key 中的指定字段的整数值加上增量 increment
	 *
	 * @param key key
	 * @param field hashKey
	 * @param delta 要增加的double值
	 * @return 增加后的值
	 */
	public static Double hIncrByFloat(String key, Object field, double delta) {
		return redisTemplate.opsForHash().increment(key, field, delta);
	}

	/**
	 * 获取所有哈希表中的字段
	 *
	 * @param key key
	 * @return 哈希表所有key
	 */
	public static Set<Object> hKeys(String key) {
		return redisTemplate.opsForHash().keys(key);
	}

	/**
	 * 获取哈希表中字段的数量
	 *
	 * @param key key
	 * @return 哈希表大小
	 */
	public static Long hSize(String key) {
		return redisTemplate.opsForHash().size(key);
	}

	/**
	 * 获取哈希表中所有值
	 *
	 * @param key key
	 * @return 哈希表所有value
	 */
	public static List<Object> hValues(String key) {
		return redisTemplate.opsForHash().values(key);
	}

	/**
	 * 迭代哈希表中的键值对
	 *
	 * @param key key
	 * @param options 查询规则
	 * @return 结果
	 */
	public static Cursor<Map.Entry<Object, Object>> hScan(String key, ScanOptions options) {
		return redisTemplate.opsForHash().scan(key, options);
	}

	/** ------------------------list相关操作---------------------------- */

	/**
	 * 通过索引获取列表中的元素
	 *
	 * @param key key
	 * @param index 索引
	 * @return 值
	 */
	public static Object lIndex(String key, long index) {
		return redisTemplate.opsForList().index(key, index);
	}

	/**
	 * 获取列表指定范围内的元素
	 *
	 * @param key key
	 * @param start
	 *            开始位置, 0是开始位置
	 * @param end
	 *            结束位置, -1返回所有
	 * @return 指定范围的值
	 */
	public static List<Object> lRange(String key, long start, long end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	/**
	 * 存储在list头部
	 *
	 * @param key key
	 * @param value value
	 * @return 插入后元素个数
	 */
	public static Long lLeftPush(String key, Object value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * 批量头插
	 * @param key key
	 * @param value 批量值
	 * @return 插入后元素个数
	 */
	public static Long lLeftPushAll(String key, Object... value) {
		return redisTemplate.opsForList().leftPushAll(key, value);
	}

	/**
	 * 批量头插
	 * @param key key
	 * @param value 批量值集合
	 * @return 插入后元素个数
	 */
	public static Long lLeftPushAll(String key, Collection<Object> value) {
		return redisTemplate.opsForList().leftPushAll(key, value);
	}

	/**
	 * 当list存在的时候才加入
	 *
	 * @param key key
	 * @param value value
	 * @return 元素个数
	 */
	public static Long lLeftPushIfPresent(String key, Object value) {
		return redisTemplate.opsForList().leftPushIfPresent(key, value);
	}

	/**
	 * 如果pivot存在,再pivot前面添加
	 *
	 * @param key key
	 * @param pivot 指定元素
	 * @param value 值
	 * @return 元素个数
	 */
	public static Long lLeftPush(String key, String pivot, Object value) {
		return redisTemplate.opsForList().leftPush(key, pivot, value);
	}

	/**
	 * 尾插
	 *
	 * @param key key
	 * @param value value
	 * @return 元素个数
	 */
	public static Long lRightPush(String key, Object value) {
		return redisTemplate.opsForList().rightPush(key, value);
	}

	/**
	 * 尾插
	 *
	 * @param key key
	 * @param value 批量value
	 * @return 元素个数
	 */
	public static Long lRightPushAll(String key, Object... value) {
		return redisTemplate.opsForList().rightPushAll(key, value);
	}

	/**
	 * 尾插
	 *
	 * @param key key
	 * @param value value集合
	 * @return 元素个数
	 */
	public static Long lRightPushAll(String key, Collection<Object> value) {
		return redisTemplate.opsForList().rightPushAll(key, value);
	}

	/**
	 * 为已存在的列表添加值
	 *
	 * @param key key
	 * @param value value
	 * @return 元素个数
	 */
	public static Long lRightPushIfPresent(String key, Object value) {
		return redisTemplate.opsForList().rightPushIfPresent(key, value);
	}

	/**
	 * 在pivot元素的右边添加值
	 *
	 * @param key key
	 * @param pivot 查找值
	 * @param value 插入值
	 * @return 元素个数
	 */
	public static Long lRightPush(String key, String pivot, Object value) {
		return redisTemplate.opsForList().rightPush(key, pivot, value);
	}

	/**
	 * 通过索引设置列表元素的值
	 *
	 * @param key key
	 * @param index
	 *            位置
	 * @param value 治好
	 */
	public static void lSet(String key, long index, Object value) {
		redisTemplate.opsForList().set(key, index, value);
	}

	/**
	 * 移出并获取列表的第一个元素
	 *
	 * @param key key
	 * @return 删除的元素
	 */
	public static Object lLeftPop(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}

	/**
	 * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
	 *
	 * @param key key
	 * @param timeout
	 *            等待时间
	 * @param unit
	 *            时间单位
	 * @return 值
	 */
	public static Object lBLeftPop(String key, long timeout, TimeUnit unit) {
		return redisTemplate.opsForList().leftPop(key, timeout, unit);
	}

	/**
	 * 移除并获取列表最后一个元素
	 *
	 * @param key key
	 * @return 删除的元素
	 */
	public static Object lRightPop(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}

	/**
	 * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
	 *
	 * @param key key
	 * @param timeout
	 *            等待时间
	 * @param unit
	 *            时间单位
	 * @return 值
	 */
	public static Object lBRightPop(String key, long timeout, TimeUnit unit) {
		return redisTemplate.opsForList().rightPop(key, timeout, unit);
	}

	/**
	 * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
	 *
	 * @param sourceKey 移除元素的key
	 * @param destinationKey 新增元素的key
	 * @return 新增的元素
	 */
	public static Object lRightPopAndLeftPush(String sourceKey, String destinationKey) {
		return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
	}

	/**
	 * 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
	 *
	 * @param sourceKey 要移除元素的key
	 * @param destinationKey 要新增元素的key
	 * @param timeout 超时时间
	 * @param unit 超时单位，详见{@link TimeUnit}
	 * @return 操作的元素
	 */
	public static Object lBRightPopAndLeftPush(String sourceKey, String destinationKey, long timeout, TimeUnit unit) {
		return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey, timeout, unit);
	}

	/**
	 * 删除集合中值等于value得元素
	 *
	 * @param key key
	 * @param index
	 *            index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素;
	 *            index<0, 从尾部开始删除第一个值等于value的元素;
	 * @param value 值
	 * @return 删除结果
	 */
	public static Long lRemove(String key, long index, Object value) {
		return redisTemplate.opsForList().remove(key, index, value);
	}

	/**
	 * 裁剪list
	 *
	 * @param key key
	 * @param start start
	 * @param end end
	 */
	public static void lTrim(String key, long start, long end) {
		redisTemplate.opsForList().trim(key, start, end);
	}

	/**
	 * 获取列表长度
	 *
	 * @param key key
	 * @return 长度
	 */
	public static Long lLen(String key) {
		return redisTemplate.opsForList().size(key);
	}

	/** --------------------set相关操作-------------------------- */

	/**
	 * set添加元素
	 *
	 * @param key key
	 * @param values 批量value
	 * @return 添加元素个数
	 */
	public static Long sAdd(String key, Object... values) {
		return redisTemplate.opsForSet().add(key, values);
	}

	/**
	 * set移除元素
	 *
	 * @param key key
	 * @param values 要移除的元素
	 * @return 移除个数
	 */
	public static Long sRemove(String key, Object... values) {
		return redisTemplate.opsForSet().remove(key, values);
	}

	/**
	 * 移除并返回集合的一个随机元素
	 *
	 * @param key key
	 * @return 元素
	 */
	public static Object sPop(String key) {
		return redisTemplate.opsForSet().pop(key);
	}

	/**
	 * 将元素value从一个集合移到另一个集合
	 *
	 * @param key key
	 * @param value value
	 * @param destKey 目标集合key
	 * @return 结果
	 */
	public static Boolean sMove(String key, Object value, String destKey) {
		return redisTemplate.opsForSet().move(key, value, destKey);
	}

	/**
	 * 获取集合的大小
	 *
	 * @param key key
	 * @return 大小
	 */
	public static Long sSize(String key) {
		return redisTemplate.opsForSet().size(key);
	}

	/**
	 * 判断集合是否包含value
	 *
	 * @param key key
	 * @param value value
	 * @return 是否包含
	 */
	public static Boolean sIsMember(String key, Object value) {
		return redisTemplate.opsForSet().isMember(key, value);
	}

	/**
	 * 获取两个集合的交集
	 *
	 * @param key key
	 * @param otherKey 另一个key
	 * @return 交集
	 */
	public static Set<Object> sIntersect(String key, String otherKey) {
		return redisTemplate.opsForSet().intersect(key, otherKey);
	}

	/**
	 * 获取key集合与多个集合的交集
	 *
	 * @param key key
	 * @param otherKeys 其他key
	 * @return 交集
	 */
	public static Set<Object> sIntersect(String key, Collection<String> otherKeys) {
		return redisTemplate.opsForSet().intersect(key, otherKeys);
	}

	/**
	 * key集合与otherKey集合的交集存储到destKey集合中
	 *
	 * @param key key
	 * @param otherKey 其他key
	 * @param destKey 要存储的key
	 * @return 个数
	 */
	public static Long sIntersectAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().intersectAndStore(key, otherKey, destKey);
	}

	/**
	 * key集合与多个集合的交集存储到destKey集合中
	 *
	 * @param key key
	 * @param otherKeys 其他key
	 * @param destKey 要存储的key
	 * @return 个数
	 */
	public static Long sIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForSet().intersectAndStore(key, otherKeys, destKey);
	}

	/**
	 * 获取两个集合的并集
	 *
	 * @param key key
	 * @param otherKeys 其他key
	 * @return 并集
	 */
	public static Set<Object> sUnion(String key, String otherKeys) {
		return redisTemplate.opsForSet().union(key, otherKeys);
	}

	/**
	 * 获取key集合与多个集合的并集
	 *
	 * @param key key
	 * @param otherKeys 其他keys
	 * @return 并集
	 */
	public static Set<Object> sUnion(String key, Collection<String> otherKeys) {
		return redisTemplate.opsForSet().union(key, otherKeys);
	}

	/**
	 * key集合与otherKey集合的并集存储到destKey中
	 *
	 * @param key key
	 * @param otherKey 其他key
	 * @param destKey 目标key
	 * @return 个数
	 */
	public static Long sUnionAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
	}

	/**
	 * key集合与多个集合的并集存储到destKey中
	 *
	 * @param key key
	 * @param otherKeys 其他key
	 * @param destKey 目标key
	 * @return 个数
	 */
	public static Long sUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
	}

	/**
	 * 获取两个集合的差集
	 *
	 * @param otherKey 其他key
	 * @return 个数
	 */
	public static Set<Object> sDifference(String key, String otherKey) {
		return redisTemplate.opsForSet().difference(key, otherKey);
	}

	/**
	 * 获取key集合与多个集合的差集
	 *
	 * @param key
	 * @param otherKeys 其他key
	 * @return 个数
	 */
	public static Set<Object> sDifference(String key, Collection<String> otherKeys) {
		return redisTemplate.opsForSet().difference(key, otherKeys);
	}

	/**
	 * key集合与otherKey集合的差集存储到destKey中
	 *
	 * @param key key
	 * @param otherKey 其他key
	 * @param destKey 目标key
	 * @return 个数
	 */
	public static Long sDifference(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
	}

	/**
	 * key集合与多个集合的差集存储到destKey中
	 *
	 * @param key key
	 * @param otherKeys 其他key
	 * @param destKey 目标key
	 * @return 个数
	 */
	public static Long sDifference(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
	}

	/**
	 * 获取集合所有元素
	 *
	 * @param key key
	 * @return 所有元素
	 */
	public static Set<Object> setMembers(String key) {
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * 随机获取集合中的一个元素
	 *
	 * @param key key
	 * @return 随机元素
	 */
	public static Object sRandomMember(String key) {
		return redisTemplate.opsForSet().randomMember(key);
	}

	/**
	 * 随机获取集合中count个元素
	 *
	 * @param key key
	 * @param count count个
	 * @return 随机元素
	 */
	public static List<Object> sRandomMembers(String key, long count) {
		return redisTemplate.opsForSet().randomMembers(key, count);
	}

	/**
	 * 随机获取集合中count个元素并且去除重复的
	 *
	 * @param key key
	 * @param count count个
	 * @return 随机元素
	 */
	public static Set<Object> sDistinctRandomMembers(String key, long count) {
		return redisTemplate.opsForSet().distinctRandomMembers(key, count);
	}

	/**
	 * 匹配值
	 *
	 * @param key key
	 * @param options 规则
	 * @return 匹配结果
	 */
	public static Cursor<Object> sScan(String key, ScanOptions options) {
		return redisTemplate.opsForSet().scan(key, options);
	}

	/**------------------zSet相关操作--------------------------------*/

	/**
	 * 添加元素,有序集合是按照元素的score值由小到大排列
	 *
	 * @param key key
	 * @param value value
	 * @param score score
	 * @return 添加结果
	 */
	public static Boolean zAdd(String key, Object value, double score) {
		return redisTemplate.opsForZSet().add(key, value, score);
	}

	/**
	 * 批量添加
	 *
	 * @param key key
	 * @param values values
	 * @return 结果
	 */
	public static Long zAdd(String key, Set<ZSetOperations.TypedTuple<Object>> values) {
		return redisTemplate.opsForZSet().add(key, values);
	}

	/**
	 *
	 * @param key
	 * @param values
	 * @return
	 */
	public static Long zRemove(String key, Object... values) {
		return redisTemplate.opsForZSet().remove(key, values);
	}

	/**
	 * 增加元素的score值，并返回增加后的值
	 *
	 * @param key
	 * @param value
	 * @param delta
	 * @return
	 */
	public static Double zIncrementScore(String key, Object value, double delta) {
		return redisTemplate.opsForZSet().incrementScore(key, value, delta);
	}

	/**
	 * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
	 *
	 * @param key
	 * @param value
	 * @return 0表示第一位
	 */
	public static Long zRank(String key, Object value) {
		return redisTemplate.opsForZSet().rank(key, value);
	}

	/**
	 * 返回元素在集合的排名,按元素的score值由大到小排列
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public static Long zReverseRank(String key, Object value) {
		return redisTemplate.opsForZSet().reverseRank(key, value);
	}

	/**
	 * 获取集合的元素, 从小到大排序
	 *
	 * @param key
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置, -1查询所有
	 * @return
	 */
	public static Set<Object> zRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().range(key, start, end);
	}

	/**
	 * 获取集合元素, 并且把score值也获取
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static Set<ZSetOperations.TypedTuple<Object>> zRangeWithScores(String key, long start, long end) {
		return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
	}

	/**
	 * 根据Score值查询集合元素
	 *
	 * @param key
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return
	 */
	public static Set<Object> zRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().rangeByScore(key, min, max);
	}

	/**
	 * 根据Score值查询集合元素, 从小到大排序
	 *
	 * @param key
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return
	 */
	public static Set<ZSetOperations.TypedTuple<Object>> zRangeByScoreWithScores(String key, double min, double max) {
		return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
	}

	/**
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @param start
	 * @param end
	 * @return
	 */
	public static Set<ZSetOperations.TypedTuple<Object>> zRangeByScoreWithScores(String key,
																		  double min, double max, long start, long end) {
		return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, start, end);
	}

	/**
	 * 获取集合的元素, 从大到小排序
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static Set<Object> zReverseRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRange(key, start, end);
	}

	/**
	 * 获取集合的元素, 从大到小排序, 并返回score值
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static Set<ZSetOperations.TypedTuple<Object>> zReverseRangeWithScores(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
	}

	/**
	 * 根据Score值查询集合元素, 从大到小排序
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public static Set<Object> zReverseRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
	}

	/**
	 * 根据Score值查询集合元素, 从大到小排序
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public static Set<ZSetOperations.TypedTuple<Object>> zReverseRangeByScoreWithScores(
			String key, double min, double max) {
		return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
	}

	/**
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @param start
	 * @param end
	 * @return
	 */
	public static Set<Object> zReverseRangeByScore(String key, double min, double max, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, start, end);
	}

	/**
	 * 根据score值获取集合元素数量
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public static Long zCount(String key, double min, double max) {
		return redisTemplate.opsForZSet().count(key, min, max);
	}

	/**
	 * 获取集合大小
	 *
	 * @param key
	 * @return
	 */
	public static Long zSize(String key) {
		return redisTemplate.opsForZSet().size(key);
	}

	/**
	 * 获取集合大小
	 *
	 * @param key
	 * @return
	 */
	public static Long zZCard(String key) {
		return redisTemplate.opsForZSet().zCard(key);
	}

	/**
	 * 获取集合中value元素的score值
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public static Double zScore(String key, Object value) {
		return redisTemplate.opsForZSet().score(key, value);
	}

	/**
	 * 移除指定索引位置的成员
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static Long zRemoveRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().removeRange(key, start, end);
	}

	/**
	 * 根据指定的score值的范围来移除成员
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public static Long zRemoveRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
	}

	/**
	 * 获取key和otherKey的并集并存储在destKey中
	 *
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public static Long zUnionAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
	}

	/**
	 *
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public static Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
	}

	/**
	 * 交集
	 *
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public static Long zIntersectAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
	}

	/**
	 * 交集
	 *
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public static Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
	}

	/**
	 *
	 * @param key
	 * @param options
	 * @return
	 */
	public static Cursor<ZSetOperations.TypedTuple<Object>> zScan(String key, ScanOptions options) {
		return redisTemplate.opsForZSet().scan(key, options);
	}
}
