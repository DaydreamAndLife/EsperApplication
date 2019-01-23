import requests
import json

url = 'http://localhost:8080/alarm/create_rule'

data = {
	'ruleName': "CPU占用率过s高",
	'target': "CpuUtilization",
	'valueType': "average",
	'compare': ">",
	'threshold': 0.6,
	'count': 3,
	'r_target': "CpuUtilization",
	'r_valueType': "average",
	'r_compare': "<",
	'r_threshold': 0.6,
	'r_count': 3,
}

response = requests.post(url, data=data)

print(response.text)
