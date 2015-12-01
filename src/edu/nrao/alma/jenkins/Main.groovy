// http://learnxinyminutes.com/docs/groovy/
// http://www.vogella.com/tutorials/Groovy/article.html
// eclipse: alt+shift+x g

package edu.nrao.alma.jenkins

import edu.nrao.alma.jenkins.tree.JobNode;

class Main {
	
	static void main(String... args) {
		JobNode jobNode = new JobNode()

		// os
		def os = "RHEL6"
		def arch = "x86_64"

		// scm
		def scmRoot = "https://alma-svn.aoc.nrao.edu/p2"
		def scmBranch = "trunk"
		def scmCredential = ""

		def subsystems = []

		JobNode icd = new JobNode()
		icd.moduleName = "ICD"
		icd.level = "00"
		subsystems << icd

		JobNode control = new JobNode()
		control.moduleName = "CONTROL"
		control.level = "01"
		subsystems << control

		JobNode corr = new JobNode()
		corr.moduleName = "CORR"
		corr.level = "05"
		subsystems << corr

		JobNode scheduling = new JobNode()
		scheduling.moduleName = "SCHEDULING"
		scheduling.level = "02"
		subsystems << scheduling

		JobNode ssr = new JobNode()
		ssr.moduleName = "SSR"
		ssr.level = "03"
		subsystems << ssr

		JobNode obsprep = new JobNode()
		obsprep.moduleName = "OBSPRER"
		obsprep.level = "06"
		subsystems << obsprep

		JobNode telcal = new JobNode()
		telcal.moduleName = "TELCAL"
		telcal.level = "04"
		subsystems << telcal

		// construct tree
		icd.childs << control
		control.childs << corr
		icd.childs << scheduling
		icd.childs << ssr
		ssr.childs << obsprep
		icd.childs << telcal

		//subsystems.each { println it.jobName }
		icd.childs.each { println it.jobName }
	}
}
