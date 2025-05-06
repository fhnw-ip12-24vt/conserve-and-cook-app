#!/bin/bash

# Check if Java is installed
if type -p java; then
    echo "Java found in PATH."
elif [[ -x "$JAVA_HOME/bin/java" ]]; then
    echo "Java found in JAVA_HOME."
else
    echo "Java not found. Installing JDK 21..."

    # For Debian/Ubuntu systems (requires sudo privileges)
    sudo apt update
    sudo apt install -y openjdk-21-jdk

    if [ $? -ne 0 ]; then
        echo "Failed to install JDK 21. Exiting."
        exit 1
    fi
fi

cd "$1"
pkill java
DISPLAY=:0 XAUTHORITY=/home/pi/.Xauthority java -Dsun.java2d.opengl=True -XX:+UseZGC -Xmx1G -jar "$2".jar
exit 0
