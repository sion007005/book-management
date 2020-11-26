package sion.bookmanagement.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.PostMapping;
import sion.mvc.render.ViewRender;
@Slf4j
public class FileUploadController implements Controller {

	@Override
	@PostMapping("/file/upload")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
		 final String CHARSET = "utf-8";
	    final String UPLOADED_DIR = "C:\\uploaded";
	    String contentType = null;
	    String fileName = "";

	    try {
      	response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding(CHARSET);
			PrintWriter out = response.getWriter();
			contentType = request.getContentType();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} 
       
       if (Objects.nonNull(contentType) &&  contentType.toLowerCase().startsWith("multipart/")) {
         Collection<Part> parts;
			
         try {
         	// getParts()를 통해 Body에 넘어온 데이터들을 각각의  Part로 쪼개어 리턴
				parts = request.getParts();
				for (Part part : parts) {
					//part.getName(),part.getContentType(), part.getSize());
					if (part.getHeader("Content-Disposition").contains("filename=")) {
						fileName =  extractFileName(part.getHeader("Content-Disposition"));
						
						if (part.getSize() > 0) {
							//업로드 파일 명 : fileName
							part.write(UPLOADED_DIR + File.separator  + fileName);
							part.delete();
						}
					} else {
						String formValue =  request.getParameter(part.getName());
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new RuntimeException(e.getMessage(), e);
			} 

			log.info("업로드 완료");
			ModelAndView mav = new ModelAndView(ViewRender.JSON_VIEW_NAME);
			mav.addObject("uploaded", true);
			mav.addObject("message", "이미지 등록 완료");
			mav.addObject("fileName", fileName);
			return mav;
       } else {
      	 log.info("업로드 실패");
      	 ModelAndView mav = new ModelAndView(ViewRender.JSON_VIEW_NAME);
      	 mav.addObject("uploaded", false);
 			return mav;
       }
	}

	 private String extractFileName(String partHeader) {
       for (String cd : partHeader.split(";")) {
           if (cd.trim().startsWith("filename")) {
               String fileName = cd.substring(cd.indexOf("=") +  1).trim().replace("\"", "");;
               int index = fileName.lastIndexOf(File.separator);
               return fileName.substring(index + 1);
           }
       }
       return null;
   }

}
