package hanu.gdsc.core_problem.services.runningSubmission;

import hanu.gdsc.core_problem.domains.KB;
import hanu.gdsc.core_problem.domains.Millisecond;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;

import java.io.IOException;
import java.util.Scanner;

public interface Judger {
    public static class Output {
        private final String actualOutputString;

        public Output(String actualOutputString) {
            this.actualOutputString = actualOutputString;
        }

        public int differentLine(String expectedOutput) {
            try {
                check((String) expectedOutput);
                return -1;
            } catch (CheckError e) {
                return e.line;
            }
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
                return false;
            }
        }

        private static class CheckError extends Error {
            int line;

            public CheckError(int line) {
                this.line = line;
            }
        }

        void check(String expectedOutputString) {
            Scanner actualOutput = new Scanner(this.actualOutputString);
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
            return actualOutputString;
        }
    }

    public static interface Submission {
        public boolean processing();
        public boolean inQueue();
        public boolean compilationError();
        public String compilationMessage();
        public boolean stdError();
        public String stdMessage();
        public KB memory();
        public Millisecond runTime();
        public Output output();
    }

    public Submission createSubmission(String code, String input, ProgrammingLanguage programmingLanguage) throws IOException, InterruptedException;
}
