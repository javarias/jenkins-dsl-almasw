// http://learnxinyminutes.com/docs/groovy/
// http://www.vogella.com/tutorials/Groovy/article.html
// eclipse: alt+shift+x g

package edu.nrao.alma.jenkins

import edu.nrao.alma.jenkins.tree.JobNode;
import edu.nrao.alma.jenkins.utils.Configuration;

class Main {
	
	static void main(String... args) {
		
		JobNode jobNode = new JobNode()

		def subsystems = []
		
		Configuration configuration = new Configuration()
		// os
		configuration.os = "RHEL6"
		configuration.arch = "x86_64"
		// scm
		configuration.scm = "https://alma-svn.aoc.nrao.edu/p2"
		configuration.branch = "trunk"
		configuration.credential = ""

		JobNode icd = new JobNode(configuration: configuration)
		icd.module = "ICD"
		icd.level = "00"
		subsystems << icd

		JobNode control = new JobNode(configuration: configuration)
		control.module = "CONTROL"
		control.level = "01"
		subsystems << control

		JobNode corr = new JobNode(configuration: configuration)
		corr.module = "CORR"
		corr.level = "05"
		subsystems << corr

		JobNode scheduling = new JobNode(configuration: configuration)
		scheduling.module = "SCHEDULING"
		scheduling.level = "02"
		subsystems << scheduling

		JobNode ssr = new JobNode(configuration: configuration)
		ssr.module = "SSR"
		ssr.level = "03"
		subsystems << ssr

		JobNode obsprep = new JobNode(configuration: configuration)
		obsprep.module = "OBSPRER"
		obsprep.level = "06"
		subsystems << obsprep

		JobNode telcal = new JobNode(configuration: configuration)
		telcal.module = "TELCAL"
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
