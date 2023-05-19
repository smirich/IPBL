#!/usr/bin/env python3
'''
This script creates a back-end to be used as a plugin.
By passing in a generator package and windspeed, a results class,
    PerformanceDataClass object will be returned containing the power
    and efficiency metrics.
'''
import numpy as np

class PerformanceDataClass:
    '''
    This class operates as a dictionary to allow a
    java function to return multiple types ans values
    '''
    def __init__(self, power,efficiency):
        self.power = power
        self.efficiency = efficiency

class BackEnd:
    def __init__(self,GeneratorPackage):
        self.generator = GeneratorPackage
        self.model = None

        self._get_power()

    def _get_power(self):
        # use model to get power

        # use model to find max power

        # pass power and max power to efficiency
        # get_efficiency(power,max_power)
        pass
    def _get_efficiency(self, power, max_power):
        '''
        By passing the power and max power from the get_power method,
        we can return the efficiency back to be used to instantiate
        a PerformanceDataClass object.
        '''
        pass

class PerformanceData:
    '''
    At instantiation, take the generator, and windspeeds, and call the build methods
    then optionally return a string with info
    '''
    def __init__(self, generator, wind_speed):
        # public fields used to evaluate the performance #
        self.performance_index = None
        self.status = None
        self.power = None
        self.wind_generator = wind_generator
        # private fields used to generate the data #
        self._power_curve = None
        self._efficiency_margin = None
        self._status_enum = {
            '0' : 'Below Threshold',
            '1' : 'Above Threshold',
            '2' : 'Sweet Spot',
            '3' : 'Shutdown Region'
        }

        # implicit builder #
        self._get_performance(wind_speed)

    # ----------------------------------------------#
    # These power curves and efficiency functions are written explicity for readability
    # they define the mathematical quantities of the generator machines
    # It may need refactoring or rethought to implement in java

    # I have modified the curves from the production units deployed in the field.
    # The E44 has been modified to be a lower power unit with a large efficiency range
    # The E82 has been modified to be a high power unit with a small efficiency range
    # The E70 sits in the middle
    def _E44_curve(self, V, cut_in,shut_down,p_max):
        p = 0 if V < cut_in else V*69.7 if V < 12 else p_max if V < shut_down else 0
        self._get_status(p, V, cut_in, 12, shut_down, p_max)
        return p if p<= p_max else p_max

    def _E70_curve(self, V, cut_in, shut_down, p_max):
        p = 0 if V < cut_in else V*192.3 if V < 16 else p_max if V < shut_down else 0
        self._get_status(p, V, cut_in, 16, shut_down, p_max)
        return p if p<= p_max else p_max

    def _E82_curve(self, V, cut_in, shut_down, p_max):
        p = 0 if V < cut_in else V*197.7 if V < 19 else p_max if V < shut_down else 0
        self._get_status(p, V, cut_in, 19, shut_down, p_max)
        return p if p<= p_max else p_max

    def _E44_efficiency(self, P, p_max):
        return np.round( P/p_max * 100,2)
    def _E70_efficiency(self, P, p_max):
        return np.round(P/p_max * 100,2)
    def _E82_efficiency(self, P, p_max):
        return np.round( P/p_max * 100 ,2)

    def _get_status(self, p, v, cut_in, efficiency_range, shut_down, p_max):
        status_dict = {
            p > p_max: '2',
            v < cut_in: '0',
            v < efficiency_range: '1',
            v > shut_down: '3'
        }
        self.status = self._status_enum[status_dict.get(True, '0')]
    #-----------------------------------------------------------------#

    def _get_curve(self, model):
        if model == 'E44':
            self._power_curve = self._E44_curve
            self._efficiency_margin = self._E44_efficiency
        elif model == 'E70':
            self._power_curve = self._E70_curve
            self._efficiency_margin = self._E70_efficiency
        elif model == 'E82':
            self._power_curve = self._E82_curve
            self._efficiency_margin = self._E82_efficiency



    def _unpack(self):
        '''
        this method is just for my own sanity
        '''
        return (
                self.generator.model_num,
                self.generator.cut_in,
                self.generator.shut_down,
                self.generator.p_max,
                self.generator.gear_package.gear_ratio
               )
    def _get_performance(self, wind_speed):
        '''
        This method uses the parameters of the generator passed in to return
        the appropriate power. The params include the model_num,cut_in, shut_down,p_max,
        and gear_package
        '''
        model_num,cut_in,shut_down,p_max,gear_ratio = self._unpack()
        # set the power curve and efficiency
        self._get_curve(model_num)
        # find the output shaft speed
        geared_windspeed = wind_speed * gear_ratio
        # find the power
        power = self._power_curve(geared_windspeed, cut_in, shut_down, p_max)

        # get the efficiency of the power generated
        self.efficiency = self._efficiency_margin(power, p_max)
        self.power = power

        # Here we all the quantities required, we can choose how to return them
        # in whatever format we need


    def toString(self):
        print(f'Generated {self.power}kW, Machine is {self.efficiency}% effective, geared_windspeed is {self.status}')


