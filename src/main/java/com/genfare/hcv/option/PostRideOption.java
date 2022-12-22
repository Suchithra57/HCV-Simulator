package com.genfare.hcv.option;

import org.apache.commons.cli.CommandLine;

import com.genfare.hcv.optionsImpl.PostRideOptionImpl;


public class PostRideOption {
	public void rideOption(CommandLine line)
	{
		String[] arguments = line.getOptionValues("hcv");
		
		PostRideOptionImpl postRideOptionImpl = new PostRideOptionImpl();
		postRideOptionImpl.process(arguments);
	}
}
