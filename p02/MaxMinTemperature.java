package p02;

//グラフ描画用
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
//ファイル操作用
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//以下のimportの内容について理解できなくても課題を解くのに支障はありません
//テスト用
import java.util.Arrays;


public class MaxMinTemperature extends Frame {
	private static final String FILENAME = "src/p02/ex2_tempdata.csv";
  	
	//ウインドウサイズ
	int g_WinWidth = 1000;
	int g_WinHeight = 700;
	//描画初期化
	double BaseX = 1870;
	double TopX = 2030;
	double BaseY = 0;
	double TopY = 40;
	//描画領域
	int LeftMargin = 125;
	int RightMargin = 125;
	int TopMargin = 125;
	int BottomMargin = 125;
	//
	int CampusWidth = g_WinWidth - LeftMargin - RightMargin;
	int CampusHeight = g_WinHeight - TopMargin - BottomMargin;
		
	//
	Graphics BufGrph;
	Image BufImg;
	
	
	
	public static void main(String[] args)	{
		
		
		// 作成したメソッドのテストを行うメソッドを呼び出します。
		// このメソッドがfalseを出力した場合、解答のメソッドが正しく設計されていません。
		// ただし、falseが出力されなかったとしても正解とは限りません。
		test();
		
		// 気象庁のサイト（https://www.jma.go.jp/jma/menu/menureport.html）から
		// DLした実データから東京の1875年から2021年の8月の気温のデータをex2_tempdata.csvとして用意しました。
		// getMaxMinTemperatureを完成させて以下のメソッドを実行すると各年の8月の最高気温と最低気温をグラフで表示することが出来ます。
		new MaxMinTemperature();
		
	}
	
	
	
