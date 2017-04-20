package com.Aries.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Aries.Tools.Tool;

@WebServlet("/ByteFileContentServlet.do")
public class ByteFileContentServlet extends HttpServlet {

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("This is doPost");
		
		//向控制台输出文件的内容长度
        System.out.println(request.getContentLength());
        //如果有内容
        if (request.getContentLength() > 297) {


            //==================开始处理文件===================

            //接收上传文件内容中临时文件的文件名
            String tempFileName = new String("tempFileName.txt");
            //tempfile 对象指向临时文件
            File tempFile = new File(request.getRealPath("/")+tempFileName);
            //outputfile 文件输出流指向这个临时文件
            FileOutputStream outputStream = new FileOutputStream(tempFile);
            //得到客服端提交的所有数据
            InputStream fileSourcel = request.getInputStream();
            //将得到的客服端数据写入临时文件
            byte b[] = new byte[1000];
            int n ;
            while ((n=fileSourcel.read(b))!=-1){
                outputStream.write(b,0,n);
            }

            //关闭输出流和输入流
            outputStream.close();
            fileSourcel.close();

            //randomFile对象指向临时文件
            RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
            //读取临时文件的前三行数据
            randomFile.readLine();
            randomFile.readLine();
            randomFile.readLine();
            //读取临时文件的第四行数据，这行数据中包含了文件的路径和文件名
            String filePath = randomFile.readLine();
            //得到文件名
            System.out.println(filePath);
            int position = filePath.lastIndexOf("filename");
            String filename =Tool.codeString(filePath.substring(position+10,filePath.length()-1));
            //重新定位读取文件指针到文件头
            randomFile.seek(0);
            //得到第四行回车符的位置，这是上传文件数据的开始位置
            long  forthEnterPosition = 0;
            int forth = 1;
            while((n=randomFile.readByte())!=-1&&(forth<=4)){
                if(n=='\n'){
                    forthEnterPosition = randomFile.getFilePointer();
                    forth++;
                }
            }

            //生成上传文件的目录
            File fileupLoad = new File(request.getRealPath("/"),"upLoad");
            fileupLoad.mkdir();
            //saveFile 对象指向要保存的文件
            File saveFile = new File(request.getRealPath("/")+"\\upLoad",filename);
            RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
            //找到上传文件数据的结束位置，即倒数第四行
            randomFile.seek(randomFile.length());
            long endPosition = randomFile.getFilePointer();
            int j = 1;
            while((endPosition>=0)&&(j<=4)){
                endPosition--;
                randomFile.seek(endPosition);
                if(randomFile.readByte()=='\n'){
                    j++;
                }
            }

            //从上传文件数据的开始位置到结束位置，把数据写入到要保存的文件中
            randomFile.seek(forthEnterPosition);
            long startPoint = randomFile.getFilePointer();
            while(startPoint<endPosition){
                randomAccessFile.write(randomFile.readByte());
                startPoint = randomFile.getFilePointer();
            }
            //关闭文件输入、输出
            randomAccessFile.close();
            randomFile.close();
            tempFile.delete();

            //==================处理文件结束===================

            //向控制台输出文件上传成功
            System.out.println("File upload success!");
            
        } 
        else 
        {
            //否则显示失败，
            System.out.println("No file!");

            //向Unity返回一个Fasle字符串
            Writer out=response.getWriter();
            out.write("false");
            out.close();
        }
	}

}
