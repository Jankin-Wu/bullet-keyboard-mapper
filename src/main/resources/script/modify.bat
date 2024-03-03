@echo off
setlocal enabledelayedexpansion

echo Processing...

for /R %%F in (*.args) do (
    set "file=%%~fF"
    set "content="
    
    for /F "usebackq tokens=* delims=" %%A in ("!file!") do (
        set "line=%%A"
        set "line=!line:\\=/!"
        echo !line!>>"!file!.tmp"
        
        if "!line!"=="" (
            set "content=!content!!file!.tmp\native-image "
        )
    )
    
    move /Y "!file!.tmp" "!file!" >nul
)
 
echo Done!
echo Modified files:
echo %content%

endlocal
