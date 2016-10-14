package com.filehandling.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.spi.FileSystemProvider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@RequestMapping(value="/uploadFile" ,method=RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("name")String name,@RequestParam("file") MultipartFile file)
	{
		
		if(!file.isEmpty())
		{
			try{
				
			byte[]	bytes=file.getBytes();
			
			//Creating the direcorty to Strore File
				
		String rootPath=System.getProperty("catlina.home");
			//String rootPath="D:";	
			
			File dir=new File(rootPath+File.separator+"tmpfile");
			
			if(!dir.exists())
			{
				
				dir.mkdirs();
			}
				
			//create File on Server
			
			File serverFile=new File(dir.getAbsolutePath()+File.separator+name);
			
			BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(serverFile));
			
			stream.write(bytes);
			stream.close();
			
			return "You have successfully Uploaded File"+name;
			
			
			}
			catch(Exception ex){
			
				return "You Failed To upload File "+name+" because "+ex.getMessage();
			
			}
		}
		else{
			return "You Failed To upload File "+name+" because File is empty ";
			
		}		
		
		
	
	
	}
	
	
	
	
	

	
		
		
		
		
		
		
		
	@RequestMapping(value="/uploadMultipleFile",method=RequestMethod.POST)
	public @ResponseBody String uplopadMultipleFileHandler(@RequestParam("name") String names[],@RequestParam("file")MultipartFile [] files)
	{
		
	
		if(files.length!=names.length)
			
			return "Mandatroy information misssing";
	
		     String message="";
	
	for (int i=0;i<files.length;i++)
	{
		
		MultipartFile file=files[i];
		
	     String name=names[i];
		try{
			
		byte [] bytes=file.getBytes();
			
			String rootPath=System.getProperty("catlina.home");
		
			File dir =new File(rootPath+File.separator+"tmpFiles");
			
			
			if(!dir.exists())
			
			dir.mkdirs();	
			
			//Create  the File on Server
			
			File serverFile=new File(dir.getAbsolutePath()+File.separator+name);
			
			BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(serverFile));
			
			stream.write(bytes);
			
			stream.close();
			
			
			message=message+"You Successfully uploaded file="+name+" ";
			
		}
		catch(Exception ex){
		
			return "You failed to upload "+name+"=>"+ex.getMessage();
		}
	}
	
	return message;

	}	
	
	
	
	
}
	

