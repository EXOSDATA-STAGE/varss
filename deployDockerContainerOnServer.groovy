def call() {
   withCredentials([usernamePassword(credentialsId: 'harbor-credentials', usernameVariable: 'HARBOR_USERNAME', passwordVariable: 'HARBOR_PASSWORD')]) {
        withCredentials([usernamePassword(credentialsId: 'server-credentials', usernameVariable: 'SERVER_USERNAME', passwordVariable: 'SERVER_PASSWORD')]) {
            sh """
            sshpass -p '${SERVER_PASSWORD}' ssh ${SERVER_USERNAME}@${SERVER_IP} '
                  echo ${HARBOR_PASSWORD} | docker login --username ${HARBOR_USERNAME} --password-stdin ${DOCKER_REGISTRY}'
            sshpass -p '${SERVER_PASSWORD}' ssh ${SERVER_USERNAME}@${SERVER_IP} 'docker stop mycontainer || true'
            sshpass -p '${SERVER_PASSWORD}' ssh ${SERVER_USERNAME}@${SERVER_IP} 'docker rm mycontainer || true'
            sshpass -p '${SERVER_PASSWORD}' ssh ${SERVER_USERNAME}@${SERVER_IP} 'docker pull ${DOCKER_REGISTRY}/${IMAGE_NAME}/repository:${IMAGE_TAG}'
            sshpass -p '${SERVER_PASSWORD}' ssh ${SERVER_USERNAME}@${SERVER_IP} 'docker run -d -p 8085:8080 --name mycontainer ${DOCKER_REGISTRY}/${IMAGE_NAME}/repository:${IMAGE_TAG}'
            """
    }}}
