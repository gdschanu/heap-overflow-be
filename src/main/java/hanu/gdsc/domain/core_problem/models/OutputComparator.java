package hanu.gdsc.domain.core_problem.models;

import lombok.Builder;

import java.util.Scanner;

public class OutputComparator {
    @Builder
    public static class CompareResult {
        public final boolean equal;
        public final int differentLine;
    }

    public static CompareResult compare(String expectedOutputString,
                                        String actualOutputString) {
        try {
            check(expectedOutputString, actualOutputString);
            return CompareResult.builder()
                    .equal(true)
                    .differentLine(-1)
                    .build();
        } catch (CheckError e) {
            return CompareResult.builder()
                    .equal(false)
                    .differentLine(e.line)
                    .build();
        }
    }

    private static class CheckError extends Error {
        int line;

        public CheckError(int line) {
            this.line = line;
        }
    }

    private static void check(String expectedOutputString, String actualOutputString) {
        Scanner actualOutput = new Scanner(actualOutputString);
        Scanner correctOutput = new Scanner(expectedOutputString);
        int line = 1;
        while (correctOutput.hasNextLine()) {
            if (!actualOutput.hasNextLine()) {
                throw new CheckError(line);
            }
            String correctLine = correctOutput.nextLine();
            String actualLine = actualOutput.nextLine();
            if (!trim(correctLine).equals(trim(actualLine))) {
                throw new CheckError(line);
            }
            line++;
        }
        while (actualOutput.hasNextLine()) {
            String actualLine = actualOutput.nextLine();
            if (!containsOnlyChars(actualLine, '\n', ' ')) {
                throw new CheckError(line);
            }
            line++;
        }
    }

    private static String trim(String line) {
        StringBuilder s = new StringBuilder();
        int i = line.length() - 1;
        while (i >= 0 && (line.charAt(i) == '\n' || line.charAt(i) == ' ')) {
            i--;
        }
        for (int j = 0; j <= i; j++)
            s.append(line.charAt(j));
        return s.toString();
    }

    private static boolean containsOnlyChars(String s, char... chars) {
        for (int i = 0; i < s.length(); i++) {
            boolean ok = false;
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == s.charAt(i)) {
                    ok = true;
                }
            }
            if (!ok) {
                return false;
            }
        }
        return true;
    }
}
