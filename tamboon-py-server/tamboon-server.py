import getopt
import sys
import socket

from flask import Flask
from flask_restful import Api

from charities import Charities
from donations import Donations
from chargeoperation import ChargeOperation

secret_key = ''
public_key = ''
host_ip = '127.0.0.1'
host_port = '8088'


def get_host():
     hostname = socket.gethostname()
     ## getting the IP address using socket.gethostbyname() method
     ip_address = socket.gethostbyname(hostname)
     ## printing the hostname and ip_address
     # print "Hostname: " + hostname
     # print "IP Address: " + ip_address
     return ip_address

def parse_keys(argv):
     global secret_key, public_key, host_port
     try:
          opts, args = getopt.getopt(argv, "hs:p:", ["skey=", "pkey=","port="])
     except getopt.GetoptError:
          print 'tamboon-server.py -s <secret key> -p <public key>'
          sys.exit(2)
     for opt, arg in opts:
          if opt == '-h':
               print 'tamboon-server.py -s <secret key> -p <public key>'
               sys.exit()
          elif opt in ("-s", "--skey"):
               secret_key = arg
          elif opt in ("-p", "--pkey"):
               public_key = arg
          elif opt in ("--port"):
               host_port = arg

def start_server():
     host_ip = get_host()
     app = Flask(__name__)
     api = Api(app)
     api.add_resource(Charities, '/charities')
     api.add_resource(Donations, '/donations')
     app.run(host = host_ip, port = host_port)

def main(argv):
     parse_keys(argv)
     ChargeOperation.Instance().set_secret_key(secret_key)
     start_server()

if __name__ == '__main__':
     main(sys.argv[1:])