package hanu.gdsc.coreProblem;

import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.services.problem.RunCodeService;
import hanu.gdsc.coreProblem.services.problem.RunCodeServiceImpl;
import org.junit.jupiter.api.Test;

public class RunCodeServiceTest {
    @Test
    public void javaSuccess() {
        RunCodeService runCodeService = new RunCodeServiceImpl();
        RunCodeService.Output output = runCodeService.execute(
                "class Main {\n" +
                        "\n" +
                        "  public static void main(String[] args) {\n" +
                        "    \n" +
                        "    System.out.println(\"Enter two numbers\");\n" +
                        "    int first = 10;\n" +
                        "    int second = 20;\n" +
                        "    \n" +
                        "    System.out.println(first + \" \" + second);\n" +
                        "\n" +
                        "    // add two numbers\n" +
                        "    int sum = first + second;\n" +
                        "    System.out.println(\"The sum is: \" + sum);\n" +
                        "  }\n" +
                        "}",
                "",
                ProgrammingLanguage.JAVA
        );
        System.out.println(output);
    }
}
