package com.genfare.hcv.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.genfare.hcv.option.PostRideOption;
import com.genfare.hcv.optionsImpl.DeviceAuthOptImpl;
import com.genfare.hcv.optionsImpl.SetOptionsImpl;

public class BasicOptions {
	
	public static ArrayList<String> commands = new ArrayList<String>();
	
	public static void main(String[] args) {

		BasicOptions basicOptions = new BasicOptions();
		Options options = new Options();
		
		options.addOption("authenticate", false, "authenticate farebox");
		
		OptionBuilder.withArgName("env tenant environment");
		OptionBuilder.hasArgs(3);
		OptionBuilder.withValueSeparator(' ');
		OptionBuilder.withDescription("set environmet variables");
		Option opt = OptionBuilder.create("set");
		options.addOption(opt);
		
		OptionBuilder.withArgName("validate cardId");
		OptionBuilder.hasArgs(2);
		OptionBuilder.withValueSeparator(' ');
		OptionBuilder.withDescription("validate ridership");
		Option opt1 = OptionBuilder.create("hcv");
		options.addOption(opt1);
		
		OptionBuilder.withArgName("post ride");
		OptionBuilder.hasArgs(2);
		OptionBuilder.withValueSeparator(' ');
		OptionBuilder.withDescription("post ride details");
		Option opt2 = OptionBuilder.create("hcv");
		options.addOption(opt2);
		
		OptionBuilder.withArgName("record ride cardprintId");
		OptionBuilder.hasArgs(3);
		OptionBuilder.withValueSeparator(' ');
		OptionBuilder.withDescription("post record ridership details");
		Option opt3 = OptionBuilder.create("hcv");
		options.addOption(opt3);
		
		
		System.out.println("Usage : <command> <option> <arguments..>");
		for (;;) {
			try {
				System.out.println();
				if (args.length == 0)
					System.out.printf("hcv>");
				else {
					System.out.printf(args[0] + ">");
				}

				BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

				String args2 = null;
				try {
					args2 = inp.readLine();
					commands.add(args2);
					args2 = "-" + args2;
				} catch (IOException e) {

					e.printStackTrace();
					continue;
				}
				String[] args3 = args2.split(" ");
				CommandLine line = new BasicParser().parse(options, args3);
				String command = args3[0].toLowerCase();
				if (line.hasOption(command)) {
					basicOptions.getResponse(command, line);
				} else {
					System.out.println("Command not found");
				}

			} catch (Exception ex) {
				System.out.println("Error:" + ex.getMessage());
				continue;
			}
		}

	}
	public void getResponse(String option, CommandLine line) {
	
		DeviceAuthOptImpl deviceAuth;
		switch (option) {
			case "-authenticate":
				deviceAuth = new DeviceAuthOptImpl();
				System.out.println(deviceAuth.authenticate());
				break;
			case "-set":
				SetOptionsImpl setOptions = new SetOptionsImpl();
				setOptions.settingEnv(line);
				break;
			case "-hcv":
				PostRideOption  pr = new PostRideOption();
				pr.rideOption(line); //  0000004548
				break;
			case "-exit":
				System.exit(0);
		}
	}
}
