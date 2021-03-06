# ๐ Git ๊ธฐ์ด

## ๐ง Git Object
- Blob: ํ์ผ ํ๋์ ๋ด์ฉ์ ๋ํ ์ ๋ณด.
- Tree: Blob์ด๋ subtree์ ๋ฉํ๋ฐ์ดํฐ(๋ํํ ๋ฆฌ ์์น, ์์ฑ, ์ด๋ฆ ๋ฑ)
- Commit: ์ปค๋ฐ ์๊ฐ์ ์ค๋์ท.


### ๐ Cloud Remote Repository Sevices
- Giihub
- bitbucket
- GitLab

## ๐ง Git setting
```
git config --global user.name "{name}" 
git config --global user.email "{email}"
git config --global core.editor "{vim}"  // ๊ธฐ๋ณธ๊ฐ nano
git config --global core.pager "{cat}"   // ๊ธฐ๋ณธ๊ฐ less
```

์ค์  ์ํ๋ `git config --list`๋ก ํ์ธ ๊ฐ๋ฅํ๊ณ , `~/.gitconfig`์์๋ ์์ , ํ์ธ ๊ฐ๋ฅํ๋ค.   
global์ local๋ก ๋ณ๊ฒฝํ๋ฉด ํด๋น ๋๋ ํ ๋ฆฌ์์๋ง ์ฌ์ฉํ๋ ์ค์ ์ผ๋ก๋ ์ธํ์ด ๊ฐ๋ฅํ๋ค.

alias ์ ์ค์ ๋ ๊ฐ๋ฅํ๋ ์์ฃผ ์ฐ๋ ๊ธฐ๋ฅ(git log --pretty --graph.. ๊ฐ์)์ alias๋ก ๋ฑ๋กํด๋ ์ข๋ค.    
`git config --global | local alias.${alias} 'og --graph'`

## ๐ง Conventional Commits
> https://www.conventionalcommits.org/en/v1.0.0/

```
1. commit์ ์ ๋ชฉ์ commit์ ์ ์ค๋ชํ๋ ํ๋์ ๊ตฌ๋ ์ ๋ก ์์ฑ.
2. Importance of Capitalize
3. prefix ๊ผญ ๋ฌ๊ธฐ
  - feat: ๊ธฐ๋ฅ ๊ฐ๋ฐ ๊ด๋ จ.
  - fix: ์ค๋ฅ ๊ฐ์  ํน์ ๋ฒ๊ทธ ํจ์น.
  - refactor: ๋ฆฌํฉํ ๋ง.
  - docs: ๋ฌธ์ํ ์์.
  - test: test ๊ด๋ จ.
  - conf: ํ๊ฒฝ์ค์  ๊ด๋ จ.
  - build: ๋น๋ ๊ด๋ จ.
  - ci: Continuous Integration ๊ด๋ จ.
  - BREAKING CHANGE: ํญ์  ๋ฒ์ ์ด๋ api ๋ฑ์ด ๋์ํ์ง ์๊ฒ ๋๋ ๋ณ๊ฒฝ์ฌํญ(Drop support /api/v1)
```
Conventional Commits ์ ํ๋ง๋ค ๋ค๋ฅด๊ธฐ ๋๋ฌธ์ ์ด๋ฅผ ์ฐธ์กฐํด์ผ ํ๋ค.

## ๐ง Commit ์ ์ ์ฌํญ.
- commit์ ๋์ ๊ฐ๋ฅํ ์ต์๋จ์๋ก ์์ฃผ ํ  ๊ฒ.
- ํด๋น ์์ ๋จ์์ ์ํ๋ ๋ชจ๋  ํ์ผ ๋ณํ๊ฐ ํด๋น commit์ ํฌํจ๋์ด์ผ ํจ.
- ๋ชจ๋๊ฐ ์ดํดํ  ์ ์๋ log๋ฅผ ์์ฑํ  ๊ฒ.
- ์คํ ์์ค์์๋ ์์ด๊ฐ ๊ฐ์ ๋์ง๋ง, ๊ทธ๋ ์ง ์์ ๊ฒฝ์ฐ ํ ๋ด ์ธ์ด๋ฅผ ์ฌ์ฉํ  ๊ฒ.
- ์ ๋ชฉ์ ์ถ์ฝํ์ผ๋ก ์ฐ๊ณ (50์ ์ด๋ด), ๋ด์ฉ์ ๋ฌธ์ฅํ์ผ๋ก ์์ฑํ์ฌ ์ถ๊ฐ ์ค๋ช.(์ด์ , ์์ ๋ด์ฉ)
- ์ ๋ชฉ๊ณผ ๋ด์ฉ์ ํ ์ค ๋์ ๋ถ๋ฆฌํ  ๊ฒ.(๋งํฌ๋ค์ด ์ธ์ด.)
- ๋ด์ฉ์ commit ์ ๊ตฌ์ฑ๊ณผ ์๋๋ฅผ ์ถฉ์คํ ์์ฑํ  ๊ฒ.

