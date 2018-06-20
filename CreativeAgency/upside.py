import sys


flipTable = {
    u'\u0021': u'\u00A1',
    u'\u0022': u'\u201E',
    u'\u0026': u'\u214B',
    u'\u0027': u'\u002C',
    u'\u0028': u'\u0029',
    u'\u002E': u'\u02D9',
    u'\u0033': u'\u0190',
    u'\u0034': u'\u152D',
    u'\u0036': u'\u0039',
    u'\u0037': u'\u2C62',
    u'\u003B': u'\u061B',
    u'\u003C': u'\u003E',
    u'\u003F': u'\u00BF',
    u'\u0041': u'\u2200',
    u'\u0042': u'\u10412',
    u'\u0043': u'\u2183',
    u'\u0044': u'\u25D6',
    u'\u0045': u'\u018E',
    u'\u0046': u'\u2132',
    u'\u0047': u'\u2141',
    u'\u004A': u'\u017F',
    u'\u004B': u'\u22CA',
    u'\u004C': u'\u2142',
    u'\u004D': u'\u0057',
    u'\u004E': u'\u1D0E',
    u'\u0050': u'\u0500',
    u'\u0051': u'\u038C',
    u'\u0052': u'\u1D1A',
    u'\u0054': u'\u22A5',
    u'\u0055': u'\u2229',
    u'\u0056': u'\u1D27',
    u'\u0059': u'\u2144',
    u'\u005B': u'\u005D',
    u'\u005F': u'\u203E',
    u'\u0061': u'\u0250',
    u'\u0062': u'\u0071',
    u'\u0063': u'\u0254',
    u'\u0064': u'\u0070',
    u'\u0065': u'\u01DD',
    u'\u0066': u'\u025F',
    u'\u0067': u'\u0183',
    u'\u0068': u'\u0265',
    u'\u0069': u'\u0131',
    u'\u006A': u'\u027E',
    u'\u006B': u'\u029E',
    u'\u006C': u'\u0283',
    u'\u006D': u'\u026F',
    u'\u006E': u'\u0075',
    u'\u0072': u'\u0279',
    u'\u0074': u'\u0287',
    u'\u0076': u'\u028C',
    u'\u0077': u'\u028D',
    u'\u0079': u'\u028E',
    u'\u007B': u'\u007D',
    u'\u203F': u'\u2040',
    u'\u2045': u'\u2046',
    u'\u2234': u'\u2235'
    }

other = {}
for i in flipTable:
    other[flipTable[i]] = i
flipTable.update(other)


def flip_string(s):
    global flipTable
    r = []
    for c in s:
        v = unichr(ord(c))
        if v in flipTable:
            u = flipTable[v]
        else:
            u = v
        r.append(u)
    return ''.join(r)[::-1]


print(flip_string(sys.argv[1]))

# /home/bsidestlv/flag.txt
# ..home/bsidestlv/flag.txt --> BSidesTLV{I_Like_FlipFlops_And_I_Cannot_Lie}