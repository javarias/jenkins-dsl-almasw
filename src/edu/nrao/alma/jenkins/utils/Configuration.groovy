package edu.nrao.alma.jenkins.utils

class Configuration {
	
	def introot
	
	// os
	def os = "RHEL6"
	def arch = "x86_64"
	
	// scm
	def scm = "https://alma-svn.aoc.nrao.edu/p2"
	def branch = "trunk"
	def credential = ""
}
