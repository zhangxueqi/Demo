package cn.zhang.opensource.util.filethread.deal;

import java.util.Iterator;
import java.util.Map;

import cn.zhang.opensource.util.filethread.enginee.ParseEnginee;

public class MyDealTask extends DealTask {

	@Override
	public void handler(Map data) throws InterruptedException {
		// TODO Auto-generated method stub
		if(data == ParseEnginee.DUMMY) {
			queue.put(data);
			setDone();
		}else {
			for(Iterator it=data.keySet().iterator(); it.hasNext();) {
				String key = String.valueOf(it.next());
				System.out.print(Thread.currentThread().getName() + "import:>>>[" + key + "]:[" + data.get(key)
                + "];\t");
			}
			System.out.println("\n");
		}
	}

}
