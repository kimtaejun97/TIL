# 버전관리 
******

### 📌 git init {dir} : git으로 관리할 디렉토리    
![img.png](img.png)    

### 📌 .git : got repository       
  ![img_1.png](img_1.png)          


### 📌 git add {file}: add to staging area       
![img_2.png](img_2.png)
> - git add * 로 모든 파일 add 가능   
> - git add . 현 디렉토리 모든 파일
> - git add {dir}

### 📌 git status : working tree status      
![img_3.png](img_3.png)

### 📌 git commit : create version

> - git commit 명령어 실행시 뜨는 에디터 창에서 message를 입력해도 되고
> - git commit -m "message"를 사용해도 된다.
> - git commit -am "message" : git add 와 git commit을 한번에

    ☝️️ untracked 상태인 file은 적용되지 않음.       
![img_4.png](img_4.png)

### 📌 git log : show version    
![img_5.png](img_5.png)

### 📌 git log —stat: version에 변경된 file list
![img_6.png](img_6.png)

###📌 git log -p : 마지막 버전과 다른 것.      
![img_7.png](img_7.png)
> - null 은 마지막 버전이 없다는 의미.
> - git log —all : Show All branch.
> - git log —graph: Show branch graph.
> - git log —oneline: 정보를 한줄로 간략하게 표기.

### 📌 git diff : show changes

> - add 하기 전에 실행. 마지막 버전과 현재 변경사항을 비교.

### 📌 git checkout  {commit id}:go back to previous version

> - 임시적으로 해당 버전으로 옮김. →head가 옮겨감

### 📌 git reset —hard {commit id}: reset to that version

> - head와 master branch모두 옮겨감. 옮겨간 이후의 버전들이 삭제됨.
> - git reset —hard : reset current changes
> - git reset HEAD {fileName} : add 취소 그냥 git reset, 모든 파일을 대상으로.
> - git reset HEAD^ : commit 취소
> - git commit amend: commit message 변경

### 📌 git revert {commit id}: 버전을 삭제하지 않으면서 이전 버전으로 되돌리기.

> - version 1, version 2, version 3, version 4 가 있고, version 4가 최신 버전일 때
> - version 3 로 revert 하고 싶다면 git revert{version 4 id}. version 4는 삭제되지 않고. version 3로 되돌아가게 된다.
> - version 1 으로 되돌아가고 싶다면 version 4, version 3, version 2 를 순서대로 revert하면된다. revert는 해당 버전 이후의 모든 버전의 변경사항을 되돌리는 것이 아니라 해당버전의 변경사항만을 되돌리기 때문.
> - 역순을 따르지 않을경우 충돌이 발생할 수 있음.

☝️ 버전관리 하고싶지 않은 파일이 있다면 .gitignore 에 파일명을 작성.- git init {dir} : git으로 관리할 디렉토리   

### .gitignore가 적용되지 않을 때.
    git rm -r --cached .

# Backup
*****
### 📌 git remote : 원격 저장소 리스트
> - git remote -v : 원격 저장소 리스트 + 주소
> - git remote add {name} https://github.com/.... : 원격 저장소 추가.
> - git remote remove {name} : 원격 저장소 제거.

### 📌 git push {remote name} {branch}
> -git push --set-upstream remote branch : 기본적인 push 저장소와 branch 설정.    
> 이 다음부터는 git push 만으로 설정된 저장소와 브랜치로 push 가능.

    🧐 ERROR : refusing to merge unrelated histories
    - 저장소를 생성할때 README 파일이나, .gitignore파일을 생성해서 발생하는 문제 먼저 파일을 가져와야 한다.
    - git pull origin master --allow-unrelated-histories

### 📌 git clone {address} : 기존의 저장소 복제.
> - 기본적으로 repository의 이름으로 directory가 생성.
> - 다른이름으로 지정하고 싶다면 git clone {address} {dir_name}

### 📌 git pull {remote} {branch}
