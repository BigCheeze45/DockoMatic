/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modinfo;

/**
 *
 * @author wbullock
 */
public class ModInfo {

	private String outDir;
	private String sequence;
	private String template;

	public ModInfo(String dir, String seq, String tp){
		outDir = dir;
		sequence = seq;
		template = tp;
	}

	public void setDir(String dir){
		outDir = dir;
	}

	public void setSeq(String seq){
		sequence = seq;
	}

	public void setTmplt(String tp){
		template = tp;
	}

	public String getDir(){
		return outDir;
	}

	public String getSeq(){
		return sequence;
	}

	public String getTmplt(){
		return template;
	}


}
