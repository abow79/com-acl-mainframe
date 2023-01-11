#!/bin/sh

## This is a sample pipeline shell.
# please change global variables to meet your project settings.
#  environment variables:
#    DEV_ENV:
#      fstop: to run pipeline steps in fstop environment
#      fcb: to run pipeline steps in fcb environment
#  arguments:
#    fstop: to run pipeline steps in fstop environment
#    fcb: to run pipeline steps in fcb environment

CWD=`pwd`

GIT_USER_NAME="tapuser"
GIT_USER_EMAIL="tapuser@fstop.com.tw"

# fstop
SONAR_URL_FSTOP=http://10.109.110.203:9000
SONAR_TOKEN_FSTOP=sqp_198386f10b41d845efdc239c3c6218fb3a75f12a

# fcb
SONAR_URL_FCB=http://10.109.110.203:9000
SONAR_TOKEN_FCB=sqp_3cfc01fae877c72233e017602caad11efdc3a20a

# modify
SONAR_PRJ_NAME=com-gateway-example
SONAR_PRJ_KEY=com-gateway-example

####
get_source() {
  echo $CWD

  #cd $(mktemp -d)

  pwd

  #wget -qO- $(params.source-url) | tar xvz -m
}

fcb() {

  #/usr/bin/mvn test --settings=/workspace/.m2/settings.xml -Pfstop
  #git config --global user.name $GIT_USER_NAME
  #git config --global user.email $GIT_USER_EMAIL
  #mvn --settings /workspace/.m2/settings.xml -Pfstop clean verify site site:stage scm-publish:publish-scm

  mvn sonar:sonar \
  -Dsonar.projectKey=$SONAR_PRJ_KEY \
  -Dsonar.projectName=$SONAR_PRJ_NAME \
  -Dsonar.login=$SONAR_TOKEN_FCB \
  -Dsonar.host.url=$SONAR_URL_FCB \
  -Dmaven.wagon.http.ssl.insecure=true \
  -Dmaven.wagon.http.ssl.allowall=true \
  -Dmaven.wagon.http.ssl.ignore.validity.dates=true
}

fstop() {

  #/usr/bin/mvn test --settings=/workspace/.m2/settings.xml -Pfstop

  git config --global user.name $GIT_USER_NAME
  git config --global user.email $GIT_USER_EMAIL
  mvn -P fstop,devsys --settings /workspace/.m2/settings.xml clean verify site site:stage scm-publish:publish-scm

  mvn -P fstop,devsys sonar:sonar \
  -Dsonar.projectKey=$SONAR_PRJ_KEY \
  -Dsonar.projectName=$SONAR_PRJ_NAME \
  -Dsonar.login=$SONAR_TOKEN_FSTOP \
  -Dsonar.host.url=$SONAR_URL_FSTOP \
  -Dmaven.wagon.http.ssl.insecure=true \
  -Dmaven.wagon.http.ssl.allowall=true \
  -Dmaven.wagon.http.ssl.ignore.validity.dates=true

}

back_cwd() {
  echo $CWD
  #cd $CWD
}

##### main
case "$1" in
fcb)
        get_source
        fcb
        back_cwd
        ;;

fstop)
        get_source
        fstop
        back_cwd
        ;;
		
*)

  case "${DEV_ENV}" in
  fcb)
        echo "DEV_ENV is assigned to ${DEV_ENV}"
        get_source
        fcb
        back_cwd
        ;;
  fstop)
        echo "DEV_ENV is assigned to ${DEV_ENV}"
        get_source
        fstop
        back_cwd
        ;;
  *)      
    echo $"Usage: $0 {fcb|fstop}"
    exit 1        
  esac
  
esac