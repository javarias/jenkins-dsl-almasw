package edu.nrao.alma.jenkins.utils

class Configuration {

	static final enum BUILD {
		STABLE,
		LATEST,
	}
	
	// https://wiki.jenkins-ci.org/display/JENKINS/Building+a+software+project

	// jenkins envars
	static final def BUILD_NUMBER = "\${BUILD_NUMBER}"
	static final def BUILD_ID = "\${BUILD_ID}"
	static final def BUILD_URL = "\${BUILD_URL}"
	static final def NODE_NAME = "\${NODE_NAME}"
	static final def JOB_NAME = "\${JOB_NAME}"
	static final def BUILD_TAG = "\${BUILD_TAG}"
	static final def JENKINS_URL = "\${JENKINS_URL}"
	static final def EXECUTOR_NUMBER = "\${EXECUTOR_NUMBER}"
	static final def JAVA_HOME = "\${JAVA_HOME}"
	static final def WORKSPACE = "\${WORKSPACE}"
	static final def SVN_REVISION = "\${SVN_REVISION}"

	// not used
	static final def CVS_BRANCH	= "\${CVS_BRANCH}"
	static final def GIT_COMMIT	= "\${GIT_COMMIT}"
	static final def GIT_URL = "\${GIT_URL}"
	static final def GIT_BRANCH	= "\${GIT_BRANCH}"

	def intlist = "/var/lib/jenkins/intlist"

	// os
	def os = "RHEL6"
	def arch = "x86_64"

	// scm
	def scm = "https://alma-svn.aoc.nrao.edu/p2"
	def branch = "trunk"
	def credential = ""
}
