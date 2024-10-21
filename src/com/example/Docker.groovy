#!/user/bin/env groovy
// test
package com.example

class Docker implements Serializable{
    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName) {
        script.echo "building the docker image..."
        script.sh "docker build -t  $imageName ."
        // vladibo/demo-app:jma-1.2
    }

    def buildDockerImageWithFilePath(String dockerfilePath, String contextDir, String imageName) {
        script.echo "building the docker image with file path..."
        script.sh "docker build -t ${imageName} -f ${dockerfilePath} ${contextDir}"
    }

    def dockerLogin() {
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin"
        }
    }

    def dockerPush(String imageName) {
        script.sh "docker push $imageName"
    }
}