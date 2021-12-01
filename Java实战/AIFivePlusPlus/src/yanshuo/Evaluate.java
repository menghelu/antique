package yanshuo;

public class Evaluate {
    private static final int FIVE = 50000;
    private static final int HUO_FOUR = 5000;
    private static final int CHONG_FOUR = 1000;
    private static final int HUO_THREE = 500;
    private static final int MIAN_THREE = 100;
    private static final int HUO_TWO = 50;
    private static final int LARGE_NUMBER = 10000000;
    private static final int SEARCH_DEPTH = 5;   //搜索的深度
    private static final int SAMPLE_NUMBER = 10;  //搜索时选择的样本数
    private static final double K=1.2;
	private ChessBoard cb;
	private int[][] blackValue;  //保存每一空位下黑子的价值
	private int[][] whiteValue;
	private int[][] staticValue; //保存每一点的位置价值，越靠中心，价值越大
	
	
	public Evaluate(ChessBoard cb){
		this.cb = cb;
		blackValue = new int[ChessBoard.COLS+1][ChessBoard.ROWS+1];
		whiteValue = new int[ChessBoard.COLS+1][ChessBoard.ROWS+1];
		staticValue = new int[ChessBoard.COLS+1][ChessBoard.ROWS+1];
		for(int i=0;i<=ChessBoard.COLS;i++){
			for(int j=0;j<=ChessBoard.ROWS;j++){
				blackValue[i][j] = 0;
				whiteValue[i][j] = 0;
			}
		}
		for(int i=0;i<=ChessBoard.COLS/2;i++){
			for(int j=0;j<=ChessBoard.ROWS/2;j++){
				staticValue[i][j]=i<j?i:j;
				staticValue[ChessBoard.COLS-i][j] = staticValue[i][j];
				staticValue[i][ChessBoard.ROWS-j] = staticValue[i][j];
				staticValue[ChessBoard.COLS-i][ChessBoard.ROWS-j] = staticValue[i][j];
			}
		}
	}
	
	private int evaluateGame(){
		int value = 0;
		int i,j,k;
		int[] line = new int[cb.COLS+1];
		//水平，对每一行估值
		for(j=0;j<=cb.ROWS;j++){
			for(i=0;i<=cb.COLS;i++){  //将每一行的状态信息复制到一维数组中
				line[i] = cb.boardStatus[i][j];  //第一个下标是列下标
			}
			value+=evaluateLine(line,cb.COLS+1,1);  //加上黑方的价值
			value-=evaluateLine(line,cb.COLS+1,2);  //减去白方的价值
		}
		//对每一列估值
		for(i=0;i<=cb.COLS;i++){
			for(j=0;j<=cb.ROWS;j++){
				line[j] = cb.boardStatus[i][j];
			}
			value+=evaluateLine(line,cb.ROWS+1,1);
			value-=evaluateLine(line,cb.ROWS+1,2);
		}
		//左下到右上斜线估值
		for(j=4;j<=cb.ROWS;j++){
			for(k=0;k<=j;k++){
				line[k] = cb.boardStatus[k][j-k];
			}
			value+=evaluateLine(line,j+1,1);
			value-=evaluateLine(line,j+1,2);
		}
		for(j=1;j<=cb.ROWS-4;j++){
			for(k=0;k<=cb.COLS-j;k++){
				line[k] = cb.boardStatus[k+j][cb.ROWS-k];
			}
			value+=evaluateLine(line,cb.ROWS+1-j,1);
			value-=evaluateLine(line,cb.ROWS+1-j,2);
		}
		//左上到右下斜线估值
		for(j=0;j<=cb.ROWS-4;j++){
			for(k=0;k<=cb.ROWS-j;k++){
				line[k] = cb.boardStatus[k][k+j];
			}
			value+=evaluateLine(line,cb.ROWS+1-j,1);
			value-=evaluateLine(line,cb.ROWS+1-j,2);
		}
		for(i=1;i<=cb.COLS-4;i++){
			for(k=0;k<=cb.ROWS-i;k++){
				line[k] = cb.boardStatus[k+i][k];
			}
			value+=evaluateLine(line,cb.ROWS+1-i,1);
			value-=evaluateLine(line,cb.ROWS+1-i,2);
		}
		if(cb.computerColor==1){
			return value;
		}else{
			return -value;
		}
	}
	
