package CodingTest.src.programmers_lv2;

public class Solution76 {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        // m n ���� �̷���� ���带 ����
        char[][] map = new char[m][n];
        // �� �ະ�� ���� �ѹ��� �������
        for (int i = 0; i < m; ++i) {
            map[i] = board[i].toCharArray();
        }

        // ���̻� ���� �� ���� �� ����
        while(true){
            int cnt = checkBlock(m,n,map);
            if(cnt==0){
                break;
            }
            answer+=cnt;
            dropBlock(m,n,map);
        }

        return answer;
    }

    private void dropBlock(int m, int n, char[][] map) {
        // ���� �������� ������� �۾�
        for (int c = 0; c < n; ++c) { // ��
            for (int r = m-1; r >= 0; --r) {
                // �Ʒ� ���� �ö��
                if(map[r][c] == '.'){
                    // �������� ���� ��
                    for (int nr = r-1; nr >= 0 ; --nr) {
                        // ���� �ִ� ���� ������
                        if(map[nr][c] != '.'){
                            // ���� ���� �����ϸ� ������
                            map[r][c] = map[nr][c];
                            // ���� ���� ������ ��
                            map[nr][c] = '.';
                            break;
                        }
                    }
                }
            }

        }
    }

    private int checkBlock(int m, int n, char[][] map) {
        // ��� ����� �� ���� ������ Ȯ���ϴ� �۾�
        int cnt = 0;
        boolean[][] checked = new boolean[m][n];

        for (int i = 0; i < m-1; ++i) {
            for (int j = 0; j < n-1; ++j) {
            	if(map[i][j] == '.') {
            		// �ƹ��͵� ���ٸ� 
            		continue;
            	}
            	checkFour(map, checked, i, j);
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
            	if(checked[i][j]) {
            		// 4���� ���� ���� ������ 
            		cnt++;
            		map[i][j] = '.';
            		// ���������ϱ� ������ ������ 
            	}
            }
        }
        return cnt;
    }

	private void checkFour(char[][] map, boolean[][] checked, int i, int j) {
		char block = map[i][j];
		// ������ ��ġ ���� 
		for (int k = i; k < i+2; ++k) {
			for (int k2 = j; k2 < j+2; ++k2) {
				if(map[k][k2] != block) {
					//������ ��ġ���� �¿���� 
					return;
				}
			}
		}
		for (int k = i; k < i+2; ++k) {
			for (int k2 = j; k2 < j+2; ++k2) {
				checked[k][k2] = true;
			}
		}
	}
}