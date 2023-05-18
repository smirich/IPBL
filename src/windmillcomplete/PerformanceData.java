package windmillcomplete;

enum Status {
    BELOWWINDTHRESHOLD, ABOVEWINDTHRESHOLD, SWEETSPOT, SHUTDOWN
}

public class PerformanceData {
    private final double performanceIndex;
    private final Status status;
    private final WindGenerator windGenerator;

    public PerformanceData(double performanceIndex, Status status, WindGenerator windGenerator) {
        this.performanceIndex = performanceIndex;
        this.status = status;
        this.windGenerator = windGenerator;
    }

    public double getPerformanceIndex() {
        return performanceIndex;
    }

    public Status getStatus() {
        return status;
    }

    public WindGenerator getWindGenerator() {
        return windGenerator;
    }

    @Override
    public String toString() {
        return "PerformanceData [performanceIndex=" + performanceIndex + ", status=" + status + "]";
    }
}
