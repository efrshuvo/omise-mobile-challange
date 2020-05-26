import omise
import json

def create_charge():
    omise.api_secret = "skey_test_5jy4os10m5wgcb4tp6t"
    charge = None
    try:
        charge = omise.Charge.create(
            amount = 10000,
            currency = 'thb',
            card = 'tokn_test_5k0arg8ox17lqzk8f3g',
            name = 'John Smith'
        )
    except Exception as ex:
        return ex
    return charge

if __name__ == '__main__':
     print create_charge()