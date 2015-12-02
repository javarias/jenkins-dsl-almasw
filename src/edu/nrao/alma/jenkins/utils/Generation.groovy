package edu.nrao.alma.jenkins.utils

import edu.nrao.alma.jenkins.tree.JobNode;

//import javaposse.jobdsl.dsl.DslScriptLoader
//import javaposse.jobdsl.dsl.FileJobManagement
//import javaposse.jobdsl.dsl.GeneratedItems
//import javaposse.jobdsl.dsl.GeneratedJob
//import javaposse.jobdsl.dsl.GeneratedView
//import javaposse.jobdsl.dsl.ScriptRequest


public class Generation {

	// deep first pre-order traverse
	static def traverse(JobNode node, out, root = false) {

		if(out != null) {
			out.println(node.name)
		}

		node.childs.each { traverse(it, out) }

		if(root) {
			node
		}
	}
	
	// go back to the root from the node
	static def intlist(JobNode node) {
		if(node.parent != null) {
			String intlist = intlist(node.parent)
			if(intlist != null && !intlist.isEmpty()) {
				node.parent.introot + ":" + intlist
			} else {
				node.parent.introot
			}
		}
	}
	
	static def jobs(JobNode node, dsl) {
		def job = dsl.job(node.name) {
			dsl.scm {
				dsl.svn {
					location(node.scm) {
						directory(mode.module)
						credentials(scm.credentials)
					}
				}
			}
		}
		
		job.with {
			dsl.steps {
				dsl.downStreamParameterized {
					node.childs.each {
						dsl.trigger(it.name, "SUCCESS", true)
					}
				}
			}
		}
	}
	
	static def view(Configuration configuration) {
		
	}
}
