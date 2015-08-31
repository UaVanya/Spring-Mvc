import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import web.spring.configuration.HibernateConfiguration;
import web.spring.configuration.WebAppConfig;
import web.spring.connector.PupilController;
import web.spring.entity.KnowledgeLevelEnum;
import web.spring.entity.Pupil;
import web.spring.service.PupilService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Checks the methods of this class
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfiguration.class, WebAppConfig.class })
@WebAppConfiguration
public class TestPupilController {

    @Autowired
    private WebAppConfig config;

    @Resource
    private PupilService pupilService;

    @Resource
    private PupilController controller;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Checks for correct operation of method @see package.web.spring.connector.PupilController#getAllPupils(Long id)
     */
    @Test
    public void testGetAllPupilsTest() throws IOException {
        Pupil pupil = new Pupil();
        pupil.setFirstname("Test");
        pupil.setSurname("GetAllPupil");
        pupil.setClassroom("1");
        pupil.setAddress("Test t/t");
        pupil.setKnowledgeLevel(KnowledgeLevelEnum.HI);
        pupilService.addPupil(pupil);

        assertEquals(mapper.writeValueAsString(pupilService.getAllPupils()), controller.getAllPupils());

        pupilService.deletePupil(pupil.getId());
    }

    /**
     * Checks for correct operation of method @see package.web.spring.connector.PupilController#getPupil(Long id)
     */
    @Test
    public void testGetPupilById() throws IOException {
        Pupil pupil = new Pupil();
        pupil.setFirstname("Test");
        pupil.setSurname("GetPupilById");
        pupil.setClassroom("1");
        pupil.setAddress("Test t/t");
        pupil.setKnowledgeLevel(KnowledgeLevelEnum.HI);
        pupilService.addPupil(pupil);

        assertEquals(mapper.writeValueAsString(pupilService.getPupil(pupil.getId())), controller.getPupilById(pupil.getId()));

        pupilService.deletePupil(pupil.getId());
    }

    /**
     * Checks for correct operation of method @see package.web.spring.connector.PupilController#getPupil(String knowledgeLevel)
     */
    @Test
    public void testGetPupil() throws IOException {
        List<Pupil> pupilList = pupilService.getPupil(String.valueOf(KnowledgeLevelEnum.HI));
        String getPupilsFromController = controller.getPupilByKnowledgeLevel(String.valueOf(KnowledgeLevelEnum.HI));
        if (pupilList.isEmpty()) {
            assertEquals(config.getNotExistPupilByKnowledgeLevel(), getPupilsFromController);
        } else {
            String getPupils = mapper.writeValueAsString(pupilService.getPupil(String.valueOf(KnowledgeLevelEnum.HI)));
            assertEquals(getPupils, getPupilsFromController);
        }
    }

    /**
     * Checks for correct operation of method @see package.web.spring.connector.PupilController#changePupil(Pupil pupil)
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

        String changeSurname = "ChangePupilController";
        pupil.setSurname(changeSurname);
        controller.changePupil(pupil);

        assertEquals(pupil.getSurname(), changeSurname);

        pupilService.deletePupil(pupil.getId());
    }

    /**
     * Checks for correct operation of method @see package.web.spring.connector.PupilController#deletePupil(Long id)
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

        controller.deletePupil(pupil.getId());

        assertNull(pupilService.getPupil(pupil.getId()));
    }

    /**
     * Checks for correct operation of method @see package.web.spring.connector.PupilController#addPupil(Pupil pupil)
     */
    @Test
    public void testAddPupil() throws IOException {
        Pupil pupil = new Pupil();
        pupil.setFirstname("Test");
        pupil.setSurname("AddPupil");
        pupil.setClassroom("5");
        pupil.setAddress("Test t/t");
        pupil.setKnowledgeLevel(KnowledgeLevelEnum.MIDDLE);
        controller.addPupil(pupil);

        assertEquals(mapper.writeValueAsString(pupilService.getPupil(pupil.getId())), controller.getPupilById(pupil.getId()));

        pupilService.deletePupil(pupil.getId());
    }
}
