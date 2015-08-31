import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import web.spring.configuration.HibernateConfiguration;
import static org.junit.Assert.*;

import javax.sql.DataSource;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfiguration.class})
@WebAppConfiguration()
@PropertySource(value = { "classpath:dbconfig.properties" })
public class TestHibernateConfiguration {
    @Autowired
    private Environment environment;

    @Autowired
    private HibernateConfiguration hibernateConfiguration;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private HibernateTransactionManager transactionManager;

    private Properties property = new Properties();

    /**
     * Check creating a LocalSessionFactoryBean
     */
    @Test
    public void sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[]{"web.spring.entity"});
        sessionFactory.setHibernateProperties(hibernateConfiguration.hibernateProperties());
        assertNotNull(sessionFactory);
    }

    /**
     * Check creating a DataSource
     */
    @Test
    public void testDataSource() {
        assertNotNull(dataSource);
    }

    /**
     * Check the properties for configuration Hibernate
     */
    @Test
    public void testHibernateProperties() {
        property.setProperty("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        property.setProperty("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        property.setProperty("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        assertEquals(hibernateConfiguration.hibernateProperties(), property);
    }

    /**
     * Check creating a HibernateTransactionManager
     */
    @Test
    public void testTransactionManager() {
        assertNotNull(transactionManager);
    }

}
