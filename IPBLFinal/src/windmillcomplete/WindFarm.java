package windmillcomplete;

public class WindFarm {
    private WindGenerator[] windGenerators = new WindGenerator[10];
    private int numWindGenerators;
    private EngineeringModel engineeringModel;

    public WindFarm(EngineeringModel engineeringModel) {
        this.engineeringModel = engineeringModel;
    }

    public void addWindGenerator(WindGenerator windGenerator)
    {
        windGenerators[numWindGenerators] = windGenerator;
        numWindGenerators++;
    }

    public String generateWindFarmReport(int windSpeed)
    {
        String output = "----Wind Farm Report for " + windSpeed + " km winds ----\n";
        for (int i = 0; i < numWindGenerators; i++)
        {
            output += windGenerators[i].toString() + "   [performance rating] = "
                    + windGenerators[i].getPerformanceData(windSpeed, engineeringModel).toString() + "\n";
        }
        return output;
    }

    public WindGenerator findBestPerformer(int windSpeed)
    {
        int bestPerformer = 0;
        for (int i = 0; i < numWindGenerators; i++)
        {
            double performanceIndexOfThisGenerator = windGenerators[i].getPerformanceData(windSpeed, engineeringModel).getPerformanceIndex();
            double topPerformanceIndexSoFar = windGenerators[bestPerformer].getPerformanceData(windSpeed, engineeringModel).getPerformanceIndex();
            if (performanceIndexOfThisGenerator > topPerformanceIndexSoFar)
            {
                bestPerformer = i;
            }
        }
        return windGenerators[bestPerformer];

    }
}
