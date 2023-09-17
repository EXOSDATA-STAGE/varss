def call() {
     sh '/opt/apache-maven-3.6.3/bin/mvn dependency:tree'
     sh '/opt/apache-maven-3.6.3/bin/mvn package'
     sh '/opt/apache-maven-3.6.3/bin/mvn clean deploy '
}
