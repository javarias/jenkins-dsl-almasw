package edu.nrao.alma.jenkins.tree

class JobNode {
	
	// edges
	def parent = null
	def childs = []
	
	// job
	String moduleName
	String level
	
	// os
	String os = "RHEL6"
	String arch = "x86_64"
	
	// scm
	String scmRoot = "https://alma-svn.aoc.nrao.edu/p2"
	String scmBranch = "trunk"
	String scmCredential = ""
	
	def getJobName() {
		"${os}-${arch}-${scmBranch.toUpperCase()}-${level}-${moduleName}"
	}

}
