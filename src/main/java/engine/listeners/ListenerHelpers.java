package engine.listeners;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;

import java.util.Set;

public class ListenerHelpers {

    public static String[] getTestMethodNames(ITestNGMethod[] context) {
        String[] methodNames = new String[context.length];
        for (int i = 0; i < context.length; i++) {
            methodNames[i] = context[i].getMethodName();
        }
        return methodNames;
    }

    public static String[] getAllTestMethodNames(ITestContext context) {
        ITestNGMethod[] li = context.getAllTestMethods();
        String[] methodNames = new String[li.length];
        for (int i = 0; i < li.length; i++) {
            methodNames[i] = li[i].getMethodName();
        }
        return methodNames;
    }

    public static String[] getFailedTestMethodNames(ITestContext context) {
        Set<ITestNGMethod> li = (Set<ITestNGMethod>) context.getFailedTests().getAllMethods();
        String[] methodNames = new String[li.size()];
        int i = 0;
        for (ITestNGMethod method : li) {
            methodNames[i++] = method.getMethodName();
        }
        return methodNames;
    }

    public static String[] getSkippedTestMethodNames(ITestContext context) {
        Set<ITestNGMethod> li = (Set<ITestNGMethod>) context.getSkippedTests().getAllMethods();
        String[] methodNames = new String[li.size()];
        int i = 0;
        for (ITestNGMethod method : li) {
            methodNames[i++] = method.getMethodName();
        }
        return methodNames;
    }


    public static String[] getTestMethodNames(Set<ITestNGMethod> context) {
        String[] methodNames = new String[context.size()];
        int i = 0;
        for (ITestNGMethod method : context) {
            methodNames[i++] = method.getMethodName();
        }
        return methodNames;
    }
}
