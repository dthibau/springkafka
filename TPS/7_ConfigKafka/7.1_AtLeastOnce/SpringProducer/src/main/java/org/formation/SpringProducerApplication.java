package org.formation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringProducerApplication implements CommandLineRunner {

	@Autowired
	private KafkaTemplate<Object, Object> template;
	
	static int nbThreads = 10;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Config is " + template.getProducerFactory().getConfigurationProperties());
		
		System.in.read();
		ExecutorService executorService = Executors.newFixedThreadPool(nbThreads);
		
		long top = System.currentTimeMillis();

		for (int i = 0; i < nbThreads; i++) {
			Runnable r = new KafkaProducerThread("" + i, template);
			Thread.sleep(100);
			executorService.execute(r);
		}

		executorService.shutdown();

		try {
			System.out.println(executorService.awaitTermination(2, TimeUnit.HOURS));
		} catch (InterruptedException e) {
			System.err.println("INTERRUPTED");
		}
		System.out.println("Produced " + nbThreads*10000 + " messages in "+ (System.currentTimeMillis()-top) + "ms");
		System.exit(0);
		
	}

}
