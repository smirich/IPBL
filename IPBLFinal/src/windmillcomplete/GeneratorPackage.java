package windmillcomplete;

enum GeneratorModel {
    SPARKY1000, SHOCKINGTON4, THUNDERBOLT7
}

public class GeneratorPackage {
    private final GeneratorModel modelNumber;
    private final String manufactureDate;
    private double cutIn;
    private double shutdownSpeed;
    private double pMax;  
    

    public GeneratorPackage(GeneratorModel modelNumber, String manufactureDate, double cutIn, double shutdownSpeed,
			double pMax) {
		super();
		this.modelNumber = modelNumber;
		this.manufactureDate = manufactureDate;
		this.cutIn = cutIn;
		this.shutdownSpeed = shutdownSpeed;
		this.pMax = pMax;
	}

	public GeneratorPackage(GeneratorModel modelNumber, String manufactureDate) {
        this.modelNumber = modelNumber;
        this.manufactureDate = manufactureDate;
    }

    public GeneratorModel getModelNumber()
    {
        return modelNumber;
    }

    public String getManufactureDate()
    {
        return manufactureDate;
    }

	public double getCutIn() {
		return cutIn;
	}

	public double getShutdownSpeed() {
		return shutdownSpeed;
	}

	public double getpMax() {
		return pMax;
	}
    

    //double cutIn;
    // double shutdownSpeed;
    // double pMax;  

}
