package web.spring.connector;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.spring.configuration.WebAppConfig;
import web.spring.entity.Pupil;
import web.spring.service.PupilService;

import java.io.IOException;
import java.util.List;

/**
 * Performs in methods Rest queries.
 */
@RestController
@RequestMapping(value = "/")
public class PupilController {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebAppConfig webAppConfig;

    @Autowired
    private PupilService pupilService;

    /**
     * Get all pupils list from database.
     *
     * @return string data of about pupils
     */
    @RequestMapping(value = { "/", "/pupilsList" }, method = RequestMethod.GET)
    public String getAllPupils() throws IOException {
        List<Pupil> pupils = pupilService.getAllPupils();
        if (!pupils.isEmpty()) {
            return mapper.writeValueAsString(pupils);
        }
        return webAppConfig.getListPupilsEmpty();
    }

    /**
     * Get pupil by id.
     *
     * @param id
     * @return string data of pupil
     */
    @RequestMapping(value = "/pupil", method = RequestMethod.GET)
    public String getPupilById(@RequestParam Long id) throws IOException {
        Pupil pupil = pupilService.getPupil(id);
        if (pupil != null){
            return mapper.writeValueAsString(pupil);
        }
        return webAppConfig.getNotExistPupilById();
    }

    /**
     * Get pupil by knowledge level.
     *
     * @param knowledgeLevel
     * @return string data of pupil
     */
    @RequestMapping(value = "/pupils", method = RequestMethod.GET)
    public String getPupilByKnowledgeLevel (@RequestParam String knowledgeLevel) throws IOException {
        List<Pupil> pupils = pupilService.getPupil(knowledgeLevel);
        if (!pupils.isEmpty()) {
            return mapper.writeValueAsString(pupils);
        }
        return webAppConfig.getNotExistPupilByKnowledgeLevel();
    }

    /**
     * Change an pupil.
     *
     * @param pupil
     */
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public void changePupil(@ModelAttribute Pupil pupil) {
        pupilService.changePupil(pupil);
    }

    /**
     * Delete an pupil
     *
     * @param id
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deletePupil(@RequestParam(value="id") Long id){
        pupilService.deletePupil(id);
    }

    /**
     * Save pupil in DB.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void addPupil(@ModelAttribute Pupil pupil) {
        pupilService.addPupil(pupil);
    }
}
