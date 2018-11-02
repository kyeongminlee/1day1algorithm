/*
 * 2018-10-12 Fri
 * 2018년 프로그래머스 써머코딩 문제 - 지형편집(난이도4)
 * 
 * - 문제정의 -  
 * XX 게임에서는 지형 편집 기능을 이용하여 플레이어가 직접 게임 속 지형을 수정할 수 있습니다. 
 * 이 게임에서는 1 x 1 x 1 크기의 정육면체 블록을 쌓아 게임 속 지형을 표현합니다. 
 * 이때, 블록이 공중에 떠 있거나, 블록 하나가 여러 개의 칸에 걸쳐 놓일 수는 없습니다. 
 * 따라서 지형을 편집하기 위해서는 각 칸의 제일 위에 블록 1개를 새로 추가하거나, 
 * 제일 위에 있는 블록 한 개를 삭제하는 방식으로 지형을 수정해야 합니다. 
 * 이때, 블록 한 개를 새로 추가하거나 삭제하기 위해서는 게임머니를 사용해야 하므로 
 * 몇 개의 블록을 추가하고 삭제할지 신중한 선택이 필요합니다.
 * 이 게임을 즐기던 한 플레이어는 N x N 크기의 지역에 자신만의 별장을 만들고 싶어졌습니다. 
 * 이를 위해서는 울퉁불퉁한 지형의 모든 칸의 높이가 같아지도록 만들어야 합니다. 
 * 이때, 블록 한 개를 추가하려면 P의 비용이, 제거하려면 Q의 비용이 들게 됩니다.
 * 다음은 블록 한 개를 추가할 때 5의 비용이, 제거할 때 3의 비용이 드는 경우, 
 * 3 x 3 넓이의 모든 칸의 블록 높이가 같아지도록 만드는 예시입니다.
 * 위 그림과 같이 지형 블록이 놓여 있는 경우 모든 칸의 높이가 3으로 같아지도록 한다면 다음과 같은 모양이 됩니다.
 * 이를 위해서는 3보다 높은 칸의 블록 2개를 제거하고, 3보다 낮은 칸에 총 8개의 블록을 추가해야 하며, 
 * 2 x 3 + 8 x 5 = 46의 비용이 들게 됩니다.
 * 그러나 아래 그림과 같이 모든 칸의 높이가 2로 같아지도록 할 때는 6개의 블록을 제거하고, 
 * 3개의 블록을 추가하면 6 x 3 + 3 x 5 = 33의 비용이 들게 되며, 이때가 최소비용이 됩니다.
 * 현재 지형의 상태를 나타내는 배열 land와 블록 한 개를 추가하는 데 필요한 비용 P, 
 * 블록 한 개를 제거하는 데 필요한 비용 Q가 매개변수로 주어질 때, 
 * 모든 칸에 쌓여있는 블록의 높이가 같아지도록 하는 데 필요한 비용의 최솟값을 return 하도록 solution 함수를 완성해 주세요.
 *
 * - 제한사항 -
 * land는 N x N 크기의 2차원 배열이며, N의 범위는 1 ≤ N ≤ 300입니다.
 * land의 각 원소는 각 칸에 놓여 있는 블록의 수를 나타내며, 0 이상 10억 이하의 정수입니다.
 * 각 칸에 블록 하나를 추가하는 데는 P, 제거하는 데는 Q의 비용이 들며, P, Q의 범위는 1 ≤ P, Q ≤ 100인 자연수입니다.
 *  
 * ex1) 
 * 입력 land : {{1, 2}, {2, 3}}
 * 입력 P : 3
 * 입력 Q : 2
 * 출력 : 5
 * 
 * ex2) 
 * 입력 land : {{4, 4, 3}, {3, 2, 2}, { 2, 1, 0 }}
 * 입력 P : 5
 * 입력 Q : 3
 * 출력 : 33
 * 
 * 
 * 
 */

package Programmers.Summer2018;

public class LandEdit {
	public static long solution(int[][] land, int P, int Q) {
		// 변수 선언
		int max = 0;	// 최고 높이
		int min = 999;	// 최저 높이
		int answer = 999;	// 답
		
		// 최고 층 구하기
		for(int i = 0; i < land.length; i++) {
			for(int j = 0; j < land.length; j++) {
				if(max < land[i][j]) {
					max = land[i][j];
				}
				if(min > land[i][j]) {
					min = land[i][j];
				}
			}
		}
		if(max == min) {
			return 0;
		}
		
		int h = min;
		// 각 층마다 빼야할 수 더해야 할 수 구하기
		while(h <= max) {
			int add = 0;	// 더해야 할 블록 수
			int minus = 0;	// 빼야 할 블록 수
			for(int i = 0; i < land.length; i++) {
				for(int j = 0; j < land[i].length; j++) {
					if(h - land[i][j] > 0) {
						add += h - land[i][j];
					} else if(h - land[i][j] < 0) {
						minus += (land[i][j]-h);
					}
				}
			}
//			System.out.println(h);
//			System.out.println(add);
//			System.out.println(minus);
//			System.out.println(add*P + minus*Q);
			if(answer > (add*P + minus*Q)) {
				answer = (add*P + minus*Q);
			}
			h++;
//			System.out.println("===============================");
		}
		
		return answer;
	}

	public static void main(String[] args) {
		int[][] land = {{4, 4, 3}, {3, 2, 2}, { 2, 1, 0 }};
		System.out.println(solution(land, 5, 3));
	}

}