- `add .`, `commit -m ""` ์ฌ์ฉ ์ง์.
# ๐ ๋ฒ์ ๊ด๋ฆฌ 

- ### ๐ git init {dir} : git ์ผ๋ก ๊ด๋ฆฌํ  ๋๋ ํ ๋ฆฌ    
    ![img.png](imgs/img.png)    
    ์๋ตํ๋ฉด ํ์ฌ ๋๋ ํ ๋ฆฌ๋ฅผ ๋์์ผ๋ก ํ๋ค.

- ### ๐ .git : git repository       
    ![img_1.png](imgs/img_1.png)          


- ### ๐ git add : add to staging area       
    ![img_2.png](imgs/img_2.png)
    blob์ working directory ์์ staging area ์ ์ฌ๋ฆฐ๋ค.
    - `git add ${file}` 
    - `git add *` ๋ก ๋ชจ๋  ํ์ผ add ๊ฐ๋ฅ   
    - `git add .` ํ ๋๋ ํ ๋ฆฌ ๋ชจ๋  ํ์ผ.(๊ถ์ฅํ์ง ์์.)
    - `git add ${dir}`

- ### ๐ git status : working tree status      
    ![img_3.png](imgs/img_3.png)

- ### ๐ git commit : create version
    - git commit ๋ช๋ น์ด ์คํ์ ๋จ๋ ์๋ํฐ ์ฐฝ์์ message๋ฅผ ์๋ ฅ(๊ถ์ฅ.)
    - git commit -m "message"๋ฅผ ์ฌ์ฉํด๋ ๋๋ค.
    - git commit -am "message" : git add ์ git commit์ ํ๋ฒ์

    ๐ก๏ธ untracked ์ํ์ธ file์ ์ ์ฉ๋์ง ์์.       
![img_4.png](imgs/img_4.png)

- ### ๐ git log : show version    
  ![img_5.png](imgs/img_5.png)

- ### ๐ git log โstat: version์ ๋ณ๊ฒฝ๋ file list
  ![img_6.png](imgs/img_6.png)

- ### ๐ git log -p : ๋ง์ง๋ง ๋ฒ์ ๊ณผ ๋ค๋ฅธ ๊ฒ.      
  ![img_7.png](imgs/img_7.png)
  - null ์ ๋ง์ง๋ง ๋ฒ์ ์ด ์๋ค๋ ์๋ฏธ.
  - git log โall : Show All branch.
  - git log โgraph: Show branch graph.
  - git log โoneline: ์ ๋ณด๋ฅผ ํ์ค๋ก ๊ฐ๋ตํ๊ฒ ํ๊ธฐ.

- ### ๐ git diff : show changes
  - add ํ๊ธฐ ์ ์ ์คํ. ๋ง์ง๋ง ๋ฒ์ ๊ณผ ํ์ฌ ๋ณ๊ฒฝ์ฌํญ์ ๋น๊ต.

- ### ๐ git checkout  {commit id}:go back to previous version
  - ์์์ ์ผ๋ก ํด๋น ๋ฒ์ ์ผ๋ก ์ฎ๊น. โ head๊ฐ ์ฎ๊ฒจ๊ฐ

