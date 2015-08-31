import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import web.spring.configuration.WebAppConfig;
import static org.junit.Assert.*;

/**
 * Check configuration class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebAppConfig.class })
@PropertySource(value = { "classpath:messages.properties" })
@WebAppConfiguration()
public class TestWebAppConfig {

    @Autowired
    private Environment environment;

    @Autowired
    private WebAppConfig config;

    @Autowired
    private PropertySourcesPlaceholderConfigurer configurer;

    /**
     * Check whether the value assigned to the variable listPupilsEmpty
     */
    @Test
    public void testGetListPupilsEmpty() {
        String getListPupilsEmpty = environment.getProperty("list.pupils.empty");
        assertEquals(config.getListPupilsEmpty(), getListPupilsEmpty);
    }

    /**
     * Check whether the value assigned to the variable notExistPupilById
     */
    @Test
    public void testGetNotExistPupilById() {
        String getNotExistPupilById = environment.getProperty("pupil.by.id");
        assertEquals(config.getNotExistPupilById(), getNotExistPupilById);
    }

    /**
     * Check whether the value assigned to the variable notExistPupilByKnowledgeLevel
     */
    @Test
    public void testGetNotExistPupilByKnowledgeLevel() {
        String getNotExistPupilByKnowledgeLevel = environment.getProperty("pupil.by.knowledgeLevel");
        assertEquals(config.getNotExistPupilByKnowledgeLevel(), getNotExistPupilByKnowledgeLevel);
    }

    /**
     * Check creating a PropertySourcesPlaceholderConfigurer
     */
    @Test
    public void testPropertySourcesPlaceholderConfigurer() {
        assertNotNull(configurer);
    }
}
