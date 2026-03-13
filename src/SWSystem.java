import java.util.ArrayList;

public class SWSystem {
    private String name;
    private String category;
    private String version;
    private ArrayList<QualityDimension> dimensions;

    public SWSystem(String name, String category, String version) {
        this.name = name;
        this.category = category;
        this.version = version;
        this.dimensions = new ArrayList<>();
    }

    public void addDimension(QualityDimension qd) {
        dimensions.add(qd);
    }

    public double calculateOverallScore() {
        if (dimensions.isEmpty()) return 0.0;
        double totalScore = 0;
        double totalWeight = 0;
        for (QualityDimension qd : dimensions) {
            totalScore += qd.calculateDimensionScore() * qd.getWeight();
            totalWeight += qd.getWeight();
        }
        return Math.round((totalScore / totalWeight) * 10.0) / 10.0;
    }

    public String getOverallQualityLabel() {
        double score = calculateOverallScore();
        if (score >= 4.5) return "Excellent Quality";
        if (score >= 3.5) return "Good Quality";
        if (score >= 2.5) return "Needs Improvement";
        return "Poor Quality";
    }

    public QualityDimension findWeakestDimension() {
        if (dimensions.isEmpty()) return null;
        QualityDimension weakest = dimensions.get(0);
        for (QualityDimension qd : dimensions) {
            if (qd.calculateDimensionScore() < weakest.calculateDimensionScore()) {
                weakest = qd;
            }
        }
        return weakest;
    }

    public String getName() { return name; }
    public ArrayList<QualityDimension> getDimensions() { return dimensions; }

    public void printReport() {
        System.out.println("======================================================");
        System.out.println("SOFTWARE QUALITY EVALUATION REPORT (ISO/IEC 25010)");
        System.out.println("System: " + name + " v" + version + " (" + category + ")");
        System.out.println("======================================================");

        for (QualityDimension qd : dimensions) {
            System.out.println("--- " + qd.getName() + " [" + qd.getIsoCode() + "] (Weight: " + (int)qd.getWeight() + ") ---");
            for (Criterion c : qd.getCriteria()) {
                String unitStr = c.getUnit().equals("%") ? "%" : " " + c.getUnit();
                System.out.printf("%s: %.1f%s -> Score: %.1f (%s is better)\n",
                        c.getName(), c.getMeasuredValue(), unitStr, c.calculateScore(), c.getDirection());
            }
            System.out.printf(">> Dimension Score: %.1f/5 [%s]\n\n", qd.calculateDimensionScore(), qd.getQualityLabel());
        }

        System.out.println("======================================================");
        System.out.printf("OVERALL QUALITY SCORE: %.1f/5 [%s]\n", calculateOverallScore(), getOverallQualityLabel());
        System.out.println("======================================================");
        System.out.println("GAP ANALYSIS (ISO/IEC 25010)");

        QualityDimension weakest = findWeakestDimension();
        if (weakest != null) {
            System.out.println("Weakest Characteristic: " + weakest.getName() + " [" + weakest.getIsoCode() + "]");
            System.out.printf("Score: %.1f/5 | Gap: %.1f\n", weakest.calculateDimensionScore(), weakest.calculateGap());
            System.out.println("Level: " + weakest.getQualityLabel());
            System.out.println(">> This characteristic requires the most improvement.");
        }
    }
}