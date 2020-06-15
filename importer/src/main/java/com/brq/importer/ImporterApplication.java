package com.brq.importer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brq.importer.batch.ScheduleTask;

@SpringBootApplication
public class ImporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImporterApplication.class, args);
		ScheduleTask schedule = new ScheduleTask();
		schedule.scheduleImportRegisterUserTask();
	}

}
