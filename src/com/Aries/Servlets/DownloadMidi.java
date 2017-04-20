package com.Aries.Servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Aries.Tools.Tool;
/**
 * Servlet implementation class DownloadMidi
 */
@WebServlet("/DownloadMidi.do")
public class DownloadMidi extends HttpServlet {

	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//如果访问参数符合条件
        if(request.getParameter("Download").equals("Midi"))
        {
            //获取输出流
            OutputStream out=response.getOutputStream();
            
            System.out.println("string==="+(request.getRealPath("/")+"upLoad/midi.mid"));
            
            //把文件变成byte字节流传入输出流
            out.write(Tool.getBytes(request.getRealPath("/")+"upLoad/midi.mid"));
            //刷新流
            out.flush();
            //关闭流
            out.close();
           
            //向控制台提示成功
            System.out.println("success!");
        }
	}

}
