package windmillcomplete;

public class EngineeringModelsProduction implements EngineeringModel {

	@Override
	public PerformanceData getPerformanceData(int windSpeed, WindGenerator windGenerator) {
		// TODO Auto-generated method stub
		double power = 0;
		double effiency = 0;
		double gearedWindSpeed = windSpeed * windGenerator.getGearPackage().getGearingRatio();
		int effiencyRange = 0;
		Status status = null;
		if (windGenerator.getGeneratorPackage().getModelNumber().equals(GeneratorModel.SHOCKINGTON4)) {
			if (gearedWindSpeed < windGenerator.getGeneratorPackage().getCutIn()) {
				power = 0;
			} else if (gearedWindSpeed < 12) {
				power = gearedWindSpeed * 69.7;
			} else if (gearedWindSpeed < windGenerator.getGeneratorPackage().getShutdownSpeed()) {
				power = windGenerator.getGeneratorPackage().getpMax();
			} else {
				power = 0;
			}
			effiencyRange = 12;

		} else if (windGenerator.getGeneratorPackage().getModelNumber().equals(GeneratorModel.SPARKY1000)) {
			if (gearedWindSpeed < windGenerator.getGeneratorPackage().getCutIn()) {
				power = 0;
			} else if (gearedWindSpeed < 16) {
				power = gearedWindSpeed * 192.3;
			} else if (gearedWindSpeed < windGenerator.getGeneratorPackage().getShutdownSpeed()) {
				power = windGenerator.getGeneratorPackage().getpMax();
			} else {
				power = 0;
			}
			effiencyRange = 16;

		} else if (windGenerator.getGeneratorPackage().getModelNumber().equals(GeneratorModel.THUNDERBOLT7)) {
			if (gearedWindSpeed < windGenerator.getGeneratorPackage().getCutIn()) {
				power = 0;
			} else if (gearedWindSpeed < 19) {
				power = gearedWindSpeed * 197.7;
			} else if (gearedWindSpeed < windGenerator.getGeneratorPackage().getShutdownSpeed()) {
				power = windGenerator.getGeneratorPackage().getpMax();
			} else {
				power = 0;
			}
			effiencyRange = 19;
		}

		if (gearedWindSpeed < windGenerator.getGeneratorPackage().getCutIn()) {
			status = Status.BELOWWINDTHRESHOLD;
		} else if (gearedWindSpeed < effiencyRange) {
			status = Status.ABOVEWINDTHRESHOLD;
		} else if (power == windGenerator.getGeneratorPackage().getpMax()) {
			status = Status.SWEETSPOT;
		} else if (gearedWindSpeed > windGenerator.getGeneratorPackage().getShutdownSpeed()) {
			status = Status.SHUTDOWN;
		}

		effiency = (power / windGenerator.getGeneratorPackage().getpMax()) * 100;

		return new PerformanceData(effiency, status, power, windGenerator);

	}
}
