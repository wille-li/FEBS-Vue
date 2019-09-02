package cc.mrbird.febs.common.config;

import com.github.qcloudsms.SmsSingleSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfig {

    @Value("${sms.app.id}")
    Integer appId;

    @Value("${sms.app.key}")
    String appKey;

    @Bean
    SmsSingleSender smsSingleSenderFactory(){
        return new SmsSingleSender(appId, appKey);
    }
}
