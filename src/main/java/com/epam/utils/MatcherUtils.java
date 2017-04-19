package com.epam.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherUtils {

    public static boolean isMatches(String uriPattern, String uri) {
        Pattern patternForMatch = Pattern.compile(uriPattern);
        Matcher matcher = patternForMatch.matcher(uri);
        return matcher.matches();
    }

}
