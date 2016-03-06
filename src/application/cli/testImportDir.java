package application.cli;

import java.util.Scanner;

import backup.ImportSvmFile;

public class testImportDir {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Input Dir> ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		ImportSvmFile.getInstance().setImportFile(input);
		String result[][] = ImportSvmFile.getInstance().run();
		for (String s : result[0]){
			System.out.println(s);
		}
		for	(String s : result[1]){
			System.out.println(s);
		}
	}

}
