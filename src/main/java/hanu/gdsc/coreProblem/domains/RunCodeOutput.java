package hanu.gdsc.coreProblem.domains;

import java.util.Scanner;

public class RunCodeOutput {
    private String output;
    private int failedAtLine;

    public RunCodeOutput(String output) {
        this.output = output;
        failedAtLine = 0;
    }

    public int getFailedAtLine() {
        return failedAtLine;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != String.class) {
            throw new Error("RunCodeOutput.equal(...) argument must be String.");
        }
        try {
            check((String) o);
            return true;
        } catch (CheckError e) {
            failedAtLine = e.line;
            return false;
        }
    }

    private static class CheckError extends Error {
        int line;

        public CheckError(int line) {
            this.line = line;
        }
    }

    void check(String correctOutputString) {
        Scanner actualOutput = new Scanner(output);
        Scanner correctOutput = new Scanner(correctOutputString);
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

    private String trim(String line) {
        StringBuilder s = new StringBuilder();
        int i = line.length() - 1;
        while (i >= 0 && (line.charAt(i) == '\n' || line.charAt(i) == ' ')) {
            i--;
        }
        for (int j = 0; j <= i; j++)
            s.append(line.charAt(j));
        return s.toString();
    }

    private boolean containsOnlyChars(String s, char... chars) {
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

    @Override
    public String toString() {
        return output;
    }
}
