package com.schen.projectdevice.controller;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.schen.projectdevice.model.DataSheet;
import com.schen.projectdevice.model.FileEntity;
import com.schen.projectdevice.model.UserInfo;
import com.schen.projectdevice.model.Version;
import com.schen.projectdevice.service.DataSheetService;
import com.schen.projectdevice.service.FileService;
import com.schen.projectdevice.service.UserService;
import com.schen.projectdevice.service.VersionService;
import com.schen.projectdevice.util.DocConverter;
import com.schen.projectdevice.util.EmailSender;

@Controller
public class FileController {
	@Autowired
	private FileService fileService;

	@Autowired
	private DataSheetService dataSheetService;

	@Autowired
	private UserService userService;

	@Autowired
	VersionService versionService;

	private Set<String> dict = new HashSet<>(Arrays.asList("doc", "docx", "ppt", "pptx", "xls", "xlsx"));

	@GetMapping("/deleteFile")
	public String deletFile(@RequestParam("verId") int verId, @RequestParam("fileId") int fileId, ModelMap model) {
		fileService.deleteFileById(fileId);
		return "redirect:/file?verId=" + verId;
	}

	@GetMapping("/deleteDS")
	public String deletDataSheet(@RequestParam("fileId") int fileId, ModelMap model) {
		dataSheetService.deleteDataSheetById(fileId);
		return "redirect:/datasheet/all";
	}

