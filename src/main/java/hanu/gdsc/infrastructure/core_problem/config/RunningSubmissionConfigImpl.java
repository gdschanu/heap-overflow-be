package hanu.gdsc.infrastructure.core_problem.config;

import hanu.gdsc.domain.core_problem.config.RunningSubmissionConfig;
import org.springframework.core.env.Environment;

public class RunningSubmissionConfigImpl implements RunningSubmissionConfig {

    private static int MAX_THREAD = 2;
    private static int SCAN_RATE_MILLIS = 5000;
    private static int SCAN_LOCK_SECOND = 60 * 5;

    private static String VM_URL = "http://103.183.113.65:2358";
    private static String VM_TOKEN = "poopoopeepee";
    private static String VM_USER = "yowtf";

    public RunningSubmissionConfigImpl(Environment environment) {
        if (environment.getProperty("coreproblem.runningsubmission.maxthread") != null) {
            MAX_THREAD = Integer.parseInt(environment.getProperty("coreproblem.runningsubmission.maxthread"));
        }
        if (environment.getProperty("coreproblem.runningsubmission.scanratemillis") != null) {
            SCAN_RATE_MILLIS = Integer.parseInt(environment.getProperty("coreproblem.runningsubmission.scanratemillis"));
        }
        if (environment.getProperty("coreproblem.runningsubmission.scanlocksecond") != null) {
            SCAN_LOCK_SECOND = Integer.parseInt(environment.getProperty("coreproblem.runningsubmission.scanlocksecond"));
        }
        if (environment.getProperty("coreproblem.runningsubmission.vmurl") != null) {
            VM_URL = environment.getProperty("coreproblem.runningsubmission.vmurl");
        }
        if (environment.getProperty("coreproblem.runningsubmission.vmtoken") != null) {
            VM_TOKEN = environment.getProperty("coreproblem.runningsubmission.vmtoken");
        }
        if (environment.getProperty("coreproblem.runningsubmission.vmuser") != null) {
            VM_USER = environment.getProperty("coreproblem.runningsubmission.vmuser");
        }
    }

    @Override
    public int getMaxProcessingThread() {
        return MAX_THREAD;
    }

    @Override
    public int getScanRateMillis() {
        return SCAN_RATE_MILLIS;
    }

    @Override
    public int getScanLockSecond() {
        return SCAN_LOCK_SECOND;
    }

    @Override
    public String getVirtualMachineUrl() {
        return VM_URL;
    }

    @Override
    public String getVirtualMachineToken() {
        return VM_TOKEN;
    }

    @Override
    public String getVirtualMachineUser() {
        return VM_USER;
    }
}