	//三个参数，分别是保存棋盘状态的一维数组，数组元素的个数，处理棋子的颜色
	private int evaluateLine(int lineState[],int num,int color){
		int chess,space1,space2;
		int i,j,k;
		int value = 0;
		int begin,end;
		for(i=0;i<num;i++){
			if(lineState[i]==color){  //遇到要找的棋子，检查棋型，得到对应的分值
				chess = 1;  //棋子数量
				begin = i;  //棋子开始的坐标
				for(j=begin+1;j<num&&lineState[j]==color;j++){
					chess++;
				}
				if(chess<2){
					continue; //如果棋子数目小于2，不需加分，继续往后搜索
				}
				end = j-1;  //棋子结束的下标
				space1 = 0; //棋子前面的空位数
				space2 = 0; //棋子后面的空位数
				//计算棋子前面的空位数
				for(j=begin-1;j>=0&&(lineState[j]==0||lineState[j]==color);j--){
					space1++;
				}
				//计算棋子后面的空位数
				for(j=end+1;j<num&&(lineState[j]==0||lineState[j]==color);j++){
					space2++;
				}
				if(chess+space1+space2>=5){
					value+=getValue(chess,space1,space2);
				}
				i = end+1;
			}
		}
		return value;
	}
	
	//这里的估值方法可能并不是太好用，考虑的情况太简单  AOAAAO  这种被认为是活三而不是冲四
	private int getValue(int chessCount,int spaceCount1,int spaceCount2){
		int  value = 0;
		switch(chessCount){
		case 5:
			value = FIVE;
			break;
		case 4:
			if(spaceCount1>0&&spaceCount2>0){  //活四
				value = HUO_FOUR;
			}else{
				value = CHONG_FOUR; //重四
			}
			break;
		case 3:
			if(spaceCount1>0&&spaceCount2>0){ //活三
				value = HUO_THREE;
			}else{
				value = MIAN_THREE;  //眠三
			}
			break;
		case 2:
			if(spaceCount1>0&&spaceCount2>0){  //活二
				value = HUO_TWO;
			}
			break;
		default:
			value = 0;
			break;
		}
		return value;
	}
	
	//查找价值最大的几个空位作为进一步搜索的样本，这样就不用对整个棋盘进行搜索，减小时间复杂度
	private int[][] getTheMostValuablePositions(){
		int i,j,k=0;
		//allValue:保存每一空位的价值（列坐标，行坐标，价值）
		int[][] allValue = new int[(cb.COLS+1)*(cb.ROWS+1)][3];
		for(i=0;i<cb.COLS;i++){
			for(j=0;j<=cb.ROWS;j++){
				if(cb.boardStatus[i][j]==0){
					allValue[k][0] = i;
					allValue[k][1] = j;
					allValue[k][2] = blackValue[i][j]+whiteValue[i][j]+staticValue[i][j];
					k++;
				}
			}
		}
		sort(allValue);
		int size = k<SAMPLE_NUMBER?k:SAMPLE_NUMBER;
		int valuablePositions[][] = new int[size][3];
		//将allvalue中的前size个空位赋给valuablePositions
		for(i=0;i<size;i++){
			valuablePositions[i][0] = allValue[i][0];
			valuablePositions[i][1] = allValue[i][1];
			valuablePositions[i][2] = allValue[i][2];
		}
		return valuablePositions;
	}
	
	
	
