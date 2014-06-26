//F74002010
//��T104
//�i��޳


import java.io.*;
import java.net.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;


public class TocHw4 {
	public static void main(String[] args) throws Exception,IOException,FileNotFoundException
	{
		String web = args[0];
        
		read(web);
		
		try
		{
			if(args.length == 1)
			{
				JSONArray data = new JSONArray(new JSONTokener(new FileReader(new File("url.json"))));
				
				int i, j, k;
				int most = 0;
				int upper = 0, lower = 99999999;
				int[] times = new int[2000]; 
				String road, roadd = null;
				String aaa;
				int date;
				int price;
				String roadlist[] = new String[2000];
				int datelist[][] = new int[2000][50];
				
				for(i = 0; i < 2000; i++)
				{
					roadlist[i] = null;
					times[i] = 0;
					
				}
				
				for(i = 0; i < data.length(); i++)
				{
					JSONObject obj = data.getJSONObject(i);
					
					road = obj.getString("�g�a�Ϭq��m�Ϋت��Ϫ��P");
					date = obj.getInt("����~��");
					price = obj.getInt("�`����");
					
					if(road.indexOf("��") != -1||road.indexOf("��") != -1||road.indexOf("�j�D") != -1)
					{
						if(road.indexOf("��") != -1)
							roadd = road.substring(0, 1+road.indexOf("��"));
						else if(road.indexOf("��") != -1)
							roadd = road.substring(0, 1+road.indexOf("��"));
						else if(road.indexOf("�j�D") != -1)
							roadd = road.substring(0, 2+road.indexOf("�j�D"));
						
						for(j = 0; j < 2000; j++)
						{
							if(roadd.equals(roadlist[j]))
							{
								for(k = 0; k < 50; k++)
								{
									if(date == datelist[j][k])
										break;
									
									if(datelist[j][k] == 0&&date != datelist[j][k])
									{
										datelist[j][k] = date;
										times[j]++;
										break;
										
									}
									
								}
								
								break;
								
							}
							else if(roadlist[j] == null)
							{
								roadlist[j] = roadd;
								datelist[j][0] = date;
								times[j]++;
								break;
								
							}
							
						}
						
					}
					
				}
				
				for(j = 0; j < 2000; j++)
				{
					if(times[j] > most)
						most = times[j];
					
				}
				
				for(j = 0; j < 2000; j++)
				{
					if(times[j] == most)
					{
						aaa = roadlist[j];
						
						for(i = 0; i < data.length(); i++)
						{
							JSONObject obj = data.getJSONObject(i);
							
							road = obj.getString("�g�a�Ϭq��m�Ϋت��Ϫ��P");
							price = obj.getInt("�`����");
							
							if(road.indexOf(aaa) != -1)
							{
								if(price > upper)
									upper = price;
								
								if(price < lower)
									lower = price;
								
							}
							
						}
						
						System.out.print(aaa+", �̰������: "+upper+", �̧C�����: "+lower+"\n");
						
						upper = 0;
						lower = 999999999;
						
					}
					
				}
				
			}
			else
				System.out.println("Unexpected Argument Numbers!");
			
		}
		catch(IOException e)
		{
			System.out.println("File Not Found!");
			
		}
		
	}
	
	public static void read( String strURL )
	{
		String check = null ;
		
		try
		{    
			URL pageUrl = new URL(strURL );
			BufferedReader bis = new BufferedReader(new InputStreamReader(pageUrl.openStream(), "UTF-8"));
			BufferedWriter bos = new BufferedWriter(new FileWriter("url.json", false));
			
			while ((check = bis.readLine()) != null)
			{
				bos.write(check);        
				
			}
			
			bos.close();
			bis.close();
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
        }
		
    }

}