- ### ๐ git reset : reset to that version(์ฌ๋งํ๋ฉด ์ฌ์ฉํ์ง ๋ง์๋ค.)
  - head์ master branch๋ชจ๋ ์ฎ๊ฒจ๊ฐ. ์ฎ๊ฒจ๊ฐ ์ดํ์ ๋ฒ์ ๋ค์ด ์ญ์ ๋จ. ๊ทธ๋ฌ๋ ๋ด๊ฐ ์ง์ฐ๋๋ผ๋ ํด๋น ํ์ผ์ด
    ๋ค๋ฅธ ์ฌ๋์๊ฒ ์๋ค๋ฉด ๊ณ์ ๋์์จ๋ค.
  - `git reset โ-hard {commit id}` : reset current changes
  - `git reset HEAD {fileName}` : unstaging(add ์ทจ์) ๊ทธ๋ฅ git reset, ๋ชจ๋  ํ์ผ์ ๋์์ผ๋ก.
  - `git reset HEAD^` : commit ์ทจ์
  - `git commit --amend`: commit message ๋ณ๊ฒฝ

- ### ๐ git revert {commit id}: ๋ฒ์ ์ ์ญ์ ํ์ง ์์ผ๋ฉด์ ์ด์  ๋ฒ์ ์ผ๋ก ๋๋๋ฆฌ๊ธฐ.
  - ` git revert --no-commit HEAD~n`
    - : ์ฌ๋ฌ๊ฐ์ ์ฌํญ์ ๋๋ฆด๋ ์ปค๋ฐ์ ํ๋ฒ๋ง ํ๊ธฐ ์ํด `--no-commit`์ ์ฌ์ฉํ๋ค.
  - version 1, version 2, version 3, version 4 ๊ฐ ์๊ณ , version 4๊ฐ ์ต์  ๋ฒ์ ์ผ ๋
  - version 3 ๋ก revert ํ๊ณ  ์ถ๋ค๋ฉด git `revert ${version 4 id}`. version 4๋ ์ญ์ ๋์ง ์๊ณ . version 3๋ก ๋๋์๊ฐ๊ฒ ๋๋ค.
  - version 1 ์ผ๋ก ๋๋์๊ฐ๊ณ  ์ถ๋ค๋ฉด version 4, version 3, version 2 ๋ฅผ ์์๋๋ก revertํ๋ฉด๋๋ค. revert๋ ํด๋น ๋ฒ์  ์ดํ์ ๋ชจ๋  ๋ฒ์ ์ ๋ณ๊ฒฝ์ฌํญ์ ๋๋๋ฆฌ๋ ๊ฒ์ด ์๋๋ผ ํด๋น๋ฒ์ ์ ๋ณ๊ฒฝ์ฌํญ๋ง์ ๋๋๋ฆฌ๊ธฐ ๋๋ฌธ.
  - ์ญ์์ ๋ฐ๋ฅด์ง ์์๊ฒฝ์ฐ ์ถฉ๋์ด ๋ฐ์ํ  ์ ์์.

๐ก ๋ฒ์ ๊ด๋ฆฌ ํ๊ณ ์ถ์ง ์์ ํ์ผ์ด ์๋ค๋ฉด .gitignore ์ ํ์ผ๋ช์ ์์ฑ.- git init {dir} : git์ผ๋ก ๊ด๋ฆฌํ  ๋๋ ํ ๋ฆฌ   

- ### ๐ rename
  - `git mv ${file} ${newFile}`
  - `git mv` ๊ฐ ์๋ `mv`๋ก ๋ณ๊ฒฝํ๊ฒ ๋๋ฉด ํ์ผ์ ์ ๊ฑฐํ ํ ์์ฑํ๋ ๊ฒ์ผ๋ก ์ถ์ ๋๋ค.
  
- ### ๐ Undoing
  ๋ณ๊ฒฝ์ฌํญ์ ๋ง์ง๋ง ์ปค๋ฐ ์์ ์ผ๋ก ์ฒ ํ.
  - `git checkout -- ${file}`
  - ์ต์  ๋ฒ์ ์์๋ `git restore`๋ก ๋ณ๊ฒฝ๋์๋ค.

# ๐ Backup
- ### ๐ git remote : ์๊ฒฉ ์ ์ฅ์ ๋ฆฌ์คํธ
  - `git remote -v`: ์๊ฒฉ ์ ์ฅ์ ๋ฆฌ์คํธ + ์ฃผ์
  - `git remote add ${name} ${remotePath}`: ์๊ฒฉ ์ ์ฅ์ ์ถ๊ฐ.(๊ด์ต์ ์ผ๋ก origin์ผ๋ก ์ด๋ฆ์ ์ ํ๋ค.)   
  - `git remote remove {name}`: ์๊ฒฉ ์ ์ฅ์ ์ ๊ฑฐ.

