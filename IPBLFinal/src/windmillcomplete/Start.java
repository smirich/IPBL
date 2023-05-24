package windmillcomplete;

public class Start {

    /* ⁡⁢⁣⁢Start here!⁡
     
        You have been asked to work with an Engineering team to calculate the optimimum power output of a wind generator, given a set of variables such as wind speed, gear packages, and generator packages.  
    
        The Engineering team is busy working on all of the technical stuff and our job is to write the software that will eventually integrate with their work
     */

    /*  ⁡⁣⁢⁢STEP 1a: Write a WindGenerator class. (worth 1 % bonus on Midterm)
    
    ⁡  Must be submitted by May 25 to qualify for midterm bonus point
    
    This windmill will contain the following information:
        - windGeneratorID of type int
        - gearPackage of type String
        - generatorPackage of type String
    
        write getters and setters for the instance variables and overload the constructor so that you can use either Constructor injection (pass the values to the instance variables through the constructor) or setter injection (instantiate an empty WindGenerator and use the setters to set the values of the WindGenerator)
        
        Once you've correctly written the WindGenerator class, instantiate two WindGenerator objects, one using Constructor injection and one using setter injection.
    */

    /*  ⁡⁣⁢⁢STEP 2:  Write a class called WindFarm that:   (worth 1% bonus on midterm)
    
    - Must be submitted by May 31 for bonus mark⁡
    
        - contains an array capable of holding 10 WindGenerator objects. We will call this windGenerators
        - contains an instance variable called numWindGenerators that will track how many WindGenerators have been added to the WindFarm
        - contains a method called addWindGenerator that accepts a WindGenerator and returns nothing.  This method will allow a WindGenerator to be added to the windGenerators instance variable
        - contains a method called getGeneratorsReport which will, for now, just return a String containing a description of each of the WindGenerators in the WindFarm.  
     */

    /* ⁡⁣⁢⁢STEP 3a:  toString equals method⁡
    
    Override the toString method for the WindGenerator class. Prove that your toString method works by modifying your getGeneratorsReport in WindFarm to use the toString method*/

    /*⁡⁣⁢⁢ STEP 2b: equals method⁡
    
    Write a equals method, along with a
    
    Override the equals and hashcode methods to allow WindGenerator object to be compared, based on their windGeneratorID.  If two objects have the same windGeneratorID, they are the same object
    
    prove that your equals and hashmethods work correctly
     */

    public static void main(String[] args)
    {

        WindFarm farm = new WindFarm(new EngineeringModelsProduction());
        farm.addWindGenerator(WindGenerator.build(123)
                .installGearPackage(new GearPackage(GearModel.CYCLONE3, .5))
                .installGeneratorPackage(new GeneratorPackage(GeneratorModel.THUNDERBOLT7, "May 1012", 3 , 40,5000)));

        farm.addWindGenerator(WindGenerator.build(456)
                .installGearPackage(new GearPackage(GearModel.CYCLONE3, .54))
                .installGeneratorPackage(new GeneratorPackage(GeneratorModel.SPARKY1000, "Sept 2004",5, 60,6000)));

        System.out.println(farm.generateWindFarmReport(80));
        System.out.println(farm.findBestPerformer(80));

    }

}
