package m02;

public class Sample2 {
	/*
	 * この例では、配列の扱いの練習をします。
	 * コメントの指示に従って、変更や追加の作業をしながら
	 * 理解するようにしてください。
	 */

	public static void main(String[] args) {
		// Javaのデータ型には、大きくわけると
		// （１）基本データ型と
		// （２）それ以外のデータ型
		// があります。この区別に対応して、データを格納する
		// 変数の性格に大きな違いがあります。
		// この違いを理解することがJava攻略の第一歩です。

		// まず、次の変数宣言を見ましょう。

		int j, k;
		double x, y;
		boolean condition;

		// int型、double型、boolean 型はすべて基本データ型です。
		// これらのデータ型の変数は、
		// **データを直接その中に格納します**。
		// int 型の値は4バイト（32ビット）で表現されるので、
		// j, k に対してはそれぞれ4バイトの場所が割り当てられます。
		// 同様に、x, y に対してはそれぞれ8バイトの場所が割り当てられます。
		// boolean の condition については1ビットの場所でよい理屈ですが、
		// 処理速度の問題もあるので、実装によるでしょう。

		// それ以外のデータ型は、一般にオブジェクトと呼ばれるものですが、
		// ここではそのなかで配列について学びます。
		// 配列を表す Java の変数は、
		// ** 配列のある場所を指し示すためのものです**。
		// C言語 のポインタに対応します。
		// Java では、配列（一般にはオブジェクト）の場所
		// を指し示すことを
		// ** 参照する **
		// と言い、
		// 指し示すもの（C のポインタ）を
		// ** 参照 **
		// と呼びます。
		// 繰り返しますが、Java の配列変数は、配列の実体への参照を格納するもので
		// 配列の実体そのものを表すのではありません。

		// したがって配列変数の大きさは通常では 4バイト、つまり配列実体への参照に必要な大きさであり、
		// 参照される配列がいくら大きくても、これは変わりません。

		// 下の宣言のコメントをはずしてみてください。

		// int a[100];

		// C では許されたこの宣言が Java ではエラーになります。
		// Java では、配列を指し示す変数は宣言により確保できますが、
		// 配列の実体を宣言によって確保することはできません。
		// これを理解したら、上の宣言をふたたびコメントアウトしてください。
		// コメントアウトとは、コメントに変えてプログラムの要素でなくすことを言います。

		// 正しい宣言は

		int[] a;

		// または

		int b[];

		// です。
		// くり返しますが、この時点では、配列を指し示すための
		// 変数 a, b が確保されただけで、配列の実体はありません。
		// a と b の値は null (何も指していないことを表す値、
		// C における NULL に対応する）です。
		//

		// 配列の実体をつくり出すには、new 演算子を用います。
		// このとき、配列の大きさを指定する必要があります。
		// たとえば、大きさ3の int型配列をつくり、a がそれを指すように
		// するには、次のようにします。

		a = new int[3];

		// いくつかの補足をします。

		// （１）new 演算子は C の smalloc 関数に対応し、実行時にメモリの確保をします。
		// （２）作られた配列の実体そのものには名前がありません。
		// 現在は、a という変数から参照されることにより、アクセスが可能になっています。
		// これは、C によって動的に確保された配列と同じ事情です。
		// （３）C で、int a[3] と宣言された配列の場合には、
		// 確保された3要素の配列自体に a という名前がついています。
		// この違いをはっきりと意識しましょう。

		// 配列変数の宣言と、配列の実体の確保をあわせて

		int[] a1 = new int[10];

		// のように書くこともできます。

		// 以上を理解すれば、次の代入で何が起きるか理解できるでしょう。
		// B提出のコメントでは、この代入で何がおきるかを説明してください。
		// *配列bにaの要素数が参照される。よって、bは要素数3の配列となる
		// *よって、b[3] = {0, 0, 0}となる

		b = a;
		// 配列要素の指定は、C言語 と同様に書けます。

		a[0] = 1;
		a[1] = a[0] * 2;
		a[2] = a[0] + a[1];

		// 少し練習しましょう。
		// 新しい、大きさ10の配列オブジェクトを生成（確保）して、
		// 変数 c がそれを参照するように
		// 前回同様「記入コード（１）」のコメントの下にコードを記述してください。

		/* 記入コード（１） */
		int[] c = new int[10];

		// この時点で、変数 a, b, c のどれもが配列の実体を指すようになりました。
		// for文を使って、これらの配列の要素に値を入れてみましょう。
		// a の指す配列に対するfor文の例を示します。

		for (int i = 0; i < 3; i++)
			a[i] = i;

		// 同様に、 b の指す配列の、第 i 要素（0 <= i < 3)に、2 * i を
		// 代入してください。

		/* 記入コード（２） */
		for (int i = 0; i < 3; i++)
			b[i] = 2 * i;

		// 同様に、 c の指す配列の、第 i 要素（0 <= i < 10)に、3 * i を
		// 代入してください。

		/* 記入コード（3） */
		for (int i = 0; i < 10; i++)
			c[i] = 3 * i;

		// 結果を確認してみましょう。 3番目の for文のなかで、
		// c[i] という配列の参照は、記入コード（１）を正しく記入するまでは
		// コンパイルエラーになります。

		// B提出のコメントでは、この出力結果を分析して、なぜそうなるかを説明してください。
		// 特に、a の要素の出力結果に注意をはらってください。

		//*aに関して、b = aという動作によってbとaはリンクづけられているのでbと等しい値が出力される。
		//*bに関して、130行目で2の倍数を代入しているので、その値が出力される
		//*cに関して、138行目で3の倍数を代入しているので、その値が出力される

		System.out.println("a = ");
		for (int i = 0; i < 3; i++)
			System.out.println(a[i]);
		System.out.println("b = ");
		for (int i = 0; i < 3; i++)
			System.out.println(b[i]);
		System.out.println("c = ");

		// cの参照する配列の要素をすべて（10個）プリントするfor文を書いてください。
		/* 記入コード（４） */
		for (int i = 0; i < 10; i++)
			System.out.println(c[i]);

		// メソッドの引数における配列の扱いは
		// C の関数の場合とほぼ同じです。
		// linearsearch は、第1引数として与えられた配列に対して、
		// 第2引数が含まれているかどうか線形探索を行うメソッドです。
		// 下にある linearsearch を完成させた上で実行して
		// 結果が正しいか確認してください。

		// B提出のコメントでは、この出力結果を分析して、
		// どうしてその様な出力になるかを説明してください。
		//*配列aには{0,2,4}は含まれているが6は含まれていないためfalseを返す。
		//*配列bにも{0,2,4}は含まれているが6は含まれていないためfalseを返す。
		System.out.println("result of search of a = " + linearsearch(a, 6));
		System.out.println("result of search of b = " + linearsearch(b, 6));

		// 同様に linearsearch を用いて配列 c に値 6 が含まれるかどうか探索した結果を出力して下さい。
		/* 記入コード（５） */
		System.out.println("result of search of c = " + linearsearch(c, 6));

		// 最後に、配列変数の宣言、配列実体の生成、配列要素の初期化のすべてを同時に行う方法を示します。
		// 右辺では、指定された要素を持った大きさ8のint型配列が生成されます。
		int[] d = { 5, 6, 7, 8, 9, 10, 11, 12 };

		// 結果を表示します。
		// ここで、d.length は d の参照する配列の長さを表す変数です。
		// B提出のコメントでは、この出力結果を分析して、
		// どうしてそういう出力になるかを説明してください。
		//*制御変数iは0からd.lengthまでの数を1ずつ足していく
		//*d[i]は配列のi番目の値を格納しているので、for文でループすることで配列dの要素が順番に出力される。
		System.out.println("d = ");
		for (int i = 0; i < d.length; i++)
			System.out.println(i + ":" + d[i]);

		// 作成したメソッドのテストを行うメソッドを呼び出します。
		// この課題の場合、linearsearchが正しいかどうかテストします。
		// このメソッドがfalseを出力した場合、linearsearchが正しく設計されていません。
		// ただし、falseが出力されなかったとしても正解とは限りません。
		// falseが出力されなくなったことを確認してから課題を提出して下さい。
		test();
	}

