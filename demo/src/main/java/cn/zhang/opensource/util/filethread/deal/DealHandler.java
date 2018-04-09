package cn.zhang.opensource.util.filethread.deal;

import java.util.Map;

/**
 * 处理接口
 * @author think
 *
 */
public interface DealHandler {
	void handler(Map data) throws InterruptedException;
}
