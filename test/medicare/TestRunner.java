package medicare;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("TEST RESULTS\n");
        System.out.println("Individual Test Results:");

        JUnitCore core = new JUnitCore();
        
        core.addListener(new RunListener() {
            private boolean failed = false;

            @Override
            public void testStarted(Description description) {
                failed = false;
            }

            @Override
            public void testFailure(Failure failure) {
                failed = true;
                System.out.println(failure.getDescription().getMethodName() + ": FAILED");
            }

            @Override
            public void testFinished(Description description) {
                if (!failed) {
                    System.out.println(description.getMethodName() + ": PASSED");
                }
            }
        });

        long startTime = System.currentTimeMillis();
        Result result = core.run(HospitalManagementSystemTest.class);
        long endTime = System.currentTimeMillis();

        System.out.println("\nDetails:");
        System.out.println("  Total Tests: " + result.getRunCount());
        System.out.println("  Passed: " + (result.getRunCount() - result.getFailureCount()));
        System.out.println("  Failed: " + result.getFailureCount());
        System.out.println("  Time: " + (endTime - startTime) + "ms");

        // Force process exit with failure code if any test fails
        if (result.getFailureCount() > 0) {
            System.exit(1);
        }
    }
}