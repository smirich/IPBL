package windmillcomplete;

enum Status {
    BELOWWINDTHRESHOLD, ABOVEWINDTHRESHOLD, SWEETSPOT, SHUTDOWN
}

public class PerformanceData {
    private final double performanceIndex;
    private final Status status;
    private final double power;
    private final WindGenerator windGenerator;
    
	public PerformanceData(double performanceIndex, Status status, double power, WindGenerator windGenerator) {
		super();
		this.performanceIndex = performanceIndex;
		this.status = status;
		this.power = power;
		this.windGenerator = windGenerator;
	}

	public double getPerformanceIndex() {
		return performanceIndex;
	}

	public Status getStatus() {
		return status;
	}

	public double getPower() {
		return power;
	}

	public WindGenerator getWindGenerator() {
		return windGenerator;
	}

	@Override
	public String toString() {
		return "PerformanceData [performanceIndex=" + performanceIndex + ", status=" + status + ", power=" + power
				+ ", windGenerator=" + windGenerator + "]";
	}
    
}
