@echo off

SET ROBORIO=10.19.87.2
SET USER=lvuser
SET LOCAL_PATHS=C:\Users\Broncobots\Documents\GitHub\Robot2018\src\paths

REM Make sure the ~/paths directory exists
plink %USER%@%ROBORIO% "if [ ! -d /home/lvuser/paths ] ; then mkdir /home/lvuser/paths ; fi"

plink %USER%@%ROBORIO% "rm -rf /home/lvuser/paths/*"

pscp %LOCAL_PATHS%\*.csv %USER%@%ROBORIO%:/home/lvuser/paths
