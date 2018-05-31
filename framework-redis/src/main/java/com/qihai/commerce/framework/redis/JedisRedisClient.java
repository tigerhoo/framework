package com.qihai.commerce.framework.redis;

import java.util.List;

/**
 * JedisRedisClient接口
 * 
 * @author zhugj
 * @date 2018年5月22日 下午2:03:29
 * @version 1.0.0
 */
public interface JedisRedisClient {

	/**
	 * key 存在的话  value 能够存入 否则存入失败
	 * */
	long setNx(String key, String value);
	/**
	 * 以key ：value 存入原生字符串类型
	 */
	String set(String key, String value);
	/**
	 * 获取key 对应的值
	 * */
	String get(String key);
	/**
	 * 对应key 是否存在
	 * */
	Boolean exists(String key);
	/**
	 * 设置key值的过期时间
	 * */
	Long expire(String key, int seconds);
	/**
	 * 查看key值的过期时间  返回  -1位永不过期 -2 为已经过期 其他为还剩下的过期秒数
	 * */
	Long ttl(String key);
	/**
	 * key值对应的value 自增加1
	 * */
	Long incr(String key);
	/**
	 * 哈希类型：每个用户属性使用一对field-value 但是只用key一个键保存,达到分组的效果
	 * */
	Long hset(String key, String field, String value);
	/**
	 * 获取key值下面的field值
	 * */
	String hget(String key, String field);
	/**
	 * 会删除一个或者多个field，返回结果为成功删除的个数
	 * */
	Long hdel(String key, String... field);
	/**
	 * 判断field是否存在
	 * */
	Boolean hexists(String key, String field);
	/**
	 * 根据key 获取 哈希的所有field
	 * */
	List<String> hvals(String key);
	/**
	 * 根据key 删除对应的value
	 * */
	Long del(String key);
	/**
	 * 通过key值 获取 redis string缓存 返回T Clazz
	 * 获取key 对应的值
	 *  @param clazz 对象中的object类型
	 * */
	 <T> T getPojoByKey(String key, Class<T> beanType) throws Exception;

}
