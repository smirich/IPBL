package windmillcomplete;

enum GeneratorModel {
    SPARKY1000, SHOCKINGTON4, THUNDERBOLT7
}

public class GeneratorPackage {
    private final GeneratorModel modelNumber;
    private final String manufactureDate;

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

    //double cutIn;
    // double shutdownSpeed;
    // double pMax;  

}
