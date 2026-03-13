import java.util.ArrayList;
import java.util.HashMap;

public class SWSystemData {
    public static HashMap<String, ArrayList<SWSystem>> getAllSystems() {
        HashMap<String, ArrayList<SWSystem>> map = new HashMap<>();

        ArrayList<SWSystem> webList = new ArrayList<>();
        webList.add(createECommercePlatform());
        map.put("Web", webList);

        ArrayList<SWSystem> mobileList = new ArrayList<>();
        mobileList.add(createHealthApp());
        map.put("Mobile", mobileList);

        return map;
    }

    private static SWSystem createECommercePlatform() {
        SWSystem s = new SWSystem("ShopSphere", "Web", "3.2.1");

        QualityDimension funcSuit = new QualityDimension("Functional Suitability", "QC.FS", 25);
        funcSuit.addCriterion(new Criterion("Functional Completeness Ratio", 50, "Higher", 0, 100, "%"));
        funcSuit.addCriterion(new Criterion("Functional Correctness Ratio", 50, "Higher", 0, 100, "%"));
        s.addDimension(funcSuit);

        QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 25);
        reliability.addCriterion(new Criterion("Availability Ratio", 50, "Higher", 95, 100, "%"));
        reliability.addCriterion(new Criterion("Defect Density", 50, "Lower", 0, 20, "def/KLOC"));
        s.addDimension(reliability);

        QualityDimension perfEff = new QualityDimension("Performance Efficiency", "QC.PE", 25);
        perfEff.addCriterion(new Criterion("Response Time", 50, "Lower", 100, 1000, "ms"));
        perfEff.addCriterion(new Criterion("CPU Utilisation Ratio", 50, "Lower", 0, 100, "%"));
        s.addDimension(perfEff);

        QualityDimension maintainability = new QualityDimension("Maintainability", "QC.MA", 25);
        maintainability.addCriterion(new Criterion("Test Coverage Ratio", 50, "Higher", 0, 100, "%"));
        maintainability.addCriterion(new Criterion("Cyclomatic Complexity (avg)", 50, "Lower", 1, 20, "score"));
        s.addDimension(maintainability);

        return s;
    }

    private static SWSystem createHealthApp() {
        SWSystem s = new SWSystem("HealthTrack", "Mobile", "1.0.0");

        QualityDimension usability = new QualityDimension("Usability", "QC.US", 100);
        usability.addCriterion(new Criterion("Task Completion Rate", 50, "Higher", 0, 100, "%"));
        usability.addCriterion(new Criterion("User Error Rate", 50, "Lower", 0, 100, "%"));
        s.addDimension(usability);

        return s;
    }
}