package com.qihai.commerce.framework.enums;

/**
 * RoleDataScope
 * 
 * @author zhugj
 *
 * 2017年12月12日 下午2:37:49
 */
public enum RoleDataScope {
	
	DATA_SCOPE_ALL("1", "所有数据"),
	DATA_SCOPE_COMPANY_AND_CHILD("2", "所在公司及以下数据"),
	DATA_SCOPE_COMPANY("3", "所在公司数据"),
	DATA_SCOPE_OFFICE_AND_CHILD("4", "所在部门及以下数据"),
	DATA_SCOPE_OFFICE("5", "所在部门数据"),
	DATA_SCOPE_SELF("8", "仅本人数据"),
	DATA_SCOPE_CUSTOM("9", "按明细设置");
	
	private RoleDataScope(String value, String info) {    
        this.value = value;    
        this.info = info;    
    } 
	
	private final String value;    
    private final String info;

    
    public String getInfo() {    
        return info;    
    }    
    
    public String getValue() {    
        return value;    
    }
    
    public static String valueToInfo(String myValue) {    
    	
    	String myInfo = null;
    	for(RoleDataScope item : RoleDataScope.values()){
    		if(item.value.equals(myValue)){
    			myInfo = item.info;
    			break ;
    		}
    	}
    	
    	return myInfo;
    }
}
