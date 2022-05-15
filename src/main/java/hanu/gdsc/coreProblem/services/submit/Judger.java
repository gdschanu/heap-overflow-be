package hanu.gdsc.coreProblem.services.submit;

import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;

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

    public static class Submission {
        private String stdout;
        private String time;
        private String memory;
        private String stderr;
        private String compileOutput;
        private int status;

        public Submission(String stdout, String time, String memory, String stderr, String compileOutput, int status) {
            this.stdout = stdout;
            this.time = time;
            this.memory = memory;
            this.stderr = stderr;
            this.compileOutput = compileOutput;
            this.status = status;
        }

        public boolean processing() {
            return status == 2;
        }

        public boolean inQueue() {
            return status == 1;
        }

        public boolean compilationError() {
            return compileOutput != null;
        }

        public String compilationMessage() {
            return compileOutput == null ?
                    "" : compileOutput;
        }

        public boolean stdError() {
            return compileOutput != null;
        }

        public String stdMessage() {
            return compileOutput == null ?
                    "" : compileOutput;
        }

        public KB memory() {
            return new KB(Double.parseDouble(memory));
        }

        public Millisecond runTime() {
            return new Millisecond(Long.parseLong(time));
        }

        public Output output() {
            return new Output(stdout);
        }
    }

    public String createSubmission(String code, String input, ProgrammingLanguage programmingLanguage) throws IOException, InterruptedException;

    public Submission getSubmissionById(String submissionId) throws IOException, InterruptedException;
}
