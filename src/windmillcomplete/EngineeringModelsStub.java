package windmillcomplete;

import java.util.Random;

public class EngineeringModelsStub implements EngineeringModel {

    @Override
    public PerformanceData getPerformanceData(int windSpeed, WindGenerator windGenerator) {
        return new PerformanceData(new Random().nextInt(50), Status.SWEETSPOT, windGenerator);
    }

}
