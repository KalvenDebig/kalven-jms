package kalven.springframework.kalvenjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Project kalven-jms
 * @Author kalvens on 2/19/23
 */
@Configuration
@EnableScheduling
@EnableAsync
public class TaskConfig {
    @Bean
    TaskExecutor executor() {
        return new SimpleAsyncTaskExecutor();
    }

}
