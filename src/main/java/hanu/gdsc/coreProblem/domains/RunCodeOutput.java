package hanu.gdsc.coreProblem.domains;

public class RunCodeOutput {
    private String output;
    
    public RunCodeOutput(String output) {
        this.output = output;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO: implement this
        return super.equals(obj);
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
