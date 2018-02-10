@echo off

SET ROBORIO=10.19.87.2
SET USER=lvuser
SET LOCAL_PATHS=C:\Users\Broncobots\Documents\GitHub\Robot2018\src\paths

REM Make sure the ~/paths directory exists
plink %USER%@%ROBORIO% "if [ ! -d /home/lvuser/paths ] ; then mkdir /home/lvuser/paths ; fi"
if errorlevel 1 goto FAILED

REM Remove everything in the robot's paths directory
plink %USER%@%ROBORIO% "rm -rf /home/lvuser/paths/*"
if errorlevel 1 goto FAILED

REM Copy the local paths to the robot
pscp %LOCAL_PATHS%\*.csv %USER%@%ROBORIO%:/home/lvuser/paths
if errorlevel 1 goto FAILED

goto :EOF

:FAILED
echo FAILED FAILED FAILED
pause
