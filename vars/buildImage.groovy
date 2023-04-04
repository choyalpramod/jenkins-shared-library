#!/usr/bin/env groovy

// import com.example.Docker

// def call(String imageName) {
//     return new Docker(this).buildDockerImage(imageName)
// }

def call() {
    echo "building the docker image..."
    echo "${params.VERSION}"
    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t choyalpramod/demo-app:jma-${params.VERSION} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push choyalpramod/demo-app:jma-${params.VERSION}"
    }
}
