package com.home.extrack.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class RegexService {
    private final RegexPool regexPool;

    public RegexService(RegexPool regexPool) {
        this.regexPool = regexPool;
    }

    /**
     * Looking for regex by input string

     * @param errorItem errorString
     */
    public Long regexSearch(String errorItem) {
        String error = errorItem.replaceAll(System.lineSeparator(), "\n");
        List<Long> regexId = new ArrayList<>();
        ConcurrentHashMap<Long, Pattern> patternMap = regexPool.getMap();
        Matcher matcher;
        for (Map.Entry<Long, Pattern> entry : patternMap.entrySet()) {
            matcher = entry.getValue().matcher(error);
            if (matcher.find()) {
                regexId.add(entry.getKey());
                break;
            }
            matcher.reset();
        }
        return regexId.size() >= 1 ? regexId.get(0) : null;
    }
}
