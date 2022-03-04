package kr.or.ddit.basic;

// yield() 메서드 연습

public class ThreadTest10 {

	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번 쓰레드");
		YieldThread th2 = new YieldThread("2번 쓰레드");
		
		th1.start();
		th2.start();

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			
		}
		System.out.println("1111111111------------------------------");
		
		th1.work = false;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			
		}
		System.out.println("2222222222------------------------------");
		
		th1.work = true;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			
		}
		System.out.println("3333333333------------------------------");
		th1.stop = true;
		th2.stop = true;
	}

}

// yield() 메서드 연습용 쓰레드
class YieldThread extends Thread {
	public boolean stop = false;
	public boolean work = true;
	
	public YieldThread(String name) {
		super(name); // 부모class인 Thread의 생성자 호출 ==> 쓰레드의 이름 설정
	}
	
	@Override
	public void run() {
		while(!stop) { // stop이 true이면 반복문을 종료
			if(work) {
				// getName()메서드 ==> 쓰레드의 이름을 반환한다.
				System.out.println(getName() + "작업중...");
			} else {
				System.out.println(getName() + "양보...");
				Thread.yield();
			}
		}
	}
}
