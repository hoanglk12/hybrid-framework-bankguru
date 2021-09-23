set ProjectPath=%~dp0
cd %ProjectPath%
set p=%PATH%
java -classpath "%ProjectPath%bin;%ProjectPath%libraries\*" org.testng.TestNG "%ProjectPath%bin\runBankGuruTestcases.xml"
pause