#!/usr/bin/env sh

DIR="$(cd "$(dirname "$0")"; pwd)"

WRAPPER_JAR="$DIR/gradle/wrapper/gradle-wrapper.jar"

if [ ! -f "$WRAPPER_JAR" ]; then
  echo "Missing gradle-wrapper.jar"
  exit 1
fi

exec java -jar "$WRAPPER_JAR" "$@"
