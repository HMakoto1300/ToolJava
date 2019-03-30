package Todo.Cmd;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Todo.Util;

public class CmdLine {
	private CmdDto dto;
	private Integer index;
	private Map<Integer, CmdDto> comHis;

	private Command command;
	private static final int forRepresentetion = 1;
	private static final String regex = "\"[^\"]+\"|[^ ]+";
	private static final Pattern p = Pattern.compile(regex);

	private static final String PACKAGE_SEPARATOR = ".";
	private static final String CLASS_SUFFIX = ".class";
	private static final String PATH = "Todo.Individual";

	private static final int numberOfretention = 10;
	private static final List<String> nonCountCommandList = Arrays.asList("d", "show");

	public CmdLine(Command command) {
		this.command = command;

		dto = new CmdDto();
		index = 0;
		comHis = new HashMap<Integer, CmdDto>();
	}

	public boolean set(String str) {
		List<String> tmp = new ArrayList<String>();
		Matcher m = p.matcher(str);

		while (m.find()) {
			tmp.add(m.group().replaceAll("\"", ""));
		}
		if (!Objects.isNull(tmp) & tmp.size() > 0) {
			String command = tmp.get(0);
			List<String> list = tmp.subList(1, tmp.size());
			String[] args = list.toArray(new String[list.size()]);
			if (!Objects.isNull(command) && !command.equals("")) {

				CmdDto ndto = new CmdDto();
				ndto.originalcommand = str;
				ndto.command = command;
				ndto.args = args;
				this.dto = ndto;
				addComandHistory();

				return true;
			}
		}

		return false;
	}

	private void addComandHistory() {
		if (!nonCountCommandList.contains(this.dto.command)) {
			this.comHis.put(this.index, this.dto);
			this.index += 1;
		}
	}

	public void exe() throws CommandErr {
		Method n = null;
		Object[] dd = { this.dto.args };
		int primetype = 0;
		
		try {
			n = this.getClass().getMethod(this.dto.command, this.dto.args.getClass());
			primetype = 1;
		} catch (NoSuchMethodException e) {
			try {
				n = this.command.getClass().getMethod(this.dto.command, this.dto.args.getClass());
				primetype = 2;

			} catch (NoSuchMethodException e1) {
				throw new CommandErr("can not find the command inputted.");
			} catch (SecurityException e1) {
				throw new CommandErr("Unknown Err");
			}
		} catch (SecurityException e) {
			throw new CommandErr("Unknown Err");
		}
		
		try {
			if(primetype==1) {
				Object r = n.invoke(this, dd);
			}else if(primetype==2) {
				Object l = n.invoke(this.command, dd);
			}else {
				
			}
		} catch (IllegalAccessException e) {
			//Util.print("Unknown Err");
		} catch (IllegalArgumentException e) {
			throw new CommandErr("Wrong parameters.");
		} catch (InvocationTargetException e) {
			//Util.print("Unknown Err");
		}
	}

	public void show(String[] args) {
		String str = "";
		int start = this.comHis.size() - numberOfretention < 0 ? 0 : this.comHis.size() - numberOfretention;
		for (int i = start; i < this.comHis.size(); i++) {
			Integer j = this.comHis.size() - i - 1;
			str = str + "[" + j.toString() + "]" + this.comHis.get(i).originalcommand;
			if (j % forRepresentetion == 0) {
				str = str + "\r\n";
			}
		}
		Util.print(str);
	}

	public void d(String[] args) throws CommandErr{
		try {
			int j = Integer.parseInt(args[0]);
			int i = this.index - j - 1;
			this.dto = this.comHis.get(i);
		} catch (NumberFormatException e) {
			throw new CommandErr("It needs parameter as Number conducted.");
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CommandErr("It needs parameter as Number conducted.");
		}
		this.exe();
	}

	public void ch(String[] args) throws CommandErr {
		Class<?> c = null;
		try {
			c = Class.forName(args[0]);
		} catch (ClassNotFoundException e) {
			throw new CommandErr("It can not found out the class inputted.");
		}
			
		try {
			Object myObj = c.newInstance();
			this.command = (Command) myObj;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CommandErr("Unknown Err.");
		}
	}

	
	public void chshow(String[] args) throws CommandErr  {
		String rootPackageName = PATH.replace(PACKAGE_SEPARATOR, File.separator);
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Enumeration<URL> rootUrls;
		try {
			rootUrls = classLoader.getResources(rootPackageName);
		} catch (IOException e) {
			throw new CommandErr("Unknown Err.");
		}

		PriorityQueue<String> classNames = new PriorityQueue();
		while (rootUrls.hasMoreElements()) {
			URL rootUrl = rootUrls.nextElement();
			Path rootPath;
			try {
				rootPath = Paths.get(rootUrl.toURI());
			} catch (URISyntaxException e) {
				throw new CommandErr("Unknown Err.");
			}

			try {
				Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
						String pathName = path.toString();
						if (pathName.endsWith(CLASS_SUFFIX)) {
							int beginIndex = pathName.lastIndexOf(rootPackageName);
							int endIndex = pathName.lastIndexOf(CLASS_SUFFIX);
							String className = pathName.substring(beginIndex, endIndex).replace(File.separator,
									PACKAGE_SEPARATOR);

							classNames.add(className);
						}

						return super.visitFile(path, attrs);
					}
				});
			} catch (IOException e) {
				throw new CommandErr("Unknown Err.");
			}
		}
		String str=null;
		for (String className : classNames) {
			str = str + className;
		}
		Util.print(str);
	}
}
	
