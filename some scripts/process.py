import os
import datetime

class Alarm_:
	def __init__(self):
		pass

def process_time(time):
	time = time.replace('-','')
	time = time.replace(':','')
	time = time.replace(' ','')
	return time

def compare_time(time1,time2):
	len_time1 = len(time1)
	len_time2 = len(time2)
	if(len_time1 != len_time2):
		print("时间格式错误！")
		exit("时间格式错误！")
	time1 = process_time(time1)
	time2 = process_time(time2)
	# for i in range(len_time1):
	# 	if (time1[i] > time2[i]):
	if time1 > time2:
		return 1
	elif time1 == time2:
		return 0
	else:
		return -1

def save_to_file(file_name,info):
	file = open(file_name,'a')
	file.write(info)
	file.close()


starttime = datetime.datetime.now()

file_path = 'C:/Users/84575/Desktop/测试数据集/result.txt'
out_file = 'C:/Users/84575/Desktop/测试数据集/python_result.txt'

Alarm_CPU_List = []
Alarm_MEM_List = []
remove_Alarm_CPU_List = []
remove_Alarm_MEM_List = []

file = open(file_path,'r', encoding='UTF-8')
num = 0
none_line = 0
while(1):
	none_line = 0
	Alarm = Alarm_()
	for num in range(5):
		context = file.readline().strip('\n').strip()
		if (context):
			# print(context)
			if "-- Alarm --" in context:
				Alarm.title = "-- Alarm --"
			elif "-- remove_alarm_count --" in context :
				Alarm.title = "-- remove_alarm_count --"
			elif "Name : CPU占用率过高" in context:
				Alarm.name = "CPU占用率过高"
			elif "Name : Mem占用率过高" in context:
				Alarm.name = "Mem占用率过高"
			elif "Time" in context:
				Alarm.time = context.split(' : ')[-1].strip()
			elif 'average : 'in context:
				Alarm.average = context.split(':')[-1].strip()
			else:
				# print('--'*5)
				print(context)
				num = 0
		else:
			none_line+=1
			# no_finished = False
			# break
	if none_line > 1:
		break
	if(Alarm.title == "-- Alarm --" and Alarm.name == "CPU占用率过高"):
		Alarm_CPU_List.append(Alarm)
	if(Alarm.title == "-- remove_alarm_count --" and Alarm.name == "CPU占用率过高"):
		remove_Alarm_CPU_List.append(Alarm)
	if(Alarm.title == "-- Alarm --" and Alarm.name == "Mem占用率过高"):
		Alarm_MEM_List.append(Alarm)
	if(Alarm.title == "-- remove_alarm_count --" and Alarm.name == "Mem占用率过高"):
		remove_Alarm_MEM_List.append(Alarm)

	# if(Alarm.name == "CPU占用率过高"):
	# 	Alarm_CPU_List.append(Alarm)
	# if( Alarm.name == "Mem占用率过高"):
	# 	Alarm_MEM_List.append(Alarm)

file.close()

print(len(Alarm_CPU_List))
print(len(Alarm_MEM_List))
print(len(remove_Alarm_CPU_List))
print(len(remove_Alarm_MEM_List))

# print(Alarm_CPU_List[0].average,Alarm_CPU_List[1].average,Alarm_CPU_List[2].average)

i = 0
j = 0
k = 0


while (i < len(Alarm_MEM_List) ):
	# print("i:" + str(i))
	while( j < len(Alarm_CPU_List)):
		# print("j:" + str(j))
		while( k < len(remove_Alarm_CPU_List)):
			# print("k:" + str(k))
			# print(Alarm_MEM_List[i].time)
			# print(Alarm_CPU_List[j].time)
			# print(remove_Alarm_CPU_List[k].time)
			# print("--"*20)
			if(compare_time(Alarm_MEM_List[i].time,Alarm_CPU_List[j].time) > 0 and compare_time(Alarm_MEM_List[i].time,remove_Alarm_CPU_List[k].time) < 0 ):

				output_info = "-- Mixture Alarm --" + "\n" + " Name : " + Alarm_MEM_List[i].name + "\n" + " Time : " + Alarm_MEM_List[i].time + "\n" + " average : " + Alarm_MEM_List[i].average + "\n\n"
				print(output_info)
				save_to_file(out_file,output_info)
				print("*"*20)
				k += 1
				break
			k += 1
		j += 1
		break
	i += 1		# print(output_info)

# for i in range(len(Alarm_MEM_List)):
# 	print("i:" + str(i))
# 	for j in range(len(Alarm_CPU_List)-1):
# 		print("j:" + str(j))
# 		for k in range(len(remove_Alarm_CPU_List)-1):
# 			print("k:" + str(k))
# 			if(compare_time(Alarm_MEM_List[i].time,Alarm_CPU_List[j].time) > 0 and compare_time(Alarm_MEM_List[i].time,remove_Alarm_CPU_List[k].time) < 0 ):
# 				print(Alarm_MEM_List[i].time)
# 				print(Alarm_CPU_List[j].time)
# 				print(remove_Alarm_CPU_List[k].time)
# 				output_info = "-- Mixture Alarm --" + "\n" + " Name : " + Alarm_MEM_List[i].name + "\n" + " Time : " + Alarm_MEM_List[i].time + "\n" + " average : " + Alarm_MEM_List[i].average
# 				print(output_info)
# 				save_to_file(out_file,output_info)
# 				print("*"*20)
# 				break
# 		break
			# print(output_info)

endtime = datetime.datetime.now()
print (endtime - starttime)
#print('花费时间' + float(time_end-time_start))