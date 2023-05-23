#!/usb/bin/env python3

'''
Python classes are written in PascalCase
methods are written in snake_case()
private methods are writen prepending an _underscore()
builtin classmethods are written prepending and appending __dunders()__
'''


import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import argparse

class Results:
    '''
    In python we would normally use a dictionary for this,
    Results class is used to simplify Java implementations
    '''
    def __init__(self, power, efficiency):
        self.power = power
        self.efficiency = efficiency

class WindGenerator:
    '''
    This class combines the Turbine and Geartrain to simplify access to
    power and efficiency based on an input windspeed
    '''
    def __init__(self, model, geartrain):
        self.model = model
        self.geartrain = geartrain
        self.power_curve = None
        self.efficiency_margin = None
        self._get_curve()

    # These power curves and efficiency functions are written explicitly for readability
    # ---- Function and efficiency math ----#
    def _E44_curve(self, V):
        return V*69.7 if V < 17 else 910 if V < 25 else 0
    def _E70_curve(self, V):
        return V*192.3 if V < 16 else 2310 if V < 25 else 0
    def _E82_curve(self, V):
        return V*197.7 if V < 13 else 2050 if V < 25 else 0

    def _E44_efficiency(self, P):
        return np.round( P/910 * 100,2)
    def _E70_efficiency(self, P):
        return np.round(P/2310 * 100,2)
    def _E82_efficiency(self, P):
        return np.round( P/2050 * 100 ,2)
    #-------------------------------------#

    def _get_curve(self):
        if self.model == 'E44':
            self.power_curve = self._E44_curve
            self.efficiency_margin = self._E44_efficiency
        elif self.model == 'E70':
            self.power_curve = self._E70_curve
            self.efficiency_margin = self._E70_efficiency
        elif self.model == 'E82':
            self.power_curve = self._E82_curve
            self.efficiency_margin = self._E82_efficiency

    def results(self, windspeed):
        geared_windspeed = windspeed * self.geartrain
        power = self.power_curve(geared_windspeed)
        efficiency = self.efficiency_margin(power)
        return Results(power,efficiency) # returning a class with result


class Windmill:
    '''
    This class has all the basic methods used by the students but also
    uses the WindGenerator class to implement a Turbine and Geartrain to return
    a results class
    It is currently written to require model and geartrain at instantiation
    '''
    def __init__(self, model:str=None, geartrain:int=None):
        self.model = None
        self.geartrain = None
        self.generator = None
        # If params are passed in at instatiation...
        if model is not None and geartrain is not None:
            self.set_model(model)
            self.set_geartrain(geartrain)
            self._build_generator(model,geartrain)

    def set_model(self, model: str):
        self.model = model

    def get_model(self):
        print(f'Windmill model is {self.model}')

    def set_geartrain(self, geartrain: int):
        self.geartrain = geartrain

    def get_geartrain(self):
        print(f'Windmill generator is {self.geartrain}')

    def _build_generator(self, model: str, geartrain: int) -> WindGenerator:
        self.model = model if model else self.model
        self.geartrain = geartrain if geartrain else self.geartrain
        self.generator = WindGenerator(self.model, self.geartrain)

    def get_power_and_efficiency(self, windspeed:float) -> Results:
        if self.generator is None:
            self._build_generator(model=self.model, geartrain=self.geartrain)
        return self.generator.results(windspeed)

    def get_params(self):
        print(f'Windmill model is {self.model}')
        print(f'Windmill gearing is {self.geartrain}')


class Windfarm:
    '''
    This class is composed of multiple windmills
    '''
    def __init__(self):
        self.name = None
        self.location = None
        self.windmill = None
        '''
        Currently the WindFarm class is implemented on only a single windmill.
        This can be improve to add multiple windmills of different types.
        Also, the windfarm could inherit the methods or attributes from the
            windmill objects
        '''

    def set_name(self, name:str):
        self.name = name

    def get_name(self):
        print(f'Windfarm name is {self.name}')

    def set_location(self, location:str):
        self.location = location

    def get_location(self):
        print(f'Windfarm location is {self.location}')

    def set_windmill(self, windmill:Windmill):
        self.windmill = windmill

    def get_windmill(self):
        self.windmill.get_params()

    def power(self, windspeed:float) -> Results:
        return self.windmill.get_power_and_efficiency(windspeed)


def main(args = None):

    # Build windfarm
    farm = Windfarm()
    farm.set_name(args.name)
    farm.get_name()
    farm.set_location(args.location)
    farm.get_location()
    #equip with windmill,
    mill = Windmill(args.model, args.gearing)
    #equip with geartrain and generator
    farm.set_windmill(mill)
    farm.get_windmill()
    #Pass in windspeed
    power = farm.power(args.wind)
    #get results
    print(f'Resultant power : {power.power}')
    print(f'Resultant efficiency : {power.efficiency}')

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Setup windfarm')
    parser.add_argument('model',choices = ['E44','E70','E82'],
                        help='Choose Windmill model')
    parser.add_argument('--name','-n', default='My Windfarm',
                        type=str, help='Specify a name')
    parser.add_argument('--location','-l', default='Ontario',
                        type=str, help='Specifcy location')
    parser.add_argument('--gearing','-g', default='1',
                        type=float, help='Set geartrain')
    parser.add_argument('wind', type=float, help='Set input windspeed')

    args = parser.parse_args()
    main(args)
