#1.将一个目录下的所有文件内容都读到一个新文件中。
import os
from functools import cmp_to_key
      
def file_name(file_dir):   
	for root, dirs, files in os.walk(file_dir):
		return files #当前路径下所有非目录子文件

def merge(directory_path,new_file_path):
	for filename in os.listdir(directory_path):
		fullname = os.path.join(directory_path, filename)
		# 如果是linux系统导出来的数据用utf-8,如果是windows则用gbk
		f1 = open(fullname, encoding='gbk')
		for i in f1:
			with open(new_file_path,encoding='gbk',mode='a+')as f2:
				f2.write(i)
				f2.close()
		f1.close()
		with open(new_file_path,encoding='gbk',mode='a+')as f2:
			f2.write('\n')
			f2.close()
	print('已经将' + Dir_Path + '下所有文件内的记录读取到' + Big_file + '之中~\n')



def clearBlankLine(file1,file2):
	#由于merge函数中最后一行有一个空行导致出错，所以这个函数的作用是除去所有的空行
    file1 = open(file1, 'r', encoding='utf-8') # 要去掉空行的文件 
    file2 = open(file2, 'w', encoding='utf-8') # 生成没有空行的文件
    try:
        for line in file1.readlines():
            if line == '\n':
                line = line.strip("\n")
            file2.write(line)
    finally:
        file1.close()
        file2.close()

def time_compare(time1,time2):
	if time1 > time2:
		return 1
	elif time1 == time2:
		return 0
	else:
		return -1

def TIME(time):
	return time

def data_check(record):
	if time_compare(record.time_stamp,time_upper_limit) == 1 or time_compare(time_lower_limit,record.time_stamp) == 1:
		#数据时间不合理
		return 1
	else:
		return 0



class data():
	def __init__(self):
		self.monitor_item = ''
		self.max_value = ''
		self.min_value = ''
		self.average_value = ''
		self.time_stamp = ''
		self.instance_id = ''
		self.user_id = ''
	def __str__(self):
		return 'monitor_item:%s,average_value:%s,time_stamp:%s,instance_id:%s,user_id:%s'%(self.monitor_item,self.average_value,self.time_stamp,self.instance_id,self.user_id)


time_upper_limit = '1542239770000'
time_lower_limit = '1542239760000'
CPU_record_list = []
MEM_record_list = []
Big_file_with_no_blank = 'C:/Users/84575/Desktop/测试数据集/1.txt'
test_file = 'C:/Users/84575/Desktop/测试数据集/Redis_test数据集.txt'
Dir_Path = 'C:/Users/84575/Desktop/测试数据集/temp_test'
file_names = file_name(Dir_Path)
File_num = len(file_names)
Big_file = 'C:/Users/84575/Desktop/测试数据集/new_file.txt'
sorted_file = 'C:/Users/84575/Desktop/测试数据集/sorted.txt'
print('该目录下一共有'  + str(File_num) + '个文件,分别是：')
for index in range(File_num):
	print(file_names[index])


merge(Dir_Path,Big_file)
clearBlankLine(Big_file,Big_file_with_no_blank)
# for filename in os.listdir(Dir_Path):
#     fullname = os.path.join(Dir_Path, filename)
#     # 如果是linux系统导出来的数据用utf-8,如果是windows则用gbk
#     f1 = open(fullname, encoding='gbk')
#     for i in f1:
#         with open(Big_file,encoding='gbk',mode='a+')as f2:
#             f2.write(i)
#             f2.close()
#     f1.close()


#2.对新文件中的内容进行排序。
file = open(Big_file_with_no_blank,'r')
while True:
	txt = file.readline()
	if txt:
		one_record = data()
		txt = txt.strip().split(',')
		# print(len(txt))
		one_record.monitor_item = txt[0]
		one_record.max_value = txt[1]
		one_record.min_value = txt[2]
		one_record.average_value = txt[3]
		one_record.time_stamp = txt[4]
		one_record.instance_id = txt[5]
		one_record.user_id = txt[6]
		# print("upper:" + time_upper_limit)
		# print("lower:" + time_lower_limit)
		#在这里形成关于CPU的列表以及MEM的列表，但在排序之前还需要进行数据清洗。即判断记录的产生时间是否在规定的范围内，
		#防止有上传、汇总滞后的数据混入。应在此之前将该数据去掉，不参与排序。
		if (one_record.monitor_item == 'CPUUtilization'):
			if (data_check(one_record)):
				print('--' * 10)
				print("CPU数据时间非法，数据内容为:")
				print(one_record)
				print('此数据将不被解析！')
				print('--' * 10)
			else:
				CPU_record_list.append(one_record)
		else:
			if (data_check(one_record)):
				print('--' * 10)
				print("MEM数据时间非法，数据内容为:")
				print(one_record)
				print('此数据将不被解析！')
				print('--' * 10)
			else:
				MEM_record_list.append(one_record)
	else:
		print('文件已读入内存，并形成列表，即将开始排序\n')
		break
# print('*'*20)
# print(len(CPU_record_list))
# print(len(MEM_record_list))
# print('*'*20)
# sorted(CPU_record_list, key =  cmp_to_keylambda data:data.time_stamp)
# new = sorted(s,key = )
# print new
cpu_sorted_list = sorted(CPU_record_list, key=cmp_to_key(lambda x,y:time_compare(x.time_stamp,y.time_stamp)))
mem_sorted_list = sorted(MEM_record_list, key=cmp_to_key(lambda x,y:time_compare(x.time_stamp,y.time_stamp)))
# print(len(cpu_sorted_list),len(mem_sorted_list))
# for i in range(len(sorted_list)):
# 	print(sorted_list[i].monitor_item)


#3.将排序完成的结果写入新文件

file = open(sorted_file,'a+')
for index in range(len(cpu_sorted_list)):
	file.write(cpu_sorted_list[index].monitor_item +',' +cpu_sorted_list[index].max_value + ',' + cpu_sorted_list[index].min_value + ',' + cpu_sorted_list[index].average_value + ',' + cpu_sorted_list[index].time_stamp +',' + cpu_sorted_list[index].instance_id + ',' + cpu_sorted_list[index].user_id)
	file.write('\n')
for index in range(len(mem_sorted_list)):
	file.write(mem_sorted_list[index].monitor_item +',' +mem_sorted_list[index].max_value + ',' + mem_sorted_list[index].min_value + ',' + mem_sorted_list[index].average_value + ',' + mem_sorted_list[index].time_stamp +',' + mem_sorted_list[index].instance_id + ',' + mem_sorted_list[index].user_id)
	file.write('\n')
print('排序完成，排序后的数据在'+sorted_file)