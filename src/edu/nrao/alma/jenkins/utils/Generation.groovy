package edu.nrao.alma.jenkins.utils

import edu.nrao.alma.jenkins.tree.JobNode;

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
	
	static def jobs(JobNode node) {
		def job = freeStyleJob() {
			scm {
				svn {
					location(node.scm) {
						directory(mode.module)
						credentials(scm.credentials)
					}
				}
			}
		}
		
		job.with {
			steps {
				downStreamParameterized {
					node.childs.each {
						trigger(it.name, "SUCCESS", true)
					}
				}
			}
		}
	}
	
	static def view(Configuration configuration) {
		
	}
}