- ### ๐ git push {remote name} {branch}
  - `git push --set-upstream remote branch` : ๊ธฐ๋ณธ์ ์ธ push ์ ์ฅ์์ branch ์ค์ .    
    ์ด ๋ค์๋ถํฐ๋ git push ๋ง์ผ๋ก ์ค์ ๋ ์ ์ฅ์์ ๋ธ๋์น๋ก push ๊ฐ๋ฅ.

- ### ๐ git clone {address} : ๊ธฐ์กด์ ์ ์ฅ์ ๋ณต์ .
  - ๊ธฐ๋ณธ์ ์ผ๋ก repository์ ์ด๋ฆ์ผ๋ก directory๊ฐ ์์ฑ.
  - ๋ค๋ฅธ์ด๋ฆ์ผ๋ก ์ง์ ํ๊ณ  ์ถ๋ค๋ฉด `git clone ${address} ${dirName}`

- ### ๐ git pull {remote} {branch}

# ๐ Branch
๋ธ๋์น๋ฅผ ์์ฑํ  ๋์๋ ํด๋น ๋ธ๋์น์์ ์ด๋ค ์ผ์ ํ  ๊ฒ์ธ์ง๋ฅผ ์ด๋ฆ์ผ๋ก ๋ช์ํ๋ค.

- ### ๐ git branch {branchName}
  > - ์๋ก์ด ๋ธ๋์น๋ฅผ ์์ฑํ๋ค. 
  > - `git branch {name} -m`์์ฑ๊ณผ ๋์์ ๋ธ๋์น๋ก ์ด๋ 
  
- ### ๐ git checkout {branchName}
  > - ๋ธ๋์น๋ฅผ ์ด๋ํ๋ค
  > - ์ต์  ๋ฒ์ ๋ถํฐ๋ checkout์ด swtich์ restore๋ก ๋๋์ด ์ง๋ค.

- ### ๐ git merge {branchName}
  > - ๋์ ๋ธ๋์น์ ์์ ๋ด์ฉ์ ํ์ฌ ๋ธ๋์น๋ก ๋ณํฉ์ํจ๋ค.
  
- ### ๐ git branch -D {branchName}
  > - ๋์ ๋ธ๋ ์น๋ฅผ ์ ๊ฑฐํ๋ค.
  
- ### ๐ค Conflict
  > Merge ์คํ์ ๊ฐ๊ฐ์ ๋ธ๋์น์์ ๊ฐ์ ๋ผ์ธ์ ์์ ํ  ๋ ๋ฐ์ํ๋ค.
  > ์ฌ์ฉ์๊ฐ ์ง์  ์ด๋ค ์ฝ๋๋ฅผ ๋จ๊ธธ์ง ์ ํํ๊ณ , ํ์์๋ ๋ถ๋ถ์ ์ง์ด ๋ค add, commit ๋ค์ ์คํ.
  
