import os
import omise

def create_charge():
    omise.api_secret = os.getenv("OMISE_SKEY")
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