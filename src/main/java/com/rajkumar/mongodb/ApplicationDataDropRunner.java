/**
 * 
 */
package com.rajkumar.mongodb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Rajkumar
 *
 */
@Component
@Order(3)
public class ApplicationDataDropRunner implements CommandLineRunner {

	static final Logger logger = LogManager.getLogger(ApplicationDataDropRunner.class);
	
	private MongoTemplate mongoTemplate;
	
	public ApplicationDataDropRunner(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.getDb().drop();
		logger.info("DB is dropped successfully");
		
	}
}
