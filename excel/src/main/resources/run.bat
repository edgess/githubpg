@echo off
:AAA
set /p num=������������(������50)
echo %num%|findstr /be "[0-9]*" >nul && goto BBB || goto AAA
:BBB
if %num% lss 51 (
echo ��������%num%����ϰ
for /l %%i in (1,1,%num%) do java -jar ex.jar
echo %num%����ϰ�������
) else (goto AAA)