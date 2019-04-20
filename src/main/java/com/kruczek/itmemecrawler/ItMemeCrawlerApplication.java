package com.kruczek.itmemecrawler;

import com.kruczek.itmemecrawler.service.CrawlerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ItMemeCrawlerApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(ItMemeCrawlerApplication.class, args);
        CrawlerService service = context.getBean(CrawlerService.class, "crawlerService");
        System.out.println("Sending test message");
        service.sendTestMessage();
        System.out.println("Exiting app");
    }
}
