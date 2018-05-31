package com.qihai.commerce.framework.utils;

import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * dubbo工厂
 * 
 * @author zhugj
 * @email zhuguojin@qihaiyun.com
 * @date 2017-12-12 0:00
 */
public class DubboServiceFactory {
	
    private final static Logger logger = LoggerFactory.getLogger(DubboServiceFactory.class);

	private ApplicationConfig application;
	private RegistryConfig registry;
	private String dubboVersion;
	
	private String applicationName;
	private String registryAddress;
	private String version;
	
	private static class SingletonHolder {
		private static DubboServiceFactory INSTANCE = new DubboServiceFactory();
	}
	
	@SuppressWarnings("rawtypes")
	private DubboServiceFactory() {
/*		
		Properties prop = new Properties();
		ClassLoader loader = DubboServiceFactory.class.getClassLoader();

		try {
			prop.load(loader.getResourceAsStream("application.yml"));
			String active = prop.getProperty("active");
			String fileName = "application-" + active + ".yml";
			prop.load(loader.getResourceAsStream(fileName));
		} catch (IOException e) {
			logger.error("读取属性文件失败", e);
		}
*/
		try {
            Yaml yaml = new Yaml();
            URL url = DubboServiceFactory.class.getClassLoader().getResource("application.yml");
            if (url != null) {
                //也可以将值转换为Map
                
				Map map =(Map)yaml.load(new FileInputStream(url.getFile()));
				Object activeObj = ((Map)((Map)map.get("spring")).get("profiles")).get("active");
                String active = activeObj.toString();
				url = DubboServiceFactory.class.getClassLoader().getResource("application-" + active + ".yml");
                map =(Map)yaml.load(new FileInputStream(url.getFile())); 
                Map dubboMap = (Map)((Map)map.get("spring")).get("dubbo");
                applicationName =  (String) ((Map)dubboMap.get("application")).get("name");
                registryAddress = (String) ((Map)dubboMap.get("registry")).get("address");
                version = (String)dubboMap.get("version");
            }
        } catch (Exception e) {
            logger.error("读取属性文件报错", e);
        }
		ApplicationConfig applicationConfig = new ApplicationConfig();
		//applicationConfig.setName(prop.getProperty("spring.dubbo.application.name"));
		applicationConfig.setName(applicationName);
		// 这里配置了dubbo的application信息
		RegistryConfig registryConfig = new RegistryConfig();
		// 这里配置dubbo的注册中心信息
		//registryConfig.setAddress(prop.getProperty("spring.dubbo.registry.address"));
		registryConfig.setAddress(registryAddress);
		
		this.application = applicationConfig;
		this.registry = registryConfig;
		//this.dubboVersion = prop.getProperty("spring.dubbo.version");
		this.dubboVersion = version;
	}

	public static DubboServiceFactory getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public Object genericInvoke(String interfaceClass, String methodName, List<Map<String, Object>> parameters) {

		ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
		reference.setApplication(application);
		reference.setRegistry(registry);
		reference.setInterface(interfaceClass); // 接口名
		reference.setVersion(dubboVersion); //版本号
		reference.setGeneric(true); // 声明为泛化接口
		/*
		 * ReferenceConfig实例很重，封装了与注册中心的连接以及与提供者的连接，
		 * 需要缓存，否则重复生成ReferenceConfig可能造成性能问题并且会有内存和连接泄漏。 API方式编程时，容易忽略此问题。
		 * 这里使用dubbo内置的简单缓存工具类进行缓存
		 */
		ReferenceConfigCache cache = ReferenceConfigCache.getCache();
		GenericService genericService = cache.get(reference);

		int len = parameters.size();
		String[] invokeParamTyeps = new String[len];
		Object[] invokeParams = new Object[len];
		for (int i = 0; i < len; i++) {
			invokeParamTyeps[i] = parameters.get(i).get("ParamType") + "";
			invokeParams[i] = parameters.get(i).get("Object");
		}
		return genericService.$invoke(methodName, invokeParamTyeps, invokeParams);
	}

}
