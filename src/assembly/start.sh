#!/bin/bash

DEPLOY_DIR="/home/pi/deploy/Trick17App/"

JAR_NAME="Trick17App"
SERVICE_NAME="trick17app"

SERVICE_FILE="/etc/systemd/system/${SERVICE_NAME}.service"
TEMPLATE_FILE="${DEPLOY_DIR}/${JAR_NAME}/autostart/trick17app.service.template"

#echo "Installing Trick17 autostart service..."

if [ -f "$TEMPLATE_FILE" ]; then
    sudo cp "$TEMPLATE_FILE" "$SERVICE_FILE"
    sudo systemctl daemon-reload
    sudo systemctl enable "$SERVICE_NAME"
#    echo "Service installed and enabled to start on boot."
#else
#    echo "ERROR: Service template not found at $TEMPLATE_FILE"
fi

sudo systemctl start "$SERVICE_NAME"