package controller;

/**
 * regEx Container
 */
public interface RegExContainer {
    String REGEX_NAME_ENG = "^[a-zA-Z-]+$";
    String REGEX_LOGIN_ENG = "^[0-9a-zA-Z_]+$";

    String REGEX_NAME_UA = "^[[^ыЫэЭъЪ]&&А-Яа-яЇїІіЄєҐґ'-]+$";
    String REGEX_LOGIN_UA = "^[[^ыЫэЭъЪ]&&0-9А-Яа-яЇїІіЄєҐґ_']+$";

    String REGEX_NAME_RUS = "^[ЁёА-яА-Я-]+$";
    String REGEX_LOGIN_RUS = "^[0-9ЁёА-яА-Я_]+$";
}
