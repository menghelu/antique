package yanshuo;

public class Evaluate {
    private static final int FIVE = 50000;
    private static final int HUO_FOUR = 5000;
    private static final int CHONG_FOUR = 1000;
    private static final int HUO_THREE = 500;
    private static final int MIAN_THREE = 100;
    private static final int HUO_TWO = 50;
    private static final int LARGE_NUMBER = 10000000;
    private static final int SEARCH_DEPTH = 5;   //���������
    private static final int SAMPLE_NUMBER = 10;  //����ʱѡ���������
    private static final double K=1.2;
	private ChessBoard cb;
	private int[][] blackValue;  //����ÿһ��λ�º��ӵļ�ֵ
	private int[][] whiteValue;
	private int[][] staticValue; //����ÿһ���λ�ü�ֵ��Խ�����ģ���ֵԽ��
	
	
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
		//ˮƽ����ÿһ�й�ֵ
		for(j=0;j<=cb.ROWS;j++){
			for(i=0;i<=cb.COLS;i++){  //��ÿһ�е�״̬��Ϣ���Ƶ�һά������
				line[i] = cb.boardStatus[i][j];  //��һ���±������±�
			}
			value+=evaluateLine(line,cb.COLS+1,1);  //���Ϻڷ��ļ�ֵ
			value-=evaluateLine(line,cb.COLS+1,2);  //��ȥ�׷��ļ�ֵ
		}
		//��ÿһ�й�ֵ
		for(i=0;i<=cb.COLS;i++){
			for(j=0;j<=cb.ROWS;j++){
				line[j] = cb.boardStatus[i][j];
			}
			value+=evaluateLine(line,cb.ROWS+1,1);
			value-=evaluateLine(line,cb.ROWS+1,2);
		}
		//���µ�����б�߹�ֵ
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
		//���ϵ�����б�߹�ֵ
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
	
