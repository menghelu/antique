package yanshuo;

public class Evaluate {
    private static final int FIVE = 50000;
    private static final int HUO_FOUR = 5000;
    private static final int CHONG_FOUR = 1000;
    private static final int HUO_THREE = 500;
    private static final int MIAN_THREE = 100;
    private static final int HUO_TWO = 50;
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
				blackValue[i][j]=0;
				whiteValue[i][j]=0;
				if(cb.boardStatus[i][j]==0){  //����ǿ�λ������й�ֵ
					for(int m=1;m<=4;m++){ //ÿ�����ֵ���ĸ������ֵ֮��
						blackValue[i][j]+=evaluate(1,i,j,m);
						whiteValue[i][j]+=evaluate(2,i,j,m);
					}
				}
			}
		}
		int k=0;  //�Ѷ�ά����ת����һά
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
		//���������ͬʱ�������÷֣������ѡȡһ����Ϊ��ѵ�
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
