#!/usr/bin/env bash
set -euo pipefail

rm *.jfr || true
mvn clean compile

counts=( 1 100 100000 1000000 )

for count in "${counts[@]}"
do
    echo "Running ${count} jobs"
    java -p target/classes \
        --enable-preview \
        -XX:+FlightRecorder -XX:StartFlightRecording=duration=200s,filename=conventional-${count}.jfr \
        -m loom.playground/it.mulders.loom.playground.ThreadedApp ${count}

    java -p target/classes \
        --enable-preview \
        -XX:+FlightRecorder -XX:StartFlightRecording=duration=200s,filename=virtual-${count}.jfr \
        -m loom.playground/it.mulders.loom.playground.LoomApp ${count}
done

for recording in *.jfr
do
    echo Processing ${recording}
    $JAVA_HOME/bin/jfr print --events 'jdk.ThreadStart' --json ${recording} | jq '.recording.events | length'
done
