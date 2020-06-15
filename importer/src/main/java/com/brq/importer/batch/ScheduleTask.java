package com.brq.importer.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleTask {
	
	private final long INTERVAL = 30000;
	
	@Autowired
	private ProcessUsers processUsers;
	
	@Scheduled(fixedDelay = INTERVAL)
	public void scheduleImportRegisterUserTask() {
		processUsers.processUsers();
	}
}
