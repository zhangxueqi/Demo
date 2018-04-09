package cn.zhang.opensource.util.filethread.enginee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * txt文本解析器
 * @author think
 *
 */
public class TextParseEnginee extends ParseEnginee {
	private static Logger logger = LoggerFactory.getLogger(TextParseEnginee.class);
	
	public TextParseEnginee(BlockingQueue<Map> queue, File file) {
		super();
		this.queue = queue;
		this.file = file;
	}

	@Override
	public void parseFileToQueue(BlockingQueue<Map> queue, File file) {
		FileInputStream is = null;
		String line = "";
		Map dataRow = new HashMap();
		int i =0;
		try {
			if(file.exists()) {
				is = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				while((line=br.readLine())!=null) {
					if(line.trim().length()>0) {
						String[] data = line.split(",");
						for(int index=0;index<data.length;index++) {
							dataRow.put(String.valueOf(index), data[index]);
						}
						queue.put(dataRow);
						dataRow = new HashMap<>();
						i++;
					}
				}
						
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("import war interrupted, error happened in "+i+ " row", e);
		} finally {
			try {
				if(is != null) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
				is = null;
				logger.error("FileInputStream close erro message={}", e.getMessage());
			}
		}
	}

}
