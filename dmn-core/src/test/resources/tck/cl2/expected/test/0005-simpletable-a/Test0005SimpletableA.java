
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"junit.ftl", "0005-simpletable-A.dmn"})
public class Test0005SimpletableA extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    @org.junit.Test
    public void testCase001() {
        com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = new com.gs.dmn.runtime.annotation.AnnotationSet();
        com.gs.dmn.runtime.listener.EventListener eventListener_ = new com.gs.dmn.runtime.listener.NopEventListener();
        com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = new com.gs.dmn.runtime.external.DefaultExternalFunctionExecutor();
        // Initialize input data
        java.math.BigDecimal age = number("18");
        String riskCategory = "Medium";
        Boolean isAffordable = true;

        // Check ApprovalStatus
        String approvalStatusOutput = new ApprovalStatus().apply(age, riskCategory, isAffordable, annotationSet_, eventListener_, externalExecutor_);
        checkValues("Approved", approvalStatusOutput);
    }

    @org.junit.Test
    public void testCase002() {
        com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = new com.gs.dmn.runtime.annotation.AnnotationSet();
        com.gs.dmn.runtime.listener.EventListener eventListener_ = new com.gs.dmn.runtime.listener.NopEventListener();
        com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = new com.gs.dmn.runtime.external.DefaultExternalFunctionExecutor();
        // Initialize input data
        java.math.BigDecimal age = number("17");
        String riskCategory = "Medium";
        Boolean isAffordable = true;

        // Check ApprovalStatus
        String approvalStatusOutput = new ApprovalStatus().apply(age, riskCategory, isAffordable, annotationSet_, eventListener_, externalExecutor_);
        checkValues("Declined", approvalStatusOutput);
    }

    @org.junit.Test
    public void testCase003() {
        com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = new com.gs.dmn.runtime.annotation.AnnotationSet();
        com.gs.dmn.runtime.listener.EventListener eventListener_ = new com.gs.dmn.runtime.listener.NopEventListener();
        com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = new com.gs.dmn.runtime.external.DefaultExternalFunctionExecutor();
        // Initialize input data
        java.math.BigDecimal age = number("18");
        String riskCategory = "High";
        Boolean isAffordable = true;

        // Check ApprovalStatus
        String approvalStatusOutput = new ApprovalStatus().apply(age, riskCategory, isAffordable, annotationSet_, eventListener_, externalExecutor_);
        checkValues("Declined", approvalStatusOutput);
    }

    private void checkValues(Object expected, Object actual) {
        com.gs.dmn.runtime.Assert.assertEquals(expected, actual);
    }
}