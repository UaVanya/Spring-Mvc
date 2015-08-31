import org.junit.Test;
import web.spring.entity.KnowledgeLevelEnum;

import static org.junit.Assert.*;

/**
 * Checks the getter values of this enum
 */
public class TestKnowledgeLevelEnum {

    /**
     * Checks value 'LOW' of Enum as String @see package.web.spring.entity.KnowledgeLevelEnum.class
     */
//    @Test
//    public void testLow() {
//        assertEquals(String.valueOf(KnowledgeLevelEnum.LOW), "LOW");
//    }

    /**
     * Checks value 'MIDDLE' of Enum as String @see package.web.spring.entity.KnowledgeLevelEnum.class
     */
//    @Test
//    public void testMiddle() {
//        assertEquals(String.valueOf(KnowledgeLevelEnum.MIDDLE), "MIDDLE");
//    }

    /**
     * Checks value 'HI' of Enum as String @see package.web.spring.entity.KnowledgeLevelEnum.class
     */
//    @Test
//    public void testHi() {
//        assertEquals(String.valueOf(KnowledgeLevelEnum.HI), "HI");
//    }

    /**
     * Checks the set value in Enum as String @see package.web.spring.entity.KnowledgeLevelEnum.class
     */
    @Test
    public void testGetValue() {
        KnowledgeLevelEnum knowledgeLevelEnum = KnowledgeLevelEnum.HI;
        assertEquals("HI", knowledgeLevelEnum.getValue());
    }

    /**
     * Compares String in Enum @see package.web.spring.entity.KnowledgeLevelEnum.class
     */
    @Test
    public void testParse() {
        KnowledgeLevelEnum knowledgeLevelEnum = KnowledgeLevelEnum.parse("HI");
        assertEquals(knowledgeLevelEnum, KnowledgeLevelEnum.HI);
    }
}