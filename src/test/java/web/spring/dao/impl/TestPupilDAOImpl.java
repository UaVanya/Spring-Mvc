import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import web.spring.configuration.HibernateConfiguration;
import web.spring.configuration.WebAppConfig;
import web.spring.dao.PupilDAO;
import web.spring.dao.impl.PupilDAOImpl;
import web.spring.entity.KnowledgeLevelEnum;
import web.spring.entity.Pupil;
import web.spring.service.PupilService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Checks the methods of this class
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfiguration.class, WebAppConfig.class })
@WebAppConfiguration
public class TestPupilDAOImpl {
    @Autowired
    private PupilService pupilService;

    /**
     * Checks for correct operation of method @see package.web.spring.doa.impl.PupilDAOImpl#getAllPupils(Long id)
     */
    @Test
    public void testGetAllPupilsTest() throws IOException {
        List<Pupil> pupilList = pupilService.getAllPupils();
        assertNotNull(pupilList);
    }

    /**
     * Checks for correct operation of method @see package.web.spring.doa.impl.PupilDAOImpl#getPupil(Long id)
     */
    @Test
    public void testGetPupilById() {
        Pupil pupil = new Pupil();
        pupil.setFirstname("Test");
        pupil.setSurname("GetPupilById");
        pupil.setClassroom("1");
        pupil.setAddress("Test t/t");
        pupil.setKnowledgeLevel(KnowledgeLevelEnum.HI);
        pupilService.addPupil(pupil);

        assertEquals(pupilService.getPupil(pupil.getId()).toString(), pupil.toString());

        pupilService.deletePupil(pupil.getId());
    }

    /**
     * Checks for correct operation of method @see package.web.spring.doa.impl.PupilDAOImpl#getPupil(String knowledgeLevel)
     */
    @Test
    public void testGetPupil() {
        List<Pupil> pupilList = pupilService.getPupil("HI");
        assertNotNull(pupilList);
    }

    /**
     * Checks for correct operation of method @see package.web.spring.doa.impl.PupilDAOImpl#changePupil(Pupil pupil)
     */
    @Test
    public void testChangePupil() {
        Pupil pupil = new Pupil();
        pupil.setFirstname("Test");
        pupil.setSurname("ChangePupil");
        pupil.setClassroom("4");
        pupil.setAddress("Test t/t");
        pupil.setKnowledgeLevel(KnowledgeLevelEnum.LOW);
        pupilService.addPupil(pupil);

        String changeSurname = "ChangePupilSuccessfully";
        pupil.setSurname(changeSurname);
        pupilService.changePupil(pupil);

        assertEquals(pupil.getSurname(), changeSurname);

        pupilService.deletePupil(pupil.getId());
    }

    /**
     * Checks for correct operation of method @see package.web.spring.doa.impl.PupilDAOImpl#deletePupil(Long id)
     */
    @Test
    public void testDeletePupil() {
        Pupil pupil = new Pupil();
        pupil.setFirstname("Test");
        pupil.setSurname("DeletePupil");
        pupil.setClassroom("4");
        pupil.setAddress("Test t/t");
        pupil.setKnowledgeLevel(KnowledgeLevelEnum.LOW);
        pupilService.addPupil(pupil);

        pupilService.deletePupil(pupil.getId());

        assertNull(pupilService.getPupil(pupil.getId()));
    }

    /**
     * Checks for correct operation of method @see package.web.spring.doa.impl.PupilDAOImpl#addPupil(Pupil pupil)
     */
    @Test
    public void testAddPupil() {
        Pupil pupil = new Pupil();
        pupil.setFirstname("Test");
        pupil.setSurname("AddPupil");
        pupil.setClassroom("5");
        pupil.setAddress("Test t/t");
        pupil.setKnowledgeLevel(KnowledgeLevelEnum.MIDDLE);
        pupilService.addPupil(pupil);

        assertEquals(pupilService.getPupil(pupil.getId()).getId().toString(), pupil.getId().toString());

        pupilService.deletePupil(pupil.getId());
    }
}
