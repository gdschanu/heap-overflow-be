package hanu.gdsc.domain.core_problem.config;

public interface RunningSubmissionConfig {
    public int getMaxProcessingThread();

    public int getScanRateMillis();

    public int getScanLockSecond();

    public String getVirtualMachineUrl();

    public String getVirtualMachineToken();

    public String getVirtualMachineUser();
}
