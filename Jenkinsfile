/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

def AGENT_LABEL = env.AGENT_LABEL ?: 'ubuntu'
def JDK_NAME = env.JDK_NAME ?: 'jdk_11_latest'

def MAVEN_PARAMS = "-B -e -fae -V -Dnoassembly -Dmaven.compiler.fork=true -Dsurefire.rerunFailingTestsCount=2"

pipeline {

    agent {
        label AGENT_LABEL
    }

    tools {
        jdk JDK_NAME
    }

    environment {
        MAVEN_SKIP_RC = true
    }

    options {
        buildDiscarder(
            logRotator(artifactNumToKeepStr: '5', numToKeepStr: '10')
        )
        disableConcurrentBuilds()
    }

    parameters {
        booleanParam(name: 'CLEAN', defaultValue: true, description: 'Perform the build in clean workspace')
    }

    stages {

        stage('Clean workspace') {
             when {
                 expression { params.CLEAN }
             }
             steps {
                 sh 'git clean -fdx'
           }
        }

        stage('Build & Deploy') {
            when {
                branch 'main'
            }
            steps {
                sh "./mvnw -U $MAVEN_PARAMS -Dmaven.test.skip.exec=true clean deploy"
            }
        }

        stage('Checks') {
            steps {
                sh "./mvnw $MAVEN_PARAMS -Psourcecheck -Dcheckstyle.failOnViolation=false checkstyle:check"
            }
        }

        stage('Code Quality Review') {
            steps {
                withCredentials([string(credentialsId: 'apache-camel-core', variable: 'SONAR_TOKEN')]) {
                    sh "./mvnw $MAVEN_PARAMS -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=apache -Dsonar.projectKey=apache_camel org.sonarsource.scanner.maven:sonar-maven-plugin:sonar"
                }
            }
        }

        stage('Test') {
            steps {
                sh "./mvnw $MAVEN_PARAMS -Dmaven.test.failure.ignore=true -Dcheckstyle.skip=true verify"
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                    junit allowEmptyResults: true, testResults: '**/target/failsafe-reports/*.xml'
                }
            }
        }

    }

    post {
        always {
            emailext(
                subject: '${DEFAULT_SUBJECT}',
                body: '${DEFAULT_CONTENT}',
                recipientProviders: [[$class: 'CulpritsRecipientProvider']]
            )
        }
    }
}

