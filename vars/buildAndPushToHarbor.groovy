def call() {
    sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
    sh "docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${DOCKER_REGISTRY}/${IMAGE_NAME}/repository:${IMAGE_TAG}"
    withCredentials([usernamePassword(credentialsId: 'harbor-credentials', usernameVariable: 'HARBOR_USERNAME', passwordVariable: 'HARBOR_PASSWORD')]) {
        sh "echo $HARBOR_PASSWORD | docker login --username $HARBOR_USERNAME --password-stdin $DOCKER_REGISTRY"
        sh "docker push ${DOCKER_REGISTRY}/${IMAGE_NAME}/repository:${IMAGE_TAG}"
    }}
