package com.sdi.business.impl;

import com.sdi.business.Command;

public class CommandExecutor {
	public Object execute(Command command) {
			return command.execute();
	}
}
