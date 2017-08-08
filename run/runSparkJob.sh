#!/usr/bin/env bash
if [ "$1" != "" ];then
    clazz=$1
    shift
fi

${SPARK_HOME}/bin/spark-submit \
    --class $clazz \
    --master yarn \
    --driver-memory 8g \
    --executor-memory 8g \
    --num-executors 32\
    ${JAR} \
    $@