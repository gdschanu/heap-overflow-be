package hanu.gdsc.domains.exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestCases {
    private List<TestCase> data;

    public void sortTestCasesByOrdinal() {
        data.sort(Comparator.comparingInt(x -> x.getOrdinal()));
    }

    List<TestCase> getData() {
        return data;
    }

    public List<String> extractOrderedInputList() {
        sortTestCasesByOrdinal();
        List<String> inputs = new ArrayList<>();
        for (TestCase tc : data) {
            inputs.add(tc.getInput());
        }
        return inputs;
    }
}
