package cn.zhang.opensource.util.filethread.deal;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhang.opensource.util.filethread.enginee.ParseEnginee;

/**
 * 处理解析后的文件数据
 * @author think
 *
 */
public abstract class DealTask implements Runnable, DealHandler {
	protected BlockingQueue<Map> queue;
	private Logger logger = LoggerFactory.getLogger(DealTask.class);
	private boolean done = false;
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!done) {
				Map data = queue.take();
				handler(data);
			}
		} catch (Exception e) {
			logger.error("import file into db error: "+e.getMessage());
			try {
				Thread.interrupted();
			} catch (Exception e1) {
			}
			try {
				queue.put(ParseEnginee.DUMMY);
			} catch (InterruptedException e1) {
			}
		}
	}
	
	public void setDone() {
		done = true;
	}

	public void setQueue(BlockingQueue<Map> queue) {
		// TODO Auto-generated method stub 
		this.queue = queue;
	}

}
