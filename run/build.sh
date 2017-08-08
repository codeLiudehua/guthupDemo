
##��һ�������ǲ�Ʒ�汾(Ĭ����master)
## build             (Ĭ��Ϊ build master)
## build master

if [ $# == 1 ]; then
	branch=$1
else
	branch=master
fi

basedir=$(cd "$(dirname "$0")"; pwd)
echo $basedir
cd $basedir
cd ..

mvn clean
git pull https://github.com/codeLiudehua/guthupDemo.git  ${branch}
mvn package

installTime=`date +%Y%m%d-%H:%M`

installDir=./release/$installTime
if [ -d $installDir ];then
	rm -r $installDir
fi
mkdir -p $installDir

cp $basedir/../target/*.jar $installDir
cp -r $basedir/../target/classes/* $installDir

runDir=./guthupDemo
if [ -h $runDir ];then
	rm $runDir
fi

ln -s $installDir $runDir

