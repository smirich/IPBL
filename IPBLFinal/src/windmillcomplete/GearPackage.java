package windmillcomplete;

enum GearModel {
    WINDJAMMER1, CYCLONE3, QUIXOTE7
}

public class GearPackage {

    private final GearModel model; //123
    private final double gearingRatio; //.54

    public GearPackage(GearModel modelNumber, double gearingRatio) {
        this.model = modelNumber;
        this.gearingRatio = gearingRatio;
    }

    public GearModel getModel() {
        return model;
    }

    public double getGearingRatio() {
        return gearingRatio;
    }

}
