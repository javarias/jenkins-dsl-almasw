package edu.nrao.alma.jenkins.utils

import edu.nrao.alma.jenkins.tree.JobNode;

//import javaposse.jobdsl.dsl.DslScriptLoader
//import javaposse.jobdsl.dsl.FileJobManagement
//import javaposse.jobdsl.dsl.GeneratedItems
//import javaposse.jobdsl.dsl.GeneratedJob
//import javaposse.jobdsl.dsl.GeneratedView
//import javaposse.jobdsl.dsl.ScriptRequest

import javaposse.jobdsl.dsl.Job


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
	
	// breath first pre order
	static def jobs(JobNode node, dsl) {
		
		def job = dsl.job(node.name) {
			// checkout strategy, clean
			scm {
				svn {
					location(node.scm) {
						directory(node.module)
						credentials(node.configuration.credential)
					}
				}
			}
			
			// Email Notification
			// Build other projects
			// Build periodically
			with {
				steps {
					downstreamParameterized {
						node.childs.each {
							trigger(it.name, "SUCCESS", true)
						}
					}
				}
			}
		}
		
		node.childs.each {
			jobs(it, dsl)
		}
	}
	
	static def view(Configuration configuration) {
		
	}
}