	// 配列に対して線形探索を実行するメソッドです。
	// main メソッドから呼ぶ場合は、static 修飾子をつけます。
	// 他のクラスから呼ぶ必要がないので、private 修飾子をつけます。
	// 宣言の場所は、mainメソッドの前でも後でも構いません。
	private static boolean linearsearch(int[] ar1, int val1) {
		// 配列 ar1 に対して線形探索を実行してください。
		// 具体的には、配列 ar1 に値 val1 が含まれれば true を、含まれなければ false を返す様なメソッドを完成させて下さい。
		// Javaでは配列そのものが、大きさ（要素数）の情報を持っています。
		// 配列 ar1 の大きさは ar1.length によって取得することができます。

		/* 記入コード（６） */
		int i;
		for (i = 0; i < ar1.length; i++) {
			if (ar1[i] == val1)
				return true;
		}
		return false;

	}

	// 作成したメソッドのテストを行うメソッドです。
	// この課題ではlinearsearchが正しいかどうかテストします。
	// このメソッドがfalseを出力した場合、linearsearchが正しく設計されていません。
	// ただし、falseが出力されなかったとしても正解とは限りません。
	private static void test() {
		int[] testar1 = { 0, 1, 2, 3, 4, 5 };
		boolean testres11 = (true == linearsearch(testar1, 3));
		boolean testres12 = (true == linearsearch(testar1, 0));
		boolean testres13 = (false == linearsearch(testar1, -1));
		System.out.println(testres11 + " " + testres12 + " " + testres13);
		int[] testar4 = { 4, 6, 2, 8 };
		boolean testres41 = (false == linearsearch(testar4, 5));
		boolean testres42 = (true == linearsearch(testar4, 6));
		boolean testres43 = (false == linearsearch(testar4, 10));
		System.out.println(testres41 + " " + testres42 + " " + testres43);

	}

}
