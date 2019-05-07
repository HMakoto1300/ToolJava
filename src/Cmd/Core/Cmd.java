package Cmd.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Cmd {
	private static final String regex = "\"[^\"]+\"|[^ ]+";
	private static final Pattern p = Pattern.compile(regex);

	public class CmdDto {
		public String originalCmd;
		public String command;
		public String[] args;
		public List<String> out;

		public CmdDto() {
			this.originalCmd = "";
			this.command = "";
			this.args = null;
			this.out=new ArrayList<String>();
		}
	}

	class CmdHis {
		private Integer index;
		private Map<Integer, CmdDto> his;

		public CmdHis() {
			this.index = 0;
			this.his = new HashMap<Integer, CmdDto>();
		}
		private Map<Integer,CmdDto> getHis(){
			return his;
		}

		private void put(CmdDto dto) {
			this.index += 1;
			this.his.put(this.index, dto);
		}

		private CmdDto get(Integer index) {
			return this.his.get(index);
		}
		
		private CmdDto get() {
			return this.his.get(this.index);
		}
		
		private Integer getIndex() {
			return this.index;
		}
		
	}

	private CmdHis cmdhis;

	public Cmd() {
		this.cmdhis = new CmdHis();
	}

	public boolean input(String originalCmd) {
		CmdDto dto = new CmdDto();
		dto.originalCmd = originalCmd;
		List<String> tmp = new ArrayList<String>();
		Matcher m = p.matcher(originalCmd);
		while (m.find()) {
			tmp.add(m.group().replaceAll("\"", ""));
		}
		if (!Objects.isNull(tmp) & tmp.size() > 0) {
			String command = tmp.get(0);
			List<String> list = tmp.subList(1, tmp.size());
			String[] args = list.toArray(new String[list.size()]);
			if (!Objects.isNull(command) && !command.equals("")) {

				dto.command = command;
				dto.args = args;
				this.cmdhis.put(dto);
				return true;
			}
		}
		return false;
	}
	
	public CmdDto get() {
		return cmdhis.get();
	}
	public CmdDto get(Integer index) {
		return cmdhis.get(index);
	}
	public Integer getIndex() {
		return cmdhis.getIndex();
	}
	
	public void show() {
		if(!Objects.isNull(this.cmdhis.get().out)) {
			for(String str : this.cmdhis.get().out) {
				System.out.println(str);
			}
		}
	}

//	public Map<Integer,CmdDto> getHis(){
//		return this.cmdhis.getHis();
//	}

}
				
			
