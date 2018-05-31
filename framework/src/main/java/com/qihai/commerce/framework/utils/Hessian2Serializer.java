package com.qihai.commerce.framework.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import org.springframework.core.serializer.Serializer;

import com.alibaba.com.caucho.hessian.io.Hessian2Output;

/**
 * Serializer that writes an object to an output stream using Hessian2 Serialization.
 *
 * @author zhugj
 */
public class Hessian2Serializer implements Serializer<Object> {

	/**
	 * Writes the source object to an output stream using Hessian2 Serialization.
	 * The source object must implement {@link Serializable}.
	 */
	@Override
	public void serialize(Object object, OutputStream outputStream) throws IOException {
		if (!(object instanceof Serializable)) {
			throw new IllegalArgumentException(getClass().getSimpleName() + " requires a Serializable payload " +
					"but received an object of type [" + object.getClass().getName() + "]");
		}
		Hessian2Output objectOutputStream = new Hessian2Output(outputStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
	}

}
