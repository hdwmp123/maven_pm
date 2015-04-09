package cn.com.king.jfinal.model.bean;

/**
 * 
 * @Title: CheckDelMsg.java
 * @Package cn.com.king.jfinal.model.bean
 * @Description: 校验是否可删除对象消息体
 * @author hdwmp123@163.com
 * @date 2015-3-20 下午5:03:37
 * @version V1.0
 */
public class CheckDelMsg {
	private boolean canDel;
	private String msg;

	public boolean isCanDel() {
		return canDel;
	}

	public void setCanDel(boolean canDel) {
		this.canDel = canDel;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
