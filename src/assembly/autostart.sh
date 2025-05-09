# Check if Java is installed
if type java; then
    echo "Java found in PATH."
elif [[ -x "usr/bin/java" ]]; then
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

if which unclutter; then
    echo "Unclutter found."
else
    echo "Unclutter not found. Installing it now"
    sudo apt install -y unclutter
fi

unclutter -display :0 -idle 0 &
DISPLAY=:0
XAUTHORITY=/home/pi/.Xauthority

sudo systemctl stop lightdm && sudo systemctl disable lightdm
cd /home/pi/deploy/Trick17App/
/usr/bin/java -Dsun.java2d.opengl=True -XX:+UseZGC -Xmx2G -jar /home/pi/deploy/Trick17App/Trick17App.jar
