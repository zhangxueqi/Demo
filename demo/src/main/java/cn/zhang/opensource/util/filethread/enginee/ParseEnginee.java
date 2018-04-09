package cn.zhang.opensource.util.filethread.enginee;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解析器处理线程
 * @author think
 *
 */
public abstract class ParseEnginee implements Runnable{
	protected File file;
	protected BlockingQueue<Map> queue;
	public static Map DUMMY = new HashMap<>();
	private static Logger logger = LoggerFactory.getLogger(ParseEnginee.class);	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			parseFileToQueue(queue, file);
		} catch (Exception e) {
			logger.error("read fileName error, parse excel quit because:"+e.getMessage(), e);
			try {
				Thread.interrupted();
			} catch (Exception e1) {
			}
		}finally {
			try {
				queue.put(DUMMY);
			} catch (InterruptedException e) {
			}
		}
	}
	
	public abstract void parseFileToQueue(BlockingQueue<Map> queue, File file);
	
}
