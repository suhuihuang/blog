
Jupyter Notebook�ĸ������ñʼ�

Jupyter Notebook�İ�װ������
��win10ϵͳ�У���װ��Anaconda֮��Jupyter�Ѿ��Զ���װ���������������У�����:jupyter notebook����������Jupyter Notebook. Ҳ���԰�Jupyter Notebookͼ���͵������ݷ�ʽ��

�޸�Jupyter Notebook��Ĭ�Ϲ���Ŀ¼
������Ҫ���������ļ���
�������У�����jupyter notebook --generate-config, �������������ļ������ɵ������ļ���λ����C:\Users\Administrator\.jupyter

����Jupyter Notebook��Ĭ��Ŀ¼��
���ı������������vim�����������ɵ������ļ� C:\Users\Administrator\.jupyter��
�ҵ� #c.NotebookApp.notebook_dir = ''
�����޸�Ϊ��ȥ��#�ţ���ӵ�Ŀ¼����Jupyter�Ĺ���Ŀ¼����
c.NotebookApp.notebook_dir = u'D:\\python'��
����D:\pythonΪ�����趨��Ŀ¼�����Ը���������Ҫ�޸ġ�

����Jupyter NotebookĬ�������Ϊfirefox
Jupyter Notebookʹ�õ���ϵͳĬ�ϵ��������һ����chrome. ����Jupyter Notebook�е�latex��ʽ��chrome�в����ܺܺõ���ʾ����������������������йء�������firefox��������Խ����һ���⡣

����Ĭ�������Ϊfirefox:
�������ļ����ҵ�#c.NotebookApp.browser =''
�����������������
import webbrowser
webbrowser.register('firefox', None, webbrowser.GenericBrowser('C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe'))
c.NotebookApp.browser = 'firefox'
����·������Ϊfirefox��ʵ��·��, �����ҵ�win10ϵͳ��firefox��·��ΪC:\\Program Files\\Mozilla Firefox\\firefox.exe.


��װjupyter notebook��java��� IJava �ķ�������:

1,����Java JDK >= 9.����12

2,����ijava-1.3.0.zip,����ѹ��

3,�����ѹ��Ŀ¼������ python3 install.py --sys-prefix��



����μ���https://github.com/SpencerPark/IJava

Ҳ������������ҳ������ֱ�ӳ���IJava��

https://mybinder.org/v2/gh/SpencerPark/ijava-binder/master


3СʱJava����
ѧϰ����:https://mp.weixin.qq.com/s/JW1_w-4Z-j3ekU-GamNakw