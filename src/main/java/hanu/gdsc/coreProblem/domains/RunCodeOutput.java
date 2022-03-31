package hanu.gdsc.coreProblem.domains;

import java.util.Scanner;

public class RunCodeOutput {
    private String output;

    public RunCodeOutput(String output) {
        this.output = output;
    }

    @Override
    public boolean equals(Object obj) {
        /*
        Thực hiện so sánh equal với string tại đây
        vì một số trường hợp thừa cách / xuống dòng
        ở cuối vẫn là hợp lệ
        Ko đc thêm get set attribute output của class này
         */
        // TODO: implement this
        if (obj.getClass() != String.class) {
            throw new Error("RunCodeOutput.equal(...) argument must be String.");
        }
        Scanner actualOutput = new Scanner(output);
        Scanner correctOutput = new Scanner((String) obj);
        while (correctOutput.hasNextLine()) {
            if (!actualOutput.hasNextLine()) {
                return false;
            }
            String correctLine = correctOutput.nextLine();
            String actualLine = actualOutput.nextLine();
            if (!correctLine.trim().equals(actualLine.trim())) {
                return false;
            }
        }
        while (actualOutput.hasNextLine()) {
            String actualLine = actualOutput.nextLine();
            if (!containsOnlyChars(actualLine, '\n', ' ')) {
                return false;
            }
        }
        return true;
    }

    public int calculateFailedLine(Object obj) {
        if(obj.getClass() != String.class) {
            throw new Error("RunCodeOutput.calculateFailedLine(...) argument must be String.");
        }
        Scanner actualOutput = new Scanner(output);
        Scanner correctOutput = new Scanner((String) obj);
        for (int line=1; correctOutput.hasNextLine(); line++) {
            if(!actualOutput.hasNextLine()) {
                return 1;
            }
            String correctLine = correctOutput.nextLine();
            String actualLine = actualOutput.nextLine();
            if(!correctLine.trim().equals(actualLine.trim())) {
                return line;
            }
        }
        return 0;
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
        return "RunCodeOutput{" +
                "output='" + output + '\'' +
                '}';
    }
}
