package edu.nrao.alma.jenkins.tree

class JobNode {
	
	// edges
	def parent = null
	def childs = []

	def module
	def level
	def configuration
	
	def getJobName() {
		"${configuration.os}-${configuration.arch}-${configuration.branch.toUpperCase()}-${level}-${module}"
	}
	
	def addChild(child) {
		child.parent = this
		childs << child
	}
}
