package com.qihai.commerce.framework.utils;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.NestedIOException;
import org.springframework.core.serializer.Deserializer;

import com.alibaba.com.caucho.hessian.io.Hessian2Input;

/**
 * Deserializer that reads an input stream using Hessian2 Serialization.
 *
 * @author zhugj
 */
public class Hessian2Deserializer implements Deserializer<Object> {

	/**
	 * Reads the input stream and deserializes into an object.
	 */
	@Override
	public Object deserialize(InputStream inputStream) throws IOException {
		Hessian2Input objectInputStream = new Hessian2Input(inputStream);
		try {
			return objectInputStream.readObject();
		} catch (Exception ex) {
			throw new NestedIOException("Failed to deserialize object type", ex);
		}

	}

}
