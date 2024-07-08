#!/bin/bash

# Change directory to where Java files are located
cd "$(dirname "$0")"

# Compile Java files into NHF/bin directory with UTF-8 encoding
javac -d NHF/bin -encoding UTF-8 NHF/src/NHF/*.java

# Check if compilation was successful
if [ $? -ne 0 ]; then
    echo "Compilation failed. Press Enter to exit..."
    read
    exit 1
fi

# Run the Java program from NHF/bin directory
java -cp NHF/bin NHF.main

# Wait for user input before closing the terminal window
echo "Press Enter to exit..."
read