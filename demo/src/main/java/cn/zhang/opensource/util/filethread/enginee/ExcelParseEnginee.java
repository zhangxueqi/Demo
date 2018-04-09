package cn.zhang.opensource.util.filethread.enginee;

import java.io.File;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * excel 解析器
 * @author think
 *
 */
public class ExcelParseEnginee extends ParseEnginee {

	public ExcelParseEnginee(BlockingQueue<Map> queue, File file) {
		super();
		this.queue = queue;
		this.file = file;
	
	}

	@Override
	public void parseFileToQueue(BlockingQueue<Map> queue, File file) {
		// TODO Auto-generated method stub

	}

}
