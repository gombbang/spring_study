오늘은 맥북으로 했다.

- 가장 먼저 git 클론 자체를 하지 않았었다, 깃 클론 필요
-- git clone은 터미널을 통해 가능했다, git bash를 별도로 들어가지 않아도 된다.
---

h2 설치를 해둔다는게 안했다.
모든 플랫폼에서 하기로 설정, 윈도우와 맥북 두개 모두 되야하니까

설치 후 실행하려니 
1. 권한 필요, chmod 755 h2/bin/h2.sh
2. java 필요, jar가 설치되지 않아 문제 발생

---
jdk 설치 중 혹시 모르니 jdk를 오라클 jdk, 17버전으로 받으려한다.
외장 톰캣 에러 해결 목적.
brew가 필요해보이나 설치되어 있지 않음.

- brew를 통한 설치
- oracle 사이트에서 설치한 뒤, 해당 내용을 가져온다.
---

https://velog.io/@baeyuna97/Homebrew-%EC%84%A4%EC%B9%98

brew란?
- brew는 맥OSmacOS 용 패키지 관리 애플리케이션입니다.
맥OS에서 프로그래밍을 하는 프로그래머들에게는 거의 필수적인 도구입니다.
apt-get 등 리눅스의 패키지 관리자들과 사용법이 비슷해서 쉽게 사용할 수 있다는 장점이 있습니다. 홈브류는 주로 커맨드라인 도구나 시스템 패키지들을 설치하는 데 사용합니다만, 캐스크Cask 확장을 통해 GUI 애플리케이션 설치에도 사용할 수 있습니다.

---
brew -> wget 설치 완료.
근데 chat gpt가 제대로 못알려준다.
링크가 다 404에러나.

다음 화면에서 Operating system는 macOS를 선택하고 Architecture는 인텔 CPU를 사용하고 있다면 x64로 선택하시면 되고 애플 CPU를 사용하고 있다면 aarch64를 선택하시면 됩니다
출처: https://pt-sheldon.tistory.com/64 [쉘든's 지식방:티스토리]
wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.tar.gz
---
근데 jdk파일 사이즈가 너무 크다, 맥북 jdk 데이터는 별도로 관리하기로 하자.

tar.gz로 설치하고, 직접 JAVA_HOME을 지정해서 하려고 했으나, 되지 않음.
심볼릭 링크를 설정하지 않아서 그런지는 모르겠는데, 맥북에서도 이 내용이 필요한지 전혀 감이 안옴.
그래서 더 찾아보니까 
https://gymdev.tistory.com/72
https://velog.io/@qpxk9211/Mac-M2-Java-%EC%84%B8%ED%8C%85
설치를 tar.gz 가 아니라 dmg로 받는다, dmg로 변경

---
내가 실수 함, https://www.oracle.com/java/technologies/downloads/#jdk17-mac
mac 탭으로 이동해서 다운로드해야하는데 
linux에서 설치하고
dmg도 여기서 찾고 있었음.
-> macos로 이동했다.

sudo wget https://download.oracle.com/java/17/latest/jdk-17_macos-x64_bin.dmg

개꿀팁 >> open . 하면 해당 터미널의 위치가 파인더로 열린다
https://velog.io/@qpxk9211/Mac-M2-Java-%EC%84%B8%ED%8C%85
---
