#!/usr/bin/env bash

export JAVA_HOME=${JAVA_HOME}
echo $JAVA_HOME
baseDir=$(cd "$(dirname "$0")"; pwd)

if [ $# == 3 ]; then
    scheme=$1
    yesterday=$2
    featureIndPathDay=$3
else
    scheme=readingList
    yesterday=`date +%Y%m%d -d "-1days"`
    featureIndPathDay=`date +%Y%m%d -d "-1days"`
fi

basePath=/ntes_weblog/userProfile/gender/$scheme

neededNormalization=true
echo $basePath
echo $yesterday
echo $scheme

HADOOP=${HADOOP}

dataPath=$basePath/source_train_data/$yesterday
featureIndSavePath=$basePath/featureInd/$featureIndPathDay
mutualInfoPath=$basePath/mutualInfo/$yesterday
libsvmDataSavePath=$basePath/libsvm/$yesterday
littleFeatureDataPath=$basePath/little_feature_data_train/$yesterday
noFilterDataPath=$basePath/all_data/$yesterday
chiSqSelectPath=$basePath/chiSqSelect/$yesterday
standardMeanPath=$basePath/stdMean/$yesterday
denoiseLibsvmDataPath=$basePath/libsvm_denoise/$yesterday
normalizationLibsvmDataPath=$basePath/normalization_libsvm/$yesterday
dataTrainPath=$basePath/libsvm_train/$yesterday
dataTestPath=$basePath/libsvm_test/$yesterday

dataTestLittlePath=$basePath/libsvm_test_little/$yesterday
dataTestFilterPath=$basePath/libsvm_test_filter/$yesterday

dataGenderNumPath=$basePath/gender_num/$yesterday
dataOverSampleSavePath=$basePath/over_sample/$yesterday
modelBakSavePath=$basePath/model-bak/$yesterday
modelSavePath=$basePath/model/$yesterday
modelBayesSavePath=$basePath/model_bayes/$yesterday




FilterFeatureNum=4

featureTotalNum=`$HADOOP fs -text $featureIndSavePath/p* |wc -l`



echo "CalcGenderNum"
sh $baseDir/runSparkJob.sh com.netease.spark.demo.CalcGenderNum $dataTrainPath/moreFeatureNorm $dataGenderNumPath
