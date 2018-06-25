package com.qihai.commerce.framework.fastdfs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qihai.commerce.framework.fastdfs.FastDFSClient;
import com.qihai.commerce.framework.fastdfs.FastDFSFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class UploadController {
	private static Logger logger = LoggerFactory.getLogger(UploadController.class);

	@GetMapping("/")
	public String index() {
		return "upload";
	}

	@PostMapping("/upload") // new annotation since 4.3
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
		try {
			// Get the file and save it somewhere
			String path = saveFile(file);//一种调用方式
			//String path = saveFile1(file);//另一种调用方式
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");
			redirectAttributes.addFlashAttribute("path", "file path url '" + path + "'");
		} catch (Exception e) {
			logger.error("upload file failed", e);
		}
		return "redirect:/uploadStatus";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}

	/**
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	public String saveFile(MultipartFile multipartFile) throws IOException {
		String[] fileAbsolutePath = {};
		String fileName = multipartFile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		byte[] file_buff = null;
		InputStream inputStream = multipartFile.getInputStream();
		if (inputStream != null) {
			int len1 = inputStream.available();
			file_buff = new byte[len1];
			inputStream.read(file_buff);
		}
		inputStream.close();
		FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
		try {
			fileAbsolutePath = FastDFSClient.upload(file); // upload to fastdfs
		} catch (Exception e) {
			logger.error("upload file Exception!", e);
		}
		if (fileAbsolutePath == null) {
			logger.error("upload file failed,please upload again!");
		}
		String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
		return path;
	}
	/**
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	public String saveFile1(MultipartFile multipartFile) throws IOException {
		String fileAbsolutePath = null;
		String fileName = multipartFile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		try {
			fileAbsolutePath = FastDFSClient.upload(multipartFile.getBytes(), ext);
		} catch (Exception e) {
			logger.error("upload file Exception!", e);
		}
		if (fileAbsolutePath == null) {
			logger.error("upload file failed,please upload again!");
		}
		String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath;
		return path;
	}
}