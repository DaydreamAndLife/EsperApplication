1. AlarmRule.java
	主要功能：
		定义告警规则以及相应的getter、setter。

2. GlobalVar.java
	主要功能：
		用作全局变量。

3. ReadFilesAndCreateObjects.java
	主要功能：
		1. 读取输入路径的内容。
			若输入路径是文件，则提取文件的所有内容。
			若输入路径是文件夹，则提取文件夹下所有文件的内容。[此项意义不大，在预处理的Python脚本中，已经将所有文件的内容全部集中到一个文件中]
		2. 建立对象。
			按照CpuUtilization.java以及MemoryFreeutilization.java中定义的数据格式，形成相应的数据。以便epl语句处理。

4. WriteStringToFile.java
	主要功能：
		向文件中写入结果。