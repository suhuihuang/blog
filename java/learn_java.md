
Jupyter Notebook的个人配置笔记

Jupyter Notebook的安装与启动
在win10系统中，安装了Anaconda之后，Jupyter已经自动安装。可以启动命令行，输入:jupyter notebook，即可启动Jupyter Notebook. 也可以把Jupyter Notebook图表发送到桌面快捷方式。

修改Jupyter Notebook的默认工作目录
首先需要生成配置文件：
打开命令行，输入jupyter notebook --generate-config, 可以生成配置文件。生成的配置文件的位置是C:\Users\Administrator\.jupyter

更改Jupyter Notebook的默认目录：
用文本浏览器（例如vim）打开以上生成的配置文件 C:\Users\Administrator\.jupyter。
找到 #c.NotebookApp.notebook_dir = ''
将其修改为（去掉#号，添加的目录就是Jupyter的工作目录）：
c.NotebookApp.notebook_dir = u'D:\\python'。
其中D:\python为个人设定的目录，可以根据自身需要修改。

更改Jupyter Notebook默认浏览器为firefox
Jupyter Notebook使用的是系统默认的浏览器，一般是chrome. 但是Jupyter Notebook中的latex公式在chrome中并不能很好地显示，这与浏览器的字体设置有关。而采用firefox浏览器可以解决这一问题。

更改默认浏览器为firefox:
在配置文件中找到#c.NotebookApp.browser =''
在下面添加如下内容
import webbrowser
webbrowser.register('firefox', None, webbrowser.GenericBrowser('C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe'))
c.NotebookApp.browser = 'firefox'
其中路径更改为firefox的实际路径, 例如我的win10系统中firefox的路径为C:\\Program Files\\Mozilla Firefox\\firefox.exe.


安装jupyter notebook的java插件 IJava 的方法如下:

1,下载Java JDK >= 9.建议12

2,下载ijava-1.3.0.zip,并解压。

3,进入解压后目录，运行 python3 install.py --sys-prefix。



详情参见：https://github.com/SpencerPark/IJava

也可以在以下网页链接中直接尝试IJava：

https://mybinder.org/v2/gh/SpencerPark/ijava-binder/master


3小时Java入门
学习资料:https://mp.weixin.qq.com/s/JW1_w-4Z-j3ekU-GamNakw