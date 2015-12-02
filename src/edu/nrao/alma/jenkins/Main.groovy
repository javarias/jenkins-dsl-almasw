// http://learnxinyminutes.com/docs/groovy/
// http://www.vogella.com/tutorials/Groovy/article.html
// eclipse: alt+shift+x g

package edu.nrao.alma.jenkins

import edu.nrao.alma.jenkins.tree.JobNode
import edu.nrao.alma.jenkins.utils.Configuration
import edu.nrao.alma.jenkins.utils.Generation;

class Main {

	static void main(String... args) {

		Configuration configuration = new Configuration()

		JobNode icd = new JobNode(configuration: configuration)
		icd.module = "ICD"
		icd.level = "00"

		JobNode control = new JobNode()
		control.module = "CONTROL"
		control.level = "01"
		control.configuration = configuration

		JobNode corr = new JobNode()
		corr.configuration = configuration
		corr.module = "CORR"
		corr.level = "05"

		JobNode scheduling = new JobNode()
		scheduling.configuration = configuration
		scheduling.module = "SCHEDULING"
		scheduling.level = "02"

		JobNode ssr = new JobNode()
		ssr.configuration = configuration
		ssr.module = "SSR"
		ssr.level = "03"

		JobNode obsprep = new JobNode()
		obsprep.configuration = configuration
		obsprep.module = "OBSPREP"
		obsprep.level = "06"

		JobNode telcal = new JobNode()
		telcal.configuration = configuration
		telcal.module = "TELCAL"
		telcal.level = "04"

		// construct tree
		icd.addChild(control)
		control.addChild(corr)
		icd.addChild(scheduling)
		icd.addChild(ssr)
		ssr.addChild(obsprep)
		icd.addChild(telcal)

		// subsystems.each { println it.jobName }
		// icd.childs.each { println it.name }

		//Generation.traverse(icd, true)
		def corrIntlist = Generation.intlist(corr)
		println corrIntlist
	}
}
