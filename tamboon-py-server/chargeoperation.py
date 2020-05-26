import omise
from singleton import Singleton
@Singleton
class ChargeOperation(object):

    def __init__(self):
        pass

    def set_secret_key(self,skey):
        omise.api_secret = skey

    def create_charge(self,donation = {}):
        try:
            charge = omise.Charge.create(
                amount = donation["amount"],
                currency = 'thb',
                card = donation["token"],
                name = donation["name"]
            )
        except Exception as ex:
            return ex
        return charge