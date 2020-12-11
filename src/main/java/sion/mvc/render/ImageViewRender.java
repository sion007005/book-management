package sion.mvc.render;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.ApplicationContext;
import sion.mvc.ModelAndView;

@Slf4j
public class ImageViewRender implements ViewRender {

	@Override
	public void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		InputStream in = null;
		ServletOutputStream out = null;
		
		try {
			addImageContextHeader(response);
			String imageFullPath = (String) mav.getModel().get("imageFullPath");
			File imageFile = new File(imageFullPath);

			validateImage(imageFile);
//			log.info("imageFile.exists() : {}", imageFile.exists());
//			log.info("imageFile.isFile() : {}", imageFile.isFile());
//			log.info("imageFile.isDirectory() : {}", imageFile.isDirectory());
//			log.info("imageFile.getAbsolutePath() : {}", imageFile.getAbsolutePath()); // 저장되어있는 경로 (확장자까지) 
//			log.info("imageFullPath :{}",imageFullPath);
			
			in = new BufferedInputStream(new FileInputStream(imageFile));
			out = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(out);
			
			byte[] buffer = new byte[512];
			int readByte = 0;
			while ((readByte = in.read(buffer)) != -1) {
				bos.write(buffer, 0, readByte);
				bos.flush();
			}
		} catch (Exception e) {
			throw new ViewRenderException(e);
		} finally {
			closeStream(in, out);
		}
	}

	private boolean validateImage(File imageFile) {
		// validation check 로직 필요 파일이 존재하는지 /  이게 파일인지 디렉토리인지 / 이미지 파일이 맞는 지 
//		imageFile.exists();
//		imageFile.isFile();
//		imageFile.isDirectory();
//		imageFile.getAbsolutePath(); // 저장되어있는 경로 (확장자까지)		
		if (!imageFile.exists()) {
			return false;
		}
		
		if (!imageFile.isFile()) {
			return false;
		}
		
		//확장자가 이미지 인지 확인
		if (!checkImageType(imageFile)) {
			return false;
		}
		
		return true;
	}
	
	private boolean checkImageType(File imageFile) {
		int index = imageFile.getAbsolutePath().indexOf(".");
		String fileType = imageFile.getAbsolutePath().substring(index + 1);
		
		List<String> imageTypeList = ApplicationContext.getImageFileTypeList();
		for (String type : imageTypeList) {
			if (fileType.equals(type)) {
				return true;
			}
		}
		
		return false;
	}
}
