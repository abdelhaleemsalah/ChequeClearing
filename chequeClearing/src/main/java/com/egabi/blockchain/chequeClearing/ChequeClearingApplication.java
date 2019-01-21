package com.egabi.blockchain.chequeClearing;

import com.egabi.blockchain.chequeClearing.notification.WebSocketNotificationClient;
//import com.egabi.blockchain.chequeClearing.notification.WebSocketNotificationClient;
import com.egabi.blockchain.chequeClearing.utils.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@ComponentScan(basePackages = "com.egabi.blockchain")
@EnableConfigurationProperties(StorageProperties.class)
@EnableScheduling
public class ChequeClearingApplication {


    @Autowired
    WebSocketNotificationClient webSocketNotificationClient;


    public static void main(String[] args) {
        SpringApplication.run(ChequeClearingApplication.class, args);
    
    }
    @Scheduled(fixedRate = 10)
    public void reminder() {

        webSocketNotificationClient.sendNotificationToUser("hello adry");

    

}
}