	@GetMapping("/download")
	public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam("fileId") int id) {
		FileEntity file = fileService.getFileById(id);
		byte[] data = file.getFileContent();
		String name = file.getFileName();
		String type = file.getFileType();
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name)
				.contentType(getMediaType(type)).contentLength(data.length).body(resource);
	}

	@GetMapping("/downloadDS")
	public ResponseEntity<ByteArrayResource> downloadDataSheet(@RequestParam("fileId") int id) {
		DataSheet file = dataSheetService.getDataSheetById(id);
		byte[] data = file.getFileContent();
		String name = file.getFileName();
		String type = file.getFileType();
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name)
				.contentType(getMediaType(type)).contentLength(data.length).body(resource);
	}

	@GetMapping("/download/all")
	public ResponseEntity<ByteArrayResource> downloadAll(@RequestParam("verId") int id) throws IOException {
		List<FileEntity> files = fileService.getFilesByVerId(id);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		for (FileEntity file : files) {
			ZipEntry entry = new ZipEntry(file.getFileName());
			entry.setSize(file.getFileContent().length);
			zos.putNextEntry(entry);
			zos.write(file.getFileContent());
			zos.closeEntry();
		}
		zos.close();
		byte[] data = baos.toByteArray();
		String fileName = "documents.zip";
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
				.contentType(getMediaType("application/zip")).contentLength(data.length).body(resource);

	}

	@GetMapping("/email/all")
	public String emailAll(HttpServletRequest request, @RequestParam("verId") int id, Authentication authentication)
			throws IOException, MessagingException {
		if (authentication == null) {
			return "redirect:/logout";
		}
		authentication.getPrincipal();
		UserInfo user = userService.getUserByUserName(authentication.getName());
		Version version = versionService.getVersionById(id);
		List<FileEntity> files = fileService.getFilesByVerId(id);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		for (FileEntity file : files) {
			ZipEntry entry = new ZipEntry(file.getFileName());
			entry.setSize(file.getFileContent().length);
			zos.putNextEntry(entry);
			zos.write(file.getFileContent());
			zos.closeEntry();
		}
		zos.close();
		byte[] data = baos.toByteArray();
		String fileName = "documents.zip";
		ServletContext servletContext = request.getSession().getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
		String filePath = contextPath + File.separator + "temp" + File.separator + fileName;
		File tempFile = new File(filePath);
		System.out.println(tempFile.getAbsolutePath());
		FileOutputStream fos = new FileOutputStream(tempFile);
		fos.write(data);
		fos.close();
		EmailSender emailSender = new EmailSender();
		emailSender.setFileName(fileName);
		emailSender.setFilePath(filePath);
		emailSender.setEmailFilePath(contextPath + File.separator + "temp");
		emailSender.setSenderEmail("do-not-reply@chiponeusa.com");
		emailSender.setSenderName("Chipone Database");
		emailSender.setRecipient(user);
		emailSender.setSubject("All the file of " + version.getProject().getName() + "-" + version.getName());
		emailSender.setBody("The Attachment is the zip file of all " + version.getProject().getName() + "-"
				+ version.getName() + " document files");
		emailSender.generateEmail();
		return "redirect:/preview/email.eml";

	}

	private MediaType getMediaType(String fileType) {
		String type = fileType.substring(0, fileType.indexOf("/"));
		String subType = fileType.substring(fileType.indexOf("/") + 1, fileType.length());
		return new MediaType(type, subType);
	}

	@GetMapping("/preview")
	public String previewFile(HttpServletRequest request, @RequestParam("fileId") int id, ModelMap model) {
		FileEntity file = fileService.getFileById(id);
		String fileName = file.getFileName();
		// String fileType = file.getFileType();
		byte[] fileContent = file.getFileContent();
		ServletContext servletContext = request.getSession().getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
		String filePath = contextPath + File.separator + "temp" + File.separator + fileName;
		File tempFile = new File(filePath);
		System.out.println(tempFile.getAbsolutePath());

		try {
			FileOutputStream fos = new FileOutputStream(tempFile);
			fos.write(fileContent);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String url = request.getRequestURL().toString();
		/*
		 * if (dict.contains(suffix)) { String pdfFileName = fileName.substring(0,
		 * fileName.lastIndexOf(suffix)) + "pdf"; String pdfFilePath = contextPath +
		 * File.separator + "temp" + File.separator + pdfFileName; DocConverter
		 * docConverter = new DocConverter(); docConverter.office2pdf(filePath,
		 * pdfFilePath); fileName = pdfFileName; }
		 */
		// url = url.replaceAll("/preivew", "/ViewerJS#../preview");
		url = url + "/" + fileName;
		System.out.println(url);
		return "redirect:" + url;
	}

	@GetMapping("/previewDS")
	public String previewDataSheet(HttpServletRequest request, @RequestParam("fileId") int id, ModelMap model) {
		DataSheet file = dataSheetService.getDataSheetById(id);
		String fileName = file.getFileName();
		// String fileType = file.getFileType();
		byte[] fileContent = file.getFileContent();
		ServletContext servletContext = request.getSession().getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
		String filePath = contextPath + File.separator + "temp" + File.separator + fileName;
		File tempFile = new File(filePath);
		System.out.println(tempFile.getAbsolutePath());

		try {
			FileOutputStream fos = new FileOutputStream(tempFile);
			fos.write(fileContent);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String url = request.getRequestURL().toString();
		url = url.replaceFirst("/previewDS", "/preview");
		/*
		 * if (dict.contains(suffix)) { String pdfFileName = fileName.substring(0,
		 * fileName.lastIndexOf(suffix)) + "pdf"; String pdfFilePath = contextPath +
		 * File.separator + "temp" + File.separator + pdfFileName; DocConverter
		 * docConverter = new DocConverter(); docConverter.office2pdf(filePath,
		 * pdfFilePath); fileName = pdfFileName; }
		 */
		url = url + "/" + fileName;
		System.out.println(url);
		return "redirect:" + url;
	}
	/*
	 * @GetMapping("/email") public String emailFile(HttpServletRequest
	 * request, @RequestParam("fileId") int id, ModelMap model) { FileEntity file =
	 * fileService.getFileById(id); String fileName = file.getFileName(); byte[]
	 * fileContent = file.getFileContent(); ServletContext servletContext =
	 * request.getSession().getServletContext(); String contextPath =
	 * servletContext.getRealPath(File.separator); String filePath = contextPath +
	 * File.separator + "temp" + File.separator + fileName; File tempFile = new
	 * File(filePath);
	 * 
	 * try { FileOutputStream fos = new FileOutputStream(tempFile);
	 * fos.write(fileContent); fos.close(); } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } filePath.replaceAll(" ",
	 * "%20"); filePath.replace('\\', '/'); filePath = "file:///" + filePath;
	 * //Desktop desktop = Desktop.getDesktop(); String url = ""; //URI mailTo; url
	 * = "mailTo:" + "?subject=" + enc("Project File: ") + enc(fileName) + "&body="
	 * + enc("Attachement is the file.") + "&attachment="+ enc(filePath);
	 * System.out.println(url); return "redirect:" + url; } private String
	 * enc(String p) { if (p == null) p = ""; try { return URLEncoder.encode(p,
	 * "UTF-8").replace("+", "%20"); } catch (UnsupportedEncodingException e) {
	 * throw new RuntimeException(); } }
	 */

	@GetMapping("/emailDS")
	public String emailDataSheet(HttpServletRequest request, @RequestParam("fileId") int id,
			Authentication authentication, ModelMap model) throws MessagingException, IOException {
		if (authentication == null) {
			return "redirect:/logout";
		}
		authentication.getPrincipal();
		UserInfo user = userService.getUserByUserName(authentication.getName());
		DataSheet file = dataSheetService.getDataSheetById(id);
		String fileName = file.getFileName();
		String deviceName = file.getDeviceName();
		byte[] fileContent = file.getFileContent();
		ServletContext servletContext = request.getSession().getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
		String filePath = contextPath + File.separator + "temp" + File.separator + fileName;
		File tempFile = new File(filePath);
		FileOutputStream fos = new FileOutputStream(tempFile);
		fos.write(fileContent);
		fos.close();
		EmailSender emailSender = new EmailSender();
		emailSender.setFileName(fileName);
		emailSender.setFilePath(filePath);
		emailSender.setEmailFilePath(contextPath + File.separator + "temp");
		emailSender.setSenderEmail("do-not-reply@chiponeusa.com");
		emailSender.setSenderName("Chipone Database");
		emailSender.setRecipient(user);
		emailSender.setSubject("Data Sheet of " + deviceName + ": " + fileName);
		emailSender.setBody("The Attachment is the Data Sheet of " + deviceName + ": " + fileName);
		emailSender.generateEmail();
		emailSender.sendEmail();
		String referer = request.getHeader("Referer");
		model.addAttribute("info", "success");
		return "redirect:" + referer;
	}

	@GetMapping("/email")
	public String emailFile(HttpServletRequest request, @RequestParam("fileId") int id, Authentication authentication,
			ModelMap model) throws MessagingException, IOException {
		if (authentication == null) {
			return "redirect:/logout";
		}
		authentication.getPrincipal();
		UserInfo user = userService.getUserByUserName(authentication.getName());
		FileEntity file = fileService.getFileById(id);
		String fileName = file.getFileName();
		String description = file.getFileDescription();
		byte[] fileContent = file.getFileContent();
		ServletContext servletContext = request.getSession().getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
		String filePath = contextPath + File.separator + "temp" + File.separator + fileName;
		File tempFile = new File(filePath);
		FileOutputStream fos = new FileOutputStream(tempFile);
		fos.write(fileContent);
		fos.close();
		EmailSender emailSender = new EmailSender();
		emailSender.setFileName(fileName);
		emailSender.setFilePath(filePath);
		emailSender.setEmailFilePath(contextPath + File.separator + "temp");
		emailSender.setSenderEmail("shilin.chen@chiponeusa.com");
		emailSender.setSenderName("Chipone Database");
		emailSender.setRecipient(user);
		emailSender.setSubject(description + ": " + fileName);
		emailSender.setBody("The Attachment is the " + description + ": " + fileName);
		emailSender.generateEmail();
		emailSender.sendEmail();
		String referer = request.getHeader("Referer");
		model.addAttribute("info", "success");
		return "redirect:" + referer;
	}
}
