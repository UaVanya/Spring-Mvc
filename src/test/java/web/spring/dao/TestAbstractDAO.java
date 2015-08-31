import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import web.spring.configuration.HibernateConfiguration;
import web.spring.configuration.WebAppConfig;
import web.spring.dao.AbstractDAO;

import javax.annotation.Resource;

/**
 * Check database connection
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfiguration.class})
@WebAppConfiguration()
public class TestAbstractDAO {
    @Resource
    AbstractDAO abstractDAO;

    /**
     * Check the resulting compound of the method AbstractDAO class
     */
    @Transactional
    @Test
    public void testGetSession() {
        Session sessionFactory = null;
        Assert.assertNotNull(abstractDAO.getSession());
    }
}
