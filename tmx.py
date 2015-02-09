import requests
import time

if __name__ == '__main__':

	for k in xrange(1000):
		tm = time.time()
		r = requests.get("http://localhost:8080/ping")
		print 'tm: {0}, info {1}'.format(time.time() - tm, r.text[:10])