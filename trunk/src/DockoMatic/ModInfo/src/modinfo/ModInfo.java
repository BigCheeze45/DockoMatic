# Program: "DockoMatic"
# Project: "DNA Safeguard"
# Filename: "dockOmatic.pl"
#
# Dr. Tim Andersen
# Department of Computer Science
# College of Engineering
# Boise State University
#
# Original Author(s): "Casey Bullock"
#
# Last Modified
#   Date: "June 14, 2010"
#
#
#  This file is part of DockoMatic.
#
#  DockoMatic is free software: you can redistribute it and/or modify
#  it under the terms of the GNU Lesser General Public License as published by
#  the Free Software Foundation, either version 3 of the License, or
#  (at your option) any later version.
#
#  DockoMatic is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU Lesser General Public License for more details.
#
#  You should have received a copy of the GNU Lesser General Public License
#  along with DockoMatic.  If not, see <http://www.gnu.org/licenses/>.
#

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
