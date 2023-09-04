package org.lone64.mws.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static Iterable<MatchResult> allMatches(final Pattern p, final CharSequence input) {
        return () -> new Iterator<>() {
            final Matcher matcher = p.matcher(input);
            MatchResult pending;

            public boolean hasNext() {
                if (pending == null && matcher.find()) {
                    pending = matcher.toMatchResult();
                }
                return pending != null;
            }

            public MatchResult next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                MatchResult next = pending;
                pending = null;
                return next;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void copyDirectory(File org, File tar) {
        try {
            FileUtils.copyDirectory(org, tar);
        } catch (IOException ignored) {}
    }

    public static void deleteFile(File file) {
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException ignored) {}
    }

}
