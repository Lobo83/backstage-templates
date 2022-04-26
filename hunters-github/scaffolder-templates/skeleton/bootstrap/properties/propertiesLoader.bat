@echo off
setlocal EnableDelayedExpansion
for /f "tokens=1* delims==" %%a in (project.properties) do (
    set x=%%a
    if "!x:~0,6!"=="config"  (
      echo %%a %%b
      curl -g --request PUT --data "%%b" http://127.0.0.1:8500/v1/kv/%%a -w "\n"

    )


)