	//�����������ֱ��Ǳ�������״̬��һά���飬����Ԫ�صĸ������������ӵ���ɫ
	private int evaluateLine(int lineState[],int num,int color){
		int chess,space1,space2;
		int i,j,k;
		int value = 0;
		int begin,end;
		for(i=0;i<num;i++){
			if(lineState[i]==color){  //����Ҫ�ҵ����ӣ�������ͣ��õ���Ӧ�ķ�ֵ
				chess = 1;  //��������
				begin = i;  //���ӿ�ʼ������
				for(j=begin+1;j<num&&lineState[j]==color;j++){
					chess++;
				}
				if(chess<2){
					continue; //���������ĿС��2������ӷ֣�������������
				}
				end = j-1;  //���ӽ������±�
				space1 = 0; //����ǰ��Ŀ�λ��
				space2 = 0; //���Ӻ���Ŀ�λ��
				//��������ǰ��Ŀ�λ��
				for(j=begin-1;j>=0&&(lineState[j]==0||lineState[j]==color);j--){
					space1++;
				}
				//�������Ӻ���Ŀ�λ��
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
	
	//����Ĺ�ֵ�������ܲ�����̫���ã����ǵ����̫��  AOAAAO  ���ֱ���Ϊ�ǻ��������ǳ���
	private int getValue(int chessCount,int spaceCount1,int spaceCount2){
		int  value = 0;
		switch(chessCount){
		case 5:
			value = FIVE;
			break;
		case 4:
			if(spaceCount1>0&&spaceCount2>0){  //����
				value = HUO_FOUR;
			}else{
				value = CHONG_FOUR; //����
			}
			break;
		case 3:
			if(spaceCount1>0&&spaceCount2>0){ //����
				value = HUO_THREE;
			}else{
				value = MIAN_THREE;  //����
			}
			break;
		case 2:
			if(spaceCount1>0&&spaceCount2>0){  //���
				value = HUO_TWO;
			}
			break;
		default:
			value = 0;
			break;
		}
		return value;
	}
	
	//���Ҽ�ֵ���ļ�����λ��Ϊ��һ�������������������Ͳ��ö��������̽�����������Сʱ�临�Ӷ�
	private int[][] getTheMostValuablePositions(){
		int i,j,k=0;
		//allValue:����ÿһ��λ�ļ�ֵ�������꣬�����꣬��ֵ��
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
		//��allvalue�е�ǰsize����λ����valuablePositions
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
			//�����ӵķ��������ͬ��ɫ����������
			for(k=col+1;k<=cb.COLS;k++){
				if(cb.boardStatus[k][row]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//�����ӵľ�ͷ���������ķ�����
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
			//���෴�ķ��������ͬ��ɫ����������
			for(k=col-1;k>=0;k--){
				if(cb.boardStatus[k][row]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//���෴�ķ�������Ӿ�ͷ���������Ŀո���
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
		case 2:     //��ֱ����
			//�����ӵķ��������ͬ��ɫ����������
			for(k=row+1;k<=cb.ROWS;k++){
				if(cb.boardStatus[col][k]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//�����Ӿ�ͷ���������Ŀո���
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
			//���෴���������ͬ��ɫ����������
			for(k=row-1;k>=0;k--){
				if(cb.boardStatus[col][k]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//���෴��������Ӿ�ͷ���������Ŀո���
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
		case 3:   //���ϵ�����
			//�����ӵķ��������ͬ��ɫ����������
			for(k=col+1,m=row+1;(k<=cb.COLS)&&(m<=cb.ROWS);k++,m++){
				if(cb.boardStatus[k][m]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//�����Ӿ�ͷ���������Ŀո���
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
			//���෴���������ͬ��ɫ����������
			for(k=col-1,m=row-1;(k>=0)&&(m>=0);k--,m--){
				if(cb.boardStatus[k][m]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//���෴��������Ӿ�ͷ���������Ŀո���
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
		case 4:     //���ϵ�����
			//����������ͬɫ����
			for(k=col+1,m=row-1;k<=cb.COLS&&m>=0;k++,m--){
				if(cb.boardStatus[k][m]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//ͳ�ƿ�λ��
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
			//�෴����
			for(k=col-1,m=row+1;k>=0&&m<=cb.ROWS;k--,m++){
				if(cb.boardStatus[k][m]==color){
					chessCount1++;
				}else{
					break;
				}
			}
			//ͳ�ƿ�λ��
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
		//ֻ��ͬɫ�����������˵Ŀ�λ����С��5ʱ�����м�ֵ,��Ȼ�Ͳ�������������
		if(chessCount1+chessCount2+chessCount3+spaceCount1+spaceCount2+spaceCount3+spaceCount4>=5){
			value = getValue(chessCount1,chessCount2,chessCount3,spaceCount1,spaceCount2,spaceCount3,spaceCount4);
		}
		return value;
	}
	
	private int getValue(int chessCount1,int chessCount2,int chessCount3,int spaceCount1,
			int spaceCount2,int spaceCount3,int spaceCount4){
		int value = 0;
		//���÷ֵ����ͷ�Ϊ���壬���ģ����ģ����������������
		switch(chessCount1){
		case 5:  //����
			value = FIVE;
			break;
		case 4:
			if((spaceCount1>0)&&spaceCount3>0){     //����   OAAAAO
				value = HUO_FOUR;
			}else{
				value = CHONG_FOUR;   //����    OAAAA
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
				if(cb.boardStatus[i][j]==0){  //����ǿ�λ������й�ֵ
					for(int m=1;m<=4;m++){ //ÿ�����ֵ���ĸ������ֵ֮��
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
			if(valuablePositions[i][2]>=FIVE){  //�Ѿ�����
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
			return evaluateGame();  //�����������ײ㣬ֱ�ӷ��ص�ǰ�Ĺ�ֵ������ǰ�ľ��ƹ�ֵ
		}
		for(int i=0;i<=cb.COLS;i++){
			for(int j=0;j<=cb.ROWS;j++){
				blackValue[i][j] = 0;
				whiteValue[i][j] = 0;
				if(cb.boardStatus[i][j]==0){  //����ǿ�λ�����й�ֵ
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
			//��һ������ʱ������ÿ�λ����һ���ļ�ֵ�ﵽ����ļ�ֵ��
			//�򲻱��ټ�������������ֱ�ӷ���һ���ܴ�ĸ�ֵ
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
			if(value<beta){  //beta���浱ǰ����С��ֵ
				beta = value;
				if(alpha>=beta){
					return alpha; //���µķ�֧����Ҫ�����ˣ�ֱ�ӷ���
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
	
	//ð�ݽ���
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
