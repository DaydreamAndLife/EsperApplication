FileMerge.py
	主要功能：
		1.将某个目录下的文件全部读到同一个文件中，方便后续Esper进行解析。
		2.对上一步骤形成的文件内的记录按照时间戳大小进行排序。

process.py
	主要功能：
		对Esper处理过后产生的告警信息进行在处理。生成混合告警信息。
		例如，当CPU连续一段时间超过阈值并且MEMORY连续一段时间超过阈值时，即可生成一条混合告警信息。

test_for_create_Alarm.py
	主要功能：
		模拟向EsperApplication发送生成告警规则的功能。

test_for_create_RemoveAlarm.py
	主要功能：
		模拟向EsperApplication发送取消告警规则的功能。

		？？？
		