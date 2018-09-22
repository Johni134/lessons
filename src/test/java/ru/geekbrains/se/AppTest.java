package ru.geekbrains.se;

import org.junit.Before;
import org.junit.Test;
import ru.geekbrains.se.base.PhoneBase;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private final PhoneBase phoneBase = new PhoneBase();

    @Before
    public void setUpData() {
        phoneBase.add("TestUser", "123");
    }

    /**
     * Test for phone base
     */
    @Test
    public void testPhoneBase() {
        assertEquals(phoneBase.findPhonesBySurname("TestUser"), phoneBase.getFormattedSearchString("TestUser") + "[123]");
    }

}
