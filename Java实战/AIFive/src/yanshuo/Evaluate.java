package yanshuo;

public class Evaluate {
    private static final int FIVE = 50000;
    private static final int HUO_FOUR = 5000;
    private static final int CHONG_FOUR = 1000;
    private static final int HUO_THREE = 500;
    private static final int MIAN_THREE = 100;
    private static final int HUO_TWO = 50;
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
				blackValue[i][j]=0;
				whiteValue[i][j]=0;
				if(cb.boardStatus[i][j]==0){  //如果是空位，则进行估值
					for(int m=1;m<=4;m++){ //每个点的值是四个方向分值之和
						blackValue[i][j]+=evaluate(1,i,j,m);
						whiteValue[i][j]+=evaluate(2,i,j,m);
					}
				}
			}
		}
		int k=0;  //把二维棋盘转换成一维
		int [][] totalValue = new int[(cb.COLS+1)*(cb.ROWS+1)][3];
		for(int i=0;i<=cb.COLS;i++){
			for(int j=0;j<=cb.ROWS;j++){
				if(cb.boardStatus[i][j]==0){
					totalValue[k][0]=i;
					totalValue[k][1]=j;
					totalValue[k][2] = blackValue[i][j]+whiteValue[i][j]+staticValue[i][j];
					k++;
				}
			}
		}
		sort(totalValue);
		k=1;
		//如果几个点同时具有最大得分，便随机选取一个作为最佳点
		int maxValue = totalValue[0][2];
		while(totalValue[k][2]==maxValue){
			k++;
		}
		int r = (int)(Math.random()*k);
		int[] position = new int[2];
		position[0] = totalValue[r][0];
		position[1] = totalValue[r][1];
		return position;
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
