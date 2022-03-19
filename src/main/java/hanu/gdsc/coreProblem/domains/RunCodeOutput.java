package hanu.gdsc.coreProblem.domains;

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
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "RunCodeOutput{" +
                "output='" + output + '\'' +
                '}';
    }
}