	private static double[] getMaxMinTemperature(double[] ar_temp, int[] ar_year, int yearnum) {
		int i, j;
		double[] ar_res = new double[yearnum * 2];
		double max = 0;
		double min = 100;
		int t = 0;
		
		for (i = 0; i < ar_year.length - 1;i++) {		//異なる年だけ
			for (j = i; j < ar_year.length; j++) {	//配列の要素数だけ
				if (ar_year[i] == ar_year[j]) {		//年の中で
					if (ar_temp[j] > max) {
						max = ar_temp[j];
					}
					if(ar_temp[j] < min) {
						min = ar_temp[j];
					} 				
				}	else {	//年が変われば
					ar_res[2*t] = max;
					ar_res[2*t + 1] = min;
					t++;
					max = 0;
					min = 100;
					i = --j;
					break;
				}
			}
		}
		ar_res[2*t] = max;
		ar_res[2*t + 1] = min;
		return ar_res;
	}
	

	
	// 作成したメソッドのテストを行うメソッドです。
	// このメソッドがfalseを出力した場合、解答のメソッドが正しく設計されていません。
	// ただし、falseが出力されなかったとしても正解とは限りません。
	private static void test() {
		//test0
		double[] ar_temptest0 = {10.3, 15.1, 18.6, 14.3, 12.3, 14.8, 11.2, 8.0};
		int[] ar_yeartest0 = {2000, 2000, 2001, 2001, 2001, 2001, 2002, 2002};
		int yearnumtest0 = 3;
		double[] ar_testans0 = {15.1, 10.3, 18.6, 12.3, 11.2, 8.0};
		double[] ar_res0 = getMaxMinTemperature(ar_temptest0, ar_yeartest0, yearnumtest0);
		//System.out.println(Arrays.toString(ar_res0));
		System.out.println(Arrays.equals(ar_res0, ar_testans0));
		//test1
		double[] ar_temptest1 = {10, 20, 30, 40, 20, 30, 40, 50, 30, 40, 50, 60, 70, 80};
		int[] ar_yeartest1 = {1900, 1900, 1900, 1900, 1910, 1910, 1910, 1910, 1920, 1920, 1920, 1920, 1920, 1920};
		int yearnumtest1 = 3;
		double[] ar_testans1 = {40.0, 10.0, 50.0, 20.0, 80.0, 30.0};
		double[] ar_res1 = getMaxMinTemperature(ar_temptest1, ar_yeartest1, yearnumtest1);
		//System.out.println(Arrays.toString(ar_res1));
		System.out.println(Arrays.equals(ar_res1, ar_testans1));
		//test2
		double[] ar_temptest2 = {25, 15, 35, 45};
		int[] ar_yeartest2 = {2000, 2000, 2010, 2010};
		int yearnumtest2 = 2;
		double[] ar_testans2 = {25.0, 15.0, 45.0, 35.0};
		double[] ar_res2 = getMaxMinTemperature(ar_temptest2, ar_yeartest2, yearnumtest2);
		//System.out.println(Arrays.toString(ar_res2));
		System.out.println(Arrays.equals(ar_res2, ar_testans2));
		
	}
	
	
	//以下実データを用いたグラフの描画用メソッド・クラス
	//以下の内容について理解できなくても課題を解くのに支障はありません
	//csvファイルの実データ呼び出し
	private static TempData getTempData() {
		BufferedReader br = null;
		String file_name = FILENAME; 
		//
		int linecnt = 0;
		try {
  			File file = new File(file_name);
			br = new BufferedReader(new FileReader(file));
 			String line; 
			while ((line = br.readLine()) != null) {
 				linecnt++;
 			}
 		} catch (Exception e) {
  			System.out.println(e.getMessage());
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		//
		TempData tempdata = new TempData(linecnt);
		int cnt = 0;
		int yearcnt = 0;
		int preyear = -1;
		try {
  			File file = new File(file_name);
			br = new BufferedReader(new FileReader(file));
 			String line;
			String[] ar1;
 			while ((line = br.readLine()) != null) {
 				ar1 = line.split(",");
				tempdata.temp[cnt] = Double.parseDouble(ar1[0]);
				tempdata.year[cnt] = Integer.parseInt(ar1[1]);
				cnt++;
				if(preyear != tempdata.year[cnt-1]){
					preyear = tempdata.year[cnt-1];
					yearcnt++;
				}
			}
		} catch (Exception e) {
  			System.out.println(e.getMessage());
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		//
		tempdata.yearnum = yearcnt;
		return tempdata;
  	}

	//
	private void drawMaxMinTemperature(){
		//最高気温と最低気温を求める
		TempData tempdata = getTempData();
		double[] ar_res = getMaxMinTemperature(tempdata.temp, tempdata.year, tempdata.yearnum);
		int inityear = 1875;
		
		//軸描画
		BufGrph.setColor(Color.BLACK);
		//Y軸
		DoublePos DPosAX1 = new DoublePos(BaseX, BaseY);
		DoublePos DPosAX2 = new DoublePos(BaseX, TopY);
		IntPos PosAX1 = convertPos(DPosAX1);
		IntPos PosAX2 = convertPos(DPosAX2);
		BufGrph.drawLine(PosAX1.PosX, PosAX1.PosY, PosAX2.PosX, PosAX2.PosY);
		//Y軸
		DoublePos DPosAY1 = new DoublePos(BaseX, BaseY);
		DoublePos DPosAY2 = new DoublePos(TopX, BaseY);
		IntPos PosAY1 = convertPos(DPosAY1);
		IntPos PosAY2 = convertPos(DPosAY2);
		BufGrph.drawLine(PosAY1.PosX, PosAY1.PosY, PosAY2.PosX, PosAY2.PosY);
		//X軸ラベル
		DoublePos DPosAXL = new DoublePos(TopX, BaseY);
		IntPos PosAXL = convertPos(DPosAXL);
		BufGrph.drawString("Year", PosAXL.PosX, PosAXL.PosY+15);
		//Y軸ラベル
		DoublePos DPosAYL = new DoublePos(BaseX, TopY);
		IntPos PosAYL = convertPos(DPosAYL);
		BufGrph.drawString("Temperature", PosAYL.PosX-40, PosAYL.PosY-3);
		//X軸原点ラベル
		DoublePos DPosOXL;
		IntPos PosOXL;
		for(int a1 = (int)BaseX; a1 < TopX; a1+= 50){
			DPosOXL = new DoublePos(a1, BaseY);
			PosOXL = convertPos(DPosOXL);
			BufGrph.drawString((int)a1 + "", PosOXL.PosX, PosOXL.PosY+15);
		}
		//Y軸原点ラベル
		DoublePos DPosOYL;
		IntPos PosOYL;
		for(int a1 = (int)BaseY; a1 < TopY; a1+= 5){
			DPosOYL = new DoublePos(BaseX, a1);
			PosOYL = convertPos(DPosOYL);
			BufGrph.drawString((double)a1 + "", PosOYL.PosX-30, PosOYL.PosY);
		}
		
		
		//折れ線グラフ折れ線グラフ描画
		for(int a1 = 0; a1 < ar_res.length-2; a1+= 2){
			//最高気温
			BufGrph.setColor(Color.RED);
			DoublePos DPos1 = new DoublePos((double)(inityear+a1/2), ar_res[a1]);
			DoublePos DPos2 = new DoublePos((double)(inityear+(a1+2)/2), ar_res[a1+2]);
			IntPos Pos1 = convertPos(DPos1);
			IntPos Pos2 = convertPos(DPos2);
			//BufGrph.drawLine(inityear+a1/2, ar_res[a1], inityear+(a1+2)/2, ar_res[a1+2]);
			BufGrph.drawLine(Pos1.PosX, Pos1.PosY, Pos2.PosX, Pos2.PosY);
			//最低気温
			BufGrph.setColor(Color.BLUE);
			DoublePos DPos3 = new DoublePos((double)(inityear+a1/2), ar_res[a1+1]);
			DoublePos DPos4 = new DoublePos((double)(inityear+(a1+2)/2), ar_res[a1+3]);
			IntPos Pos3 = convertPos(DPos3);
			IntPos Pos4 = convertPos(DPos4);
			//BufGrph.drawLine(inityear+a1/2, ar_res[a1+1], inityear+(a1+2)/2, ar_res[a1+3]);
			BufGrph.drawLine(Pos3.PosX, Pos3.PosY, Pos4.PosX, Pos4.PosY);
		}
		
		//
		
		
		repaint();
	}
	
	public IntPos convertPos(DoublePos DPos1){
		double rx1 = (DPos1.PosX - BaseX) / (TopX - BaseX) * (double)CampusWidth;
		double ry1 = (DPos1.PosY - BaseY) / (TopY - BaseY) * (double)CampusHeight;
		
		IntPos PosR = new IntPos(LeftMargin + (int)rx1, TopMargin + CampusHeight - (int)ry1);
		
		return PosR;
	}
	
	public MaxMinTemperature(){
		//
		this.setSize(g_WinWidth, g_WinHeight);
		this.setVisible(true);
		//
		BufImg = createImage(getSize().width, getSize().height);
		BufGrph = BufImg.getGraphics();
		
		//描画本体呼び出し
		drawMaxMinTemperature();
	}
	
	public void paint(Graphics g) {
		g.drawImage(BufImg, 0, 0, this);
	}
	
	
	
}

class TempData{
	double[] temp;
	int[] year;
	int yearnum;
	TempData(int num){
		temp = new double[num];
		year = new int[num];
	}
}

class IntPos{
	int PosX;
	int PosY;
	
	IntPos(){}
	
	IntPos(int PosX1, int PosY1){
		PosX = PosX1;
		PosY = PosY1;
	}
}

class DoublePos{
	double PosX;
	double PosY;
	
	DoublePos(){}
	
	DoublePos(double PosX1, double PosY1){
		PosX = PosX1;
		PosY = PosY1;
	}
}

