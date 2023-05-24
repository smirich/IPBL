package windmillcomplete;

public class WindGenerator {
    private final int windGeneratorID;
    private GearPackage gearPackage;
    private GeneratorPackage generatorPackage;

    private WindGenerator(int windGeneratorID) {
        this.windGeneratorID = windGeneratorID;
    }

    public static WindGenerator build(int windGeneratorID)
    {
        return new WindGenerator(windGeneratorID);
    }

    public int getWindGeneratorID()
    {
        return windGeneratorID;
    }

    public GeneratorPackage getGeneratorPackage()
    {
        return generatorPackage;
    }

    public GearPackage getGearPackage()
    {
        return gearPackage;
    }

    public WindGenerator installGeneratorPackage(GeneratorPackage generatorPackage)
    {
        this.generatorPackage = generatorPackage;
        return this;
    }

    public WindGenerator installGearPackage(GearPackage gearPackage)
    {
        this.gearPackage = gearPackage;
        return this;
    }

    public PerformanceData getPerformanceData(int windSpeed, EngineeringModel engineeringModel)
    {
        PerformanceData data = engineeringModel.getPerformanceData(windSpeed, this);
        return data;
    }

    @Override
    public String toString()
    {
        return "[windGeneratorID] = " + windGeneratorID;
    }
}
