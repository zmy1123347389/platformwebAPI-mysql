package com.behere.common.utils.exam;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

public class WordToPdfUtil {
	/**
	 * word转pdf工具类，全面支持doc、docx
	 * @param 
	 * @return
	 * @throws Exception 
	 */
	
	public static List<String> pdfFormatpng (String newpaths,String filepath) {
		List<String> list = new ArrayList<String>();
		try {
			System.out.println("开始转码....."+filepath);
			String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			Document document = new Document();
			document.setFile(filepath);
			float scale = 2.5f;// 缩放比例
			float rotation = 0f;// 旋转角度
			System.out.println("开始转码....."+newpaths);
			File dirFile = new File(newpaths);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			for (int i = 0; i < document.getNumberOfPages(); i++) {
				BufferedImage image = (BufferedImage) document.getPageImage(i,
						GraphicsRenderingHints.SCREEN,
						org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation,
						scale);
				RenderedImage rendImage = image;
				try {
					String newFileName =  name + "_" + (i + 1)+ ".png";
					System.out.println("生成图片url:"+newFileName);
					File file = new File(newpaths + newFileName);
					ImageIO.write(rendImage, "png", file);
					list.add(i,newFileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
				image.flush();
			}
			document.dispose();
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
	}
	
	
	public static byte[] wordToPdf (InputStream inputStream) throws Exception {
		byte[] pdfs = null;
//		LoadOptions opt = new LoadOptions();
//		opt.setEncoding(Charset.forName("UTF-8"));
//		com.aspose.words.Document doc = new com.aspose.words.Document(inputStream,opt);
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		doc.save(out, SaveFormat.PDF);
//		pdfs = out.toByteArray();
//		out.close();
		return pdfs;
	}
	
	
	public static void main(String[] args) throws Exception {
		String newpaths = "D:/test/pdf/";
		String filePath = "C:\\Users\\taven\\Desktop\\kda\\智器云第三方接口文档v1.5.pdf";
		pdfFormatpng(newpaths, filePath);
		String filePath1 = "C:\\Users\\taven\\Desktop\\kda\\服务器端口映射2.doc";
		byte [] pdfs = wordToPdf(new FileInputStream(new File(filePath1)));
		String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String filePath2 = "D:/test/doc/";
		File pdfFile = new File(filePath2+name+"/"+name+".pdf");
		FileUtils.writeByteArrayToFile(pdfFile, pdfs);
		
		System.out.println(pdfFile.getAbsolutePath());
		
		pdfFormatpng(newpaths, pdfFile.getAbsolutePath());
	}
}