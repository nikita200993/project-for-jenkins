set -e

script_dir=$(dirname "$0")
pid_file="$script_dir"/pid
jar_file="$script_dir"/app.jar

if [ -f "$pid_file" ]; then
  childPid=$(cat "$pid_file")
  if ps -p "$childPid" >/dev/null; then
      kill "$childPid"
  fi
fi

java -jar "$jar_file" 9999 &
echo "$!" > "$pid_file"
