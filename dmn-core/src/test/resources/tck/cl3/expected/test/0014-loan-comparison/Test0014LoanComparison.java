
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"junit.ftl", "0014-loan-comparison.dmn"})
public class Test0014LoanComparison extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    @org.junit.Test
    public void testCase001() {
        com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = new com.gs.dmn.runtime.annotation.AnnotationSet();
        com.gs.dmn.runtime.listener.EventListener eventListener_ = new com.gs.dmn.runtime.listener.NopEventListener();
        com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = new com.gs.dmn.runtime.external.DefaultExternalFunctionExecutor();
        // Initialize input data
        java.math.BigDecimal requestedAmt = number("330000");

        // Check Bankrates
        checkValues(asList(new type.TLoanProductImpl(number("0"), "Oceans Capital", number("0"), number(".03500")), new type.TLoanProductImpl(number("2700"), "eClick Lending", number("1.1"), number(".03200")), new type.TLoanProductImpl(number("1200"), "eClickLending", number("0.1"), number(".03375")), new type.TLoanProductImpl(number("3966"), "AimLoan", number("1.1"), number(".03000")), new type.TLoanProductImpl(number("285"), "Home Loans Today", number("1.1"), number(".03125")), new type.TLoanProductImpl(number("4028"), "Sebonic", number("0.1"), number(".03125")), new type.TLoanProductImpl(number("4317"), "AimLoan", number("0.1"), number(".03125")), new type.TLoanProductImpl(number("2518"), "eRates Mortgage", number("1.1"), number(".03125")), new type.TLoanProductImpl(number("822"), "Home Loans Today", number("0.1"), number(".03250")), new type.TLoanProductImpl(number("1995"), "AimLoan", number("0"), number(".03250"))), new Bankrates().apply(annotationSet_, eventListener_, externalExecutor_));
        // Check RankedProducts
        checkValues(new type.TRankedProductsImpl(asList(new type.TMetricImpl(number("66000"), number("0.1229130806675829"), number("0"), "Oceans Capital", number("330000"), number("1481.847469769075"), number("0"), number(".03500")), new type.TMetricImpl(number("67266"), number("0.1137029731874643"), number("2700"), "eClick Lending", number("336330"), number("1454.51580776472"), number("1.1"), number(".03200")), new type.TMetricImpl(number("66306"), number("0.1219807467513794"), number("1200"), "eClickLending", number("331530"), number("1465.681565899816"), number("0.1"), number(".03375")), new type.TMetricImpl(number("67519.2"), number("0.1154298007315222"), number("3966"), "AimLoan", number("337596"), number("1423.318353709273"), number("1.1"), number(".03000")), new type.TMetricImpl(number("66783"), number("0.1219478687825963"), number("285"), "Home Loans Today", number("333915"), number("1430.409890005697"), number("1.1"), number(".03125")), new type.TMetricImpl(number("66871.6"), number("0.1207829702481527"), number("4028"), "Sebonic", number("334358"), number("1432.307593257341"), number("0.1"), number(".03125")), new type.TMetricImpl(number("66929.4"), number("0.1200230251545745"), number("4317"), "AimLoan", number("334647"), number("1433.545598313153"), number("0.1"), number(".03125")), new type.TMetricImpl(number("67229.6"), number("0.1160760438900096"), number("2518"), "eRates Mortgage", number("336148"), number("1439.975513845245"), number("1.1"), number(".03125")), new type.TMetricImpl(number("66230.4"), number("0.1261025270361139"), number("822"), "Home Loans Today", number("331152"), number("1441.194429734607"), number("0.1"), number(".03250")), new type.TMetricImpl(number("66399"), number("0.1238778822515175"), number("1995"), "AimLoan", number("331995"), number("1444.863219004387"), number("0"), number(".03250"))), asList(new type.TMetricImpl(number("66000"), number("0.1229130806675829"), number("0"), "Oceans Capital", number("330000"), number("1481.847469769075"), number("0"), number(".03500")), new type.TMetricImpl(number("66230.4"), number("0.1261025270361139"), number("822"), "Home Loans Today", number("331152"), number("1441.194429734607"), number("0.1"), number(".03250")), new type.TMetricImpl(number("66306"), number("0.1219807467513794"), number("1200"), "eClickLending", number("331530"), number("1465.681565899816"), number("0.1"), number(".03375")), new type.TMetricImpl(number("66399"), number("0.1238778822515175"), number("1995"), "AimLoan", number("331995"), number("1444.863219004387"), number("0"), number(".03250")), new type.TMetricImpl(number("66783"), number("0.1219478687825963"), number("285"), "Home Loans Today", number("333915"), number("1430.409890005697"), number("1.1"), number(".03125")), new type.TMetricImpl(number("66871.6"), number("0.1207829702481527"), number("4028"), "Sebonic", number("334358"), number("1432.307593257341"), number("0.1"), number(".03125")), new type.TMetricImpl(number("66929.4"), number("0.1200230251545745"), number("4317"), "AimLoan", number("334647"), number("1433.545598313153"), number("0.1"), number(".03125")), new type.TMetricImpl(number("67229.6"), number("0.1160760438900096"), number("2518"), "eRates Mortgage", number("336148"), number("1439.975513845245"), number("1.1"), number(".03125")), new type.TMetricImpl(number("67266"), number("0.1137029731874643"), number("2700"), "eClick Lending", number("336330"), number("1454.51580776472"), number("1.1"), number(".03200")), new type.TMetricImpl(number("67519.2"), number("0.1154298007315222"), number("3966"), "AimLoan", number("337596"), number("1423.318353709273"), number("1.1"), number(".03000"))), asList(new type.TMetricImpl(number("66230.4"), number("0.1261025270361139"), number("822"), "Home Loans Today", number("331152"), number("1441.194429734607"), number("0.1"), number(".03250")), new type.TMetricImpl(number("66399"), number("0.1238778822515175"), number("1995"), "AimLoan", number("331995"), number("1444.863219004387"), number("0"), number(".03250")), new type.TMetricImpl(number("66000"), number("0.1229130806675829"), number("0"), "Oceans Capital", number("330000"), number("1481.847469769075"), number("0"), number(".03500")), new type.TMetricImpl(number("66306"), number("0.1219807467513794"), number("1200"), "eClickLending", number("331530"), number("1465.681565899816"), number("0.1"), number(".03375")), new type.TMetricImpl(number("66783"), number("0.1219478687825963"), number("285"), "Home Loans Today", number("333915"), number("1430.409890005697"), number("1.1"), number(".03125")), new type.TMetricImpl(number("66871.6"), number("0.1207829702481527"), number("4028"), "Sebonic", number("334358"), number("1432.307593257341"), number("0.1"), number(".03125")), new type.TMetricImpl(number("66929.4"), number("0.1200230251545745"), number("4317"), "AimLoan", number("334647"), number("1433.545598313153"), number("0.1"), number(".03125")), new type.TMetricImpl(number("67229.6"), number("0.1160760438900096"), number("2518"), "eRates Mortgage", number("336148"), number("1439.975513845245"), number("1.1"), number(".03125")), new type.TMetricImpl(number("67519.2"), number("0.1154298007315222"), number("3966"), "AimLoan", number("337596"), number("1423.318353709273"), number("1.1"), number(".03000")), new type.TMetricImpl(number("67266"), number("0.1137029731874643"), number("2700"), "eClick Lending", number("336330"), number("1454.51580776472"), number("1.1"), number(".03200"))), asList(new type.TMetricImpl(number("67519.2"), number("0.1154298007315222"), number("3966"), "AimLoan", number("337596"), number("1423.318353709273"), number("1.1"), number(".03000")), new type.TMetricImpl(number("66783"), number("0.1219478687825963"), number("285"), "Home Loans Today", number("333915"), number("1430.409890005697"), number("1.1"), number(".03125")), new type.TMetricImpl(number("66871.6"), number("0.1207829702481527"), number("4028"), "Sebonic", number("334358"), number("1432.307593257341"), number("0.1"), number(".03125")), new type.TMetricImpl(number("66929.4"), number("0.1200230251545745"), number("4317"), "AimLoan", number("334647"), number("1433.545598313153"), number("0.1"), number(".03125")), new type.TMetricImpl(number("67229.6"), number("0.1160760438900096"), number("2518"), "eRates Mortgage", number("336148"), number("1439.975513845245"), number("1.1"), number(".03125")), new type.TMetricImpl(number("66230.4"), number("0.1261025270361139"), number("822"), "Home Loans Today", number("331152"), number("1441.194429734607"), number("0.1"), number(".03250")), new type.TMetricImpl(number("66399"), number("0.1238778822515175"), number("1995"), "AimLoan", number("331995"), number("1444.863219004387"), number("0"), number(".03250")), new type.TMetricImpl(number("67266"), number("0.1137029731874643"), number("2700"), "eClick Lending", number("336330"), number("1454.51580776472"), number("1.1"), number(".03200")), new type.TMetricImpl(number("66306"), number("0.1219807467513794"), number("1200"), "eClickLending", number("331530"), number("1465.681565899816"), number("0.1"), number(".03375")), new type.TMetricImpl(number("66000"), number("0.1229130806675829"), number("0"), "Oceans Capital", number("330000"), number("1481.847469769075"), number("0"), number(".03500"))), asList(new type.TMetricImpl(number("67519.2"), number("0.1154298007315222"), number("3966"), "AimLoan", number("337596"), number("1423.318353709273"), number("1.1"), number(".03000")), new type.TMetricImpl(number("66783"), number("0.1219478687825963"), number("285"), "Home Loans Today", number("333915"), number("1430.409890005697"), number("1.1"), number(".03125")), new type.TMetricImpl(number("66871.6"), number("0.1207829702481527"), number("4028"), "Sebonic", number("334358"), number("1432.307593257341"), number("0.1"), number(".03125")), new type.TMetricImpl(number("66929.4"), number("0.1200230251545745"), number("4317"), "AimLoan", number("334647"), number("1433.545598313153"), number("0.1"), number(".03125")), new type.TMetricImpl(number("67229.6"), number("0.1160760438900096"), number("2518"), "eRates Mortgage", number("336148"), number("1439.975513845245"), number("1.1"), number(".03125")), new type.TMetricImpl(number("67266"), number("0.1137029731874643"), number("2700"), "eClick Lending", number("336330"), number("1454.51580776472"), number("1.1"), number(".03200")), new type.TMetricImpl(number("66230.4"), number("0.1261025270361139"), number("822"), "Home Loans Today", number("331152"), number("1441.194429734607"), number("0.1"), number(".03250")), new type.TMetricImpl(number("66399"), number("0.1238778822515175"), number("1995"), "AimLoan", number("331995"), number("1444.863219004387"), number("0"), number(".03250")), new type.TMetricImpl(number("66306"), number("0.1219807467513794"), number("1200"), "eClickLending", number("331530"), number("1465.681565899816"), number("0.1"), number(".03375")), new type.TMetricImpl(number("66000"), number("0.1229130806675829"), number("0"), "Oceans Capital", number("330000"), number("1481.847469769075"), number("0"), number(".03500")))), new RankedProducts().apply(requestedAmt, annotationSet_, eventListener_, externalExecutor_));
    }

    private void checkValues(Object expected, Object actual) {
        com.gs.dmn.runtime.Assert.assertEquals(expected, actual);
    }
}
