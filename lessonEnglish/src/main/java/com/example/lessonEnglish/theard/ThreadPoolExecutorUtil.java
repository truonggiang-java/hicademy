package com.example.lessonEnglish.theard;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ThreadPoolExecutorUtil {
	private Logger logger= LoggerFactory.getLogger(ThreadPoolExecutorUtil.class);
	
	private ThreadPoolExecutor threadPoolExecutor;
	
	public ThreadPoolExecutorUtil() {
		//BlockingQueue la cho phep moi thao tac cho den khi thuc hien xong
		BlockingQueue<Runnable> blockingQueue=new ArrayBlockingQueue<Runnable>(10); //so luong hang doi max la 10000
		
		//Sẽ tạo trong bộ nhớ Pool tối đa 2 luồng(corePoolSize)
		//Khi số lượng vượt qua sẽ vào hàng đợi. số lượng hàng đợi full là 10000(blockingQueue)
		//Lúc này sẽ tạo thêm luồng mới. Số luồng tạo tối đa là 10(maxPoolSize)
		//Khi yêu cầu vượt quá 10 thread. Request bị từ chối
		threadPoolExecutor=new ThreadPoolExecutor(2, 10, 20, TimeUnit.SECONDS, blockingQueue);
		
		//xử lý luồng lỗi
		threadPoolExecutor.setRejectedExecutionHandler((r,executor)->{
			try {
				Thread.sleep(1000);
				 logger.error("Exception occurred while adding task, Waiting for some time");	
			} catch (Exception e) {
				logger.error("Thread interrupted:  ()",e.getCause());
                Thread.currentThread().interrupt();
			}
			threadPoolExecutor.execute(r);
		});
	}
	
	public void excuteTask(com.example.lessonEnglish.theard.TaskThread taskThread) {
		Future<?> future=threadPoolExecutor.submit(taskThread);
		System.out.println("Kích thước hàng đợi: "+threadPoolExecutor.getQueue().size());
        System.out.println("Số luồng hoạt động: "+threadPoolExecutor.getActiveCount());
	}
}
