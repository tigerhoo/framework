package com.qihai.commerce.framework.mq.consumer;

public class MessageHandler {

	public void onMessage(byte[] message) {
		System.out.println("---------onMessage----byte-------------");
		System.out.println(new String(message));
	}

	public void onMessage(String message) {
		System.out.println("---------onMessage---String-------------");
		System.out.println(message);
	}
}
