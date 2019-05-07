package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@EnableScheduling
public class QuartzDemo {
	@Autowired
	private LabMapper labMapper;

	@Scheduled(cron = "0 0/10 * * * ?") // 每10分钟执行一次
	public void work() {

		Lab lab = labMapper.getlastone();
		if (Integer.parseInt(lab.getTp()) > 35) {
			RestTemplate restTemplate = new RestTemplate();
			String url1 = "http://sh2.cshxsp.com/sms.aspx?action=send&account=hxsc022&password=123456a&mobile=13916703715&content=机房报警，请检查建滔机房";
			String url2 = "http://sh2.cshxsp.com/sms.aspx?action=send&account=hxsc022&password=123456a&mobile=13524171275&content=机房报警，请检查建滔机房";
			System.out.println(restTemplate.getForEntity(url1, String.class).getBody());
			System.out.println(restTemplate.getForEntity(url2, String.class).getBody());
		}
	}

	// @Scheduled(fixedRate = 2000) // 每2秒执行一次
	// public void play() throws Exception {
	// System.out.println("每2秒执行一个的定时任务：" + new Date());
	// }

	// @Scheduled(cron = "0/2 * * * * ?") // 每2秒执行一次
	// public void doSomething() throws Exception {
	// System.out.println("每2秒执行一个的定时任务：" + new Date());
	// }
	//
	// @Scheduled(cron = "0 0 0/1 * * ? ") // 每一小时执行一次
	// public void goWork() throws Exception {
	// System.out.println("每一小时执行一次的定时任务：" + new Date());
	// }

}