package ru.geekbrains.se.base;

import java.util.HashMap;
import java.util.HashSet;

import static ru.geekbrains.se.util.Constants.INFO_NOT_FOUNDED;
import static ru.geekbrains.se.util.Constants.SEARCH_BY_SURNAME;

/**
 * Work with phones
 */
public final class PhoneBase {

    private HashMap<String, HashSet<String>> phoneBase = new HashMap<>();

    /**
     * Add a phone to a surname
     *
     * @param surname surname for adding
     * @param phone   phone number for adding
     */
    public void add(String surname, String phone) {
        if (phoneBase.containsKey(surname)) {
            phoneBase.get(surname).add(phone);
        } else {
            HashSet<String> phones = new HashSet<>();
            phones.add(phone);
            phoneBase.put(surname, phones);
        }
    }

    /**
     * Find a phones by surname
     *
     * @param surname surname, that we wan`t to find in the phone book
     * @return string, that we found by the surname (or not found)
     */
    public String findPhonesBySurname(String surname) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getFormattedSearchString(surname));
        if (phoneBase.containsKey(surname)) {
            stringBuilder.append(phoneBase.get(surname).toString());
        } else {
            stringBuilder.append(INFO_NOT_FOUNDED);
        }
        return stringBuilder.toString();
    }

    public String getFormattedSearchString(String surname) {
        return String.format(SEARCH_BY_SURNAME + " \"%s\": ", surname);
    }
}
