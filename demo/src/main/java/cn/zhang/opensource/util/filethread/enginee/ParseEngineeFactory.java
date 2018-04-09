package cn.zhang.opensource.util.filethread.enginee;

import java.io.File;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import cn.zhang.opensource.util.filethread.enginee.type.ParseSupportType;

/**
 * 解析器工厂
 * @author think
 *
 */
public class ParseEngineeFactory {
	
	/**
	 * 获取真实解析类，文件的后缀，对应ParseSupportType
	 * @param queue
	 * @param file
	 * @return
	 */
	public static ParseEnginee getInstance(BlockingQueue<Map> queue, File file) {
		ParseEnginee parseEnginee = null;
		String fileName = file.getName();
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		if(ParseSupportType.PARSE_EXCEL.equals(type)) {
			parseEnginee = new ExcelParseEnginee(queue, file);
		} else if(ParseSupportType.PARSE_TXT.equals(type)) {
			parseEnginee = new TextParseEnginee(queue, file);
		}
		
		return parseEnginee;
	}
		
}
