package com.minh.Lab10FileUpload.localization;

import com.minh.Lab10FileUpload.entity.LocalizeString;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

import java.util.HashMap;
import java.util.Locale;

public class DBMessageSource implements MessageSource {
    private HashMap<String, LocalizeString> localizeDB;

    public DBMessageSource(){
        localizeDB = new HashMap<>();
        localizeDB.put("home", new LocalizeString("Home", "Trang chủ"));
        localizeDB.put("list_people", new LocalizeString("List People", "Danh sách người dùng"));
        localizeDB.put("list_jobs", new LocalizeString("List Jobs", "Danh sách công việc"));
        localizeDB.put("add_person", new LocalizeString("Add Person", "Thêm người dùng"));

    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        LocalizeString localizeString = localizeDB.get(code);
        if (localizeString == null) {
            return defaultMessage;
        }

        switch (locale.getLanguage()){
            case "vn":
                return localizeString.getVn();
            case "en":
                return localizeString.getEn();
            default:
                return defaultMessage;
        }
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        LocalizeString localizeString = localizeDB.get(code);
        if (localizeString == null) {
            return code;
        }

        switch (locale.getLanguage()){
            case "vn":
                return localizeString.getVn();
            case "en":
                return localizeString.getEn();
            default:
                return code;
        }
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return resolvable.getDefaultMessage();
    }
}
