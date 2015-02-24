"""
testing for marshalling and unmarshalling post requests
"""

import requests
import time
import json

if __name__ == '__main__':

	for k in xrange(5):
		tm = time.time()
		ply = {"size": k+10 }
		dtt = {"name":"jong", "age":12}
		headers = {'content-type': 'application/json'}
		r = requests.post("http://localhost:8080/catch", params=ply, data=json.dumps(dtt), headers=headers) 
		print 'tm: {0}, info {1}'.format(time.time() - tm, r.text)