package edu.nrao.alma.jenkins.tree

import edu.nrao.alma.jenkins.utils.Configuration;

class JobNode {
	
	// edges
	def parent = null
	def childs = []

	def module
	def level
	def configuration
	
	def getName() {
		"${configuration.os}-${configuration.arch}-${configuration.branch.toUpperCase()}-${level}-${module}"
	}
	
	def getIntroot() {
		"${configuration.intlist}/${name}-b${configuration.JENKINS_BUILD_NUMBER}"
	}
	
	def getScm() {
		"${configuration.scm}/${configuration.branch}/${module}"
	}
	
	def addChild(child) {
		child.parent = this
		childs << child
	}
}