import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, ArrayList<SWSystem>> allSystems = SWSystemData.getAllSystems();

        ArrayList<SWSystem> webSystems = allSystems.get("Web");

        SWSystem shopSphere = null;
        for (SWSystem system : webSystems) {
            if (system.getName().equals("ShopSphere")) {
                shopSphere = system;
                break;
            }
        }

        if (shopSphere != null) {
            for (QualityDimension qd : shopSphere.getDimensions()) {
                for (Criterion c : qd.getCriteria()) {
                    switch (c.getName()) {
                        case "Functional Completeness Ratio": c.setMeasuredValue(94.0); break;
                        case "Functional Correctness Ratio": c.setMeasuredValue(91.0); break;
                        case "Availability Ratio": c.setMeasuredValue(99.2); break;
                        case "Defect Density": c.setMeasuredValue(2.1); break;
                        case "Response Time": c.setMeasuredValue(220.0); break;
                        case "CPU Utilisation Ratio": c.setMeasuredValue(38.0); break;
                        case "Test Coverage Ratio": c.setMeasuredValue(72.0); break;
                        case "Cyclomatic Complexity (avg)": c.setMeasuredValue(8.5); break;
                    }
                }
            }
            shopSphere.printReport();
        }
    }
}