	private int evaluate(int color,int col,int row,int dir){
		int k,m;
		int value = 0;
		int chessCount1 = 1;
		int chessCount2 = 0;
		int chessCount3 = 0;
		int spaceCount1 = 0;
		int spaceCount2 = 0;
		int spaceCount3 = 0;
		int spaceCount4 = 0;
		switch(dir){
		case 1:
			//向增加的方向查找相同颜色连续的棋子
			for(k=col+1;k<=cb.COLS;k++){
				if(cb.boardStatus[k][row]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//在棋子的尽头查找连续的方格数
			while((k<=cb.COLS)&&(cb.boardStatus[k][row]==0)){
				spaceCount1++;
				k++;
			}
			if(spaceCount1==1){
				while((k<=cb.COLS)&&(cb.boardStatus[k][row]==color)){
					chessCount2++;
					k++;
				}
				while((k<=cb.COLS)&&(cb.boardStatus[k][row]==0)){
					spaceCount2++;
					k++;
				}
			}
			//在相反的方向查找相同颜色连续的棋子
			for(k=col-1;k>=0;k--){
				if(cb.boardStatus[k][row]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//在相反的方向的棋子尽头查找连续的空格数
			while((k>=0)&&(cb.boardStatus[k][row]==0)){
				spaceCount3++;
				k--;
			}
			if(spaceCount3==1){
				while((k>=0)&&(cb.boardStatus[k][row]==color)){
					chessCount3++;
					k--;
				}
				while((k>=0)&&(cb.boardStatus[k][row]==0)){
					spaceCount4++;
					k--;
				}
			}
			break;
		case 2:     //垂直方向
			//向增加的方向查找相同颜色连续的棋子
			for(k=row+1;k<=cb.ROWS;k++){
				if(cb.boardStatus[col][k]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//在棋子尽头查找连续的空格数
			while((k<=cb.ROWS)&&(cb.boardStatus[col][k]==0)){
				spaceCount1++;
				k++; 
			}
			if(spaceCount1==1){
				while((k<=cb.ROWS)&&(cb.boardStatus[col][k]==color)){
					chessCount2++;
					k++;
				}
				while((k<=cb.ROWS)&&(cb.boardStatus[col][k]==0)){
					spaceCount2++;
					k++;
				}
			}
			//向相反方向查找相同颜色连续的棋子
			for(k=row-1;k>=0;k--){
				if(cb.boardStatus[col][k]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//向相反方向的棋子尽头查找连续的空格数
			while((k>=0)&&(cb.boardStatus[col][k]==0)){
				spaceCount3++;
				k--;
			}
			if(spaceCount3==1){
				while((k>=0)&&(cb.boardStatus[col][k]==color)){
					chessCount3++;
					k--;
				}
				while((k>=0)&&(cb.boardStatus[col][k]==0)){
					spaceCount4++;
					k--;
				}
			}
			break;
		case 3:   //左上到右下
			//向增加的方向查找相同颜色连续的棋子
			for(k=col+1,m=row+1;(k<=cb.COLS)&&(m<=cb.ROWS);k++,m++){
				if(cb.boardStatus[k][m]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//在棋子尽头查找连续的空格数
			while((k<=cb.COLS)&&(m<=cb.ROWS)&&(cb.boardStatus[k][m]==0)){
				spaceCount1++;
				k++;
				m++;
			}
			if(spaceCount1==1){
				while((k<=cb.COLS)&&(m<=cb.ROWS)&&(cb.boardStatus[k][m]==color)){
					chessCount2++;
					k++;
					m++;
				}
				while((k<=cb.COLS)&&(m<=cb.ROWS)&&(cb.boardStatus[k][m]==0)){
					spaceCount2++;
					k++;
					m++;
				}
			}
			//向相反方向查找相同颜色连续的棋子
			for(k=col-1,m=row-1;(k>=0)&&(m>=0);k--,m--){
				if(cb.boardStatus[k][m]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//在相反方向的棋子尽头查找连续的空格数
			while((k>=0)&&(m>=0)&&(cb.boardStatus[k][m]==0)){
				spaceCount3++;
				k--;
				m--;
			}
			if(spaceCount3==1){
				while((k>=0)&&(m>=0)&&(cb.boardStatus[k][m]==color)){
					chessCount3++;
					k--;
					m--;
				}
				while((k>=0)&&(m>=0)&&(cb.boardStatus[k][m]==0)){
					spaceCount4++;
					k--;
					m--;
				}
			}
			break;
		case 4:     //右上到左下
			//查找连续的同色棋子
			for(k=col+1,m=row-1;k<=cb.COLS&&m>=0;k++,m--){
				if(cb.boardStatus[k][m]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//统计空位数
			while(k<=cb.COLS&&m>=0&&cb.boardStatus[k][m]==0){
				spaceCount1++;
				k++;
				m--;
			}
			if(spaceCount1==1){
				while(k<=cb.COLS&&m>=0&&cb.boardStatus[k][m]==color){
					chessCount2++;
					k++;
					m--;
				}
				while(k<=cb.COLS&&m>=0&&cb.boardStatus[k][m]==0){
					spaceCount2++;
					k++;
					m--;
				}
			}
			//相反方向
			for(k=col-1,m=row+1;k>=0&&m<=cb.ROWS;k--,m++){
				if(cb.boardStatus[k][m]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//统计空位数
			while(k>=0&&m<=cb.ROWS&&cb.boardStatus[k][m]==0){
				spaceCount3++;
				k--;
				m++;
			}
			if(spaceCount3==1){
				while(k>=0&&m<=cb.ROWS&&cb.boardStatus[k][m]==color){
					chessCount3++;
					k--;
					m++;
				}
				while(k>=0&&m<=cb.ROWS&&cb.boardStatus[k][m]==0){
					spaceCount4++;
					k--;
					m++;
				}
			}
			break;
		}
		//只有同色棋子数加两端的空位数不小于5时，才有价值,不然就不可能下满五子
		if(chessCount1+chessCount2+chessCount3+spaceCount1+spaceCount2+spaceCount3+spaceCount4>=5){
			value = getValue(chessCount1,chessCount2,chessCount3,spaceCount1,spaceCount2,spaceCount3,spaceCount4);
		}
		return value;
	}
	
	private int getValue(int chessCount1,int chessCount2,int chessCount3,int spaceCount1,
			int spaceCount2,int spaceCount3,int spaceCount4){
		int value = 0;
		//将得分的棋型分为连五，活四，冲四，活三，眠三，活二
		switch(chessCount1){
		case 5:  //连五
			value = FIVE;
			break;
		case 4:
			if((spaceCount1>0)&&spaceCount3>0){     //活四   OAAAAO
				value = HUO_FOUR;
			}else{
				value = CHONG_FOUR;   //冲四    OAAAA
			}
			break;
		case 3:
			if(spaceCount1==1&&chessCount2>=1&&spaceCount3==1&&chessCount3>=1){
				value = HUO_FOUR;  //AOAAAOA
			}else if((spaceCount1==1&&chessCount2>=1)||(spaceCount3==1&&chessCount3>=1)){
				value = CHONG_FOUR;  //AAAOA
			}else if((spaceCount1>1&&spaceCount3>0)||(spaceCount1>0&&spaceCount3>1)){
				value = HUO_THREE;  //OOAAAO
			}else{
				value = MIAN_THREE;
			}
			break;
		case 2:
			if(spaceCount1==1&&chessCount2>=2&&spaceCount3==1&&chessCount3>=2){
				value = HUO_FOUR;   //AA0AA0AA
			}else if((spaceCount1==1&&chessCount2>=2)||(spaceCount3==1&&chessCount3>=2)){
				value = CHONG_FOUR; //AAOAA
			}else if((spaceCount1==1&&chessCount2==1&&spaceCount2>0&&spaceCount3>0)||
					(spaceCount3==1&&chessCount3==1&&spaceCount1>0&&spaceCount4>0)){
				value = HUO_THREE;  //OAAOAO
			}else if(spaceCount1>0&&spaceCount3>0){
				value = HUO_TWO;
			}
			break;
		case 1:
			if((spaceCount1==1&&chessCount2>=3)||(spaceCount3==1&&chessCount3>=3)){
				value = CHONG_FOUR;  //AOAAA
			}else if((spaceCount1==1&&chessCount2==2&&spaceCount2>0&&spaceCount3>0)||
					(spaceCount3==1&&chessCount3==2&&spaceCount1>0&&spaceCount4>0)){
				value = HUO_THREE; //OAOAAO
			}else if((spaceCount1==1&&chessCount2==2&&(spaceCount2>0||spaceCount3>0))||
					(spaceCount3==1&&chessCount3==2&&(spaceCount1>0||spaceCount4>0))){
				value = MIAN_THREE;//OAOAA
			}else if((spaceCount1==1&&chessCount2==1&&spaceCount2>1&&spaceCount3>0)||
					(spaceCount3==1&&chessCount3==1&&spaceCount1>0&&spaceCount4>1)){
				value = HUO_TWO;  // OAOAOO
			}
			break;
		default:
			value = 0;
			break;
		}
		return value;
	}
	
	int[] getTheBestPosition(){
		for(int i=0;i<=cb.COLS;i++){
			for(int j=0;j<=cb.ROWS;j++){
				blackValue[i][j] = 0;
				whiteValue[i][j] = 0;
				if(cb.boardStatus[i][j]==0){  //如果是空位，则进行估值
					for(int m=1;m<=4;m++){ //每个点的值是四个方向分值之和
						blackValue[i][j]+=evaluate(1,i,j,m);
						whiteValue[i][j]+=evaluate(2,i,j,m);
					}
					if(cb.isBlack){
						blackValue[i][j]*=K;
					}else{
						whiteValue[i][j]*=K;
					}
				}
			}
		}
		int maxValue = -LARGE_NUMBER;
		int value;
		int[] position = new int[2];
		int valuablePositions[][] = getTheMostValuablePositions();
		for(int i=0;i<valuablePositions.length;i++){
			if(valuablePositions[i][2]>=FIVE){  //已经连五
				position[0] = valuablePositions[i][0];
				position[1] = valuablePositions[i][1];
				break;
			}
			cb.boardStatus[valuablePositions[i][0]][valuablePositions[i][1]] = cb.computerColor;
			value = min(SEARCH_DEPTH,-LARGE_NUMBER,LARGE_NUMBER);
			cb.boardStatus[valuablePositions[i][0]][valuablePositions[i][1]] = 0;
			if(value>maxValue){
				maxValue = value;
				position[0] = valuablePositions[i][0];
				position[1] = valuablePositions[i][1];
			}
		}
		return position;
	}
	
	private int min(int depth,int alpha,int beta){
		if(depth==0){
			return evaluateGame();  //如果搜索到最底层，直接返回当前的估值，即当前的局势估值
		}
		for(int i=0;i<=cb.COLS;i++){
			for(int j=0;j<=cb.ROWS;j++){
				blackValue[i][j] = 0;
				whiteValue[i][j] = 0;
				if(cb.boardStatus[i][j]==0){  //如果是空位，进行估值
					for(int m=1;m<=4;m++){
						blackValue[i][j]+=evaluate(1,i,j,m);
						whiteValue[i][j]+=evaluate(2,i,j,m);
					}
				}
			}
		}
		int value;
		int valuablePositions[][] = getTheMostValuablePositions();
		for(int i=0;i<valuablePositions.length;i++){
			//人一方下棋时，如果该空位的人一方的价值达到连五的价值，
			//则不必再继续向下搜索，直接返回一个很大的负值
			if(cb.computerColor==1){
				if(whiteValue[valuablePositions[i][0]][valuablePositions[i][1]]>=FIVE){
					return -10*FIVE;
				}
			}else{
				if(blackValue[valuablePositions[i][0]][valuablePositions[i][1]]>=FIVE){
					return -10*FIVE;
				}
			}
			cb.boardStatus[valuablePositions[i][0]][valuablePositions[i][1]] = cb.computerColor==1?2:1;
			value = max(depth-1,alpha,beta);
			cb.boardStatus[valuablePositions[i][0]][valuablePositions[i][1]] = 0;
			if(value<beta){  //beta保存当前的最小估值
				beta = value;
				if(alpha>=beta){
					return alpha; //余下的分支不需要搜索了，直接返回
				}
			}
		}
		return beta;
	}
	
	private int max(int depth,int alpha,int beta){
		if(depth==0){
			return evaluateGame();
		}
		for(int i=0;i<=cb.COLS;i++){
			for(int j=0;j<=cb.ROWS;j++){
				blackValue[i][j] = 0;
				whiteValue[i][j] = 0;
				if(cb.boardStatus[i][j]==0){
					for(int m=1;m<=4;m++){
						blackValue[i][j]+=evaluate(1,i,j,m);
						whiteValue[i][j]+=evaluate(2,i,j,m);
					}
				}
			}
		}
		int value;
		int valuablePositions[][] = getTheMostValuablePositions();
		for(int i=0;i<valuablePositions.length;i++){
			if(cb.computerColor==1){
				if(blackValue[valuablePositions[i][0]][valuablePositions[i][1]]>=FIVE){
					return 10*FIVE;
				}
			}else{
				if(whiteValue[valuablePositions[i][0]][valuablePositions[i][1]]>=FIVE){
					return 10*FIVE;
				}
			}
			cb.boardStatus[valuablePositions[i][0]][valuablePositions[i][1]] = cb.computerColor;
			value = min(depth-1,alpha,beta);
			cb.boardStatus[valuablePositions[i][0]][valuablePositions[i][1]] = 0;
			if(value>alpha){
				alpha = value;
				if(alpha>=beta){
					return beta;
				}
			}
		}
		return alpha;
	}
	
	//冒泡降序
	private void sort(int[][] allValue){
		for(int i=0;i<allValue.length-1;i++){
			for(int j=0;j<allValue.length-1-i;j++){
				int ti,tj,tvalue;
				if(allValue[j][2]<allValue[j+1][2]){
					tvalue = allValue[j][2];
					allValue[j][2] = allValue[j+1][2];
					allValue[j+1][2] = tvalue;
					ti = allValue[j][0];
					allValue[j][0] = allValue[j+1][0];
					allValue[j+1][0] = ti;
					tj = allValue[j][1];
					allValue[j][1] = allValue[j+1][1];
					allValue[j+1][1] = tj;
				}
			}
		}
	}

}