## ๐ง Branching model
- ### ๐ git flow
  > https://danielkummer.github.io/git-flow-cheatsheet/index.ko_KR.html
  - ๊ฐ์ฅ ๋ง์ด ์ฌ์ฉ๋๋ ๋ชจ๋ธ, ๊ฐ ๋จ๊ณ๊ฐ ๋ชํํ๊ฒ ๊ตฌ๋ถ๋๋ค.
  - (hotfix) - `master` - (release) - `develop` - feature
    - develop: ๋ค์ ๊ฐ๋ฐ์ด ์งํ๋  branch
    - master: ์ฌ์ฉ์๊ฐ ๋ณด๊ฒ๋  ๋ฒ์ .
  - develop ๋ธ๋์น์์ ๋ feature ๋ธ๋์น๋ฅผ ์์ฑํ์ฌ ์์ ์ ์ผ๋ก ๋ณํฉ๋  ์ ์๋๋ก ์ค๊ผ.
  
  - #### git flow ์ธํ ๋ฐ ์ฌ์ฉ.
    - MacOs ์ค์น: `brew install git-flow-avh`
    - ์ด๊ธฐํ: git ์ ์ฅ์ ๋ด์์ `git flow init`
    - ์ ๊ธฐ๋ฅ ์์: `git flow feature start ${featureName}`
      > ๋ธ๋์น๋ฅผ ์์ฑํ๊ณ  ํด๋น ๋ธ๋์น๋ก ์ ํ.
    - ๊ธฐ๋ฅ ์๋ฃ: `fit flow feature finish ${featureName}`
      > feature ๋ธ๋์น๋ฅผ develop ์ ๋ณํฉ, feature ๋ธ๋์น๋ฅผ ์ญ์ ํ๊ณ  develop ๋ธ๋์น๋ก ์ ํ.
    - ๋ฆด๋ฆฌ์ฆ ์์: `git flow release start ${version}`
      > version ex) v1.0.00220113001 ( ๋,์,์ผ ๋ช ๋ฒ์งธ) 
    - ๋ฆด๋ฆฌ์ฆ ์ข๋ฃ: `git flow release finish ${version}`
      - ์ข๋ฃ์ ์๋ํฐ๊ฐ 3๋ฒ ๋์จ๋ค.
        > - ์ฒซ ๋ฒ์จฐ: main(master)์ผ๋ก ๋ค์ด๊ฐ๋ merge commit message.
        > - ๋ ๋ฒ์จฐ: ๋ฆด๋ฆฌ์ฆ ๋ธํธ. ๋ฌด์์ ํ๋์ง.(์ค์).
        > - ์ธ ๋ฒ์งธ: develop์ผ๋ก ๋ค์ด๊ฐ๋ merge commit message.
  
    - ๋ฆด๋ฆฌ์ฆ๊ฐ ์ข๋ฃ ๋๋ฉด
      - develop ๋ธ๋์น์์ remote develop ๋ธ๋์น๋ก push.
      - main ๋ธ๋์น์์ remote main ๋ธ๋์น๋ก push.
      - `git push --tags`๋ก ํ๊ทธ๋ push
      - `git tag` ๋ก ๋ฒ์  ํ๊ทธ ํ์ธ ๊ฐ๋ฅ.
- ### ๐ github flow
  - master - feature
  - ๋ธ๋์น ๋ชจ๋ธ์ ๋จ์ํ CI ์์กด์ฑ์ด ๋์, pull request๋ก ์๋ชป๋ push๋ฅผ ๋ฐฉ์ง.
  
- ### ๐ gitlab flow
  - production - pre-production - master - feature
  - deploy, issue์ ๋ํ ๋์์ด ๊ฐ๋ฅํ๋๋ก ๋ณด์.
  
# ๐ Forking workflow 
1. ๊ด๋ฆฌ์๊ฐ Repository๋ฅผ ์์ฑ.
2. ๊ด๋ฆฌ์๊ฐ develop ๋ธ๋์น ์์ฑ.
3. ํ์์ด ํด๋น Repository๋ฅผ fork.
4. ํ์์ด ํด๋น Repository๋ฅผ clone ํ๊ณ  git flow init ํ๋ฉด develop์ ๊ด๋ฆฌ์๊ฐ ์ด๊ธฐํํ ํ์ผ์ด ๋ค์ด์ด.
5. ํ์์ด ์์ ํ ์์ ์ remote์ push
6. pull requests ์์ฑ.
7. ํ์์ด ์ถ๊ฐ ์์ ํ ์์ ์ remote์ push
8. ํ์ฅ์ด pull request ์๋ฝ.
9. ํ์ฅ์ ์์ ์ local์ `git pull` or `git fetch origin develop` + `git merge FETCH_HEAD`
10. ํ์์ ์์ ์ local์ `git remote add upstream ${url}`, `git fetch upstream develop` + `git merge FETCH_HEAD`


# ๐ Bitbucket - Jira ์ฐ๊ฒฐ
> - Jira: Issue Tracking Service
> - Issue ๋ฅผ ์์ฑํ๊ณ , Commit ๋ฉ์์ง์ jira ํฐ์ผ์ ๋งจ ์์ ๋ช์ํ๋ฉด ์๋์ผ๋ก ํด๋น ์ด์์ ์ปค๋ฐ.

# ๐ Issue
### ๐ค .gitignore๊ฐ ์ ์ฉ๋์ง ์์ ๋.
    git rm -r --cached .

### ๐ค ERROR : refusing to merge unrelated histories
- ์ ์ฅ์๋ฅผ ์์ฑํ ๋ README ํ์ผ์ด๋, .gitignoreํ์ผ์ ์์ฑํด์ ๋ฐ์ํ๋ ๋ฌธ์  ๋จผ์  ํ์ผ์ ๊ฐ์ ธ์์ผ ํ๋ค.
- git pull origin master --allow-unrelated-histories

