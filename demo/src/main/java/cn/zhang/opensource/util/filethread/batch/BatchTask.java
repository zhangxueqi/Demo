package cn.zhang.opensource.util.filethread.batch;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import cn.zhang.opensource.util.filethread.deal.DealTask;
import cn.zhang.opensource.util.filethread.deal.MyDealTask;
import cn.zhang.opensource.util.filethread.enginee.ParseEnginee;
import cn.zhang.opensource.util.filethread.enginee.ParseEngineeFactory;

public class BatchTask {
	private static final int QUEUE_SIZE = 500;
	private static final int TASH_THREAD_SIZE = Runtime.getRuntime().availableProcessors() * 2;
	private DealTask dealTask;
	
	public BatchTask(DealTask dealTask) {
		this.dealTask = dealTask;
	}
	
	public void doBatch(File file){
		if(!file.exists()) {
			System.out.println("file not found");
			return;
		}else {
			BlockingQueue<Map> queue= new ArrayBlockingQueue<>(QUEUE_SIZE);
			
			readFile(queue, file);
			
			dealFile(queue);
		}
	}
	
	/**
	 * 处理文件的数据
	 * @param queue
	 *
	 * @author think
	 */
	private void dealFile(BlockingQueue<Map> queue) {
		// TODO Auto-generated method stub
		dealTask.setQueue(queue);
		
		for(int i =0;i<TASH_THREAD_SIZE;i++) {
			new Thread(dealTask, "dealTask"+i).start();
		}
	}
	
	/**
	 * 读取文件
	 * @param queue
	 * @param file
	 *
	 * @author think
	 */
	private void readFile(BlockingQueue<Map> queue, File file) {
		// TODO Auto-generated method stub
		ParseEnginee parseEnginee = ParseEngineeFactory.getInstance(queue, file);
		new Thread(parseEnginee).start();
	}
	
	public static void main(String[] args) {
		File file = new File(BatchTask.class.getResource("/").getPath()+"/cn/zhang/opensource/util/filethread/test.txt");
		MyDealTask myDealTask = new MyDealTask();
        BatchTask batchTask = new BatchTask(myDealTask);
        batchTask.doBatch(file);
	}
}
