package com.codechasers.license.core.evaluation;

import org.springframework.scheduling.annotation.Scheduled;

public class ProductKeyValidatorJob {

	@Scheduled(cron="0 0 0 1/1 * ?")
	public void execute() {
		String thread=Thread.currentThread().getName();
		Thread.currentThread().setName(thread+"_"+"productKeyValidation");
		ProductKeyEval.validateKey();
	}

}
