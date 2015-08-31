package web.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Configure Spring.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "web.spring")
@PropertySource(value = { "classpath:messages.properties" })
public class WebAppConfig {

    @Value("${list.pupils.empty}")
    private String listPupilsEmpty;

    @Value("${pupil.by.id}")
    private String notExistPupilById;

    @Value("${pupil.by.knowledgeLevel}")
    public String notExistPupilByKnowledgeLevel;

    /**
     * @return the that reports that the list of pupil is empty
     */
    public String getListPupilsEmpty() {
        return listPupilsEmpty;
    }

    /**
     * @return the that reports that the list of pupil is empty
     */
    public String getNotExistPupilById() {
        return notExistPupilById;
    }

    /**
     * @return the that reports that the list of pupil is empty
     */
    public String getNotExistPupilByKnowledgeLevel() {
        return notExistPupilByKnowledgeLevel;
    }

    /**
     * Define properties sources.
     *
     * @return configuration class
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}