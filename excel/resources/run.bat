@echo off
:AAA
set /p num=输入生成数量(不超过50)
echo %num%|findstr /be "[0-9]*" >nul && goto BBB || goto AAA
:BBB
if %num% lss 51 (
echo 正在生成%num%份练习
for /l %%i in (1,1,%num%) do java -jar ex.jar
echo %num%份练习生成完成
) else (goto AAA)