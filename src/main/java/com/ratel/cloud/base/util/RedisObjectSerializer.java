package com.ratel.cloud.base.util;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  RedisObjectSerializer.java   
 * @Package com.ratel.cloud.base.util   
 * @Description:定义的序列化操作表示可以序列化所有类的对象，类的对象所在的类一定要实现序列化接口
 * @author: gaoheng
 * @date:   2017年11月26日 上午10:17:54   
 * @version V1.0
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {
	// 进行对象与字节数组的转换，准备出两个转换器
	private Converter<Object, byte[]> serializingConverter = new SerializingConverter();//序列化
	private Converter<byte[], Object> deserializingConverter = new DeserializingConverter();//反序列化
	private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];	// 做一个空数组，不是null
	@Override
	public byte[] serialize(Object obj) throws SerializationException {
		if (obj == null) {	// 这个时候没有要序列化的对象出现，所以返回的字节数组应该就是一个空数组
			return EMPTY_BYTE_ARRAY ;
		}
		return this.serializingConverter.convert(obj);	// 将对象变为字节数组
	}
	@Override
	public Object deserialize(byte[] data) throws SerializationException {
		if (data == null || data.length == 0) {	// 此时没有对象的内容信息
			return null ;
		}
		return this.deserializingConverter.convert(data);
	}

}